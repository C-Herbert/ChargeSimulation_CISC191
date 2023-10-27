package view.elements;

import java.awt.Graphics2D;
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
	/**
	 * Draws the elements included in this view using the provided graphics
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
	public boolean tryDrawElement(IGraphElement element, Graphics2D graphics)
	{
		try
		{
			if (canDraw(element))
			{
				// Draw element and return true
				drawElement((T) element, graphics);
				return true;
			}
			else
			{
				// Failed to draw
				return false;
			}
		}
		catch (ClassCastException e)
		{
			//ClassCastException will only be thrown if an invalid object managed to slip by canDraw()
			throw new ClassCastException(
					"Invalid class passed to tryDrawElement, ensure canDraw implementation is functioning properly");
		}
	}
}
