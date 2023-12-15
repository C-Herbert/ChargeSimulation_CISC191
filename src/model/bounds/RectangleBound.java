package model.bounds;

/**
 * RectangleBound is a subclass of Bound used to define rectangular boundary
 * boxes. These boxes are used by the interaction system to determine what
 * elements were clicked on.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class RectangleBound extends Bound
{
	/**
	 * RectangleBounds have a width and height.
	 * 
	 * The program saves half values so the bounds check doesn't have to divide
	 * during every check. Dimension values are in pixels.
	 */
	protected int halfWidth, halfHeight;

	/**
	 * Creates a new RectangleBound using the provided width and height
	 * parameters.
	 * 
	 * @param width  The integer width to assign to the boundary.
	 * @param height The integer height to assign to the boundary.
	 */
	public RectangleBound(int width, int height)
	{
		this.halfWidth = width / 2;
		this.halfHeight = height / 2;
	}

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
	@Override
	public boolean isPointInBounds(double x, double y)
	{
		// Check if the point is within the defined bounds.
		if (x >= -halfWidth && x <= halfWidth)
		{
			if (y >= -halfHeight && y <= halfHeight)
			{
				return true;
			}
		}

		// Otherwise, return false
		return false;
	}

}
