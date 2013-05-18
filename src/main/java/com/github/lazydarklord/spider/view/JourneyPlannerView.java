package com.github.lazydarklord.spider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.github.lazydarklord.spider.model.Graph;
import com.github.lazydarklord.spider.model.GraphManager;
import com.github.lazydarklord.spider.model.JourneyPlanner;
import com.github.lazydarklord.spider.model.Route;
import com.github.lazydarklord.spider.model.Station;
import com.github.lazydarklord.spider.model.Trip;

/**
 * The Journey Planner view class provides user with the following functionality 1. Load a network from file 2.
 * Calculate the distance of a particular path 3. Find all possible paths between stations (with constraints) 4. Find
 * the shortest path between 2 stations
 */
public class JourneyPlannerView
{

    /** The frame. */
    private JFrame frame;

    /** The content panel. */
    private JPanel contentPanel;

    /** The status panel. */
    private JPanel statusPanel;

    /** Label to display calculate distance option. */
    private JLabel lblCalculateDistance;

    /** Input box for calculating distance between multiple stations. */
    private JTextField txtDistance;

    /** Button to start calculating distance between multiple stations. */
    private JButton btnCalculate;

    /** Label to hold result of calculating distance between multiple stations. */
    private JLabel lblResultCalculateDistance;

    /** Input box for shortest distance between 2 stations. */
    private JTextField txtShortestDistance;

    /** Label to display shortest route option. */
    private JLabel lblShortestRoute;

    /** Button to get shortest distance between 2 stations. */
    private JButton btnGetShortest;

    /** Label to hold result of shortest distance between 2 stations. */
    private JLabel lblResultShortestRoute;

    /** Label to display all trips option. */
    private JLabel lblAllTrips;

    /** Input box for all trips between 2 stations. */
    private JTextField txtAllTrips;

    /** Button to get all trips between 2 stations. */
    private JButton btnGetAllTrips;

    /** Label to hold result of all trips between 2 stations. */
    private JLabel lblResultAllTrips;

    /** The menu bar. */
    private JMenuBar menuBar;

    /** File menu. */
    private JMenu mnFile;

    /** Load menu item. */
    private JMenuItem mntmLoad;

    /** Quit menu item. */
    private JMenuItem mntmQuit;

    /** The file chooser. */
    private JFileChooser fileChooser;

    /** The status label. */
    private JLabel statusLabel;

    /** JourneyPlanner object from model. */
    JourneyPlanner jp;

    /**
     * Launch the application.
     * 
     * @param args the arguments
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JourneyPlanner jp;

                Graph graph;

                String[] routesText =
                    {"AB12", "AD19", "AE20", "AG16", "BC5", "BD13", "BI15", "CD5", "DE7", "EF5", "FA5", "GF11", "HA4",
                    "HB19", "HG6", "IJ10", "IH21", "JB7", "JC15"};

                GraphManager gm = new GraphManager();
                gm.loadGraph(routesText);

                graph = gm.getGraph();

                jp = new JourneyPlanner(graph);

                try
                {
                    JourneyPlannerView window = new JourneyPlannerView(jp);
                    window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the view.
     */
    public JourneyPlannerView()
    {
        initialize();
    }

    /**
     * Instantiates a new journey planner view.
     * 
     * @param jp the jp
     */
    public JourneyPlannerView(JourneyPlanner jp)
    {
        this.jp = jp;
        initialize();
    }

