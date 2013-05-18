package com.github.lazydarklord.spider.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.lazydarklord.spider.model.Graph;
import com.github.lazydarklord.spider.model.GraphManager;
import com.github.lazydarklord.spider.model.JourneyPlanner;
import com.github.lazydarklord.spider.model.Station;
import com.github.lazydarklord.spider.model.Trip;

/**
 * 
 */
public class JourneyPlannerTest
{
    JourneyPlanner jp;

    Graph graph;

    Station A;
    Station B;
    Station C;
    Station D;
    Station E;
    Station F;
    Station G;
    Station H;
    Station I;
    Station J;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        String[] routesText =
            {"AB12", "AD19", "AE20", "AG16", "BC5", "BD13", "BI15", "CD5", "DE7", "EF5", "FA5", "GF11", "HA4", "HB19",
            "HG6", "IJ10", "IH21", "JB7", "JC15"};

        GraphManager gm = new GraphManager();
        gm.loadGraph(routesText);

        this.graph = gm.getGraph();

        this.jp = new JourneyPlanner(graph);

        this.A = this.graph.findStation("A");
        this.B = this.graph.findStation("B");
        this.C = this.graph.findStation("C");
        this.D = this.graph.findStation("D");
        this.E = this.graph.findStation("E");
        this.F = this.graph.findStation("F");
        this.G = this.graph.findStation("G");
        this.H = this.graph.findStation("H");
        this.I = this.graph.findStation("I");
        this.J = this.graph.findStation("J");
        
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
    }

    /**
     * Test method for
     * {@link com.github.lazydarklord.spider.model.JourneyPlanner#calculateDistance(com.github.lazydarklord.spider.model.Station[])}
     * .
     */
    @Test
    public void testCalculateDistance()
    {
        Station[] validStations = {this.A, this.B, this.C, this.D};

        Station[] inValidStations = {this.A, this.B, this.J, this.C};

        assertEquals(22.0, this.jp.calculateDistance(validStations), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, this.jp.calculateDistance(inValidStations), 0.0);
    }

    /**
     * Test method for
     * {@link com.github.lazydarklord.spider.model.JourneyPlanner#getAllTrips(com.github.lazydarklord.spider.model.Station, com.github.lazydarklord.spider.model.Station, int, int)}
     * .
     */
    @Test
    public void testGetAllTrips()
    {
        assertEquals(1, this.jp.getAllTrips(this.A, this.C, -1, 3).size());
        assertEquals(1, this.jp.getAllTrips(this.A, this.C, 1, -1).size());
        assertEquals(2, this.jp.getAllTrips(this.A, this.C, 3, -1).size());
        assertEquals(2, this.jp.getAllTrips(this.A, this.C, 3, 1).size());
        assertEquals(2, this.jp.getAllTrips(this.A, this.C, -1, -1).size());        
    }

    /**
     * Test method for
     * {@link com.github.lazydarklord.spider.model.JourneyPlanner#getShortestRoute(com.github.lazydarklord.spider.model.Station, com.github.lazydarklord.spider.model.Station)}
     * .
     */
    @Test
    public void testGetShortestRoute()
    {
        // Values generated using Floyd-Warshalls algorithm
        
//              A       B       C       D       E       F       G       H       I       J
//        A : 0.00    12.00   17.00   19.00   20.00   25.00   16.00   48.00   27.00   37.00
//        B : 27.00   0.00    5.00    10.00   17.00   22.00   42.00   36.00   15.00   25.00
//        C : 22.00   34.00   0.00    5.00    12.00   17.00   38.00   70.00   49.00   59.00
//        D : 17.00   29.00   34.00   0.00    7.00    12.00   33.00   65.00   44.00   54.00
//        E : 10.00   22.00   27.00   29.00   0.00    5.00    26.00   58.00   37.00   47.00
//        F : 5.00    17.00   22.00   24.00   25.00   0.00    21.00   53.00   32.00   42.00
//        G : 16.00   28.00   33.00   35.00   36.00   11.00   0.00    64.00   43.00   53.00
//        H : 4.00    16.00   21.00   23.00   24.00   17.00   6.00    0.00    31.00   41.00
//        I : 25.00   17.00   22.00   27.00   34.00   38.00   27.00   21.00   0.00    10.00
//        J : 34.00   7.00    12.00   17.00   24.00   29.00   49.00   43.00   22.00   0.00 
        
        assertEquals(37.0, this.jp.getShortestRoute(this.A, this.J).getDistance(), 0.0);
        assertEquals(34.0, this.jp.getShortestRoute(this.C, this.B).getDistance(), 0.0);
        assertEquals(53.0, this.jp.getShortestRoute(this.G, this.J).getDistance(), 0.0);
        assertEquals(44.0, this.jp.getShortestRoute(this.D, this.I).getDistance(), 0.0);
        assertEquals(00.0, this.jp.getShortestRoute(this.A, this.A).getDistance(), 0.0);
    }

}
