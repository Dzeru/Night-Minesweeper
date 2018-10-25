package com.dzeru.nightminesweeper.dto;

import java.util.ArrayList;

public class GameState
{
    private ArrayList<String> phrases;
    private ArrayList<ArrayList<Boolean>> field;
    private ArrayList<ArrayList<Boolean>> flags;
    private int countOfMines;
    private int minesNearby;
    private int x;
    private int y;

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

    public int getMinesNearby()
    {
        return minesNearby;
    }

    public void setMinesNearby(int minesNearby)
    {
        this.minesNearby = minesNearby;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
