package model.bounds;

public class OffsetRectangleBound extends RectangleBound
{
	private int xOffset, yOffset;
	
	public OffsetRectangleBound(int width, int height, int xOffset, int yOffset)
	{
		super(width, height);
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	@Override
	public boolean isPointInBounds(double x, double y)
	{
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
