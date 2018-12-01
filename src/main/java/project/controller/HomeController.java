package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.service.Interfaces.IAuthenticationService;

@Controller
public class HomeController {

    // Instance Variables
    private IAuthenticationService authenticationService;

    // Dependency Injection
    @Autowired
    public HomeController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        if (authenticationService.isAuthenticated()) {
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        return "Index";
    }

}
