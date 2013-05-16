package com.github.lazydarklord.spider.model;

/**
 * Route
 */
public class Route
{
    Station source;

    Station destination;

    double distance;

    public Route(){}

    public Route(Station source, Station destionation)
    {
        this.source = source;
        this.destination = destionation;
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

        Route other = (Route) obj;
        if (!this.source.equals(other.source)) return false; // routes do not have the same source
        if (!this.destination.equals(other.destination)) return false; // routes do not have the same destination
        if (this.distance != other.distance) return false; // routes do not have the same distance

        // everything passed, so the object are equivalent
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return this.getSource() + "->" + this.getDestination() + "(" + this.getDestination() + ")";
    }

}
