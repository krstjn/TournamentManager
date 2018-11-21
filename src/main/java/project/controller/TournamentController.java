package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Team;
import project.persistence.entities.Tournament;
import project.persistence.entities.User;
import project.service.Interfaces.IAuthenticationService;
import project.service.Interfaces.IMatchService;
import project.service.Interfaces.ITournamentService;
import project.service.Interfaces.IUserService;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/tournaments" )
public class TournamentController {

    // Instance Variables
    private ITournamentService tournamentService;
    private IAuthenticationService authenticationService;
    private IUserService userService;
    private IMatchService matchService;
    private Logger logger = LogManager.getLogger(TournamentController.class);

    public TournamentController(ITournamentService tournamentService, IAuthenticationService authenticationService,
                                IUserService userService, IMatchService matchService) {
        this.tournamentService = tournamentService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.matchService = matchService;
    }

    @GetMapping()
    public String tournamentsGet(Model model,
                                 @RequestParam(value = "id")Long id){


        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }

        Tournament tournament = tournamentService.findOne(id);

        model.addAttribute("tournaments", tournamentService.findAll());
        model.addAttribute("tournament", tournamentService.findOne(id));
        model.addAttribute("scoreboard", tournamentService.generateScoreboard(tournament));

        // model.addAttribute("match", matchService.findByTournamentId(id));
        //model.addAttribute("generate", matchService.generateMatches(tournamentService.findOne(id)) );

        if (id == 0)
            return "ViewTournaments";
        else
            return "TournamentView";

    }

    @RequestMapping(value ="/create", method = RequestMethod.GET)
    public String createTournamentGet(Model model) {
        Tournament tournament = new Tournament();

        model.addAttribute("tournament", tournament);

        // Setup authentication
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm");
        LocalDateTime minDate = LocalDateTime.now();
        model.addAttribute("minDate", minDate.format(formatter));
        return "CreateTournament";
    }

    @RequestMapping(value ="/create", method = RequestMethod.POST)
    public String createTournamentPost(@ModelAttribute("tournament") Tournament tournament,
                                       @RequestParam(value = "myTeams", required = false)String[] myTeams,
                                       @RequestParam(value = "signUpExp", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")LocalDateTime signUpExp,
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

        // Set how many rounds should be played
        tournament.setNrOfRounds(2);

        // Check if matches should be created
        if (signUpExp == null || signUpExp.isAfter(LocalDateTime.now())){
            tournament.setSignUpExpiration(signUpExp);
            // logger.info("Creating matches for: " + tournament.getName());
            // matchService.generateMatches(tournament);
            // logger.info("Matches created for: " + tournament.getName());
        }

        // Setup owner of tournament
        User user = userService.findByUsername(authenticationService.getUsername());
        tournament.setUser(user);

        tournament.setCreated(LocalDateTime.now());
        try{
            tournamentService.save(tournament);
        } catch (Exception ex){
            logger.error(ex.getMessage());
        }

        model.addAttribute("tournament",new Tournament());

        // Setup authentication
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        logger.info("Tournament created: " + tournament.getName());

        // TODO: Redirecta á tournament síðuna
        return "CreateTournament";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public String tournamentEditGet(Model model,
                                    @RequestParam(value = "id")Long id){

        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        model.addAttribute("tournaments", tournamentService.findAll());
        model.addAttribute("tournament", tournamentService.findOne(id));
        Tournament tournament = tournamentService.findOne(id);
        model.addAttribute("scoreboard", tournamentService.generateScoreboard(tournament));

        return "Edit";
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