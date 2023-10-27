package model;

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
	 * @return the integer value representing this element's x position on the
	 *         graph.
	 */
	int getX();

	/**
	 * Gets the y position of this graph element.
	 * 
	 * @return the integer value representing this element's y position on the
	 *         graph.
	 */
	int getY();
}
