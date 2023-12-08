package controller.listeners;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import model.ChargeGraph2D;
import model.Graph2D;
import model.IGraphElement;
import view.ProgramView;

public class DeleteElementListener extends GraphKeyCommandListener
{
	public DeleteElementListener(ChargeGraph2D graph, ProgramView view)
	{
		
		super(graph, view);
		System.out.println("Init");
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// Unused by this listener.
		System.out.println("Type");
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		System.out.println("Press");

		if (e.getKeyCode() == KeyEvent.VK_DELETE)
		{
			System.out.println("Delete");

			Point mousePos = view.getGraphView().getMousePosition();

			if (mousePos != null)
			{
				List<IGraphElement> potentialTargets = view.getGraphView()
						.getInteractablesAtPoint(mousePos.getX(),
								mousePos.getY());

				if (potentialTargets.size() > 0)
				{
					graph.removeElement(potentialTargets.get(0));

					view.repaintGraph();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// Unused by this listener.
		System.out.println("Release");
	}

}
