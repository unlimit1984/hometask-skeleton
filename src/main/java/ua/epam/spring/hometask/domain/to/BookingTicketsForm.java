package ua.epam.spring.hometask.domain.to;

import java.util.List;

/**
 * Created by Vladimir_Vysokomorny on 06-Dec-17.
 */
public class BookingTicketsForm {
    private List<BookingTicketDTO> tickets;

    public List<BookingTicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<BookingTicketDTO> tickets) {
        this.tickets = tickets;
    }
}
