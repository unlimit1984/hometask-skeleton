package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/users")
    public ModelAndView getAll() {

        ModelAndView mav = new ModelAndView("users");
        List<User> users = new ArrayList<>(userService.getAll());
        mav.addObject("users", users);

        return mav;
    }

//    @RequestMapping()
//    public ModelAndView getById(@RequestParam long id) {
//
//        ModelAndView mav = new ModelAndView();
//
//        User user = userService.getById(id);
//        mav.addObject("user", user);
//
//        return mav;
//    }

}
