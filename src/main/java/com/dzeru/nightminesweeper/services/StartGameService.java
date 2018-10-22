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

        for(int x = 0; x < lengthOfSide; x++)
        {
            field.add(new ArrayList<>());
            flags.add(new ArrayList<>());

            int len = countOfMines + random.nextInt(10);
            lengthOfY.add(len);

            for(int y = 0; y < len; y++)
            {
                field.get(x).add(false);
                flags.get(x).add(false);
            }
        }

        while(counter > 0)
        {
            int x = random.nextInt(lengthOfSide);
            int y = random.nextInt(lengthOfY.get(x));

            if(counter > 0 && !field.get(x).get(y))
                field.get(x).set(y, true);
            counter--;
        }

        ArrayList<String> phrases = phraseService.createPhrases(field, 0, 0, locale);

        initialGameState.setCountOfMines(countOfMines);
        initialGameState.setField(field);
        initialGameState.setFlags(flags);
        initialGameState.setPhrases(phrases);
        initialGameState.setX(0);
        initialGameState.setY(0);

        return initialGameState;
    }
}
