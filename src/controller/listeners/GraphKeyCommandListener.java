package controller.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.ChargeGraph2D;
import view.ProgramView;

public abstract class GraphKeyCommandListener implements KeyListener
{
	protected ChargeGraph2D graph;
	protected ProgramView view;

	public GraphKeyCommandListener(ChargeGraph2D graph, ProgramView view)
	{
		this.graph = graph;
		this.view = view;
	}

}
