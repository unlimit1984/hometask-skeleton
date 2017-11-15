package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping("/auditoriums")
    public ModelAndView getAll() {

        ModelAndView mav = new ModelAndView("auditoriums");
        List<Auditorium> auditoriums = new ArrayList<>(auditoriumService.getAll());
        mav.addObject("auditoriums", auditoriums);

        return mav;
    }

}
