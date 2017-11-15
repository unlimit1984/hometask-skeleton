package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping("/events")
    public ModelAndView getAll() {

        ModelAndView mav = new ModelAndView("events");
        List<Event> users = new ArrayList<>(eventService.getAll());
        mav.addObject("events", users);

        return mav;
    }
}
