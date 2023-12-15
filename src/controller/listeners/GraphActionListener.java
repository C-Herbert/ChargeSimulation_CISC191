package controller.listeners;

import java.awt.event.ActionListener;

import model.ChargeGraph2D;
import view.ProgramView;

/**
 * GraphActionListener is used to relate similar ActionListener subclasses
 * that manage inputs on a ChargeGraph2D. It includes 2 fields, one for a
 * ChargeGraph2D and another for a ProgramView.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public abstract class GraphActionListener implements ActionListener
{
	/**
	 * GraphActionListener has an associated graph field.
	 */
	protected ChargeGraph2D graph;
	/**
	 * GraphActionListener has an associated program view field.
	 */
	protected ProgramView view;

	/**
	 * Creates a new GraphActionListener using the provided graph and view
	 * objects.
	 * 
	 * @param graph The graph to assign to this listener.
	 * @param view  The view to assign to this listener.
	 */
	public GraphActionListener(ChargeGraph2D graph, ProgramView view)
	{
		this.graph = graph;
		this.view = view;
	}
}
