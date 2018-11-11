package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Team;
import project.persistence.entities.Tournament;
import project.persistence.entities.User;
import project.service.Interfaces.IAuthenticationService;
import project.service.Interfaces.ITournamentService;
import project.service.Interfaces.IUserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/tournaments")
public class TournamentController {

    // Instance Variables
    private ITournamentService tournamentService;
    private IAuthenticationService authenticationService;
    private IUserService userService;
    Logger logger = LogManager.getLogger(UserController.class);

    public TournamentController(ITournamentService tournamentService, IAuthenticationService authenticationService, IUserService userService) {
        this.tournamentService = tournamentService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @RequestMapping(value ="/create", method = RequestMethod.GET)
    public String createTournamentGet(Model model) {

        User user = userService.findByUsername(authenticationService.getUsername());
        model.addAttribute("tournament", new Tournament(user));

        // Setup authentication
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        return "CreateTournament";
    }

    @RequestMapping(value ="/create", method = RequestMethod.POST)
    public String createTournamentPost(@ModelAttribute("tournament") Tournament tournament,
                                       @RequestParam(value = "myTeams", required = false)String[] myTeams,
                                       Model model){
        // TODO: Improve User input, figure out if more fields are needed
        // TODO: Improve view based on input fields
        logger.info("Creating tournament: " + tournament.getName());
        Set<Team> teams = new HashSet<>();
        if(myTeams != null)
            for (String s: myTeams)
                teams.add(new Team(s, tournament));

        if(teams.size() > 0) {
            tournament.setTeams(teams);
        }

        tournamentService.save(tournament);

        model.addAttribute("tournament",new Tournament());

        // Setup authentication
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        logger.info("Tournament created: " + tournament.getName());
        return "CreateTournament";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public String tournamentEditGet(Model model,
                                    @RequestParam(value = "id")Long id){
        // TODO: Implement this
        model.addAttribute("errorMsg", "501 - Not implemented yet");

        return "errors/error";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public String tournamentEditPut(Model model,
                                    @RequestParam(value = "id")Long id){
        // TODO: Implement this
        model.addAttribute("errorMsg", "501 - Not implemented yet");

        return "errors/error";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public String tournamentEditPatch(Model model,
                                      @RequestParam(value = "id")Long id){
        // TODO: Implement this
        model.addAttribute("errorMsg", "501 - Not implemented yet");

        return "errors/error";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public String tournamentDelete(Model model,
                                   @RequestParam(value = "id")Long id){
        // TODO: Implement this
        model.addAttribute("errorMsg", "501 - Not implemented yet");

        return "errors/error";
    }


}
