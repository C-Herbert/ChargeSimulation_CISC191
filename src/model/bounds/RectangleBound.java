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
	// Saving half values so bounds check doesn't have to divide each time.
	// Dimension values are in pixels.
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
