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


}
