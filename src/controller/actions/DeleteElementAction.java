package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.List;

import model.ChargeGraph2D;
import model.IGraphElement;
import view.ProgramView;

/**
 * The DeleteElementAction class is a GraphAction used to delete a graph element
 * under the mouse when the user enters the bound input.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

@SuppressWarnings("serial")
public class DeleteElementAction extends GraphAction
{
	/**
	 * Constructs a new DeleteElementAction using the provided graph and view
	 * parameters.
	 * 
	 * @param graph The ChargeGraph2D to assign to this DeleteElementAction.
	 * @param view  The ProgramView to assign to this DeleteElementAction.
	 */
	public DeleteElementAction(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Get the user's mouse position.
		Point mousePos = view.getGraphView().getMousePosition();

		if (mousePos != null)
		{
			// If the mouse is on the graph, collect a list of valid targets.
			List<IGraphElement> potentialTargets = view.getGraphView()
					.getInteractablesAtPoint(mousePos.getX(), mousePos.getY());

			if (potentialTargets.size() > 0)
			{
				// If we have at least one target, select the first.
				graph.removeElement(potentialTargets.get(0));
				// Update the graph.
				graph.updateFieldArrows();
				view.repaintGraph();
			}
		}
	}

}
