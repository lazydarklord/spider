package com.github.lazydarklord.spider;

import com.github.lazydarklord.spider.model.Graph;
import com.github.lazydarklord.spider.model.GraphManager;
import com.github.lazydarklord.spider.model.JourneyPlanner;
import com.github.lazydarklord.spider.model.Station;
import com.github.lazydarklord.spider.model.Trip;

/**
 * Spider Test App
 */
public class App
{
    Graph graph;

    JourneyPlanner jp;

    public static void main(String[] args)
    {
        App app = new App();
        // String[] routesText =
        // {"AB12", "AD19", "AE20", "AG16", "BC5", "BD13", "BI15", "CD5", "DE7", "EF5", "FA5", "GF11", "HA4", "HB19",
        // "HG6", "IJ10", "IH21", "JB7", "JC15"};
        //
        // GraphManager gm = new GraphManager();
        // gm.loadGraph(routesText);
        app.sample();
    }

    private void sample()
    {
        String[] routesText =
            {"AB12", "AD19", "AE20", "AG16", "BC5", "BD13", "BI15", "CD5", "DE7", "EF5", "FA5", "GF11", "HA4", "HB19",
            "HG6", "IJ10", "IH21", "JB7", "JC15"};

        String[] shortRoutesText = {"AB1", "AC1", "CB1", "BD1"};

        GraphManager gm = new GraphManager();
        gm.loadGraph(routesText);
        //gm.loadGraph(shortRoutesText);

        this.graph = gm.getGraph();

        this.jp = new JourneyPlanner(graph);
        // jp.init();
        Station[] validStations =
            {this.graph.findStation("A"), this.graph.findStation("B"), this.graph.findStation("C"),
            this.graph.findStation("D")};

        Station[] inValidStations =
            {this.graph.findStation("A"), this.graph.findStation("B"), this.graph.findStation("J"),
            this.graph.findStation("C")};

        System.out.println(this.jp.calculateDistance(validStations));

        Station A = this.graph.findStation("A");
        Station B = this.graph.findStation("B");
        Station J = this.graph.findStation("J");

        Trip result = this.jp.getShortestRoute(A, J);

        if (result != null)
            System.out.println(result.getDistance());
        else
            System.out.println("No path available");
    }
}
