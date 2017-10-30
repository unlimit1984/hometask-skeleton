package ua.epam.spring.hometask.repository;

import ua.epam.spring.hometask.domain.Event;

import java.util.Collection;

/**
 * Created by Vladimir on 10.10.2017.
 */
public interface EventRepository {
    Event save(Event event);
    void delete(Event event);
    Event get(long id);
    Collection<Event> getAll();

}
