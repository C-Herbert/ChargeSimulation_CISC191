package view.elements;

import java.awt.Graphics2D;
import java.util.LinkedList;

import model.IGraphElement;

/**
 * Subclasses of ElementView allow a given IGraphElementType to be displayed on
 * a Graph.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

// Trying to follow the model-view-controller approach,
// https://en.wikipedia.org/wiki/Model-view-controller

// Generic allows subclasses to work on only specific types of graph elements
public abstract class ElementView<T extends IGraphElement>
{
	private int sortOrder;

	/*
	 * ElementViews maintain a cache of elements. This cache is cleared upon
	 * drawing. Clearing the cache ensures all data is kept in sync with the
	 * model.
	 * While simply making the draw function a part of the IGraphElement
	 * interface to remove the need to cache or type check each element is
	 * appealing, I'm trying to keep the model and view as separate as possible.
	 */
	private LinkedList<T> cachedElements;

	/**
	 * Creates a new element view with an initialized sort order.
	 * 
	 * @param sortOrder The sorting order of this view object.
	 */
	public ElementView(int sortOrder)
	{
		this.sortOrder = sortOrder;
		cachedElements = new LinkedList<>();
	}

	/**
	 * Draws an element using the provided graphics
	 * 
	 * @param graphics Graphics2D object to be used for drawing the elements
	 */
	public abstract void drawElement(T graphElement, Graphics2D graphics);

	/**
	 * Checks if an IGraphElement can be drawn by this view.
	 * 
	 * @param element Element to check.
	 * @return true if the element can be drawn, false otherwise.
	 */
	public abstract boolean canDraw(IGraphElement element);

	/**
	 * Attempts to draw an element. Only elements that can be drawn by this view
	 * will be displayed.
	 * 
	 * Suppression is intentional as canDraw is used to check each element
	 * before casting.
	 * 
	 * @param element  IGraphElement to attempt to draw.
	 * @param graphics Graphics to use when drawing element.
	 * @return true if element could be drawn, false otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean tryCacheElement(IGraphElement element)
	{
		try
		{
			if (canDraw(element))
			{
				// Cache the element and return true.
				cachedElements.add((T) element);
				return true;
			}
			else
			{
				// Failed to cache.
				return false;
			}
		}
		catch (ClassCastException e)
		{
			// ClassCastException will only be thrown if an invalid object
			// managed to slip by canDraw()
			throw new ClassCastException(
					"Invalid class passed to tryDrawElement, ensure canDraw implementation is functioning properly");
		}
	}

	/**
	 * Draws all elements cached in this view using the provided graphics. The
	 * cache is cleared after drawing.
	 * 
	 * @param graphics
	 * @return true if any elements were drawn, false otherwise.
	 */
	public boolean draw(Graphics2D graphics)
	{
		// Check if we have any elements to draw, return false if not.
		if (cachedElements.size() == 0) return false;

		// Draw all elements.
		for (T element : cachedElements)
		{
			drawElement(element, graphics);
		}

		// Clear the cache
		cachedElements.clear();
		
		return true;
		
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
