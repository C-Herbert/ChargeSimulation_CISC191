package model;

import utils.Vec2D;

/**
 * The Arrow class is used to represent 2D vector values with a position on a
 * graph.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

// TODO: maybe abstract?
public class Arrow2D extends Vec2D implements IGraphElement
{
	// Arrows have an x position
	private double xPos;
	// Arrows have a y position
	private double yPos;

	/**
	 * Constructs a new Arrow2D object with the given position and component
	 * arguments
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

	@Override
	public double getX()
	{
		return this.xPos;
	}

	@Override
	public double getY()
	{
		return this.yPos;
	}
}
