package ua.epam.spring.hometask.service;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Service
public class DiscountServiceImpl implements DiscountService {


    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte discount = 0;
        if (numberOfTickets >= 50) {
            discount = 30;
        } else if (numberOfTickets >= 20) {
            discount = 20;
        } else if (numberOfTickets >= 10) {
            discount = 10;
        }
        return discount;
    }
}
