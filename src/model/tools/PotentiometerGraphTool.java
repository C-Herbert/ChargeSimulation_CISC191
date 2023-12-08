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
 * @author Charlie Herbert
 * @version 1.0
 */

// References:
// http://hyperphysics.phy-astr.gsu.edu/hbase/electric/potpoi.html

public class PotentiometerGraphTool implements IGraphElement
{
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

	// Coulomb's constant, see references.
	private static final double kConst = 8.987552 * Math.pow(10, 9);

	/**
	 * Returns a voltage reading based on the passed list of charges.
	 * 
	 * @param charges
	 * @return
	 */
	public double getReading(List<Charge> charges)
	{
		double readVoltage = 0;

		// Temporary loop variable
		double distance = 0;

		for (Charge c : charges)
		{
			distance = Math.sqrt(Math.pow(this.getX() - c.getX(), 2)
					+ Math.pow(this.getY() - c.getY(), 2));

			// 100pixels = 1cm
			distance /= Math.pow(10, 4);

			readVoltage += kConst * (c.getMagnitude() / distance);
		}

		return readVoltage;
	}

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
