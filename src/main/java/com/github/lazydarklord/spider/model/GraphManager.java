package com.github.lazydarklord.spider.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Graph Manager class provides functionality to load graphs from different sources.
 */
public class GraphManager
{
    Graph graph;

    /**
     * Create the GraphManager with a default graph object.
     */
    public GraphManager()
    {
        graph = new Graph();
    }

    /**
     * Load graph from an array of strings
     * 
     * @param routesText array of routes of the format <source-station><destination-station><distance>
     * @return true if graph loaded successfully, false otherwise
     */
    public boolean loadGraph(String[] routesText)
    {
        for (String routeText : routesText)
        {
            String srcId = String.valueOf(routeText.charAt(0));
            String dstId = String.valueOf(routeText.charAt(1));
            Double distance = Double.parseDouble(routeText.substring(2));

            if (graph.findStation(srcId) == null) graph.addStation(new Station(srcId));
            if (graph.findStation(dstId) == null) graph.addStation(new Station(dstId));
            if (graph.findRoute(srcId, dstId) == null)
            {
                Station src = graph.findStation(srcId);
                Station dst = graph.findStation(dstId);
                Route route = new Route();
                route.setSource(src);
                route.setDestination(dst);
                route.setDistance(distance);

                graph.addRoute(route);
            }
        }

        return true;
    }

    /**
     * Load graph from a file
     * 
     * @param file containing routes one per line in the format <source-station><destination-station><distance>
     * @return true if graph loaded successfully, false otherwise
     */
    public boolean loadGraph(File file)
    {
        List<String> routes = new ArrayList<String>();
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null)
            {
                routes.add(str);
            }
            in.close();
        }
        catch (IOException e)
        {
            System.out.println("Unable to load routes.");
            return false;
        }

        this.loadGraph(routes.toArray(new String[0]));
        return true;
    }

    /**
     * @return the graph
     */
    public Graph getGraph()
    {
        return graph;
    }
}
