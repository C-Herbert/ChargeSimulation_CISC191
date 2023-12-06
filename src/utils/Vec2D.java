package utils;

/**
 * The Vec2D class is used to represent 2D vector values.
 * 2D vector values have an x and y component, used to
 * represent the overall magnitude of the vector.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

// References:
// https://en.wikipedia.org/wiki/Dot_product

public class Vec2D
{
	// 2D Vectors have an x component
	private double xComp;
	// 2D Vectors have a y component
	private double yComp;

	/**
	 * Creates a new Vec2D using the provided component values.
	 * 
	 * @param xComp Magnitude of this vector's x component.
	 * @param yComp Magnitude of this vector's y component.
	 */
	public Vec2D(double xComp, double yComp)
	{
		this.xComp = xComp;
		this.yComp = yComp;
	}

	/**
	 * Gets the magnitude of the x component for this vector.
	 * 
	 * @return magnitude of the vector's x component.
	 */
	public double getXComp()
	{
		return xComp;
	}

	/**
	 * Gets the magnitude of the y component for this vector.
	 * 
	 * @return magnitude of the vector's y component.
	 */
	public double getYComp()
	{
		return yComp;
	}

	/**
	 * Gets the overall magnitude of this vector.
	 * 
	 * @return magnitude of the vector.
	 */
	public double getMagnitude()
	{
		return Math.sqrt(Math.pow(xComp, 2) + Math.pow(yComp, 2));
	}

	/**
	 * Gets direction of this vector, measured in radians.
	 * 
	 * @return the double representation of this vector's direction, measured
	 *         from the positive x-axis.
	 */
	public double getDirection()
	{
		return Math.atan2(yComp, xComp);
	}

	/**
	 * Sets the magnitude of this vector, maintaining its current direction. A
	 * magnitude less than or equal to zero cannot be assigned to a vector.
	 * 
	 * @param newMagnitude The magnitude to assign to this vector.
	 * @return a reference to this vector.
	 */
	public Vec2D setMagnitude(double newMagnitude)
	{
		// If the newMagnitude is less than or equal to zero, don't assign it.
		if (newMagnitude <= 0) return this;

		double currentMagnitude = getMagnitude();

		xComp = (xComp / currentMagnitude) * newMagnitude;
		yComp = (yComp / currentMagnitude) * newMagnitude;

		// Return this in case we want to chain operations
		return this;
	}

	/**
	 * Changes the direction of this vector while maintaining its magnitude.
	 * 
	 * @param radians New direction of the vector, measured from the positive
	 *                x-axis.
	 * @return a reference to this vector.
	 */
	public Vec2D setDirection(double radians)
	{
		double mag = getMagnitude();
		this.xComp = mag * Math.cos(radians);
		this.yComp = mag * Math.sin(radians);

		// Return this in case we want to chain operations
		return this;
	}

	/**
	 * Adds another vector to this vector.
	 * 
	 * @param other The second vector to be used in this operation.
	 * @return a reference to this vector.
	 */
	public Vec2D add(Vec2D other)
	{
		this.xComp += other.xComp;
		this.yComp += other.yComp;
		// Return this in case we want to chain operations
		return this;
	}

	/**
	 * Calculates the dot product of two vectors.
	 * 
	 * @param other The second vector to be used in this operation.
	 * @return the dot product of this vector and other.
	 */
	public double dotProduct(Vec2D other)
	{
		return (this.xComp * other.xComp) + (this.yComp * other.yComp);
	}

	/**
	 * Calculates the angle between vectors using the dot product.
	 * 
	 * @param other The second vector to be used in this operation.
	 * @return the angle between this vector and other.
	 */
	public double angleBetween(Vec2D other)
	{
		double absoluteAngle = Math.acos(this.dotProduct(other)
				/ (this.getMagnitude() * other.getMagnitude()));

		if (this.yComp < other.yComp)
		{
			// If angle exceeds 180 degrees, assign the appropriate sign
			return -absoluteAngle;
		}
		else
		{
			// Otherwise, return our value
			return absoluteAngle;
		}
	}

}
