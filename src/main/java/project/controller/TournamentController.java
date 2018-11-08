package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        Set<Team> t = new HashSet<>();
        if(myTeams != null)
            for (String s: myTeams)
                t.add(new Team(s, tournament));

        if(t.size() > 0) {
            tournament.setTeams(t);
        }
        try{
            tournamentService.save(tournament);
        } catch (Exception e){
            System.out.println("villa");
        }

        model.addAttribute("tournament",new Tournament());
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        logger.info("Tournament created: " + tournament.getName());
        return "CreateTournament";
    }
}
