package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/user/id")
    public ModelAndView getById(@RequestParam long id) {

        ModelAndView mav = new ModelAndView();

        User user = userService.getById(id);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/user/email")
    public ModelAndView getByEmail(@RequestParam String email) {

        ModelAndView mav = new ModelAndView();

        User user = userService.getUserByEmail(email);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/user/add")
    public ModelAndView addUser() {

        ModelAndView mav = new ModelAndView("user");

        mav.addObject("user", new User());

        return mav;
    }


    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("error");
        }

        userService.save(user);

        return getAll();
    }
}
