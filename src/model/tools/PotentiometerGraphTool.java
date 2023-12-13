package model.tools;

import java.util.List;

import model.Charge;
import model.IGraphElement;
import model.bounds.Bound;
import model.bounds.OffsetRectangleBound;
import model.bounds.RectangleBound;

/**
 * PotentiometerGraphTool is used to represent a potentiometer instance on a
 * Graph2D.
 * 
 * References:
 * 1. Nave, Rod. “Electric Potential for Different Charge Geometries.”
 * HyperPhysics,
 * http://hyperphysics.phy-astr.gsu.edu/hbase/electric/potpoi.html. Accessed 8
 * Dec. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public class PotentiometerGraphTool implements IGraphElement
{
	// Coulomb's constant, see references.
	private static final double kConst = 8.987552 * Math.pow(10, 9);

	// Potentiometers have an x and y position.
	protected double xPos, yPos;

	// Potentiometers have an interaction boundary
	private static final RectangleBound BOUNDS = new OffsetRectangleBound(64,
			64, 32, 32);

	/**
	 * Creates a new potentiometer at the specified location.
	 * 
	 * @param x The x position to assign to the potentiometer.
	 * @param y The y position to assign to the potentiometer.
	 */
	public PotentiometerGraphTool(double x, double y)
	{
		this.xPos = x;
		this.yPos = y;
	}

	/**
	 * Returns a voltage reading based on the passed list of charges.
	 * 
	 * @param charges The list of charges to consider when calculating the
	 *                voltage at the tool's location.
	 * @return a double representation of the voltage at the potentiometer's
	 *         location.
	 */
	public double getReading(List<Charge> charges)
	{
		// Declare a variable to hold the voltage at the potentiometer's
		// location.
		double readVoltage = 0;

		// Temporary loop variable
		double distance = 0;

		for (Charge c : charges)
		{
			// Calculate the distance between the charge and the potentiometer.
			distance = Math.sqrt(Math.pow(this.getX() - c.getX(), 2)
					+ Math.pow(this.getY() - c.getY(), 2));
			
			// Use the same scaling as the charge class.
			distance /= Charge.CHARGE_PIXELS_TO_METERS;
			
			// Add the voltage, per the superposition principle.
			readVoltage += kConst * (c.getMagnitude() / distance);
		}

		return readVoltage;
	}

	// Basic implementation of functions required by IGraphElement:

	@Override
	public double getX()
	{
		return xPos;
	}

	@Override
	public double getY()
	{
		return yPos;
	}

	@Override
	public void setX(double newX)
	{
		this.xPos = newX;
	}

	@Override
	public void setY(double newY)
	{
		this.yPos = newY;
	}

	@Override
	public Bound getInteractionBounds()
	{
		return BOUNDS;
	}

}
