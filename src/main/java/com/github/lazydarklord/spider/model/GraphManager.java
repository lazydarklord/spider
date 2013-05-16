package com.github.lazydarklord.spider.model;


/**
 * Graph Manager
 */
public class GraphManager
{
    Graph graph;

    public GraphManager()
    {
        graph = new Graph();
    }

    public void loadGraph(String[] routesText)
    {
        for (String routeText : routesText)
        {
            String srcId = String.valueOf(routeText.charAt(0));
            String dstId = String.valueOf(routeText.charAt(1));
            Double distance = Double.parseDouble(routeText.substring(2));

            if (graph.findStation(srcId) == null) graph.addStation(new Station(srcId));
            if (graph.findStation(dstId) == null) graph.addStation(new Station(dstId));
            if(graph.findRoute(srcId, dstId) == null)
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
        
        //System.out.println("Graph.......................\n"+graph);
    }

    /**
     * @return the graph
     */
    public Graph getGraph()
    {
        return graph;
    }
    
    
}
