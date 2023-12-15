package controller.listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import model.Charge;
import model.ChargeGraph2D;
import model.DraggableFieldArrow;
import model.IGraphElement;
import model.tools.PotentiometerGraphTool;
import view.ProgramView;

/**
 * The ElementDragListener is a GraphMouseListener responsible for handling
 * element drag inputs on a graph.
 * 
 * References:
 * 1. “Lesson: Concurrency.” Oracle Java Documentation,
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html.
 * Accessed 15 Nov. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class ElementDragListener extends GraphMouseListener
{
	/**
	 * All ElementDragListeners share a list of types that can be dragged.
	 */
	// This array is only used to check types within this class, so I think the
	// suppression is okay.
	@SuppressWarnings("rawtypes")
	private static final Class[] DRAGGABLE_TYPES = { Charge.class,
			DraggableFieldArrow.class, PotentiometerGraphTool.class };

	/**
	 * ElementDragListener tracks an IGraphElement.
	 */
	private IGraphElement draggingElement = null;
	/**
	 * ElementDragListener has a thread for updating the element's position.
	 */
	private Thread dragThread;

	/**
	 * Creates a new ElementDragListener using the provided graph and view
	 * objects.
	 * 
	 * @param graph The graph to assign to this listener.
	 * @param view  The view to assign to this listener.
	 */
	public ElementDragListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	/**
	 * Unused by this listener
	 * 
	 * @param e The event that prompted this listener.
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Unused by this listener.
	}

	/**
	 * Invoked when the mouse is pressed. Attempts to start dragging the first
	 * IGraphElement under the mouse pointer.
	 * 
	 * @param e The event that prompted this listener.
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		// Check that input is a left mouse press. If not, exit.
		if (e.getButton() != MouseEvent.BUTTON1) return;

		// Early exit if we're already dragging something. (This shouldn't
		// happen)
		if (draggingElement != null) return;

		// Get all elements under the mouse.
		List<IGraphElement> potentialElements = view.getGraphView()
				.getInteractablesAtPoint(e.getX(), e.getY());

		// Find the first draggable element.
		for (IGraphElement element : potentialElements)
		{
			// Check that the element is a draggable type.
			for (Class<?> c : DRAGGABLE_TYPES)
			{
				if (element.getClass().equals(c))
				{
					// Found a draggable element, start the thread.
					draggingElement = element;
					dragThread = new Thread(new DragElement());
					dragThread.start();

					// Don't drag any other elements.
					return;
				}
			}
		}
	}

	/**
	 * Invoked when the mouse is released. Ends any current drag action and
	 * clears the draggingElement field.
	 * 
	 * @param e The event that prompted this listener.
	 */
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

	/**
	 * Unused by this listener
	 * 
	 * @param e The event that prompted this listener.
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Unused by this listener.
	}

	/**
	 * Unused by this listener
	 * 
	 * @param e The event that prompted this listener.
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{
		// Unused by this listener.
	}

	/**
	 * DragElement is an internal class of ElementDragListener for
	 * handling the drag action execution. It is a runnable that continuously
	 * updates the position of the draggingElement.
	 * 
	 * @author Charlie Herbert
	 * @version 1.0
	 */
	private class DragElement implements Runnable
	{
		/**
		 * DragElement has a Point to keep track of the mouse's position.
		 * Declared outside run to prevent repeated memory allocations.
		 */
		Point lastPoint = null;

		/**
		 * Starts the DragElement's execution. Attempts to continuously drag the
		 * draggingElement along with the mouse.
		 */
		@Override
		public void run()
		{
			try
			{
				// Continue execution until we're interrupted
				while (true)
				{
					// Double check that draggingElement hasn't been unassigned.
					// Note that we also handle NullPointerExceptions in case
					// this field gets un-assigned during execution.
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
						graph.updateFieldArrows();

						// Sleep before executing again.
						// ~1/60th of a second, should be smooth enough.
						Thread.sleep(17);
					}
					else
					{
						return;
					}
				}
			}
			catch (InterruptedException e)
			{
				// Interrupt guarantees exit.
				return;
			}
			catch (NullPointerException e)
			{
				// In case the draggingElement gets un-assigned before we are
				// done with it.

				// Since new threads are created for each drag operation,
				// returning on a null pointer doesn't present any real issues.
				return;
			}
		}
	}
}
