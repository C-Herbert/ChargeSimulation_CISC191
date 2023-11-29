package controller.listeners;

import java.awt.event.MouseListener;

import model.ChargeGraph2D;
import model.Graph2D;
import view.ProgramView;

/**
 * GraphMouseListener is used to relate similar MouseListener subclasses
 * that manage inputs on a ChargeGraph2D. It includes 2 fields, one for a
 * ChargeGraph2D and another for a ProgramView.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public abstract class GraphMouseListener implements MouseListener
{
	// GraphActionListener has a graph.
	protected ChargeGraph2D graph;
	// GraphActionListener has a program view.
	protected ProgramView view;

	/**
	 * Creates a new GraphMouseListener using the provided graph and view
	 * objects.
	 * 
	 * @param graph The graph to assign to this listener.
	 * @param view  The view to assign to this listener.
	 */
	public GraphMouseListener(ChargeGraph2D graph, ProgramView view)
	{
		this.graph = graph;
		this.view = view;
	}
}
