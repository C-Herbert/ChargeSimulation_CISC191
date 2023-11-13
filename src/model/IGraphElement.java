package model;

import model.bounds.Bound;

/**
 * IGraphElement provides an interface for all elements that may be part of a
 * Graph.
 * 
 * Graph elements must have a position represented by an integer x and y value.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public interface IGraphElement
{
	/**
	 * Gets the x position of this graph element.
	 * 
	 * @return the double value representing this element's x position on the
	 *         graph.
	 */
	double getX();

	/**
	 * Gets the y position of this graph element.
	 * 
	 * @return the double value representing this element's y position on the
	 *         graph.
	 */
	double getY();

	/**
	 * Sets the x position of this graph element.
	 */
	void setX(double newX);

	/**
	 * Sets the x position of this graph element.
	 */
	void setY(double newY);

	/**
	 * Returns the interaction bounds associated with this graph element.
	 * 
	 * @return the Bounds object representing this graph element's interaction
	 *         bounds.
	 */
	Bound getInteractionBounds();
}
