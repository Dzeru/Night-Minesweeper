package com.dzeru.nightminesweeper.controllers;

import com.dzeru.nightminesweeper.dto.GameState;
import com.dzeru.nightminesweeper.services.ConvertService;
import com.dzeru.nightminesweeper.services.GameStepService;
import com.dzeru.nightminesweeper.services.StartGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Locale;

@Controller
public class GameController
{
    @Autowired
    StartGameService startGameService;

    @Autowired
    GameStepService gameStepService;

    @Autowired
    ConvertService convertService;

    @GetMapping("/startgame")
    public String startGame(Model model, Locale locale)
    {
        GameState initialGameState = startGameService.start(locale);

        model.addAttribute("x", initialGameState.getX());
        model.addAttribute("y", initialGameState.getY());
        model.addAttribute("field", convertService.listToString(initialGameState.getField()));
        model.addAttribute("flags", convertService.listToString(initialGameState.getFlags()));
        model.addAttribute("phrases", initialGameState.getPhrases());
        model.addAttribute("countOfMines", initialGameState.getCountOfMines());
        return "game";
    }

    @PostMapping("/gamestep")
    public String gameStep(Model model, Locale locale,
                           @RequestParam int x,
                           @RequestParam int y,
                           @RequestParam String step,
                           @RequestParam(required = false) String flag,
                           @RequestParam String field,
                           @RequestParam String flags,
                           @RequestParam int countOfMines)
    {
        GameState gameState = gameStepService.gameStep(locale, x, y, step, flag, countOfMines,
                                                       convertService.stringToList(field),
                                                       convertService.stringToList(flags));

        if(gameState.getCountOfMines() == -1)
        {
            model.addAttribute("fail", true);
            return "finish";
        }

        model.addAttribute("x", gameState.getX());
        model.addAttribute("y", gameState.getY());
        model.addAttribute("field", convertService.listToString(gameState.getField()));
        model.addAttribute("flags", convertService.listToString(gameState.getFlags()));
        model.addAttribute("phrases", gameState.getPhrases());
        model.addAttribute("countOfMines", gameState.getCountOfMines());
        return "game";
    }

    @GetMapping("/game")
    public String game(Model model)
    {
        return "game";
    }
}
