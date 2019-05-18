package com.dzeru.nightminesweeper.dto;

import java.util.ArrayList;

public class GameState
{
    private ArrayList<String> phrases;
    private ArrayList<ArrayList<Boolean>> field;
    private ArrayList<ArrayList<Boolean>> flags;
    private boolean[] possibleDirections;
    private int countOfMines;
    private int minesNearby;
    private int horizontal;
    private int vertical;

    public GameState(){}

    public GameState(ArrayList<String> phrases,
                     ArrayList<ArrayList<Boolean>> field,
                     ArrayList<ArrayList<Boolean>> flags,
                     boolean[] possibleDirections,
                     int countOfMines, int minesNearby,
                     int horizontal, int vertical)
    {
        this.phrases = phrases;
        this.field = field;
        this.flags = flags;
        this.possibleDirections = possibleDirections;
        this.countOfMines = countOfMines;
        this.minesNearby = minesNearby;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

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

    public boolean[] getPossibleDirections()
    {
        return possibleDirections;
    }

    public void setPossibleDirections(boolean[] possibleDirections)
    {
        this.possibleDirections = possibleDirections;
    }

    public int getCountOfMines()
    {
        return countOfMines;
    }

    public void setCountOfMines(int countOfMines)
    {
        this.countOfMines = countOfMines;
    }

    public int getMinesNearby()
    {
        return minesNearby;
    }

    public void setMinesNearby(int minesNearby)
    {
        this.minesNearby = minesNearby;
    }

    public int getHorizontal()
    {
        return horizontal;
    }

    public void setHorizontal(int horizontal)
    {
        this.horizontal = horizontal;
    }

    public int getVertical()
    {
        return vertical;
    }

    public void setVertical(int vertical)
    {
        this.vertical = vertical;
    }
}
