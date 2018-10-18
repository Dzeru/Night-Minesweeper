package com.dzeru.nightminesweeper.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PhraseService
{
    public ArrayList<String> createPhrases(ArrayList<ArrayList<Boolean>> field, int x, int y)
    {
        ArrayList<String> phrases = new ArrayList<>();

        int sf = field.size() - 1;

        if(x == 0)
            phrases.add("No way on West");
        if(x == sf)
            phrases.add("No way on East");
        if(y == 0)
            phrases.add("No way on South");
        if(y == sf)
            phrases.add("No way on North");

        return phrases;
    }
}
