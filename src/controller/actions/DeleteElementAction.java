package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.List;

import model.ChargeGraph2D;
import model.IGraphElement;
import view.ProgramView;

public class DeleteElementAction extends GraphAction
{
	public DeleteElementAction(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Point mousePos = view.getGraphView().getMousePosition();

		if (mousePos != null)
		{
			List<IGraphElement> potentialTargets = view.getGraphView()
					.getInteractablesAtPoint(mousePos.getX(), mousePos.getY());

			if (potentialTargets.size() > 0)
			{
				graph.removeElement(potentialTargets.get(0));

				view.repaintGraph();
			}
		}
	}

}
