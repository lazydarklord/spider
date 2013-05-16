package com.github.lazydarklord.spider.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.lazydarklord.spider.model.Graph;
import com.github.lazydarklord.spider.model.GraphManager;
import com.github.lazydarklord.spider.model.Route;
import com.github.lazydarklord.spider.model.Station;

/**
 * 
 */
public class GraphTest
{
    Graph graph;

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
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
        this.graph = null;
    }

    /**
     * Test method for
     * {@link com.github.lazydarklord.spider.model.Graph#addStation(com.github.lazydarklord.spider.model.Station)}.
     */
    @Test
    public void testAddStation()
    {
        int stationsCount = graph.getStationsCount();
        Station station = new Station("testId");
        this.graph.addStation(station);
        assertEquals(stationsCount+1, this.graph.getStationsCount());
    }

    /**
     * Test method for
     * {@link com.github.lazydarklord.spider.model.Graph#addRoute(com.github.lazydarklord.spider.model.Route)}.
     */
    @Test
    public void testAddRoute()
    {
        int routesCount = graph.getRoutesCount();
        Station srcStation = new Station("testSrcId");
        Station dstStation = new Station("testDstId");
        this.graph.addStation(srcStation);
        this.graph.addStation(dstStation);
        Route route = new Route(srcStation, dstStation);
        this.graph.addRoute(route);
        
        assertEquals(routesCount+1, this.graph.getRoutesCount());
    }

    /**
     * Test method for {@link com.github.lazydarklord.spider.model.Graph#findStation(java.lang.String)}.
     */
    @Test
    public void testFindStation()
    {
        assertEquals(null, this.graph.findStation("unknown"));
        
        Station station = new Station("A");        
        assertEquals(station, this.graph.findStation("A"));        
    }

    /**
     * Test method for
     * {@link com.github.lazydarklord.spider.model.Graph#getOutboundRoutes(com.github.lazydarklord.spider.model.Station)}
     * .
     */
    @Test
    public void testGetOutboundRoutes()
    {
        assertEquals(2, this.graph.getInboundRoutes(this.graph.findStation("A")).size());
    }

    /**
     * Test method for
     * {@link com.github.lazydarklord.spider.model.Graph#getInboundRoutes(com.github.lazydarklord.spider.model.Station)}
     * .
     */
    @Test
    public void testGetInboundRoutes()
    {
        assertEquals(4, this.graph.getOutboundRoutes(this.graph.findStation("A")).size());
    }

}
