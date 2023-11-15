package controller.listeners;

import java.awt.event.MouseListener;

import model.ChargeGraph2D;
import model.Graph2D;
import view.ProgramView;

public abstract class GraphMouseListener implements MouseListener
{
	//TODO: Same code as GraphActionListener, maybe replace with some form of inheritance?
	
	// GraphActionListener has a graph.
	protected ChargeGraph2D graph;
	// GraphActionListener has a program view.
	protected ProgramView view;

	public GraphMouseListener(ChargeGraph2D graph, ProgramView view)
	{
		this.graph = graph;
		this.view = view;
	}
}
