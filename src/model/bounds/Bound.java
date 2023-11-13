package model.bounds;

/**
 * Bound is used to define the interaction bounding-box of a given object. It
 * does not define a position, only the dimensions of the bound.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public abstract class Bound
{
	/**
	 * Checks if a point is within the given bounds. Note that Bound does not
	 * possess a location. Thus, the isPointInBounds function should only be
	 * used with coordinates relative to the component possessing the bounds.
	 * 
	 * @param x the x position of the point to check.
	 * @param y the y position of the point to check.
	 * @return true if the point defined by x and y is contained within this
	 *         bounds, false otherwise.
	 */
	public abstract boolean isPointInBounds(double x, double y);
}
