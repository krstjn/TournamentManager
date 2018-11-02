package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.User;
import project.service.Interfaces.IUserService;

@Controller
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title", "Login");
        model.addAttribute("target", "/login");

        return "Login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGet(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title", "Signup");
        model.addAttribute("target", "/signup");
        return "Login";
    }
}
