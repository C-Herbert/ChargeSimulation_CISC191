package model;

import utils.Vec2D;

/**
 * The ChargeGraph2D class extends the functionality of Graph2D to manage a list
 * of charges on a graph.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */
public class ChargeGraph2D extends Graph2D
{
	/**
	 * Creates a new ChargeGraph2D.
	 * 
	 * @param width  integer width to assign to new ChargeGraph2D
	 * @param height integer height to assign to new ChargeGraph2D
	 */
	public ChargeGraph2D(int width, int height)
	{
		super(width, height);
	}

	/**
	 * Calculates and returns the net electric field vector at a point.
	 * 
	 * @param x The x position of the point.
	 * @param y The y position of the point.
	 * @return The vector representing the net field at the given point.
	 */
	public Vec2D getNetFieldAtPoint(double x, double y)
	{
		Vec2D netVector = new Vec2D(0, 0);
		Charge currentCharge = null;

		for (IGraphElement c : this.getElementsOfType(Charge.class))
		{
			// Cast is safe because getElementsOfType ensures only Charges will
			// appear here.
			currentCharge = (Charge) c;

			// Only add a charge's influence if its magnitude is not zero.
			if (currentCharge.getMagnitude() != 0)
			{
				netVector.add(currentCharge.getFieldAtPoint(x, y));
			}
		}

		return netVector;
	}

	/**
	 * Updates the state of all FieldArrows present on this ChargeGraph. This
	 * involves calculating the magnitude and direction of the net electric
	 * field at each arrow's position.
	 */
	public void updateFieldArrows()
	{
		// Declare temporary variables for the loop
		Vec2D localField = null;
		double localMag = 0;
		double localDirection = 0;

		// Iterate through all arrows on the graph and update their magnitude
		// and direction.
		for (IGraphElement arrow : getElementsOfType(FieldArrow.class))
		{
			localField = getNetFieldAtPoint(arrow.getX(), arrow.getY());
			localMag = localField.getMagnitude();
			localDirection = localField.getDirection();

			// Casting is safe here because getElementsOfType ensures only
			// FieldArrows will appear.
			((FieldArrow) arrow).setMagnitude(localMag);
			((FieldArrow) arrow).setDirection(localDirection);
		}
	}
}