    /**
     * Initialize the contents of the frame. Create a frame with a content panel and status panel. Content panel uses
     * GridBagLayout for arranging components.
     */
    private void initialize()
    {
        // frame settings
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // GridBagLayout for conentPanel
        contentPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {0, 0, 0};
        gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[] {1.0, 1.0, 1.0};
        gridBagLayout.rowWeights = new double[] {1.0, 0.25, 1.0, 0.25, 1.0, 0.25, 1.0, 0.25, 4.0};
        contentPanel.setLayout(gridBagLayout);
        frame.getContentPane().add(contentPanel);

        // Journey Panel heading label
        JLabel lblJourneyPlanner = new JLabel("Journey Planner");
        GridBagConstraints gbc_lblJourneyPlanner = new GridBagConstraints();
        gbc_lblJourneyPlanner.anchor = GridBagConstraints.WEST;
        gbc_lblJourneyPlanner.insets = new Insets(0, 0, 5, 5);
        gbc_lblJourneyPlanner.gridx = 0;
        gbc_lblJourneyPlanner.gridy = 0;
        contentPanel.add(lblJourneyPlanner, gbc_lblJourneyPlanner);

        // Calculate Distance option label
        lblCalculateDistance = new JLabel("Calculate Distance");
        GridBagConstraints gbc_lblCalculateDistance = new GridBagConstraints();
        gbc_lblCalculateDistance.insets = new Insets(0, 0, 5, 5);
        gbc_lblCalculateDistance.anchor = GridBagConstraints.WEST;
        gbc_lblCalculateDistance.gridx = 0;
        gbc_lblCalculateDistance.gridy = 2;
        contentPanel.add(lblCalculateDistance, gbc_lblCalculateDistance);

        // Calculate Distance input
        txtDistance = new JTextField();
        GridBagConstraints gbc_txtDistance = new GridBagConstraints();
        gbc_txtDistance.insets = new Insets(0, 0, 5, 5);
        gbc_txtDistance.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDistance.gridx = 1;
        gbc_txtDistance.gridy = 2;
        contentPanel.add(txtDistance, gbc_txtDistance);
        txtDistance.setColumns(10);

        // Calculate Distance button and action handler
        btnCalculate = new JButton("Get Distance");
        btnCalculate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                hndlCalculateDistance();
            }
        });
        GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
        gbc_btnCalculate.insets = new Insets(0, 0, 5, 5);
        gbc_btnCalculate.gridx = 2;
        gbc_btnCalculate.gridy = 2;
        contentPanel.add(btnCalculate, gbc_btnCalculate);

        // Calculate Distance result label
        lblResultCalculateDistance = new JLabel();
        GridBagConstraints gbc_lblResultCalculateDistance = new GridBagConstraints();
        gbc_lblResultCalculateDistance.anchor = GridBagConstraints.WEST;
        gbc_lblResultCalculateDistance.insets = new Insets(0, 0, 5, 5);
        gbc_lblResultCalculateDistance.gridx = 1;
        gbc_lblResultCalculateDistance.gridy = 3;
        contentPanel.add(lblResultCalculateDistance, gbc_lblResultCalculateDistance);

        // Shortest Distance option label
        lblShortestRoute = new JLabel("Shortest Route");
        GridBagConstraints gbc_lblShortestRoute = new GridBagConstraints();
        gbc_lblShortestRoute.insets = new Insets(0, 0, 5, 5);
        gbc_lblShortestRoute.anchor = GridBagConstraints.WEST;
        gbc_lblShortestRoute.gridx = 0;
        gbc_lblShortestRoute.gridy = 4;
        contentPanel.add(lblShortestRoute, gbc_lblShortestRoute);

        // Shortest Distance input
        txtShortestDistance = new JTextField();
        GridBagConstraints gbc_txtShortestDistance = new GridBagConstraints();
        gbc_txtShortestDistance.insets = new Insets(0, 0, 5, 5);
        gbc_txtShortestDistance.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtShortestDistance.gridx = 1;
        gbc_txtShortestDistance.gridy = 4;
        contentPanel.add(txtShortestDistance, gbc_txtShortestDistance);
        txtShortestDistance.setColumns(10);

        // Shortest Distance button and action handler
        btnGetShortest = new JButton("Get Shortest");
        btnGetShortest.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                hndlShortestRoute();
            }
        });
        GridBagConstraints gbc_btnGetShortest = new GridBagConstraints();
        gbc_btnGetShortest.insets = new Insets(0, 0, 5, 5);
        gbc_btnGetShortest.gridx = 2;
        gbc_btnGetShortest.gridy = 4;
        contentPanel.add(btnGetShortest, gbc_btnGetShortest);

        // Shortest Distance result label
        lblResultShortestRoute = new JLabel();
        GridBagConstraints gbc_lblResultShortestRoute = new GridBagConstraints();
        gbc_lblResultShortestRoute.anchor = GridBagConstraints.WEST;
        gbc_lblResultShortestRoute.insets = new Insets(0, 0, 5, 5);
        gbc_lblResultShortestRoute.gridx = 1;
        gbc_lblResultShortestRoute.gridy = 5;
        gbc_lblResultShortestRoute.gridwidth = 2;
        contentPanel.add(lblResultShortestRoute, gbc_lblResultShortestRoute);

        // All Trips option label
        lblAllTrips = new JLabel("All Trips");
        GridBagConstraints gbc_lblAllTrips = new GridBagConstraints();
        gbc_lblAllTrips.anchor = GridBagConstraints.WEST;
        gbc_lblAllTrips.insets = new Insets(0, 0, 5, 5);
        gbc_lblAllTrips.gridx = 0;
        gbc_lblAllTrips.gridy = 6;
        contentPanel.add(lblAllTrips, gbc_lblAllTrips);

        // All Trips input
        txtAllTrips = new JTextField();
        GridBagConstraints gbc_txtAllTrips = new GridBagConstraints();
        gbc_txtAllTrips.insets = new Insets(0, 0, 5, 5);
        gbc_txtAllTrips.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtAllTrips.gridx = 1;
        gbc_txtAllTrips.gridy = 6;
        contentPanel.add(txtAllTrips, gbc_txtAllTrips);
        txtAllTrips.setColumns(10);

        // All Trips button and action handler
        btnGetAllTrips = new JButton("Get All Trips");
        btnGetAllTrips.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                hndlAllTrips();
            }
        });
        GridBagConstraints gbc_btnGetAllTrips = new GridBagConstraints();
        gbc_btnGetAllTrips.insets = new Insets(0, 0, 5, 5);
        gbc_btnGetAllTrips.gridx = 2;
        gbc_btnGetAllTrips.gridy = 6;
        contentPanel.add(btnGetAllTrips, gbc_btnGetAllTrips);

        // All Trips result label
        lblResultAllTrips = new JLabel();
        GridBagConstraints gbc_lblResultAllTrips = new GridBagConstraints();
        gbc_lblResultAllTrips.anchor = GridBagConstraints.WEST;
        gbc_lblResultAllTrips.insets = new Insets(0, 0, 0, 5);
        gbc_lblResultAllTrips.gridx = 1;
        gbc_lblResultAllTrips.gridy = 7;
        gbc_lblResultAllTrips.gridwidth = 2;
        contentPanel.add(lblResultAllTrips, gbc_lblResultAllTrips);

        // Menu bar for the view
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // "File" menu
        mnFile = new JMenu("File");
        menuBar.add(mnFile);

        // "Load from file" menu item
        mntmLoad = new JMenuItem("Load from file");
        mntmLoad.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                hndlLoadFile();
            }
        });
        mnFile.add(mntmLoad);

        // "Quit" menu item
        mntmQuit = new JMenuItem("Quit");
        mntmQuit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        mnFile.add(mntmQuit);

        // Status panel
        statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        // Label to hold status messages
        statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
        statusLabel.setText("Ready");
        frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);
    }

    /**
     * Handler for "Load from file" menu item.
     */
    private void hndlLoadFile()
    {
        String filename;
        String dir;
        fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            filename = fileChooser.getSelectedFile().getName();
            dir = fileChooser.getCurrentDirectory().toString();
            lblResultCalculateDistance.setText(dir + filename);
            statusLabel.setText("Loaded from file: " + dir + filename);
        }
    }

    /**
     * Handler to call functions from model to calculate distance between multiple stations in a path.
     */
    private void hndlCalculateDistance()
    {
        String input = txtDistance.getText();
        StringTokenizer strtok = new StringTokenizer(input, "-");

        List<Station> stations = new LinkedList<Station>();

        // Break input into multiple stations using a string tokenizer. Each token is a station in the path.
        while (strtok.hasMoreTokens())
        {
            String stationId = strtok.nextToken();
            Station newStation = new Station(stationId);
            stations.add(newStation);
        }

        // Calculate distance by calling method from model
        Double distance = jp.calculateDistance(stations.toArray(new Station[0]));
        String result;

        if (distance == Double.POSITIVE_INFINITY)
        {
            result = "Invalid Path";
        }
        else
        {
            result = distance.toString();
        }

        // Update result in view
        lblResultCalculateDistance.setText("Result: " + result);
    }

    /**
     * Handler to call functions from model to get all trips between 2 stations.
     */
    private void hndlAllTrips()
    {
        String input = txtAllTrips.getText();
        StringTokenizer strtok = new StringTokenizer(input, " to,");

        List<Station> stations = new LinkedList<Station>();
        // Break input into multiple stations using a string tokenizer.
        // Format is "<station> to <station> <max stops> <exact stops> 
        stations.add(new Station(strtok.nextToken()));
        stations.add(new Station(strtok.nextToken()));
        int maxStops = Integer.parseInt(strtok.nextToken());
        int exactStops = Integer.parseInt(strtok.nextToken());

        // Get all trips by calling method from model
        Set<Trip> trips = jp.getAllTrips(stations.get(0), stations.get(1), maxStops, exactStops);
        String result;

        // Update result in the view
        if (trips == null)
        {
            result = "Invalid Path";
        }
        else
        {
            result = "<html>Trips";

            int tripCounter = 0;
            for (Trip trip : trips.toArray(new Trip[0]))
            {
                tripCounter++;
                result += "<br/>" + tripCounter + ": ";
                int hopCounter = 0;
                for (Route hop : trip.getHops())
                {
                    if (hopCounter == 0)
                        result += hop.getSource() + "->" + hop.getDestination();
                    else
                        result += "->" + hop.getDestination();
                    hopCounter++;
                }
                result += " Dist: " + trip.getDistance();
            }

            result += "</html>";
        }
        lblResultAllTrips.setText(result);
    }

    /**
     * Handler to call functions from model to get the shortest trips between 2 stations.
     */
    private void hndlShortestRoute()
    {
        String input = txtShortestDistance.getText();
        StringTokenizer strtok = new StringTokenizer(input, " to");

        List<Station> stations = new LinkedList<Station>();
        // Break input into multiple stations using a string tokenizer. Each token is a station.
        stations.add(new Station(strtok.nextToken()));
        stations.add(new Station(strtok.nextToken()));

        // Get the shortest path by calling method from model
        Trip trip = jp.getShortestRoute(stations.get(0), stations.get(1));
        String result;

        // Update result in view
        if (trip == null)
        {
            result = "Invalid Path";
        }
        else
        {
            result = "Distance: " + trip.getDistance();
        }
        lblResultShortestRoute.setText("Result: " + result);
    }

    /**
     * @return the frame
     */
    public JFrame getFrame()
    {
        return frame;
    }
}
