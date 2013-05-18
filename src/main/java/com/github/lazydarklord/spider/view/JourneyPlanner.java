package com.github.lazydarklord.spider.view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class JourneyPlanner
{

    private JFrame frame;

    private JTextField txtDistance;

    private JButton btnCalculate;

    private JLabel lblResultCalculateDistance;

    private JTextField txtShortestDistance;

    private JButton btnGetShortest;

    private JLabel lblAllTrips;

    private JTextField txtAllTrips;

    private JLabel lblResultAllTrips;

    private JButton btnGetAllTrips;

    private JLabel lblResultShortestRoute;

    private JLabel lblCalculateDistance;

    private JLabel lblShortestRoute;

    private JMenuBar menuBar;

    private JMenu mnFile;

    private JMenuItem mntmLoad_1;

    private JMenuItem mntmQuit;

    private JFileChooser fileChooser;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    JourneyPlanner window = new JourneyPlanner();
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
     * Create the application.
     */
    public JourneyPlanner()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {0, 0, 0};
        gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[] {1.0, 1.0, 1.0};
        gridBagLayout.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        frame.getContentPane().setLayout(gridBagLayout);

        JLabel lblJourneyPlanner = new JLabel("Journey Planner");
        GridBagConstraints gbc_lblJourneyPlanner = new GridBagConstraints();
        gbc_lblJourneyPlanner.anchor = GridBagConstraints.WEST;
        gbc_lblJourneyPlanner.insets = new Insets(0, 0, 5, 5);
        gbc_lblJourneyPlanner.gridx = 0;
        gbc_lblJourneyPlanner.gridy = 0;
        frame.getContentPane().add(lblJourneyPlanner, gbc_lblJourneyPlanner);

        lblCalculateDistance = new JLabel("Calculate Distance");
        GridBagConstraints gbc_lblCalculateDistance = new GridBagConstraints();
        gbc_lblCalculateDistance.insets = new Insets(0, 0, 5, 5);
        gbc_lblCalculateDistance.anchor = GridBagConstraints.WEST;
        gbc_lblCalculateDistance.gridx = 0;
        gbc_lblCalculateDistance.gridy = 2;
        frame.getContentPane().add(lblCalculateDistance, gbc_lblCalculateDistance);

        txtDistance = new JTextField();
        GridBagConstraints gbc_txtDistance = new GridBagConstraints();
        gbc_txtDistance.insets = new Insets(0, 0, 5, 5);
        gbc_txtDistance.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDistance.gridx = 1;
        gbc_txtDistance.gridy = 2;
        frame.getContentPane().add(txtDistance, gbc_txtDistance);
        txtDistance.setColumns(10);

        btnCalculate = new JButton("Get Distance");
        btnCalculate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                lblResultCalculateDistance.setText(lblResultCalculateDistance.getText() + "10.0");
            }
        });
        GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
        gbc_btnCalculate.insets = new Insets(0, 0, 5, 5);
        gbc_btnCalculate.gridx = 2;
        gbc_btnCalculate.gridy = 2;
        frame.getContentPane().add(btnCalculate, gbc_btnCalculate);

        lblResultCalculateDistance = new JLabel("Distance: ");
        GridBagConstraints gbc_lblResultCalculateDistance = new GridBagConstraints();
        gbc_lblResultCalculateDistance.anchor = GridBagConstraints.WEST;
        gbc_lblResultCalculateDistance.insets = new Insets(0, 0, 5, 5);
        gbc_lblResultCalculateDistance.gridx = 1;
        gbc_lblResultCalculateDistance.gridy = 3;
        frame.getContentPane().add(lblResultCalculateDistance, gbc_lblResultCalculateDistance);

        lblShortestRoute = new JLabel("Shortest Route");
        GridBagConstraints gbc_lblShortestRoute = new GridBagConstraints();
        gbc_lblShortestRoute.insets = new Insets(0, 0, 5, 5);
        gbc_lblShortestRoute.anchor = GridBagConstraints.WEST;
        gbc_lblShortestRoute.gridx = 0;
        gbc_lblShortestRoute.gridy = 4;
        frame.getContentPane().add(lblShortestRoute, gbc_lblShortestRoute);

        txtShortestDistance = new JTextField();
        GridBagConstraints gbc_txtShortestDistance = new GridBagConstraints();
        gbc_txtShortestDistance.insets = new Insets(0, 0, 5, 5);
        gbc_txtShortestDistance.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtShortestDistance.gridx = 1;
        gbc_txtShortestDistance.gridy = 4;
        frame.getContentPane().add(txtShortestDistance, gbc_txtShortestDistance);
        txtShortestDistance.setColumns(10);

        btnGetShortest = new JButton("Get Shortest");
        btnGetShortest.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                lblResultShortestRoute.setText(lblResultShortestRoute.getText() + "10.0 : A->B->C");
            }
        });
        GridBagConstraints gbc_btnGetShortest = new GridBagConstraints();
        gbc_btnGetShortest.insets = new Insets(0, 0, 5, 5);
        gbc_btnGetShortest.gridx = 2;
        gbc_btnGetShortest.gridy = 4;
        frame.getContentPane().add(btnGetShortest, gbc_btnGetShortest);

        lblResultShortestRoute = new JLabel("Shortest Route");
        GridBagConstraints gbc_lblResultShortestRoute = new GridBagConstraints();
        gbc_lblResultShortestRoute.anchor = GridBagConstraints.WEST;
        gbc_lblResultShortestRoute.insets = new Insets(0, 0, 5, 5);
        gbc_lblResultShortestRoute.gridx = 1;
        gbc_lblResultShortestRoute.gridy = 5;
        frame.getContentPane().add(lblResultShortestRoute, gbc_lblResultShortestRoute);

        lblAllTrips = new JLabel("All Trips");
        GridBagConstraints gbc_lblAllTrips = new GridBagConstraints();
        gbc_lblAllTrips.anchor = GridBagConstraints.WEST;
        gbc_lblAllTrips.insets = new Insets(0, 0, 5, 5);
        gbc_lblAllTrips.gridx = 0;
        gbc_lblAllTrips.gridy = 6;
        frame.getContentPane().add(lblAllTrips, gbc_lblAllTrips);

        txtAllTrips = new JTextField();
        GridBagConstraints gbc_txtAllTrips = new GridBagConstraints();
        gbc_txtAllTrips.insets = new Insets(0, 0, 5, 5);
        gbc_txtAllTrips.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtAllTrips.gridx = 1;
        gbc_txtAllTrips.gridy = 6;
        frame.getContentPane().add(txtAllTrips, gbc_txtAllTrips);
        txtAllTrips.setColumns(10);

        btnGetAllTrips = new JButton("Get All Trips");
        btnGetAllTrips.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                lblResultAllTrips.setText("<html>" + lblResultAllTrips.getText()
                    + "<br/>1.A->B->C 10.0 <br/> 2.A->D->C 12.0" + "</html>");
            }
        });
        GridBagConstraints gbc_btnGetAllTrips = new GridBagConstraints();
        gbc_btnGetAllTrips.insets = new Insets(0, 0, 5, 5);
        gbc_btnGetAllTrips.gridx = 2;
        gbc_btnGetAllTrips.gridy = 6;
        frame.getContentPane().add(btnGetAllTrips, gbc_btnGetAllTrips);

        lblResultAllTrips = new JLabel("All Trips:");
        GridBagConstraints gbc_lblResultAllTrips = new GridBagConstraints();
        gbc_lblResultAllTrips.anchor = GridBagConstraints.WEST;
        gbc_lblResultAllTrips.insets = new Insets(0, 0, 0, 5);
        gbc_lblResultAllTrips.gridx = 1;
        gbc_lblResultAllTrips.gridy = 7;
        frame.getContentPane().add(lblResultAllTrips, gbc_lblResultAllTrips);

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnFile = new JMenu("File");
        menuBar.add(mnFile);

        mntmLoad_1 = new JMenuItem("Load from file");
        mntmLoad_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
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
                }
            }
        });
        mnFile.add(mntmLoad_1);

        mntmQuit = new JMenuItem("Quit");
        mntmQuit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        mnFile.add(mntmQuit);
    }
}
