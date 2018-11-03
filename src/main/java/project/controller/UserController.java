package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.persistence.entities.User;
import project.service.Interfaces.IUserService;

import java.util.ArrayList;

@Controller
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model){
        model.addAttribute("title", "Login");

        return "Login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGet(Model model){
        model.addAttribute("title", "Signup");
        model.addAttribute("target", "/signup");
        return "Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(Model model,
                            @RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password ){

        ArrayList<String> errors = new ArrayList<>();
        User user = userService.checkCredentials(username.toUpperCase(),password);
        if(user != null){
            return "redirect:/user?id="+user.getId();
        } else {
            errors.add("Login unsuccessful, try again");
            model.addAttribute("errors", errors);
            model.addAttribute("title","Login");
            model.addAttribute("target", "/login");

            return "Login";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(Model model,
                             @RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password){
        ArrayList<String> errors = new ArrayList<>();
        if(userService.findByUsername(username.toUpperCase()) != null){
            errors.add("Username already in use.");
        }
        if(username.length() < 5){
            errors.add("Username has to be at least 5 letters.");
        }
        if(password.length() < 5){
            errors.add("Password has to be at least 5 letters.");

        }
        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            model.addAttribute("title","Signup");
            model.addAttribute("target", "/signup");

            return "Login";
        }
        User user = new User(username.toUpperCase(), userService.hashPW(password));
        userService.save(user);
        return "redirect:/login";
    }
}
