package view.elements;

import java.awt.Graphics2D;
import java.util.ArrayList;
import model.Graph2D;
import model.IGraphElement;

/**
 * Subclasses of ElementView allow a given IGraphElementType to be displayed on
 * a Graph.
 * 
 * References:
 * 1. Contributors to Wikimedia projects. �Model�View�Controller.� Wikipedia,
 * Wikimedia Foundation, Inc., 2003,
 * https://en.wikipedia.org/wiki/Model-view-controller.
 * 2. Peterlic, Ana. �Type Parameter vs Wildcard in Java Generics.� Baeldung, 28
 * Dec. 2022, https://www.baeldung.com/java-generics-type-parameter-vs-wildcard.
 * 
 * @version 1.1
 * @author Charlie Herbert
 */
// Generic allows subclasses to work on only specific types of graph elements
public abstract class ElementView<T extends IGraphElement>
{
	/**
	 * ElementViews have an associated graph. The view will draw any draw-able
	 * elements, as defined by the getDrawableType() function, contained by this
	 * graph.
	 */
	protected Graph2D graph;
	/**
	 * ElementViews have an integer sort order used to determine when the view
	 * should be drawn during the GraphView's repaint process.
	 */
	protected int sortOrder;

	/**
	 * Creates a new element view with an initialized sort order.
	 * 
	 * @param graph     The graph to associate this view with.
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
	 * Gets the subclasses of IGraphElement that can be drawn by this view
	 * 
	 * @return The class object of the type that can be drawn by this view.
	 */
	public abstract Class<? extends IGraphElement> getDrawableType();

	/**
	 * Draws an element using the provided graphics
	 * 
	 * @param graphElement The element to draw.
	 * @param graphics     Graphics2D object to be used for drawing the elements
	 */
	protected abstract void drawElement(T graphElement, Graphics2D graphics);

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
		// Declare a list to hold all interactables we find.
		ArrayList<IGraphElement> interactables = new ArrayList<IGraphElement>();

		// Iterate through all elements on the graph and find those with a
		// boundary that contains the given point.
		for (IGraphElement e : graph.getElementsOfType(getDrawableType()))
		{
			// Remember, x and y must be relative to the element itself.
			if (e.getInteractionBounds().isPointInBounds(x - e.getX(),
					y - e.getY()))
			{
				// Add the element if it contains the point.
				interactables.add(e);
			}
		}

		// Return the list we've compiled.
		return interactables;
	}
}
