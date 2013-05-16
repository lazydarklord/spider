package com.github.lazydarklord.spider.model;

/**
 * Station
 */
public class Station
{
    private String id;
    
    public Station() {}
    
    public Station(String id){this.id = id;}

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;   // same object reference
        if (obj == null) return false;  // can't compare with non-existent object
        if (getClass() != obj.getClass()) return false; // can't compare with an object of different class

        Station other = (Station) obj;
        if (id.equals(other.id)) return true;   // objects are equal as they have the same station ID

        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return this.getId();
    }

}
