package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Vladimir on 22.10.2017.
 */
@Aspect
public class CounterAspect {

    private Map<Event, Map<EventInfoType, Integer>> counter = new HashMap<>();

    @Pointcut("execution(public ua.epam.spring.hometask.domain.Event getByName(..))")
    private void getByName() {
    }

    @Pointcut("execution(public double getTicketsPrice(..))")
    private void getTicketsPrice() {
    }


    @Pointcut("execution(public void bookTickets(..)) && within(ua.epam.spring.hometask.service.*)")
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

            if (!counter.containsKey(event)) {
                Map<EventInfoType, Integer> eventInfo = new HashMap<>();
                eventInfo.put(EventInfoType.GET_BY_NAME, 0);
                eventInfo.put(EventInfoType.GET_TICKET_PRICE, 0);
                eventInfo.put(EventInfoType.BOOK_TICKET, 0);
                counter.put(event, eventInfo);
            }
            Map<EventInfoType, Integer> eventInfo = counter.get(event);
            int newValue = eventInfo.get(category) + 1;
            eventInfo.put(category, newValue);
        }
    }

    public void printStatistics() {
        counter.entrySet().forEach(entry -> {
            System.out.println("Event: " + entry.getKey());
            Map<EventInfoType, Integer> eventInfo = entry.getValue();
            System.out.println(eventInfo.get(EventInfoType.GET_BY_NAME) + " - get by name");
            System.out.println(eventInfo.get(EventInfoType.GET_TICKET_PRICE) + " - get ticket price");
            System.out.println(eventInfo.get(EventInfoType.BOOK_TICKET) + " - booked");
        });
    }

}
