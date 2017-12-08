package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.to.BookingTicketDTO;
import ua.epam.spring.hometask.domain.to.BookingTicketsForm;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Vladimir_Vysokomorny on 05-Dec-17.
 */
@Controller
public class BookingController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

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


    @RequestMapping("/tickets")
    public ModelAndView getBookedTickets(@RequestParam("eventId") long eventId,
                                         @RequestParam("dateTime") LocalDateTime dateTime) {

        Event event = eventService.getById(eventId);
        Set<Ticket> ticketSet = bookingService.getPurchasedTicketsForEvent(event, dateTime);
        List<BookingTicketDTO> tickets = ticketSet.stream()
                .map(t -> new BookingTicketDTO(
                        t.getId(),
                        t.getUser().getId(),
                        t.getEvent().getId(),
                        t.getDateTime(),
                        t.getSeat()))
                .collect(Collectors.toList());
        ModelAndView mav = new ModelAndView("tickets");
        mav.addObject("ticketsToShow", tickets);
        mav.addObject("eventId", eventId);
        mav.addObject("airDate", dateTime);

        return mav;
    }

    @RequestMapping(value = "/tickets/book", method = RequestMethod.POST)
    public String bookTickets(@ModelAttribute("ticketsForm") BookingTicketsForm ticketsForm,
                              @RequestParam long eventId,
                              @RequestParam LocalDateTime airDate,
                              BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.toString());
            //return "error";
        }

        Event event = eventService.getById(eventId);
        Set<Ticket> tickets = ticketsForm.getTickets()
                .stream()
                .map(t -> {
                    User user = userService.getById(t.getUserId());
                    //Event event = eventService.getById(t.getEventId());
                    return new Ticket(user, event, t.getDateTime(), t.getSeat());
                }).collect(Collectors.toSet());
        bookingService.bookTickets(tickets);
        return "redirect:/tickets?eventId="+eventId+"&dateTime="+airDate;

    }
}
