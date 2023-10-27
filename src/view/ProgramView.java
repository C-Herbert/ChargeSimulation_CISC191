package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ChargeGraph2D;

public class ProgramView
{
	JFrame mainFrame;
	//ProgramView has a panel to hold the model display
	JPanel mainPanel;

	public ProgramView()
	{
		mainFrame = new JFrame("CISC_191 Charge Project");

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainFrame.add(mainPanel);

		createToolbar();
		mainPanel.add(toolbarPanel, BorderLayout.NORTH);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	//ProgramView has a panel to hold toolbar elements
	JPanel toolbarPanel;

	private void createToolbar()
	{
		toolbarPanel = new JPanel();
		toolbarPanel.setPreferredSize(null);

		// Setup panel's layout
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		layout.setHgap(0);
		layout.setVgap(0);
		toolbarPanel.setLayout(layout);

		// Temporary example buttons to match diagram
		toolbarPanel.add(new JButton("File"));
		toolbarPanel.add(new JButton("Board"));
		toolbarPanel.add(new JButton("Settings"));

		// Blend in with the window's own toolbar
		toolbarPanel.setBackground(Color.WHITE);
	}

	//ProgramView has a GraphView 
	ChargeGraphView graphView;

	public void initializeChargeBoard(ChargeGraph2D model)
	{
		graphView = new ChargeGraphView(model);
		mainPanel.add(graphView, BorderLayout.CENTER);
		mainFrame.pack();
	}
	
	public void repaintGraph()
	{
		graphView.repaint();
	}
	
	public void addGraphMouseListener(MouseListener listener)
	{
		graphView.addMouseListener(listener);
	}

}
