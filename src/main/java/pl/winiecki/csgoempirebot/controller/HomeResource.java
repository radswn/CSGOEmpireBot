package pl.winiecki.csgoempirebot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.winiecki.csgoempirebot.bet.Bet;
import pl.winiecki.csgoempirebot.bet.BetDto;
import pl.winiecki.csgoempirebot.bet.BetService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeResource {

    private BetService betService;
    private BotController botController;

    public HomeResource(BetService betService, BotController botController) {
        this.betService = betService;
        this.botController = botController;
    }

    @GetMapping("/")
    public String getHome(Model model){
        List<BetDto> bets = new ArrayList<>();
        model.addAttribute("bets", bets);
        return "index";
    }

    @GetMapping("/all/{number}")
    public String getAll(@PathVariable int number, Model model){
        List<BetDto> bets = betService.getAll(number);
        model.addAttribute("bets", bets);
        return "index";
    }

    @GetMapping("/end")
    public String endBot(Model model){
        model.addAttribute("bets", new ArrayList<>());
        botController.end();
        return "index";
    }

    @GetMapping("/last/{type}/{pageNumber}")
    public String getCurrentResults(@PathVariable int pageNumber, @PathVariable String type, Model model){
        model.addAttribute("bets", betService.getBySpecificTime(pageNumber, type));
        return "index";
    }
}
