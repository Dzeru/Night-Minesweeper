package com.dzeru.nightminesweeper.services;

import com.dzeru.nightminesweeper.dto.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

@Service
public class StartGameService
{
    @Autowired
    PhraseService phraseService;

    public GameState start(Locale locale)
    {
        GameState initialGameState = new GameState();

        Random random = new Random();

        int countOfMines = random.nextInt(10) + 15;

        int lengthOfSide = countOfMines + random.nextInt(5);

        ArrayList<ArrayList<Boolean>> field = new ArrayList<>();
        ArrayList<ArrayList<Boolean>> flags = new ArrayList<>();

        int counter = countOfMines; //Is used in loop

        ArrayList<Integer> lengthOfY = new ArrayList<>();

        for(int y = 0; y < lengthOfSide; y++)
        {
            field.add(new ArrayList<>());
            flags.add(new ArrayList<>());

            int len = countOfMines + random.nextInt(10);
            lengthOfY.add(len);

            for(int x = 0; x < len; x++)
            {
                field.get(y).add(false);
                flags.get(y).add(false);
            }
        }

        while(counter > 0)
        {
            int y = random.nextInt(lengthOfSide);
            int x = random.nextInt(lengthOfY.get(y));

            if(counter > 0 && !field.get(y).get(x))
                field.get(y).set(x, true);
            counter--;
        }

        ArrayList<String> phrases = phraseService.createPhrases(field, 0, 0, 0, locale);

        initialGameState.setCountOfMines(countOfMines);
        initialGameState.setField(field);
        initialGameState.setFlags(flags);
        initialGameState.setPhrases(phrases);
        initialGameState.setX(0);
        initialGameState.setY(0);
        initialGameState.setMinesNearby(0);

        return initialGameState;
    }
}
