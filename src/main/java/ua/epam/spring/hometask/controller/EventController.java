package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping("/event/add")
    public ModelAndView addEvent() {

        ModelAndView mav = new ModelAndView("event");

        mav.addObject("event", new Event());

        return mav;
    }

    @RequestMapping(value = "/event/addEvent", method = RequestMethod.POST)
    public String submit(@ModelAttribute("event") Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }

        eventService.save(event);

        return "redirect:/events";
    }


    @RequestMapping("/events")
    public ModelAndView getAll() {

        ModelAndView mav = new ModelAndView("events");
        List<Event> events = new ArrayList<>(eventService.getAll());
        mav.addObject("events", events);

        return mav;
    }

    @RequestMapping(value = "/event/removeEvent")
    public String remove(@RequestParam("id") long eventId) {

        Event e = new Event();
        e.setId(eventId);
        eventService.remove(e);

        return "redirect:/events";
    }

}
