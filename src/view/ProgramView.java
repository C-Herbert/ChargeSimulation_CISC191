package view;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.ChargeGraph2D;

/**
 * The ProgramView class is responsible for managing all view components of the
 * program. All controller/view interactions should be done through an instance
 * of this class.
 * 
 * References:
 * 1. Morelli, Ralph. Java, Java, Java. 3rd ed., Prentice Hall, 2006.
 * 2. “How to Use Key Bindings.” Oracle Java Documentation,
 * https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html.
 * Accessed 8 Dec. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0a
 */

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
		toolbar = new ToolbarView();
		mainPanel.add(toolbar, BorderLayout.NORTH);

		// Create the side toolbox
		toolbox = new ToolboxView();
		mainPanel.add(toolbox, BorderLayout.EAST);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
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

	// ProgramView has a toolbar
	private ToolbarView toolbar;

	/**
	 * Gets the ToolbarView associated with this ProgramView.
	 * 
	 * @return the ToolbarView associated with this ProgramView.
	 */
	public ToolbarView getToolbar()
	{
		return toolbar;
	}

	// ProgramView has a toolbox
	private ToolboxView toolbox;

	/**
	 * Gets the ToolboxView associated with this ProgramView.
	 * 
	 * @return the ToolboxView associated with this ProgramView.
	 */
	public ToolboxView getToolbox()
	{
		return toolbox;
	}

	// ProgramView has a GraphView
	private ChargeGraphView graphView;

	/**
	 * Gets the graph view managed by this program.
	 * 
	 * @return the ChargeGraphView managed by this program.
	 */
	public ChargeGraphView getGraphView()
	{
		return graphView;
	}

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
	 * Adds a key binding to this view's associated graph. Note that binding
	 * created through this function are always active.
	 * 
	 * @param input      The KeyStroke to bind the action to.
	 * @param actionName The internal name of the action.
	 * @param action     The action to execute when the binding is triggered.
	 */
	public void addGraphKeyBinding(KeyStroke input, String actionName,
			Action action)
	{
		graphView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(input,
				actionName);
		graphView.getActionMap().put(actionName, action);
	}
}
