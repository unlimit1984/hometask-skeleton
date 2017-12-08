package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.form.EventForm;
import ua.epam.spring.hometask.service.EventService;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDateTime) getValue());
            }
        });
    }

    @RequestMapping("/event/id")
    public ModelAndView getById(@RequestParam long id) {

        ModelAndView mav = new ModelAndView("event");

        Event event = eventService.getById(id);
        mav.addObject("event", event);

        return mav;
    }

    @RequestMapping("/event/add")
    public ModelAndView addEvent() {

        ModelAndView mav = new ModelAndView("event");

        mav.addObject("event", new Event());

        return mav;
    }

    @RequestMapping(value = "/event/addEvent", method = RequestMethod.POST)
    public String submit(@ModelAttribute("event") EventForm event, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        Event createdEvent = new Event();
        createdEvent.setId(event.getId());
        createdEvent.setName(event.getName());
        createdEvent.setBasePrice(event.getBasePrice());
        createdEvent.setRating(event.getRating());
        createdEvent.setAirDates(new TreeSet<>(event.getAirDates()));
        eventService.save(createdEvent);

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
