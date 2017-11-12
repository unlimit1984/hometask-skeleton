package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/all")
    public ModelAndView getAll(HttpServletRequest request){

        ModelAndView mav = new ModelAndView();


        return mav;
    }

    @RequestMapping()
    public ModelAndView getById(@RequestParam long id){

        ModelAndView mav = new ModelAndView();

        User user = userService.getById(id);
        mav.addObject("user", user);

        return mav;
    }

}
