package ua.epam.spring.hometask.aspects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.epam.spring.hometask.EventTestData;
import ua.epam.spring.hometask.UserTestData;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

import static ua.epam.spring.hometask.EventTestData.*;
import static ua.epam.spring.hometask.UserTestData.*;

/**
 * Created by Vladimir on 22.10.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = AppConfig.class,
        loader = AnnotationConfigContextLoader.class
)
public class CounterAspectTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CounterAspect counterAspect;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditoriumService auditoriumService;

    private Auditorium auditorium;
    private NavigableMap<LocalDateTime, Auditorium> auditoriumMap1;
    private NavigableMap<LocalDateTime, Auditorium> auditoriumMap2;
    private NavigableMap<LocalDateTime, Auditorium> auditoriumMap3;


    @Before
    public void setUp() {
        auditorium = auditoriumService.getByName("alpha");

        auditoriumMap1 = new TreeMap<>();
        auditoriumMap2 = new TreeMap<>();
        auditoriumMap3 = new TreeMap<>();
        for (LocalDateTime airDate : EVENT_AIR_DATES1) {
            auditoriumMap1.put(airDate, auditorium);
        }
        for (LocalDateTime airDate : EVENT_AIR_DATES2) {
            auditoriumMap2.put(airDate, auditorium);
        }
        for (LocalDateTime airDate : EVENT_AIR_DATES3) {
            auditoriumMap3.put(airDate, auditorium);
        }


        eventService.save(EventTestData.createNew(EVENT_NAME1, EVENT_PRICE1, EVENT_RATING1, EVENT_AIR_DATES1, auditoriumMap1));
        eventService.save(EventTestData.createNew(EVENT_NAME2, EVENT_PRICE2, EVENT_RATING2, EVENT_AIR_DATES2, auditoriumMap2));
        eventService.save(EventTestData.createNew(EVENT_NAME3, EVENT_PRICE3, EVENT_RATING3, EVENT_AIR_DATES3, auditoriumMap3));

        userService.save(UserTestData.createNew(EMAIL1, USER_NAME1, LAST_NAME1, USER_BIRTHDAY1));
        //userService.save(UserTestData.createNew(EMAIL2, USER_NAME2,LAST_NAME2));

    }

    @Test
    public void printStatistics() throws Exception {

        Event event1 = eventService.getByName(EVENT_NAME1);
        Event event2 = eventService.getByName(EVENT_NAME2);

        Set<Ticket> tickets = new TreeSet<>(Arrays.asList(
                new Ticket(userService.getUserByEmail(EMAIL1), event1, NOW, 1L),
                new Ticket(userService.getUserByEmail(EMAIL1), event2, NOW, 2L)
        ));
        Set<Ticket> tickets2 = new TreeSet<>(Arrays.asList(
                new Ticket(userService.getUserByEmail(EMAIL1), event2, NOW, 1L),
                new Ticket(userService.getUserByEmail(EMAIL1), event2, NOW, 2L),
                new Ticket(userService.getUserByEmail(EMAIL1), event2, NOW, 2L)
        ));

        eventService.getByName("event0");
        eventService.getByName(EVENT_NAME1);
        eventService.getByName(EVENT_NAME1);
        eventService.getByName(EVENT_NAME2);
        eventService.getByName(EVENT_NAME2);
        eventService.getByName(EVENT_NAME2);
        eventService.getByName("event3");

        bookingService.getTicketsPrice(event1, AIR_DATE1, USER1, new HashSet<>(Arrays.asList(1L, 2L, 3L)));
        bookingService.getTicketsPrice(event2, AIR_DATE2, USER1, new HashSet<>(Arrays.asList(1L, 2L, 3L)));
        bookingService.getTicketsPrice(event2, AIR_DATE2, USER1, new HashSet<>(Arrays.asList(1L, 2L, 3L)));

        bookingService.bookTickets(tickets);
        bookingService.bookTickets(tickets);

        bookingService.bookTickets(tickets2);
        bookingService.bookTickets(tickets2);

        counterAspect.printStatistics();

    }
}