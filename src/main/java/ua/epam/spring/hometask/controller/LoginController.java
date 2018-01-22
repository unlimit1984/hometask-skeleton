package ua.epam.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vladimir_Vysokomorny on 20-Dec-17.
 */
@Controller
public class LoginController {

    //@RequestMapping(value = "/login", method = RequestMethod.GET)
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mav = new ModelAndView("login");
        if(error!=null){
            mav.addObject("error", "error");
        }
        if(logout!=null){
            mav.addObject("logout", "logout");
        }

        return mav;
    }
    @RequestMapping(value = "/logoutPage", method = RequestMethod.GET)
    public String logoutPage() {

        return "logoutPage";
    }


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public void submit(){
//
//    }


}
