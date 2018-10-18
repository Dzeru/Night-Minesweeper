package com.dzeru.nightminesweeper.dto;

import java.util.ArrayList;

public class GameState
{
    private ArrayList<String> phrases;
    private ArrayList<ArrayList<Boolean>> field;
    private ArrayList<ArrayList<Boolean>> flags;
    private int countOfMines;

    public ArrayList<String> getPhrases()
    {
        return phrases;
    }

    public void setPhrases(ArrayList<String> phrases)
    {
        this.phrases = phrases;
    }

    public ArrayList<ArrayList<Boolean>> getField()
    {
        return field;
    }

    public void setField(ArrayList<ArrayList<Boolean>> field)
    {
        this.field = field;
    }

    public ArrayList<ArrayList<Boolean>> getFlags()
    {
        return flags;
    }

    public void setFlags(ArrayList<ArrayList<Boolean>> flags)
    {
        this.flags = flags;
    }

    public int getCountOfMines()
    {
        return countOfMines;
    }

    public void setCountOfMines(int countOfMines)
    {
        this.countOfMines = countOfMines;
    }
}
