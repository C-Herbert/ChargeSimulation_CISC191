package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ChargeGraph2D;

/**
 * The ProgramView class is responsible for managing all view components of the
 * program. All controller/view interactions should be done through an instance
 * of this class.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

// TODO: Java Java Java citation

public class ProgramView
{
	// ProgramView has a frame to hold the UI
	private JFrame mainFrame;
	// ProgramView has a panel to hold the model display
	private JPanel mainPanel;
	// ProgramView shares a file selector
	private JFileChooser fileChooser;

	public ProgramView()
	{
		// Initialize fields
		mainFrame = new JFrame("CISC_191 Charge Project");

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainFrame.add(mainPanel);

		// Initialize the fileChooser
		fileChooser = new JFileChooser();

		// Create the upper toolbar
		createToolbar();
		mainPanel.add(toolbarPanel, BorderLayout.NORTH);

		// Create the side toolbox
		toolbox = new ToolboxView();
		mainPanel.add(toolbox, BorderLayout.EAST);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	// ProgramView has a panel to hold toolbar elements
	private JPanel toolbarPanel;
	// ProgramView has buttons to control file io
	private JButton fileSaveButton;
	private JButton fileOpenButton;

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

		// Add toolbar buttons
		fileSaveButton = new JButton("Save File");
		toolbarPanel.add(fileSaveButton);

		fileOpenButton = new JButton("Open File");
		toolbarPanel.add(fileOpenButton);

		// Blend in with the window's own toolbar
		toolbarPanel.setBackground(Color.WHITE);
	}

	/**
	 * Returns the main frame of this view. Note that this function should only
	 * be used to assign parent components for dialog options. Do not modify the
	 * frame.
	 * 
	 * @return The JFrame representing the main frame of the program.
	 */
	public JFrame getFrame()
	{
		return mainFrame;
	}

	// ProgramView has a toolbox
	private ToolboxView toolbox;

	public ToolboxView getToolbox()
	{
		return toolbox;
	}

	// ProgramView has a GraphView
	ChargeGraphView graphView;

	/**
	 * Initializes the graph field of this view. Note that any existing graph
	 * will be overwritten.
	 * 
	 * @param model The graph to assign to this view.
	 */
	public void initializeGraph(ChargeGraph2D model)
	{
		graphView = new ChargeGraphView(model);
		mainPanel.add(graphView, BorderLayout.CENTER);
		mainFrame.pack();
	}

	/**
	 * Repaints the main graph associated with this view
	 */
	public void repaintGraph()
	{
		graphView.repaint();
	}

	/**
	 * Gets the file chooser associated with this view. Use this to maintain a
	 * consistent file path when requesting file selections.
	 * 
	 * @return The JFileChooser associated with this view.
	 */
	public JFileChooser getFileChooser()
	{
		return fileChooser;
	}

	/**
	 * Adds a mouse listener to this view's associated graph.
	 * 
	 * @param listener The listener to add.
	 */
	public void addGraphMouseListener(MouseListener listener)
	{
		graphView.addMouseListener(listener);
	}

	/**
	 * Adds an action listener to this view's associated save button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addFileSaveListener(ActionListener listener)
	{
		fileSaveButton.addActionListener(listener);
	}

	/**
	 * Adds an action listener to this view's associated open button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addFileOpenListener(ActionListener listener)
	{
		fileOpenButton.addActionListener(listener);
	}

}
