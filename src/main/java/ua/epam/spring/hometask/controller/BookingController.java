package ua.epam.spring.hometask.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.form.BookingTicketsForm;
import ua.epam.spring.hometask.domain.to.BookingTicketDTO;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @RequestMapping("/tickets/pdf")
    public void getBookedTicketsInPdf(@RequestParam("eventId") long eventId,
                                      @RequestParam("dateTime") LocalDateTime dateTime,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        Event event = eventService.getById(eventId);
        Set<Ticket> tickets = bookingService.getPurchasedTicketsForEvent(event, dateTime);

        String fileName = "tickets.pdf";
        final ServletContext servletContext = request.getSession().getServletContext();
        final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        final String temperotyFilePath = tempDirectory.getAbsolutePath();
        final String fullPath = temperotyFilePath+"\\"+fileName;


        //AB: added - it's not critical, but would be better to have separate class with the logic below
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fullPath));
        document.open();
        Paragraph paragraph = new Paragraph("Tickets");
        document.add(paragraph);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        Stream.of("Ticket Id", "User Id", "Event Id", "Air Date", "Seat")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        tickets.forEach(t -> {
            table.addCell(String.valueOf(t.getId()));
            table.addCell(String.valueOf(t.getUser().getId()));
            table.addCell(String.valueOf(t.getEvent().getId()));
            table.addCell(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(t.getDateTime()));
            table.addCell(String.valueOf(t.getSeat()));
        });
        document.add(table);
        document.close();

        File file = new File(fullPath);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + fullPath + " was not found.");
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
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
        return "redirect:/tickets?eventId=" + eventId + "&dateTime=" + airDate;

    }
}
