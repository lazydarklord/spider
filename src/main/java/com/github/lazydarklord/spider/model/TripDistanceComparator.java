package com.github.lazydarklord.spider.model;

import java.util.Comparator;

public class TripDistanceComparator implements Comparator<Trip>
{

    /**
     * {@inheritDoc}
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Trip o1, Trip o2)
    {
        return (o1.getDistance() < o2.getDistance() ? -1 : (o1.getDistance() == o2.getDistance() ? 0 : 1));
    }
}
