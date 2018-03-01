package com.prem.studyproject.controllers;


import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.services.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@Slf4j
public class MainController {

    private RegistrationService registrationService;

    @Autowired
    public MainController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping("/")
    public String getMain(Map<String, Object> model) {
        return "main";
    }

    @RequestMapping("/login")
    public String getLogin(@RequestParam(required = false) String error,
                           @RequestParam(required = false) String logout,
                           @RequestParam(required = false) String msg,
                           Model model,
                           Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("user", authentication.getPrincipal());
        }
        if (msg != null) {
            model.addAttribute("msg", msg);
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        if (logout != null) {
            model.addAttribute("logout", logout);
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Map<String, Object> model) {
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registrationPost(ModelAndView model, User user) {
        User registeredUser = registrationService.registerNewUser(user);
        model.addObject("user", registeredUser);
        model.addObject("message", "Check your email for confirmation of registration!" + " <br> " + "Email: " + user.getEmail());
        model.setViewName("message");
        return model;
    }


    @RequestMapping(value = "/registration/{token}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView confirmEmail(ModelAndView model, Authentication authentication,
                                     @PathVariable(required = true) String token) {
        User user = registrationService.confirmToken(token);
        if (user != null) {
            model.addObject("message", "User " + user.getUsername() + " were succesfully activated");
        } else {
            model.addObject("message", "Invalid token");
        }
        model.setViewName("message");
        return model;
    }
}
