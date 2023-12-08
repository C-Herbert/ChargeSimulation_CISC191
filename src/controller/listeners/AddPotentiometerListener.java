package controller.listeners;

import java.awt.event.ActionEvent;

import model.ChargeGraph2D;
import model.DraggableFieldArrow;
import model.tools.PotentiometerGraphTool;
import view.ProgramView;

/**
 * AddPotentiometerListener is a GraphActionListener responsible for handling
 * potentiometer tool creation for the graph.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public class AddPotentiometerListener extends GraphActionListener
{
	/**
	 * Creates a new AddPotentiometerListener, which handles potentiometer
	 * creation.
	 * 
	 * @param graph the graph to assign to this GraphActionListener.
	 * @param view  the graph to assign to this GraphActionListener.
	 */
	public AddPotentiometerListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		graph.addElement(new PotentiometerGraphTool(200, 200));
		view.repaintGraph();
	}

}
