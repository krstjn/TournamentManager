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
import java.util.*;

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
            List<Match> matches = tournament.getMatches();
            Collections.sort(matches);
            model.addAttribute("matches", matches);

            boolean allowSignUp = tournament.getMatches().size() == 0 &&
                                  ((tournament.getTeams().size() < tournament.getMaxTeams() &&
                                  authenticationService.isAuthenticated() &&
                                  tournament.getSignUpExpiration() != null &&
                                  tournament.getSignUpExpiration().compareTo(LocalDateTime.now()) > 0) ||
                                  tournament.getUser().getUsername().equals(authenticationService.getUsername()));

            model.addAttribute("allowSignUp", allowSignUp);
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
        Tournament t = tournamentService.create(tournament, myTeams, signUpExp);
        logger.info("Tournament created: " + t.getName());

        // Redirecta á tournament síðuna
        return "redirect:/tournaments?id=" + t.getId();
    }

    @RequestMapping(value="/addTeam", method = RequestMethod.POST)
    public String addTeam(Model model,
                          @RequestParam(value = "id")Long id,
                          @RequestParam(value="team")String name){
        Tournament tournament = tournamentService.findOne(id);
        Team team = new Team(name,tournament);
        tournament.addTeam(team);
        tournamentService.save(tournament);

        return "redirect:/tournaments?id="+id;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String tournamentEditGet(Model model,
                                    @RequestParam(value = "id")Long id){

        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }

        Tournament tournament = tournamentService.findOne(id);

        // Only the tournament owner and admin are allowed to enter the edit site
        if(authenticationService.isAdmin() || authenticationService.getUsername().equals(tournament.getUser().getUsername())){
            List<Match> matches = tournament.getMatches();
            Collections.sort(matches);
            model.addAttribute("tournament", tournament);
            model.addAttribute("scoreboard", tournamentService.generateScoreboard(tournament));
            model.addAttribute("matches", matches);

            return "Edit";
        }

        return "redirect:/tournaments?id="+id;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String tournamentEditPost(@ModelAttribute("tournament") Tournament tournament,
                                    @RequestParam(value = "id")Long id){

        tournamentService.save(tournament);

        return "redirect:/tournaments?id="+id;
    }

    @RequestMapping(value = "/editMatch", method = RequestMethod.POST)
    public String tournamentEditMatch(Model model,
                                      @RequestParam(value = "id")Long id,
                                      @RequestParam(value = "homeScore")int home,
                                      @RequestParam(value = "awayScore")int away){

        Match match = matchService.findOne(id);
        match.setHomeTeamScore(home);
        match.setAwayTeamScore(away);
        match.setPlayed(true);
        matchService.save(match);

        return "redirect:/tournaments/edit?id="+match.getTournament().getId()+"#matches";
    }

    @RequestMapping(value="generateMatches", method=RequestMethod.GET)
    public String generateMatches(Model model, @RequestParam(value="id")Long id){
        Tournament tournament = tournamentService.findOne(id);
        if(tournament.getMatches().isEmpty())
            matchService.generateMatches(tournament);

        return "redirect:/tournaments?id="+id;
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