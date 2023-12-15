package model;

import model.bounds.Bound;
import model.bounds.RectangleBound;
import utils.Vec2D;

/**
 * Data representation of an electric charge.
 * 
 * References:
 * 1. Morelli, Ralph. Java, Java, Java. 3rd ed., Prentice Hall, 2006.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class Charge implements IGraphElement
{
	/**
	 * Charges have an x and y position.
	 */
	private double x, y;
	/**
	 * Charges have a magnitude.
	 */
	private double magnitude;
	/**
	 * Charges have an interaction boundary.
	 */
	private static final RectangleBound BOUNDS = new RectangleBound(64, 64);
	/**
	 * Charges store their x and y position in units of pixels. To convert from
	 * pixels to meters for calculations, this constant should be used. Current
	 * factor is 10^4pixels = 1m (10pixels = 1 centimeter)
	 */
	public static final double CHARGE_PIXELS_TO_METERS = Math.pow(10, 4);

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
		double dX = (pointX - x) / CHARGE_PIXELS_TO_METERS;
		double dY = (pointY - y) / CHARGE_PIXELS_TO_METERS;

		if (dX == 0.0 && dY == 0.0)
		{
			// If there is no distance, return a unit vector as the calculation
			// cannot be completed.
			return new Vec2D(0, 1);
		}

		// If our magnitude is less than zero, we need to flip the direction
		// vectors. (positive = repel, negative = attract)
		if (magnitude < 0)
		{
			dX *= -1;
			dY *= -1;
		}

		// Calculate the magnitude of the field per Coulomb's law.
		double fieldMagnitude = (8.99 * Math.pow(10, 9) * Math.abs(magnitude))
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
	 * Gets the magnitude of this charge.
	 * 
	 * @return The magnitude of this charge.
	 */
	public double getMagnitude()
	{
		return this.magnitude;
	}

	/**
	 * Sets the x position of this charge.
	 * 
	 * @param newX The x value to assign to this charge.
	 */
	@Override
	public void setX(double newX)
	{
		this.x = newX;
	}

	/**
	 * Sets the y position of this charge.
	 * 
	 * @param newY The y value to assign to this charge.
	 */
	@Override
	public void setY(double newY)
	{
		this.y = newY;
	}

	/**
	 * Sets the magnitude of this charge.
	 * 
	 * @param newMag The magnitude to assign to the charge.
	 */
	public void setMagnitude(double newMag)
	{
		this.magnitude = newMag;
	}

	/**
	 * Gets the interaction boundary for this charge.
	 * 
	 * @return The Bound object that defines this element's boundaries.
	 */
	@Override
	public Bound getInteractionBounds()
	{
		return BOUNDS;
	}
}
