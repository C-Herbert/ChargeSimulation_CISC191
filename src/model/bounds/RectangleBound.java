package model.bounds;

public class RectangleBound extends Bound
{
	// Saving half values so bounds check doesn't have to divide each time.
	// Dimension values are in pixels.
	private int halfWidth;
	private int halfHeight;

	public RectangleBound(int width, int height)
	{
		this.halfWidth = width / 2;
		this.halfHeight = height / 2;
	}

	@Override
	public boolean isPointInBounds(double x, double y)
	{
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
