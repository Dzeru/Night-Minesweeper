package com.dzeru.nightminesweeper.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class GameStepService
{
    @Autowired
    PhraseService phraseService;

    public void gameStep(Locale locale, int x, int y, String step, String flag)
    {

    }
}
