package com.dzeru.nightminesweeper.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConvertService
{
    //Each char match true or false condition of field's cell
    private Character[] cipherForFalse = {'3','4','7','d','a',};
    private Character[] cipherForTrue = {'6','1','B','2',};

    public String encryptField(ArrayList<ArrayList<Boolean>> field)
    {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < field.size(); i++)
        {
            for(int k = 0; k < field.get(i).size(); k++)
            {
                if(field.get(i).get(k))
                {
                    sb.append(cipherForTrue[random.nextInt(cipherForTrue.length)]);
                }
                else
                {
                    sb.append(cipherForFalse[random.nextInt(cipherForFalse.length)]);
                }
            }
            sb.append("/");
        }

        return sb.toString();
    }

    public ArrayList<ArrayList<Boolean>> decryptField(String field)
    {
        ArrayList<ArrayList<Boolean>> list = new ArrayList<>();
        String[] strings = field.split("/");

        for(int i = 0; i < strings.length; i++)
        {
            list.add(new ArrayList<Boolean>());

            while(!strings[i].equals(""))
            {
                if(Arrays.asList(cipherForTrue).contains(strings[i].charAt(0)))
                {
                    list.get(i).add(true);
                }
                else
                {
                    list.get(i).add(false);
                }
                strings[i] = strings[i].substring(1);
            }
        }

        return list;
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

    public Map<String, Object> getPossibleDirectionsParams(boolean[] possibleDirection)
    {
        Map<String, Object> possibleDirectionsParams = new HashMap<>();

        if(possibleDirection[0])
        {
            possibleDirectionsParams.put("nw", "nw");
        }
        if(possibleDirection[1])
        {
            possibleDirectionsParams.put("n", "n");
        }
        if(possibleDirection[2])
        {
            possibleDirectionsParams.put("ne", "ne");
        }
        if(possibleDirection[3])
        {
            possibleDirectionsParams.put("w", "w");
        }
        if(possibleDirection[5])
        {
            possibleDirectionsParams.put("e", "e");
        }
        if(possibleDirection[6])
        {
            possibleDirectionsParams.put("sw", "sw");
        }
        if(possibleDirection[7])
        {
            possibleDirectionsParams.put("s", "s");
        }
        if(possibleDirection[8])
        {
            possibleDirectionsParams.put("se", "se");
        }

        return possibleDirectionsParams;
    }
}
