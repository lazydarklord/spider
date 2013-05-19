package com.github.lazydarklord.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.github.lazydarklord.spider.config.ConfigManager;
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
        ConfigManager cm = ConfigManager.getInstance();

        // Load default routes from config manager
        String defaultRoutesText = cm.getConfig("default-routes-short");
        StringTokenizer strtok = new StringTokenizer(defaultRoutesText, cm.getConfig("default-delim"));
        List<String> defaultRoutesList = new ArrayList<String>();

        while (strtok.hasMoreTokens())
        {
            defaultRoutesList.add(strtok.nextToken());
        }
        String[] defaultRoutes = defaultRoutesList.toArray(new String[0]);

        // Create a Journey Planner model and initialize values with the default routes
        JourneyPlanner jp;
        Graph graph;
        GraphManager gm = new GraphManager();
        gm.loadGraph(defaultRoutes);
        graph = gm.getGraph();
        jp = new JourneyPlanner(graph);

        // Display the Journey Planner view
        JourneyPlannerView window = new JourneyPlannerView(jp);
        window.getFrame().setVisible(true);
    }

}
