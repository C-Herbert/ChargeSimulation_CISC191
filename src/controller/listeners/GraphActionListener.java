package controller.listeners;

import java.awt.event.ActionListener;

import model.Graph2D;
import view.ProgramView;

/**
 * GraphActionListener is used to relate similar ActionListener subclasses
 * throughout this program. It includes 2 fields, one for a Graph2D and another
 * for a ProgramView.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public abstract class GraphActionListener implements ActionListener
{
	// GraphActionListener has a graph.
	protected Graph2D graph;
	// GraphActionListener has a program view.
	protected ProgramView view;

	public GraphActionListener(Graph2D graph, ProgramView view)
	{
		this.graph = graph;
		this.view = view;
	}
}
