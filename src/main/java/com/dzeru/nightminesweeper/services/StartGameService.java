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
        Random random = new Random();

        int countOfMines = random.nextInt(10) + 15;

        //Size of ArrayList<ArrayList<Boolean>>
        int countOfHorizontalLines = countOfMines + random.nextInt(5);

        ArrayList<ArrayList<Boolean>> field = new ArrayList<>();
        ArrayList<ArrayList<Boolean>> flags = new ArrayList<>();

        int counter = countOfMines; //Is used in loop

        //Size of each ArrayList<Boolean>
        ArrayList<Integer> lengthsOfHorizontalLines = new ArrayList<>();

        for(int vertical = 0; vertical < countOfHorizontalLines; vertical++)
        {
            field.add(new ArrayList<>());
            flags.add(new ArrayList<>());

            int len = countOfMines + random.nextInt(10);
            lengthsOfHorizontalLines.add(len);

            for(int horizontal = 0; horizontal < len; horizontal++)
            {
                field.get(vertical).add(false);
                flags.get(vertical).add(false);
            }
        }

        while(counter > 0)
        {
            int vertical = random.nextInt(countOfHorizontalLines);
            int horizontal = random.nextInt(lengthsOfHorizontalLines.get(vertical));

            if(counter > 0 && !field.get(vertical).get(horizontal) && !(horizontal == 0 && vertical == 0))
                field.get(vertical).set(horizontal, true);
            counter--;
        }

        ArrayList<String> phrases = phraseService.createPhrases(field, 0, 0, 0, locale);
        boolean possibleDirections[] = new boolean[9];

        for(int i = 0; i < possibleDirections.length; i++)
            possibleDirections[i] = true;

        return new GameState(phrases, field, flags, possibleDirections,
                countOfMines, 0, 0, 0);
    }
}
