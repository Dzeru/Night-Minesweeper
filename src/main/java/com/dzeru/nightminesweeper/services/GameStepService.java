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

    public GameState gameStep(Locale locale, int x, int y, String step, String flag, int countOfMines,
                              ArrayList<ArrayList<Boolean>> field, ArrayList<ArrayList<Boolean>> flags)
    {
        GameState gm = new GameState();

        int xx = x;
        int yy = y;

        switch(step)
        {
            case "northwest":
                if(x > 0)
                    x = x - 1;
                if(y > 0)
                    y = y - 1;
                break;
            case "north":
                if(y > 0)
                    y = y - 1;
                break;
            case "northeast":
                if(x < field.get(y).size() - 1)
                    x = x + 1;
                if(y > 0)
                    y = y - 1;
                break;
            case "east":
                if(x < field.get(y).size() - 1)
                    x = x + 1;
                break;
            case "southeast":
                if(x < field.get(y).size() - 1)
                    x = x + 1;
                if(y < field.size() - 1)
                    y = y + 1;
                break;
            case "south":
                if(y < field.size() - 1)
                    y = y + 1;
                break;
            case "southwest":
                if(x > 0)
                    x = x - 1;
                if(y < field.size() - 1)
                    y = y + 1;
                break;
            case "west":
                if(x > 1)
                    x = x - 1;
                break;
            default: break;
        }

        if(field.get(y).get(x))
        {
            //minesweeper died
            gm.setCountOfMines(-1);
            return gm;
        }

        if(flag != null)
        {
            switch(flag)
            {
                case "northwest":
                    if(xx > 1 && yy > 1)
                        if(field.get(yy - 1).get(xx - 1))
                        {
                            field.get(yy - 1).set(xx - 1, false);
                            flags.get(yy - 1).set(xx - 1, true);
                            countOfMines--;
                        }
                    break;
                case "north":
                    if(yy > 1)
                        if(field.get(yy - 1).get(xx))
                        {
                            field.get(yy - 1).set(xx, false);
                            flags.get(yy - 1).set(xx, true);
                            countOfMines--;
                        }
                    break;
                case "northeast":
                    if(xx < field.get(yy).size() - 1 && yy > 1)
                        if(field.get(yy - 1).get(xx + 1))
                        {
                            field.get(yy - 1).set(xx + 1, false);
                            flags.get(yy - 1).set(xx + 1, true);
                            countOfMines--;
                        }
                    break;
                case "east":
                    if(xx < field.get(yy).size() - 1)
                        if(field.get(yy).get(xx + 1))
                        {
                            field.get(yy).set(xx + 1, false);
                            flags.get(yy).set(xx + 1, true);
                            countOfMines--;
                        }
                    break;
                case "southeast":
                    if(xx < field.get(yy).size() - 1 && yy < field.size() - 1)
                        if(field.get(yy + 1).get(xx + 1))
                        {
                            field.get(yy + 1).set(xx + 1, false);
                            flags.get(yy + 1).set(xx + 1, true);
                            countOfMines--;
                        }
                    break;
                case "south":
                    if(yy < field.size() - 1)
                        if(field.get(yy + 1).get(xx))
                        {
                            field.get(yy + 1).set(xx, false);
                            flags.get(yy + 1).set(xx, true);
                            countOfMines--;
                        }
                    break;
                case "southwest":
                    if(xx > 1 && yy < field.size() - 1)
                        if(field.get(yy + 1).get(xx - 1))
                        {
                            field.get(yy + 1).set(xx - 1, false);
                            flags.get(yy + 1).set(xx - 1, true);
                            countOfMines--;
                        }
                    break;
                case "west":
                    if(xx > 1)
                        if(field.get(yy).get(xx - 1))
                        {
                            field.get(yy).set(xx - 1, false);
                            flags.get(yy).set(xx - 1, true);
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

        if(x > 0)
            left = -1;
        if(x < field.get(y).size() - 1)
            right = 1;
        if(y > 0)
            top = 1;
        if(y < field.size() - 1)
            bottom = -1;

        int minesNear = 0;

        for(int i = x + left; i < x + right; i++)
            for(int k = y + bottom; k < y + top; k++)
                if(flags.get(y).get(x))
                    minesNear++;

        ArrayList<String> phrases = phraseService.createPhrases(field, minesNear, x, y, locale);

        gm.setField(field);
        gm.setFlags(flags);
        gm.setCountOfMines(countOfMines);
        gm.setMinesNear(minesNear);
        gm.setX(x);
        gm.setY(y);
        gm.setPhrases(phrases);

        return gm;
    }
}
