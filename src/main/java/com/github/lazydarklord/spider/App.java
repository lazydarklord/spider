package com.github.lazydarklord.spider;

import com.github.lazydarklord.spider.model.Graph;
import com.github.lazydarklord.spider.model.GraphManager;
import com.github.lazydarklord.spider.model.JourneyPlanner;
import com.github.lazydarklord.spider.view.JourneyPlannerView;

/**
 * Spider Test App
 */
public class App
{
    Graph graph;

    JourneyPlanner jp;

    public static void main(String[] args)
    {
        JourneyPlanner jp;

        Graph graph;

        String[] routesText =
            {"AB12", "AD19", "AE20", "AG16", "BC5", "BD13", "BI15", "CD5", "DE7", "EF5", "FA5", "GF11", "HA4", "HB19",
            "HG6", "IJ10", "IH21", "JB7", "JC15"};

        GraphManager gm = new GraphManager();
        gm.loadGraph(routesText);

        graph = gm.getGraph();

        jp = new JourneyPlanner(graph);

        JourneyPlannerView window = new JourneyPlannerView(jp);
        window.getFrame().setVisible(true);
    }

}
