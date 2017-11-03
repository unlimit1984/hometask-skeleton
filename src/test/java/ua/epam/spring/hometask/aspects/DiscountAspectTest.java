package ua.epam.spring.hometask.aspects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.service.DiscountService;

import static ua.epam.spring.hometask.EventTestData.EVENT1;
import static ua.epam.spring.hometask.EventTestData.NOW;
import static ua.epam.spring.hometask.UserTestData.USER1;
import static ua.epam.spring.hometask.UserTestData.USER2;

/**
 * Created by Vladimir on 22.10.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = AppConfig.class,
        loader = AnnotationConfigContextLoader.class
)
public class DiscountAspectTest {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private DiscountAspect counterAspect;

    @Test
    public void collectStatistics() throws Exception {
//        discountService.getDiscount(USER1, EVENT1, NOW, 5);
//        discountService.getDiscount(USER1, EVENT1, NOW, 15);
//        discountService.getDiscount(USER1, EVENT1, NOW, 25);
//
//        discountService.getDiscount(USER2, EVENT1, NOW, 1);
//        discountService.getDiscount(USER2, EVENT1, NOW, 5);
//        discountService.getDiscount(USER2, EVENT1, NOW, 10);
//        discountService.getDiscount(USER2, EVENT1, NOW, 30);
//        discountService.getDiscount(USER2, EVENT1, NOW, 51);
//
//        counterAspect.printStatistics();
    }

}