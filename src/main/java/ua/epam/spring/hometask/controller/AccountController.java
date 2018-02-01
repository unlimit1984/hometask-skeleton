package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.UserAccount;
import ua.epam.spring.hometask.service.UserAccountService;
import ua.epam.spring.hometask.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {


    @Autowired
    private UserAccountService accountService;

    @Autowired
    private UserService userService;

    @RequestMapping("/accounts")
    public ModelAndView getAll() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        long userId = userService.getUserByEmail(email).getId();


        ModelAndView mav = new ModelAndView("accounts");
        List<UserAccount> accounts = new ArrayList<>(accountService.getAll(userId));
        mav.addObject("accounts", accounts);

        return mav;
    }

}
