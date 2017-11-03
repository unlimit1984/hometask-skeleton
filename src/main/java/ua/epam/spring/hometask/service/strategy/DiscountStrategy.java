package ua.epam.spring.hometask.service.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Vladimir on 01.11.2017.
 */
public interface DiscountStrategy {
    byte getPercentDiscount(User user, Event event, LocalDateTime dateTime, Set<Long> seats);
}
