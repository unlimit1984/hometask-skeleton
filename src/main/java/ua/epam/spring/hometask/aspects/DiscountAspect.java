package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir on 22.10.2017.
 */
@Aspect
public class DiscountAspect {

    private Map<User, Integer> counter = new HashMap<>();

    @Pointcut("execution(public byte getDiscount(..))")
    private void getDiscount() {
    }

    @AfterReturning(pointcut = "getDiscount() && args(user,..)", returning = "discount")
    public void collectStatistics(User user, byte discount) {
        if (discount > 0) {
            if (!counter.containsKey(user)) {
                counter.put(user, 0);
            }
            counter.put(user, counter.get(user) + 1);
        }
    }

    public void printStatistics() {
        int count = counter.values().stream().mapToInt(Integer::byteValue).sum();
        System.out.println(count + " - total amount of discounts");
        counter.forEach((user, integer) -> System.out.println(integer+" - "+user));
    }

}
