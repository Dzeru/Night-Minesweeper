package com.dzeru.nightminesweeper.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConvertService
{
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
