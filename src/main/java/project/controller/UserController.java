package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.persistence.entities.Role;
import project.persistence.entities.User;
import project.service.Interfaces.IRoleService;
import project.service.Interfaces.IUserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    Logger logger = LogManager.getLogger(UserController.class);
    private IUserService userService;
    private IRoleService roleService;
    @Autowired
    public UserController(IUserService userService, IRoleService roleService){
        this.roleService = roleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model,
                           @RequestParam(name = "error", required = false, defaultValue = "false")String error){
        logger.info("Innskrá page");
        ArrayList<String> errors = new ArrayList<>();
        if(Boolean.valueOf(error)) errors.add("Login unsuccessful, try again");
        model.addAttribute("title", "Login");
        model.addAttribute("errors", errors);


        return "Login_Signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGet(Model model){
        model.addAttribute("title", "Signup");
        model.addAttribute("target", "/signup");
        return "Login_Signup";
    }

    @RequestMapping(value = "/login_test", method = RequestMethod.POST)
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

            return "Login_Signup";
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

            return "Login_Signup";
        }
        User user = new User(username.toUpperCase(), userService.hashPW(password));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(user, "ROLE_USER"));

        user.setRoles(roles);
        userService.save(user);
        for(Role r : roles)
            roleService.save(r);
        return "redirect:/login";
    }
}
