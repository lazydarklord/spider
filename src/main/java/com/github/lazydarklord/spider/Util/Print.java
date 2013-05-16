package com.github.lazydarklord.spider.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.github.lazydarklord.spider.model.Trip;

/**
 * Utility functions for printing.
 */
public class Print
{
    public static void printTripDictionary(HashMap<String, Set<Trip>> tripDict)
    {
        for (Entry<String, Set<Trip>> allTripPaths : tripDict.entrySet())
        {
            String key = allTripPaths.getKey();

            System.out.println("Alternatives for: " + key);

            for (Trip trip : allTripPaths.getValue())
            {
                System.out.println(trip);
            }
        }
    }

    public static void printTripList(List<Trip> trips)
    {
        int i = 0;
        for (Trip trip : trips)
        {
            i++;
            System.out.println(i + ": " + trip);
        }
    }
}
