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
	private int xPos;
	// Arrows have a y position
	private int yPos;

	/**
	 * Constructs a new Arrow2D object with the given position and component
	 * arguments
	 * 
	 * @param xPos the x position of this arrow on the graph
	 * @param yPos the y position of this arrow on the graph
	 * @param xMag the magnitude of this arrow's vector x component
	 * @param yMag the magnitude of this arrow's vector y component
	 */
	public Arrow2D(int xPos, int yPos, int xMag, int yMag)
	{
		super(xMag, yMag);
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public int getX()
	{
		return this.xPos;
	}

	@Override
	public int getY()
	{
		return this.yPos;
	}
}
