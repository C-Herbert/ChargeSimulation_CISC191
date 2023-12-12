package model.bounds;

/**
 * OffsetRectangleBound is a subclass of RectangleBound that applies an x and y
 * offset to the boundary.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class OffsetRectangleBound extends RectangleBound
{
	// OffsetRectangleBounds have an x and y offset.
	private int xOffset, yOffset;

	/**
	 * Creates a new OffsetRectangleBound using the provided width and height
	 * parameters.
	 * 
	 * @param width   The integer width to assign to the boundary.
	 * @param height  The integer height to assign to the boundary.
	 * @param xOffset The integer x-offset to apply to the boundary.
	 * @param yOffset The integer y-offset to apply to the boundary.
	 */
	public OffsetRectangleBound(int width, int height, int xOffset, int yOffset)
	{
		super(width, height);

		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	@Override
	public boolean isPointInBounds(double x, double y)
	{
		// Check if the point is within the defined offset bounds.
		if (x >= (-halfWidth + xOffset) && x <= (halfWidth + xOffset))
		{
			if (y >= (-halfHeight + yOffset) && y <= (halfHeight + yOffset))
			{
				return true;
			}
		}

		// Otherwise, return false
		return false;
	}
}
