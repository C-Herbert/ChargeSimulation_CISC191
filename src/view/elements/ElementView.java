package view.elements;

import java.awt.Graphics2D;
import java.util.ArrayList;
import model.Graph2D;
import model.IGraphElement;

/**
 * Subclasses of ElementView allow a given IGraphElementType to be displayed on
 * a Graph.
 * 
 * @version 1.1
 * @author Charlie Herbert
 */

// References
// https://en.wikipedia.org/wiki/Model-view-controller
// https://www.baeldung.com/java-generics-type-parameter-vs-wildcard

// Generic allows subclasses to work on only specific types of graph elements
public abstract class ElementView<T extends IGraphElement>
{
	protected Graph2D graph;
	protected int sortOrder;

	/**
	 * Creates a new element view with an initialized sort order.
	 * 
	 * @param sortOrder The sorting order of this view object.
	 */
	public ElementView(Graph2D graph, int sortOrder)
	{
		this.graph = graph;
		this.sortOrder = sortOrder;
	}

	/**
	 * Gets this view's sort order. Used to determine how elements should be
	 * drawn. A higher value means that this view will be drawn on top of the
	 * others.
	 * 
	 * @return the numerical value of this view's sort order.
	 */
	public int getSortOrder()
	{
		return sortOrder;
	}

	/**
	 * Checks if a type of IGraphElement can be drawn by this view.
	 * 
	 * @param type Type to check.
	 * @return true if the type can be drawn, false otherwise.
	 */
	public abstract Class<? extends IGraphElement> getDrawableType();

	/**
	 * Draws an element using the provided graphics
	 * 
	 * @param graphics Graphics2D object to be used for drawing the elements
	 */
	public abstract void drawElement(T graphElement, Graphics2D graphics);

	/**
	 * Draws all elements of this view's graph field that match getDrawableType
	 * using the provided graphics.
	 * 
	 * @param graphics The graphics to use when drawing each element.
	 */
	// Suppress is okay because getDrawableType should prevent any invalid
	// casts.
	@SuppressWarnings("unchecked")
	public void draw(Graphics2D graphics)
	{
		for (IGraphElement e : graph.getElementsOfType(getDrawableType()))
		{
			drawElement((T) e, graphics);
		}
	}

	/**
	 * Gets all potential interactables managed by this view at a point.
	 * 
	 * @param x the x position to consider.
	 * @param y the y position to consider.
	 * @return A list of IGraphElements that have bounds containing the passed
	 *         point.
	 */
	public ArrayList<IGraphElement> getInteractablesAtPoint(double x, double y)
	{
		ArrayList<IGraphElement> interactables = new ArrayList<IGraphElement>();

		for (IGraphElement e : graph.getElementsOfType(getDrawableType()))
		{
			// Remember, x and y must be relative to the element itself.
			if (e.getInteractionBounds().isPointInBounds(x - e.getX(),
					y - e.getY()))
			{
				interactables.add(e);
			}
		}
		
		return interactables;
	}
}
