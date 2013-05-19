package com.github.lazydarklord.spider.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.github.lazydarklord.spider.Util.Print;

/**
 * Journey Planner
 */
public class JourneyPlanner
{
    private Graph graph;

    private HashMap<String, Set<Trip>> tripDict;

    private HashMap<String, Double> routeDict;

    private List<Trip> tripList;

    public JourneyPlanner(Graph graph)
    {
        this.graph = graph;
        this.init();
    }

    /**
     * Initialize the Journey Planner by pre-computing all paths and costs.
     */
    public void init()
    {
        this.tripDict = new HashMap<String, Set<Trip>>();
        this.routeDict = new HashMap<String, Double>();
        this.tripList = new LinkedList<Trip>();
        this.computeAllPaths();
        this.generateDictionaries();
    }

    /**
     * Compute all the paths in the graph and store them in a list.
     */
    private void computeAllPaths()
    {
        for (Station src : this.graph.stations)
        {
            List<Station> visited = new ArrayList<Station>();
            computePathsForNode(src, null, null, visited);
        }
        Print.printTripList(this.tripList);
    }

    /**
     * Compute all paths that can be traversed in the graph starting from a single node. Uses recursive depth first
     * traversal of the graph to generate all the paths(trips)
     * 
     * @param curr node from which traversal is to begin/continue
     * @param route route using which we arrive at the current
     * @param trip current path of traversal
     */
    private void computePathsForNode(Station curr, Route route, Trip trip, List<Station> visited)
    {
        if (visited.contains(curr)) return; // cycle detected

        visited.add(curr); // add current station to visited list

        if (route != null) // this is not the source node
        {
            if (trip == null)
            {
                trip = new Trip();
                trip.addHop(route);
                this.tripList.add(trip);
            }
            else
            {
                if (trip.getHopCount() > 0)
                {
                    // Intermediate paths within a larger traversal path.
                    // Add all the routes from the original traversal and add the current route at the end.
                    // This generates all the sub-paths in a traversal.
                    Trip newTrip = new Trip();
                    for (Route hop : trip.getHops())
                        newTrip.addHop(hop);

                    tripList.add(newTrip);
                }

                // original traversal path
                trip.addHop(route);
            }
        }

        // if any outbound routes exist, proceed with the next set of nodes in a depth-first manner
        List<Route> outbound = this.graph.getOutboundRoutes(curr);
        if (outbound != null)
        {
            while (!outbound.isEmpty())
            {
                Route outRoute = outbound.remove(0);
                computePathsForNode(outRoute.destination, outRoute, trip, new ArrayList<Station>(visited));
            }
        }
        if (trip != null)
        {
            trip.removeLastHop(); // remove the hop from the trip list
        }

    }

    /**
     * Generate a dictionary with trips categorized by source-destination pairs and sorted in ascending order of
     * distance.
     */
    private void generateDictionaries()
    {
        // add routes to dictionary
        for (Route route : this.graph.getRoutes())
        {
            this.routeDict.put(route.source.getId() + "-" + route.destination.getId(), route.getDistance());
        }

        // add trips to dictionary
        for (Trip trip : this.tripList)
        {
            String key = trip.getSource().getId() + "-" + trip.getDestination().getId();

            if (this.tripDict.containsKey(key))
            {
                Set<Trip> tripsBetweenNodes = this.tripDict.get(key);
                tripsBetweenNodes.add(trip);
            }
            else
            {
                Set<Trip> tripsBetweenNodes = new TreeSet<Trip>(new TripDistanceComparator());
                tripsBetweenNodes.add(trip);

                this.tripDict.put(key, tripsBetweenNodes);
            }
        }

        this.tripList.clear();
    }

    /**
     * Calculate the total distance to be traveled to cover all the given stations in the order that is given.
     * 
     * @param stations list of stations for which the total traversal distance is to be calculated
     * @return distance to travel all the stations in the given order; Infinity if an invalid path is given
     */
    public double calculateDistance(Station[] stations)
    {
        double result = 0.0;

        // Start with the second station. If only station exists in the list, then the distance to travel is zero.
        // Fetch the distance between every two stations and add it to the running sum. If the path is invalid, set the
        // sum to Infinity.
        for (int i = 1; i < stations.length; i++)
        {
            String key = stations[i - 1].getId() + "-" + stations[i].getId();

            if (this.routeDict.containsKey(key))
            {
                // valid path; add the distance
                result += this.routeDict.get(key);
            }
            else
            {
                // invalid path; set the distance to Infinity
                result = Double.POSITIVE_INFINITY;
                return result;
            }

        }

        return result;
    }

    /**
     * Get all the trips between 2 stations with constraints on maximum stops between them or an exact number of stops
     * between them. The constraints are mutually exclusive i.e., either maximum stops or exact number of stops need to
     * fixed but not both. In case where both are supplied, maximum stops is considered and exact number of stops is
     * ignored.
     * 
     * @param src source station for the paths
     * @param dst destination station for the paths
     * @param maxStops maximum number of stops that can occur in the path
     * @param exactStops exact number of stops that need to occur in the path
     * @return a set of trips indicating the possible valid paths based on the constraints; null if the conditions
     *         cannot be met
     */
    public Set<Trip> getAllTrips(Station src, Station dst, int maxStops, int exactStops)
    {
        String key = src.getId() + "-" + dst.getId();

        if (tripDict.containsKey(key))
        {
            TreeSet<Trip> trips = new TreeSet<Trip>(new TripDistanceComparator());

            // check if max stops has been set
            if (maxStops > -1)
            {
                for (Trip trip : this.tripDict.get(key))
                {
                    if (trip.getHopCount() - 1 <= maxStops)
                    {
                        trips.add(trip);
                    }
                }
            }
            // check if exact stops has been set
            else if (exactStops > -1)
            {
                for (Trip trip : this.tripDict.get(key))
                {
                    // Subtract 1 as the hop to destination is also counted
                    if (trip.getHopCount() - 1 == exactStops)
                    {
                        trips.add(trip);
                    }
                }
            }
            // no options set, return all trips
            else
            {
                for (Trip trip : this.tripDict.get(key))
                {
                    trips.add(trip);
                }
            }

            return trips;
        }

        return null;
    }

    /**
     * Looks up and returns the shortest path between two stations from the trips dictionary.
     * 
     * @param src source station
     * @param dst destination station
     * @return the shortest path between two stations (empty Trip if src and dst are the same); null if a path does not
     *         exist
     */
    public Trip getShortestRoute(Station src, Station dst)
    {
        if (src.equals(dst)) return new Trip(); // no self loops

        String key = src.getId() + "-" + dst.getId();

        if (tripDict.containsKey(key))
        {
            TreeSet<Trip> trips = (TreeSet<Trip>) this.tripDict.get(key);

            return trips.first();
        }

        return null;
    }

    /**
     * @return the graph
     */
    public Graph getGraph()
    {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(Graph graph)
    {
        this.graph = graph;
        this.init();
    }

}
