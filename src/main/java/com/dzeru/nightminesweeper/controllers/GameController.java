package com.dzeru.nightminesweeper.controllers;

import com.dzeru.nightminesweeper.dto.GameState;
import com.dzeru.nightminesweeper.services.StartGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class GameController
{
    @Autowired
    StartGameService startGameService;

    @GetMapping("/startgame")
    public String startGame(Model model, Locale locale)
    {
        GameState initialGameState = startGameService.start(locale);

        model.addAttribute("phrases", initialGameState.getPhrases());
        model.addAttribute("countOfMines", initialGameState.getCountOfMines());
        return "game";
    }

    @GetMapping("/game")
    public String game(Model model)
    {
        return "game";
    }
}
