package ua.epam.spring.hometask.service.strategy;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Vladimir on 02.11.2017.
 */
@Component
public class TicketCountStrategy implements DiscountStrategy {

    @Override
    public byte getPercentDiscount(User user, Event event, LocalDateTime dateTime, Set<Long> seats) {
        int count = seats.size();
        if (user.getId() != null) {
            count += user.getTickets().size() % 10;
        }
        if((count / 10) > 0){
            return 5;
        }
        else{
            return 0;
        }
    }
}
