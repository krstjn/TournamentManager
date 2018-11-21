package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import project.persistence.entities.Tournament;
import project.service.Interfaces.IAuthenticationService;
import project.service.Interfaces.ITeamService;
import project.service.Interfaces.ITournamentService;
import project.service.Interfaces.IUserService;



@Controller
public class HomeController {

    // Instance Variables
    private ITournamentService tournamentService;
    private IAuthenticationService authenticationService;
    Logger logger = LogManager.getLogger(UserController.class);

    // Dependency Injection
    @Autowired
    public HomeController(ITournamentService tournamentService, IAuthenticationService authenticationService) {
        this.tournamentService = tournamentService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        // TODO: Create a landing page
        if (authenticationService.isAuthenticated()) {
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        return "Index";
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String error(NoHandlerFoundException ex) {
        return "Error";
    }

}
