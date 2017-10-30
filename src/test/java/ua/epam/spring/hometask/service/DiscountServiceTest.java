package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

//@ContextConfiguration({"classpath:spring-test.xml"})
@ContextConfiguration(
        classes = AppConfig.class,
        loader = AnnotationConfigContextLoader.class
)
@RunWith(SpringJUnit4ClassRunner.class)
public class DiscountServiceTest {

    @Autowired
    DiscountService service;


    @Test
    public void getDiscount() throws Exception {

        User user = new User();
        Event event = new Event();
        event.setBasePrice(10);
        LocalDateTime ldt = LocalDateTime.now();

        Assert.assertEquals(0, service.getDiscount(user,event,ldt,9));
        Assert.assertEquals(10, service.getDiscount(user,event,ldt,15));
        Assert.assertEquals(20, service.getDiscount(user,event,ldt,21));
        Assert.assertEquals(30, service.getDiscount(user,event,ldt,51));
    }

}