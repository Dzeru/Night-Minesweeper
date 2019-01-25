package com.dzeru.nightminesweeper.services;

import com.dzeru.nightminesweeper.dto.GameState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class GameStepService
{
    @Autowired
    PhraseService phraseService;

    public GameState gameStep(Locale locale, int horizontal, int vertical, String step, String flag, int countOfMines,
                              ArrayList<ArrayList<Boolean>> field, ArrayList<ArrayList<Boolean>> flags)
    {
        GameState gm = new GameState();

        int hor = horizontal;
        int ver = vertical;

        //Make a step, change the coordinates
        switch(step)
        {
            case "northwest":
                if(vertical > 0)
                    vertical = vertical - 1;
                if(horizontal > 0)
                    horizontal = horizontal - 1;
                break;
            case "north":
                if(vertical > 0)
                    vertical = vertical - 1;
                break;
            case "northeast":
                if(vertical > 0)
                    vertical = vertical - 1;
                if(horizontal < field.get(vertical).size() - 1)
                    horizontal = horizontal + 1;
                else
                    horizontal = field.get(vertical).size() - 1;
                break;
            case "east":
                if(horizontal < field.get(vertical).size() - 1)
                    horizontal = horizontal + 1;
                else
                    horizontal = field.get(vertical).size() - 1;
                break;
            case "southeast":
                if(vertical < field.size() - 1)
                    vertical = vertical + 1;
                if(horizontal < field.get(vertical).size() - 1)
                    horizontal = horizontal + 1;
                else
                    horizontal = field.get(vertical).size() - 1;
                break;
            case "south":
                if(vertical < field.size() - 1)
                    vertical = vertical + 1;
                break;
            case "southwest":
                if(vertical < field.size() - 1)
                    vertical = vertical + 1;
                if(horizontal > 0)
                    horizontal = horizontal - 1;
                break;
            case "west":
                if(horizontal > 0)
                    horizontal = horizontal - 1;
                break;
            default: break;
        }

        System.out.println("Current field verts: " + field.size() + ", cells: " + field.get(vertical).size());
        System.out.println("Current vert: " + vertical + ", hor: " + horizontal);

        if(field.get(vertical).get(horizontal))
        {
            //minesweeper died
            gm.setCountOfMines(-1);
            return gm;
        }

        //Check marked mines with coordinates BEFORE step
        if(flag != null)
        {
            switch(flag)
            {
                case "northwest":
                    if(hor > 0 && ver > 0)
                        if(field.get(ver - 1).get(hor - 1))
                        {
                            field.get(ver - 1).set(hor - 1, false);
                            flags.get(ver - 1).set(hor - 1, true);
                            countOfMines--;
                        }
                    break;
                case "north":
                    if(ver > 0)
                        if(field.get(ver - 1).get(hor))
                        {
                            field.get(ver - 1).set(hor, false);
                            flags.get(ver - 1).set(hor, true);
                            countOfMines--;
                        }
                    break;
                case "northeast":
                    if(hor < field.get(ver).size() - 1 && ver > 0)
                        if(field.get(ver - 1).get(hor + 1))
                        {
                            field.get(ver - 1).set(hor + 1, false);
                            flags.get(ver - 1).set(hor + 1, true);
                            countOfMines--;
                        }
                    break;
                case "east":
                    if(hor < field.get(ver).size() - 1)
                        if(field.get(ver).get(hor + 1))
                        {
                            field.get(ver).set(hor + 1, false);
                            flags.get(ver).set(hor + 1, true);
                            countOfMines--;
                        }
                    break;
                case "southeast":
                    if(hor < field.get(ver).size() - 1 && ver < field.size() - 1)
                        if(field.get(ver + 1).get(hor + 1))
                        {
                            field.get(ver + 1).set(hor + 1, false);
                            flags.get(ver + 1).set(hor + 1, true);
                            countOfMines--;
                        }
                    break;
                case "south":
                    if(ver < field.size() - 1)
                        if(field.get(ver + 1).get(hor))
                        {
                            field.get(ver + 1).set(hor, false);
                            flags.get(ver + 1).set(hor, true);
                            countOfMines--;
                        }
                    break;
                case "southwest":
                    if(hor > 0 && ver < field.size() - 1)
                        if(field.get(ver + 1).get(hor - 1))
                        {
                            field.get(ver + 1).set(hor - 1, false);
                            flags.get(ver + 1).set(hor - 1, true);
                            countOfMines--;
                        }
                    break;
                case "west":
                    if(hor > 0)
                        if(field.get(ver).get(hor - 1))
                        {
                            field.get(ver).set(hor - 1, false);
                            flags.get(ver).set(hor - 1, true);
                            countOfMines--;
                        }
                    break;
                default:
                    break;
            }
        }

        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;

        if(horizontal > 0)
            left = -1;
        if(vertical > 0)
            bottom = -1;
        if(vertical < field.size() - 1)
            top = 1;

        int minesNearby = 0;

        for(int i = vertical + bottom; i <= vertical + top; i++)
        {
            if(horizontal < field.get(i).size() - 1)
                right = 1;
            for(int k = horizontal + left; k <= horizontal + right; k++)
            {
                if(field.get(i).get(k))
                    minesNearby++;
            }
            right = 0;
        }

        ArrayList<String> phrases = phraseService.createPhrases(field, minesNearby, horizontal, vertical, locale);

        gm.setField(field);
        gm.setFlags(flags);
        gm.setCountOfMines(countOfMines);
        gm.setMinesNearby(minesNearby);
        gm.setHorizontal(horizontal);
        gm.setVertical(vertical);
        gm.setPhrases(phrases);

        return gm;
    }
}