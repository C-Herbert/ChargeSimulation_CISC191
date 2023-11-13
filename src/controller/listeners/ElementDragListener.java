package controller.listeners;

import java.awt.event.MouseEvent;

import model.Graph2D;
import model.IGraphElement;
import view.ProgramView;

public class ElementDragListener extends GraphMouseListener
{
	private boolean isMouseInGraph = false;
	private IGraphElement draggingElement = null;

	public ElementDragListener(Graph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Unused by this listener
	}

	@Override
	public void mousePressed(MouseEvent e)
	{

		if (draggingElement != null) return;

		draggingElement = view.getGraphView().getInteractableAtPoint(e.getX(),
				e.getY());

		System.out.println(draggingElement);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (draggingElement != null)
		{
			draggingElement.setX(e.getX());
			draggingElement.setY(e.getY());
			view.repaintGraph();
		}

		draggingElement = null;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Update listener state
		isMouseInGraph = true;
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// Update listener state
		isMouseInGraph = false;
	}

}
