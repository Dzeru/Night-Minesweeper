package com.dzeru.nightminesweeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class PhraseService
{
    @Autowired
    MessageSource messageSource;

    public ArrayList<String> createPhrases(ArrayList<ArrayList<Boolean>> field, int x, int y, Locale locale)
    {
        ArrayList<String> phrases = new ArrayList<>();

        String[] loc = new String[]{locale.getDisplayName(locale)};
        String noWay = messageSource.getMessage("phrase.noWay", loc, locale);
        String north = messageSource.getMessage("dir.north", loc, locale);
        String east = messageSource.getMessage("dir.east", loc, locale);
        String south = messageSource.getMessage("dir.south", loc, locale);
        String west = messageSource.getMessage("dir.west", loc, locale);


        int sf = field.size() - 1;

        if(x == 0)
            phrases.add(noWay + west);
        if(x == sf)
            phrases.add(noWay + east);
        if(y == 0)
            phrases.add(noWay + south);
        if(y == sf)
            phrases.add(noWay + north);

        return phrases;
    }
}
