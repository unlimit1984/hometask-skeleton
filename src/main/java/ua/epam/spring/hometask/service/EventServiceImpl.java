package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.repository.EventRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Vladimir on 10.10.2017.
 */
@Service
public class EventServiceImpl implements EventService {

    public EventServiceImpl() {
        this(Clock.systemDefaultZone());
    }
    public EventServiceImpl(Clock clock) {
        this.clock = clock;
    }

    private Clock clock;

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    private EventRepository eventRepository;

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventRepository.getAll()
                .stream()
                .filter(event -> name.equals(event.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Event save(@Nonnull Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventRepository.delete(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventRepository.get(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventRepository.getAll();
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {

        return eventRepository.getAll()
                .stream()
                .filter(event -> event.airsOnDates(from, to))
                //.filter(event -> event.getAirDates().stream().anyMatch(ldt -> ldt.toLocalDate().compareTo(from)>=0 && ldt.toLocalDate().compareTo(to)<=0))
                .collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        LocalDateTime now  = LocalDateTime.now(clock);

        return eventRepository.getAll()
                .stream()
                .filter(event -> event.getAirDates().stream().anyMatch(ldt -> ldt.compareTo(now)>=0 && ldt.compareTo(to) <= 0))
                .collect(Collectors.toSet());
    }
}
