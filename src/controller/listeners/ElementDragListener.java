package controller.listeners;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.lang.Thread.State;

import model.Graph2D;
import model.IGraphElement;
import view.ProgramView;

/**
 * The ElementDragListener is a GraphMouseListener responsible for handling
 * element drag inputs on a graph.
 * 
 * References:
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class ElementDragListener extends GraphMouseListener
{
	// ElementDragListener tracks an IGraphElement.
	private IGraphElement draggingElement = null;
	// ElementDragListener has a thread for updating the element's position.
	private Thread dragThread;

	public ElementDragListener(Graph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Unused by this listener.
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// Early exit if we're already dragging something. (This shouldn't
		// happen)
		if (draggingElement != null) return;

		// Get the first element under the mouse.
		draggingElement = view.getGraphView().getInteractableAtPoint(e.getX(),
				e.getY());

		// If we found an element, start dragging it.
		if (draggingElement != null)
		{
			dragThread = new Thread(new DragElement());
			dragThread.start();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// End thread execution
		if (dragThread != null)
		{
			dragThread.interrupt();
			// Remove reference so old thread (ideally) gets collected sooner
			dragThread = null;
		}

		// Clear the element being dragged.
		draggingElement = null;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Unused by this listener.
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// Unused by this listener.
	}

	private class DragElement implements Runnable
	{
		// Declare outside run to prevent repeated memory allocations.
		Point lastPoint = null;

		@Override
		public void run()
		{
			try
			{
				// Continue execution until we're interrupted
				while (true)
				{
					// Double check that draggingElement hasn't been unassigned.
					if (draggingElement == null)
					{
						// If so, exit the thread.
						return;
					}

					// Get the last position of the mouse pointer on the graph.
					lastPoint = view.getGraphView().getMousePosition();

					if (lastPoint != null)
					{
						// If we got a position, update the element's x and y.
						draggingElement.setX(lastPoint.getX());
						draggingElement.setY(lastPoint.getY());
						view.repaintGraph();

						// Sleep before executing again.
						Thread.sleep(10);
					}
					else
					{
						// TODO: exception?
						return;
					}
				}
			}
			catch (InterruptedException e)
			{
				// Interrupt guarantees exit.
				return;
			}
		}

	}

}
