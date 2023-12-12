package model;

import model.bounds.Bound;
import model.bounds.RectangleBound;
import utils.Vec2D;

/**
 * The Arrow class is used to represent 2D vector values with a position on a
 * graph.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class Arrow2D extends Vec2D implements IGraphElement
{
	// Arrows have an x position
	private double xPos;
	// Arrows have a y position
	private double yPos;
	// Arrows have a RectangleBound for user interaction.
	private static final RectangleBound ARROW_BOUNDS = new RectangleBound(64,
			64);

	/**
	 * Constructs a new Arrow2D object with the given position and component
	 * arguments.
	 * 
	 * @param xPos the x position of this arrow on the graph
	 * @param yPos the y position of this arrow on the graph
	 * @param xMag the magnitude of this arrow's vector x component
	 * @param yMag the magnitude of this arrow's vector y component
	 */
	public Arrow2D(double xPos, double yPos, double xMag, double yMag)
	{
		super(xMag, yMag);
		this.xPos = xPos;
		this.yPos = yPos;
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

	/**
	 * Gets the x position of this arrow.
	 * 
	 * @return x position of this arrow.
	 */
	@Override
	public double getX()
	{
		return this.xPos;
	}

	/**
	 * Gets the y position of this arrow.
	 * 
	 * @return y position of this arrow.
	 */
	@Override
	public double getY()
	{
		return this.yPos;
	}

	/**
	 * Sets the x position of this arrow.
	 */
	@Override
	public void setX(double newX)
	{
		this.xPos = newX;
	}

	/**
	 * Sets the y position of this arrow.
	 */
	@Override
	public void setY(double newY)
	{
		this.yPos = newY;
	}

	@Override
	public Bound getInteractionBounds()
	{
		return ARROW_BOUNDS;
	}
}
