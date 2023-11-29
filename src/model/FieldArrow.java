package model;

import java.io.Serializable;

import utils.Vec2D;

/**
 * The FieldArrow class is a specific type of Arrow2D used to
 * represent field vectors on a graph.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */
public class FieldArrow extends Arrow2D
{
	public FieldArrow(double xPos, double yPos, double xMag, double yMag)
	{
		super(xPos, yPos, xMag, yMag);
	}

	/**
	 * Sets the direction of this arrow to point towards a given point on the
	 * graph.
	 * 
	 * @param targetX X position of the target point.
	 * @param targetY Y position of the target point.
	 */
	public void pointTowardsLocation(double targetX, double targetY)
	{
		// Calculate target vector
		Vec2D targetVector = new Vec2D(targetX - this.getX(),
				targetY - this.getY());

		// Assign our new direction
		setDirection(targetVector.angleBetween(new Vec2D(1, 0)));
	}
}
