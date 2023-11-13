package model;

import java.io.Serializable;

import model.bounds.Bound;
import model.bounds.RectangleBound;
import utils.Vec2D;

/**
 * Data representation of an electric charge.
 * 
 * @version 1.0
 * @author Charlie Herbert
 * 
 *         References: Java, Java, Java 3rd Edition, Chapter 11
 */

public class Charge implements IGraphElement, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8055174990777812535L;

	// Charges have an x and y position
	private double x, y;
	// Charges have a magnitude
	private double magnitude;

	/**
	 * Creates a new charge object with x, y, and magnitude values.
	 * 
	 * @param x         The x position of this charge on a graph.
	 * @param y         The y position of this charge on a graph.
	 * @param magnitude The magnitude of this charge.
	 */
	public Charge(double x, double y, double magnitude)
	{
		this.x = x;
		this.y = y;
		this.magnitude = magnitude;
	}

	/**
	 * Calculates and returns the electric field produced by this charge at a
	 * point.
	 * 
	 * @param pointX The x position of the point being considered.
	 * @param pointY The y position of the point being considered.
	 * @return Vec2D representing the field at the point.
	 */
	public Vec2D getFieldAtPoint(double pointX, double pointY)
	{
		// Calculate the delta x/y between this charge and the point being
		// considered.
		double dX = pointX - x;
		double dY = pointY - y;

		// Calculate the magnitude of the field per Coulomb's law.
		double fieldMagnitude = (8.99 * Math.pow(10, 9) * magnitude)
				/ (Math.pow(dX, 2) + Math.pow(dY, 2));

		// Return a vector pointing along (dX, dY), with the appropriate
		// magnitude.
		return new Vec2D(dX, dY).setMagnitude(fieldMagnitude);
	}

	/**
	 * Gets the x position of this charge.
	 * 
	 * @return x position of this charge.
	 */
	@Override
	public double getX()
	{
		return this.x;
	}

	/**
	 * Gets the y position of this charge.
	 * 
	 * @return y position of this charge.
	 */
	@Override
	public double getY()
	{
		return this.y;
	}

	/**
	 * Sets the x position of this charge.
	 */
	@Override
	public void setX(double newX)
	{
		this.x = newX;
	}

	/**
	 * Sets the y position of this charge.
	 */
	@Override
	public void setY(double newY)
	{
		this.y = newY;
	}

	/**
	 * Gets the magnitude of this charge.
	 * 
	 * @return The magnitude of this charge.
	 */
	public double getMagnitude()
	{
		return this.magnitude;
	}

	private static final RectangleBound BOUNDS = new RectangleBound(64, 64);

	@Override
	public Bound getInteractionBounds()
	{
		return BOUNDS;
	}
}
