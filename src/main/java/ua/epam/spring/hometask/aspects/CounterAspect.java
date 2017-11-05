package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Vladimir on 22.10.2017.
 */
@Aspect
public class CounterAspect {
    /*InMemory impl*/
//    private Map<Event, Map<EventInfoType, Integer>> counter = new HashMap<>();

    private JdbcTemplate jdbcTemplate;

    public CounterAspect(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.EventService.getByName(..))")
    private void getByName() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    private void getTicketsPrice() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.BookingService.bookTickets(..))")
    private void bookTickets() {
    }

    @AfterReturning(pointcut = "getByName()", returning = "event")
    public void gettingEventByNameStatistics(Event event) {
        putEventToCounter(event, EventInfoType.GET_BY_NAME);
    }

    @AfterReturning("getTicketsPrice() && args(event,..)")
    public void gettingEventPriceStatistics(Event event) {
        putEventToCounter(event, EventInfoType.GET_TICKET_PRICE);

    }

    @AfterReturning("bookTickets() && args(tickets)")
    public void bookTicketsStatistics(Set<Ticket> tickets) {
        Set<Event> events = tickets.stream().map(Ticket::getEvent).collect(Collectors.toSet());
        events.forEach(event -> putEventToCounter(event, EventInfoType.BOOK_TICKET));
    }

    private void putEventToCounter(Event event, EventInfoType category) {
        if (Objects.nonNull(event) && Objects.nonNull(category)) {

            List<String> events = jdbcTemplate.query(
                    "SELECT event_name FROM event_counter_audit WHERE event_name=?",
                    new Object[]{event.getName()},
                    (rs, rowNum) -> {
                        String event_name = rs.getString("event_name");
                        return event_name;
                    });

            if (events.size() == 0) {

                List<Object[]> batchArgs = Arrays.asList(
                        new Object[]{event.getName(), EventInfoType.GET_BY_NAME.name()},
                        new Object[]{event.getName(), EventInfoType.GET_TICKET_PRICE.name()},
                        new Object[]{event.getName(), EventInfoType.BOOK_TICKET.name()}
                );
                jdbcTemplate.batchUpdate("INSERT INTO event_counter_audit(event_name, name, count) VALUES(?,?,0)", batchArgs);
            }


            jdbcTemplate.update(
                    "UPDATE event_counter_audit SET count = count+1 WHERE event_name=? AND name=?",
                    event.getName(),
                    category.name());

            /*InMemory impl*/

//            if (!counter.containsKey(event)) {
//                Map<EventInfoType, Integer> eventInfo = new HashMap<>();
//                eventInfo.put(EventInfoType.GET_BY_NAME, 0);
//                eventInfo.put(EventInfoType.GET_TICKET_PRICE, 0);
//                eventInfo.put(EventInfoType.BOOK_TICKET, 0);
//                counter.put(event, eventInfo);
//            }
//            Map<EventInfoType, Integer> eventInfo = counter.get(event);
//            int newValue = eventInfo.get(category) + 1;
//            eventInfo.put(category, newValue);
        }

    }

    public void printStatistics() {

        Map<String, HashMap<EventInfoType, Integer>> events = new TreeMap<>();
        jdbcTemplate.query("SELECT * FROM event_counter_audit", (rs, rowNum) -> {

            String event_name = rs.getString("event_name");
            EventInfoType eventType = EventInfoType.valueOf(rs.getString("name"));
            Integer count = rs.getInt("count");

            if (!events.containsKey(event_name)) {
                events.put(event_name, new HashMap<>());
            }
            Map<EventInfoType, Integer> eventInfo = events.get(event_name);
            eventInfo.put(eventType, count);

            return null;

        });


        events.forEach((event_name, eventInfo) -> {
            System.out.println("Event: " + event_name);
            System.out.println(eventInfo.get(EventInfoType.GET_BY_NAME) + " - get by name");
            System.out.println(eventInfo.get(EventInfoType.GET_TICKET_PRICE) + " - get ticket price");
            System.out.println(eventInfo.get(EventInfoType.BOOK_TICKET) + " - booked");
        });


                    /*InMemory impl*/

//        counter.entrySet().forEach(entry -> {
//            System.out.println("Event: " + entry.getKey());
//            Map<EventInfoType, Integer> eventInfo = entry.getValue();
//            System.out.println(eventInfo.get(EventInfoType.GET_BY_NAME) + " - get by name");
//            System.out.println(eventInfo.get(EventInfoType.GET_TICKET_PRICE) + " - get ticket price");
//            System.out.println(eventInfo.get(EventInfoType.BOOK_TICKET) + " - booked");
//        });
    }

}
