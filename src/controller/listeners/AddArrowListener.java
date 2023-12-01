package controller.listeners;

import java.awt.event.ActionEvent;

import model.ChargeGraph2D;
import model.DraggableFieldArrow;
import view.ProgramView;

/**
 * AddArrowListener is a GraphActionListener responsible for handling
 * adding/creating draggable arrows to the graph.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public class AddArrowListener extends GraphActionListener
{
	/**
	 * Creates a new AddArrowListener, which handles arrow creation.
	 * 
	 * @param graph the graph to assign to this GraphActionListener.
	 * @param view  the graph to assign to this GraphActionListener.
	 */
	public AddArrowListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		graph.addElement(new DraggableFieldArrow(100, 100, 0, 1));

		graph.updateFieldArrows();
		view.repaintGraph();
	}

}
