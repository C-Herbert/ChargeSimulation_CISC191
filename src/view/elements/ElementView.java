package view.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import model.Graph2D;
import model.IGraphElement;
import view.ChargeGraphView;

/**
 * Subclasses of ElementView allow a given IGraphElementType to be displayed on
 * a Graph.
 * 
 * @version 1.1
 * @author Charlie Herbert
 */

// Trying to follow the model-view-controller approach,
// https://en.wikipedia.org/wiki/Model-view-controller

// Generic allows subclasses to work on only specific types of graph elements
public abstract class ElementView<T extends IGraphElement>
{
	private Graph2D graph;
	private int sortOrder;

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
	 * Draws an element using the provided graphics
	 * 
	 * @param graphics Graphics2D object to be used for drawing the elements
	 */
	public abstract void drawElement(T graphElement, Graphics2D graphics);

	/**
	 * Checks if a type of IGraphElement can be drawn by this view.
	 * 
	 * @param type Type to check.
	 * @return true if the type can be drawn, false otherwise.
	 */
	public abstract Class<? extends IGraphElement> getDrawableType();

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
}
