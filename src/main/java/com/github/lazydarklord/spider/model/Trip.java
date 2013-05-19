package com.github.lazydarklord.spider.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Trip
 */
public class Trip
{
    private Station source;

    private Station destination;

    private double distance;

    private List<Route> hops;

    public Trip()
    {
        hops = new LinkedList<Route>();
    }

    /**
     * Copy constructor
     * 
     * @param another Another trip to copy data from
     */
    public Trip(Trip another)
    {
        if (another != null)
        {
            this.source = another.source;
            this.destination = another.destination;
            this.distance = another.distance;
            this.hops = another.hops;
        }
    }

    /**
     * @return the number of hops from source to destination
     */
    public int getHopCount()
    {
        return this.hops.size();
    }

    /**
     * @param hop add a hop at the end of the trip updating the destination and the distance
     */
    public void addHop(Route hop)
    {
        this.hops.add(hop);
        if (this.source == null)
        {
            this.setSource(hop.source);
        }

        this.destination = hop.destination;
        this.distance += hop.distance;
    }

    public Route removeLastHop()
    {
        if (this.hops.size() > 1)
        {
            //this.destination = this.hops.get(this.hops.size() - 1).source;
            //this.distance -= this.hops.get(this.hops.size() - 1).distance;
            return this.hops.remove(this.hops.size() - 1);
        }

        return null;
    }

    /**
     * @return the source
     */
    public Station getSource()
    {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(Station source)
    {
        this.source = source;
    }

    /**
     * @return the destination
     */
    public Station getDestination()
    {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(Station destination)
    {
        this.destination = destination;
    }

    /**
     * @return the distance
     */
    public double getDistance()
    {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    /**
     * @return the hops
     */
    public List<Route> getHops()
    {
        return hops;
    }

    /**
     * @param hops the hops to set
     */
    public void setHops(List<Route> hops)
    {
        this.hops = hops;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(this.source.getId() + "->" + this.destination.getId() + " Dist: " + this.getDistance() + "===>");

        for (Route hop : this.hops)
        {
            str.append(hop.getSource().getId() + "->" + hop.getDestination().getId() + " (" + hop.getDistance() + ")");
        }

        return str.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true; // same object reference
        if (obj == null) return false; // can't compare with non-existent object
        if (getClass() != obj.getClass()) return false; // can't compare with an object of different class

        Trip otherTrip = (Trip) obj;
        if (!this.source.equals(otherTrip.source)) return false; // trips do not have the same source
        if (!this.destination.equals(otherTrip.destination)) return false; // trips do not have the same destination
        if (this.distance != otherTrip.distance) return false; // trips do not have the same distance

        List<Route> thisHops = this.getHops();
        List<Route> otherHops = otherTrip.getHops();

        if (thisHops.size() != otherHops.size()) return false; // different number of hops
        for (int i = 0; i < thisHops.size(); i++)
            if (!thisHops.get(i).equals(otherHops.get(i))) return false; // at least one hop is not the same

        // everything passed, so the object are equivalent
        return true;
    }

}
