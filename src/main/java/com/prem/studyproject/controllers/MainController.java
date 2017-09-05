package com.prem.studyproject.controllers;


import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.services.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/admin")
    public String getAdmin(Map<String, Object> model, Authentication authentication) {
        return "admin";
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

    @RequestMapping(value = "/registration/{token}", method = RequestMethod.GET)
    @ResponseBody
    public String confirmEmail(Map<String, Object> model, Authentication authentication,
                               @PathVariable(required = true) String token) {
        User user = registrationService.confirmToken(token);
        if (user != null) {
            return "User " + user.getUsername() + " were succesfully activated";
        } else {
            return "Invalid token";
        }
    }




}
