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

        model.addAttribute("horizontal", initialGameState.getHorizontal());
        model.addAttribute("vertical", initialGameState.getVertical());
        model.addAttribute("field", convertService.encryptField(initialGameState.getField()));
        model.addAttribute("flags", convertService.encryptField(initialGameState.getFlags()));
        model.addAttribute("phrases", initialGameState.getPhrases());
        model.addAttribute("countOfMines", initialGameState.getCountOfMines());
        return "game";
    }

    @PostMapping("/gamestep")
    public String gameStep(Model model, Locale locale,
                           @RequestParam int horizontal,
                           @RequestParam int vertical,
                           @RequestParam String step,
                           @RequestParam(required = false) String flag,
                           @RequestParam String field,
                           @RequestParam String flags,
                           @RequestParam int countOfMines)
    {
        GameState gameState = gameStepService.gameStep(locale, horizontal, vertical, step, flag, countOfMines,
                                                       convertService.decryptField(field),
                                                       convertService.decryptField(flags));

        if(gameState.getCountOfMines() == -1)
        {
            model.addAttribute("fail", true);
            return "finish";
        }
        if(gameState.getCountOfMines() == 0)
        {
            model.addAttribute("win", true);
            return "finish";
        }

        model.addAttribute("horizontal", gameState.getHorizontal());
        model.addAttribute("vertical", gameState.getVertical());
        model.addAttribute("field", convertService.encryptField(gameState.getField()));
        model.addAttribute("flags", convertService.encryptField(gameState.getFlags()));
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
