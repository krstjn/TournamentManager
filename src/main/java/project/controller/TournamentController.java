package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Match;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/tournaments" )
public class TournamentController {

    // Instance Variables
    private ITournamentService tournamentService;
    private IMatchService matchService;
    private IAuthenticationService authenticationService;
    private IUserService userService;
    private Logger logger = LogManager.getLogger(TournamentController.class);

    public TournamentController(ITournamentService tournamentService, IAuthenticationService authenticationService, IUserService userService, IMatchService matchService) {
        this.tournamentService = tournamentService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.matchService = matchService;
    }

    @GetMapping
    public String tournamentsGet(Model model,
                                 @RequestParam(value="search", required = false)String search,
                                 @RequestParam(value="id", required = false)Long id){
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }

        Tournament tournament = tournamentService.findOne(id);

        if(tournament != null){
            model.addAttribute("tournament", tournament);
            model.addAttribute("scoreboard", tournamentService.generateScoreboard(tournament));
            model.addAttribute("matches", matchService.generateMatches(tournament));
            return "TournamentView";
        }

        if(search != null) {
            model.addAttribute("tournaments", tournamentService.findByNameSearch(search.toUpperCase()));
        } else {
            model.addAttribute("tournaments", tournamentService.findAll());
        }
        return "ViewTournaments";
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
        Tournament t = tournamentService.create(tournament, myTeams);
        logger.info("Tournament created: " + t.getName());

        // Redirecta á tournament síðuna
        return "redirect:/tournaments?id=" + t.getId();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String tournamentEditGet(Model model,
                                    @RequestParam(value = "id")Long id){

        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }

        model.addAttribute("tournaments", tournamentService.findAll());
        model.addAttribute("tournament", tournamentService.findOne(id));
        Tournament tournament = tournamentService.findOne(id);
        List<Match> Matches = matchService.generateMatches(tournament);
        model.addAttribute("scoreboard", tournamentService.generateScoreboard(tournament));
        model.addAttribute("matches", Matches);




        return "Edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String tournamentEditPost(@ModelAttribute("tournament") Tournament tournament,
                                    @RequestParam(value = "id")Long id){

        tournamentService.save(tournament);

        return "redirect:/tournaments?id="+id;
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