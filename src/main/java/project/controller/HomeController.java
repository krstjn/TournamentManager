package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import project.persistence.entities.Team;
import project.persistence.entities.Tournament;
import project.persistence.entities.User;
import project.service.Interfaces.IAuthenticationService;
import project.service.Interfaces.ITeamService;
import project.service.Interfaces.ITournamentService;
import project.service.Interfaces.IUserService;

import java.util.Set;


@Controller
public class HomeController {

    // Instance Variables
    private ITournamentService tournamentService;
    private IAuthenticationService authenticationService;
    private IUserService userService;
    private ITeamService teamService;
    Logger logger = LogManager.getLogger(UserController.class);

    // Dependency Injection
    @Autowired
    public HomeController(ITournamentService tournamentService,ITeamService teamService, IUserService userService, IAuthenticationService authenticationService) {
        this.tournamentService = tournamentService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.teamService = teamService;
    }

    // Request mapping is the path that you want to map this method to
    // In this case, the mapping is the root "/", so when the project
    // is running and you enter "localhost:8080" into a browser, this
    // method is called
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){

        // The string "Index" that is returned here is the name of the view
        // (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
        // If you change "Index" to something else, be sure you have a .jsp
        // file that has the same name
        model.addAttribute("tournament",new Tournament());
        model.addAttribute("tournaments", tournamentService.findAll());
        // Here we get all the Tournaments (in a reverse order) and add them to the model
        // model.addAttribute("tournaments",tournamentService.findAllReverseOrder());
        // Sækja lista yfir öll mót
        // model.addAttribute("tournaments", tournamentService.getTournaments());
        return "Index";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String error(NoHandlerFoundException ex){
        return "Error";
    }

    // To call this method, enter "localhost:8080/user" into a browser
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model){

        // Here we will show how to add attributes to a model and send it to the view

        // Since this small example is for a user, let's create some attributes
        // that users might usually have in a system
        String name = "Rincewind";
        String job  = "Wizzard";
        String email = "rincewizz@unseenuni.edu";
        String description = "most likely to survive in a dungeon dimension.";


        // Since we want our attributes regarding the user always in the same format,
        // we are going to convert some strings using our StringManipulationService


        // Now let's add the attributes to the model
        model.addAttribute("name",name);
        model.addAttribute("job",job);
        model.addAttribute("email",email);
        model.addAttribute("description",description);
        User user = userService.findByUsername(authenticationService.getUsername());
        Set<Tournament> tournaments = user.getTournaments();
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
            model.addAttribute("tournaments", tournaments);
        }        // By adding attributes to the model, we can pass information from the controller
        // to the view (the .jsp file).
        // Look at the User.jsp file in /main/webapp/WEB-INF/jsp/ to see how the data is accessed
        return "User";
    }



    // To call this method, enter "localhost:8080/tournament" into a browser
    @RequestMapping(value = "/tournament", method = RequestMethod.GET)
    public String tournament(Model model){
        // The string "TournamentView" that is returned here is the name of the view
        // (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
        // If you change "TournamentView" to something else, be sure you have a .jsp
        // file that has the same name
        model.addAttribute("tournament",new Tournament());
        model.addAttribute("tournaments", tournamentService.findAll());
        model.addAttribute("teams", teamService.findAll());



        // Here we get all the Tournaments (in a reverse order) and add them to the model
        // model.addAttribute("tournaments",tournamentService.findAllReverseOrder());
        // Sækja lista yfir öll mót
        // model.addAttribute("tournaments", tournamentService.getTournaments());

        return "TournamentView";
    }
}
