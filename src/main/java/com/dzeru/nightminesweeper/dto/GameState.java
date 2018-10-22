package com.dzeru.nightminesweeper.dto;

import java.util.ArrayList;

public class GameState
{
    private ArrayList<String> phrases;
    private ArrayList<ArrayList<Boolean>> field;
    private ArrayList<ArrayList<Boolean>> flags;
    private int countOfMines;
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

    public String listToString(ArrayList<ArrayList<Boolean>> list)
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < list.size(); i++)
        {
            for(int k = 0; k < list.get(i).size(); k++)
                sb.append(list.get(i).get(k));
            sb.append('/');
        }

        return sb.toString();
    }

    public ArrayList<ArrayList<Boolean>> stringToList(String s)
    {
        ArrayList<ArrayList<Boolean>> list = new ArrayList<>();

        String[] strings = s.split("/");

        for(int i = 0; i < strings.length; i++)
        {
            list.add(new ArrayList<Boolean>());
            while(!strings[i].equals(""))
            {
                if(strings[i].substring(0, 4).equals("true"))
                {
                    list.get(i).add(true);
                    strings[i] = strings[i].substring(4);
                }
                else
                {
                    list.get(i).add(false);
                    strings[i] = strings[i].substring(5);
                }
            }
        }
        return list;
    }
}
