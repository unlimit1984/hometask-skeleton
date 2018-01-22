package ua.epam.spring.hometask.security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Vladimir_Vysokomorny on 22-Jan-18.
 */
public class MovieSessionListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(20);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        //session destroyed
    }
}
