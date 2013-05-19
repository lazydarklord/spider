package com.github.lazydarklord.spider.Util;

import java.util.List;
import java.util.Stack;

import com.github.lazydarklord.spider.model.Station;

public class Containers
{
    public static void pushStationsToStack(List<Station> stations, Stack<Station> stack)    
    {
        for(Station station: stations)
        {
            stack.push(station);
        }
    }
}
