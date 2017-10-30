package ua.epam.spring.hometask.repository;

import ua.epam.spring.hometask.domain.Ticket;

import java.util.Collection;
import java.util.Set;

public interface TicketRepository {
    void bookTickets(Set<Ticket> tickets);
    Collection<Ticket> getAll();
}
