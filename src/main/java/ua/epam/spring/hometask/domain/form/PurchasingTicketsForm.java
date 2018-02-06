package ua.epam.spring.hometask.domain.form;

import java.util.Set;

public class PurchasingTicketsForm {
    private Set<Long> seats;

    public Set<Long> getSeats() {
        return seats;
    }

    public void setSeats(Set<Long> seats) {
        this.seats = seats;
    }
//    private List<PurchasingTicketDTO> purchasingTickets;
//
//    public List<PurchasingTicketDTO> getPurchasingTickets() {
//        return purchasingTickets;
//    }
//
//    public void setPurchasingTickets(List<PurchasingTicketDTO> purchasingTickets) {
//        this.purchasingTickets = purchasingTickets;
//    }
}
