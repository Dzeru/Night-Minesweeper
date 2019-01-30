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

    public ArrayList<String> createPhrases(ArrayList<ArrayList<Boolean>> field, int minesNearby, int horizontal, int vertical, Locale locale)
    {
        ArrayList<String> phrases = new ArrayList<>();

        String[] loc = new String[]{locale.getDisplayName(locale)};

        String noWay = messageSource.getMessage("phrase.noWay", loc, locale);
        String north = messageSource.getMessage("dir.north", loc, locale);
        String east = messageSource.getMessage("dir.east", loc, locale);
        String south = messageSource.getMessage("dir.south", loc, locale);
        String west = messageSource.getMessage("dir.west", loc, locale);

        String possibleMines = messageSource.getMessage("phrase.possibleMines", loc, locale);
        String noMinesNearby = messageSource.getMessage("phrase.noMinesNearby", loc, locale);

        if(horizontal == 0)
            phrases.add(noWay + west);
        if(horizontal == field.get(vertical).size() - 1)
            phrases.add(noWay + east);
        if(vertical == 0 || horizontal > field.get(vertical - 1).size() - 1)
            phrases.add(noWay + north);
        if(vertical == field.size() - 1 || horizontal > field.get(vertical + 1).size() - 1)
            phrases.add(noWay + south);

        if(minesNearby > 0)
            phrases.add(minesNearby + " " + possibleMines);
        else
            phrases.add(noMinesNearby);

        return phrases;
    }
}