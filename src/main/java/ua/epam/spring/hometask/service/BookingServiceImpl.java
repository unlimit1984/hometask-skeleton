package ua.epam.spring.hometask.service;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.TicketRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private TicketRepository ticketRepository;
    private DiscountService discountService;

    public BookingServiceImpl(TicketRepository ticketRepository, DiscountService discountService) {
        this.ticketRepository = ticketRepository;
        this.discountService = discountService;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        long numberOfSeats = seats.size();
        return numberOfSeats * event.getBasePrice() * (100 - discountService.getDiscount(user, event, dateTime, numberOfSeats)) / 100;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        ticketRepository.bookTickets(tickets);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketRepository.getAll()
                .stream()
                .filter(ticket -> ticket.getEvent().getName().equals(event.getName()) &&
                        ticket.getDateTime().equals(dateTime))
                .collect(Collectors.toSet());
    }
}
