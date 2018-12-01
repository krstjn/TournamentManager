package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Match;
import project.persistence.entities.Team;
import project.persistence.entities.Tournament;
import project.service.Interfaces.IAuthenticationService;
import project.service.Interfaces.IMatchService;
import project.service.Interfaces.ITournamentService;
import project.service.Interfaces.IUserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping(value = "/tournaments" )
public class TournamentController {
    private final String path = "tournament/";

    // Instance Variables
    private ITournamentService tournamentService;
    private IMatchService matchService;
    private IAuthenticationService authenticationService;
    private Logger logger = LogManager.getLogger(TournamentController.class);

    public TournamentController(ITournamentService tournamentService, IAuthenticationService authenticationService, IMatchService matchService) {
        this.tournamentService = tournamentService;
        this.authenticationService = authenticationService;
        this.matchService = matchService;
    }

    /**
     * Gets the tournaments/ site
     * If search is defined, user will always be given the search results
     * If id is defined and references an existing tournament, user will get the tournaments site
     * Otherwise the user will be given all public tournaments
     * @param model
     * @param search
     * @param id
     * @return
     */
    @GetMapping
    public String tournamentsGet(Model model,
                                 @RequestParam(value="search", required = false)String search,
                                 @RequestParam(value="id", required = false)Long id){
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }

        Tournament tournament = tournamentService.findOne(id);

        if(tournament != null &&
            (tournament.getIsPublic() || authenticationService.getUsername().equals(tournament.getUser().getUsername()))){
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
            model.addAttribute("timeFormatter", DateTimeFormatter.ofPattern("HH:mm - dd. MMM YYYY"));
            return path + "TournamentView";
        }

        if(search != null) {
            model.addAttribute("tournaments", tournamentService.findByNameSearch(search.toUpperCase()));
        } else {
            model.addAttribute("tournaments", tournamentService.findAll());
        }
        return path + "ViewTournaments";
    }


    /**
     * Sets up the model for creating a tournament
     * @param model
     * @return
     */
    @RequestMapping(value ="/create", method = RequestMethod.GET)
    public String createTournamentGet(Model model) {
        Tournament tournament = new Tournament();

        model.addAttribute("tournament", tournament);

        // Setup authentication
        if(authenticationService.isAuthenticated()){
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", authenticationService.getUsername());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime minDate = LocalDateTime.now();
        model.addAttribute("minDate", minDate.format(formatter));
        return path +"CreateTournament";
    }

    @RequestMapping(value ="/create", method = RequestMethod.POST)
    public String createTournamentPost(@ModelAttribute("tournament") Tournament tournament,
                                       @RequestParam(value = "myTeams", required = false)String[] myTeams,
                                       @RequestParam(value = "signUpExp", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")LocalDateTime signUpExp,
                                       Model model){
        logger.info("Creating tournament: " + tournament.getName());
        Tournament t = tournamentService.create(tournament, myTeams, signUpExp);
        logger.info("Tournament created: " + t.getName());
        
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

            return path + "Edit";
        }

        return "redirect:/tournaments?id="+id;
    }

    /**
     * Edits the match with the given ID, based on the params provided by the user
     * @param model
     * @param id
     * @param home home team score(goals)
     * @param away away team score(goals)
     * @param location where the match is taking place
     * @param matchDate when it takes place
     * @param played has it been played
     * @return redirects back to the edit site
     */
    @RequestMapping(value = "/editMatch", method = RequestMethod.POST)
    public String tournamentEditMatch(Model model,
                                      @RequestParam(value = "id")Long id,
                                      @RequestParam(value = "homeScore", required = false)int home,
                                      @RequestParam(value = "awayScore", required = false)int away,
                                      @RequestParam(value = "location", required = false) String location,
                                      @RequestParam(value = "matchDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime matchDate,
                                      @RequestParam(value = "played", required = false) String played){

        Match match = matchService.findOne(id);

        // Extra security
        // If the user that tries to edit the match isn't the owner of the tournament or an admin, the edit is denied
        if(!authenticationService.isAdmin() ||
                !authenticationService.getUsername().equals(match.getTournament().getUser().getUsername())) {
            return "redirect:/";
        }


        if(location != null) match.setLocation(location);
        if(matchDate != null) match.setMatchDate(matchDate);
        if(played != null && played.equals("on")){
            match.setHomeTeamScore(home);
            match.setAwayTeamScore(away);
            match.setPlayed(true);
        }
        matchService.save(match);

        return "redirect:/tournaments/edit?id="+match.getTournament().getId()+"#matches";
    }

    /**
     * Generates the matches for the given tournament ID
     * @param model
     * @param id the tournament ID
     * @return redirects back to the tournaments site
     */
    @RequestMapping(value="generateMatches", method=RequestMethod.GET)
    public String generateMatches(Model model, @RequestParam(value="id")Long id){
        Tournament tournament = tournamentService.findOne(id);
        if(tournament.getMatches().isEmpty())
            matchService.generateMatches(tournament);

        return "redirect:/tournaments?id="+id;
    }


}