package com.github.lazydarklord.spider.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph
 */
public class Graph
{
    List<Station> stations; // list of all stations in the graph

    List<Route> routes; // list of all directed routes in the graph

    /**
     * Initialize the graph to accept stations and directed routes.
     */
    public Graph()
    {
        this.stations = new ArrayList<Station>();
        this.routes = new ArrayList<Route>();
    }

    /**
     * Initialize the graph with stations and directed routes.
     * 
     * @param stations stations in the graph
     * @param routes directed routes connecting stations in the graph
     */
    public Graph(List<Station> stations, List<Route> routes)
    {
        this.stations = stations;
        this.routes = routes;
    }

    /**
     * @param station station to add to the graph
     */
    public void addStation(Station station)
    {
        this.stations.add(station);
    }

    /**
     * @param route directed route to add to the graph
     */
    public void addRoute(Route route)
    {
        this.routes.add(route);
    }

    /**
     * Find if a station with the specified id exists.
     * 
     * @param stationId id of the station to be searched
     * @return station object if it exists, null otherwise
     */
    public Station findStation(String stationId)
    {
        for (Station station : this.stations)
        {
            if (station.getId().equals(stationId))
            {
                return station; // found the station
            }
        }

        return null;
    }

    /**
     * Find if a route exists between two stations.
     * 
     * @param src source station for the route
     * @param dst destination station for the route
     * @return route object if it exists, null otherwise
     */
    public Route findRoute(Station src, Station dst)
    {
        for (Route route : this.routes)
        {
            if (route.source.equals(src) && (route.destination.equals(dst)))
            {
                return route; // found the route
            }
        }

        return null;
    }

    /**
     * Find if a route exists between two stations.
     * 
     * @param src id of source station for the route
     * @param dst id destination station for the route
     * @return route object if it exists, null otherwise
     */
    public Route findRoute(String srcId, String dstId)
    {
        Station src = this.findStation(srcId);
        Station dst = this.findStation(dstId);

        return this.findRoute(src, dst);
    }

    /**
     * Get all outbound routes from a station
     * 
     * @param station station from which outbound routes are to be fetched
     * @return list of outbound routes if they exist, null otherwise
     */
    public List<Route> getOutboundRoutes(Station station)
    {
        List<Route> outRoutes = null;

        for (Route route : routes)
        {
            if (route.getSource().equals(station))
            {
                if (outRoutes == null)
                {
                    outRoutes = new ArrayList<Route>();
                }

                outRoutes.add(route);
            }
        }

        return outRoutes;
    }

    /**
     * Get all inbound routes from a station
     * 
     * @param station station from which inbound routes are to be fetched
     * @return list of inbound routes if they exist, null otherwise
     */
    public List<Route> getInboundRoutes(Station station)
    {
        List<Route> inRoutes = null;

        for (Route route : routes)
        {
            if (route.getDestination().equals(station))
            {
                if (inRoutes == null)
                {
                    inRoutes = new ArrayList<Route>();
                }

                inRoutes.add(route);
            }
        }

        return inRoutes;
    }

    /**
     * @return count of all stations in graph
     */
    public int getStationsCount()
    {
        return this.stations.size();
    }

    /**
     * @return count of all directed routes in graph
     */
    public int getRoutesCount()
    {
        return this.routes.size();
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

        for (Station station : this.stations)
        {
            str.append("Station: " + station);
            // list inbound stations
            str.append(" Inbound Stations: ");
            for (Route adjStation : this.getInboundRoutes(station))
            {
                str.append(adjStation.getSource() + " | ");
            }

            // list outbound stations
            str.append(" Outbound Stations: ");
            for (Route adjStation : this.getOutboundRoutes(station))
            {
                str.append(adjStation.getDestination() + " | ");
            }
            str.append("\n");
        }

        for (Route track : this.routes)
        {
            str.append("Track: " + track.getSource().getId() + " -> " + track.getDestination().getId() + "\n");
        }

        return str.toString();
    }

    /**
     * @return the stations
     */
    public List<Station> getStations()
    {
        return stations;
    }

    /**
     * @return the routes
     */
    public List<Route> getRoutes()
    {
        return routes;
    }

}
