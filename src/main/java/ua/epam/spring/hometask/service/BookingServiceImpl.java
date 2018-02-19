package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.spring.hometask.domain.*;
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
    private AuditoriumService auditoriumService;

    @Autowired
    private UserAccountService accountService;

    public BookingServiceImpl(TicketRepository ticketRepository, DiscountService discountService, AuditoriumService auditoriumService) {
        this.ticketRepository = ticketRepository;
        this.discountService = discountService;
        this.auditoriumService = auditoriumService;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        double movieRateIndex = 1;
        EventRating rating = event.getRating();
        if (rating == EventRating.LOW) {
            movieRateIndex = 0.8;
        } else if (rating == EventRating.HIGH) {
            movieRateIndex = 1.2;
        }

        double price = 0;
        Auditorium auditorium = auditoriumService.getByName(event.getAuditoriums().get(dateTime).getName());
        for (long seat : seats) {
            double ticket_price = event.getBasePrice();
            //if (event.getAuditoriums().get(dateTime).getVipSeats().contains(seat)) {
            if (auditorium.getVipSeats().contains(seat)) {
                ticket_price *= 2;
            }
            price += ticket_price;
        }
        return movieRateIndex * price * (100 - discountService.getDiscount(user, event, dateTime, seats.size())) / 100;

    }

    @Transactional
    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets, long userId, UserAccount account, double price) {


        UserAccount acc = accountService.getById(account.getId(), userId);
        //check that account has enough money
        if (!account.canBuy(price)) {
            throw new RuntimeException("Account " + account + " doesn't have enough money to buy tickets");
        }

        //try to book tickets
        if (!ticketRepository.bookTickets(tickets)) {
            throw new RuntimeException("Can't book tickets for userId: " + userId + " via account: " + account + ". Price: " + price);
        }

        //withdraw money
        account.buy(price);

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        accountService.save(account, userId);
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
