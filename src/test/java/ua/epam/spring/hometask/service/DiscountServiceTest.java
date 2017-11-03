package ua.epam.spring.hometask.service;

import org.junit.Assert;
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
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.TreeMap;

import static ua.epam.spring.hometask.EventTestData.*;
import static ua.epam.spring.hometask.UserTestData.*;

//@ContextConfiguration({"classpath:spring-test.xml"})
@ContextConfiguration(
        classes = AppConfig.class,
        loader = AnnotationConfigContextLoader.class
)
@RunWith(SpringJUnit4ClassRunner.class)
public class DiscountServiceTest {

    @Autowired
    DiscountService discountService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;


    private NavigableMap<LocalDateTime, Auditorium> auditoriumMap = new TreeMap<>();


    @Before
    public void setUp() throws Exception {
        userService.save(UserTestData.createNew(EMAIL1, USER_NAME1, LAST_NAME1, USER_BIRTHDAY1));//good
        userService.save(UserTestData.createNew(EMAIL2, USER_NAME2, LAST_NAME2, USER_BIRTHDAY2));//bad

        Auditorium auditorium = auditoriumService.getByName("gama");

        for (LocalDateTime airDate : EVENT_AIR_DATES1) {
            auditoriumMap.put(airDate, auditorium);
        }

        eventService.save(EventTestData.createNew(EVENT_NAME1, EVENT_PRICE1, EVENT_RATING1, EVENT_AIR_DATES1, auditoriumMap));
    }

    @Test
    public void getDiscount() throws Exception {

        User luckyUser = userService.getUserByEmail(EMAIL1);
        User simpleUser = userService.getUserByEmail(EMAIL2);

        Event event = eventService.getByName(EVENT_NAME1);

        //nothing special
        Assert.assertEquals(0, discountService.getDiscount(simpleUser, event, AIR_DATE1, new HashSet<>(Arrays.asList(2L, 3L, 4L))));
        //Birthday is close
        Assert.assertEquals(10, discountService.getDiscount(luckyUser, event, AIR_DATE1, new HashSet<>(Arrays.asList(2L, 3L, 4L))));
        //10 tickets
        Assert.assertEquals(5, discountService.getDiscount(simpleUser, event, AIR_DATE1, new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L))));
        //Birthday is close and 10 tickets
        Assert.assertEquals(10, discountService.getDiscount(luckyUser, event, AIR_DATE1, new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L))));


//        Assert.assertEquals(0, discountService.getDiscount(user, event, AIR_DATE1, new HashSet<>(Arrays.asList(2L, 3L, 4L))));
//        Assert.assertEquals(20, discountService.getDiscount(user,event,ldt,21));
//        Assert.assertEquals(30, discountService.getDiscount(user,event,ldt,51));
    }

}