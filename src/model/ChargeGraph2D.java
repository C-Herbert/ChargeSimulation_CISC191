package model;

import java.util.ArrayList;
import java.util.List;

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
	// A ChargeGraph2D has many charges
	ArrayList<Charge> chargeList;

	/**
	 * Creates a new ChargeGraph2D object with its elements initialized.
	 * 
	 * @param elements list of IGraphElements to initialize this ChargeGraph2D
	 *                 with
	 * @param width    integer width to assign to new ChargeGraph2D
	 * @param height   integer height to assign to new ChargeGraph2D
	 */
	public ChargeGraph2D(List<IGraphElement> elements, int width, int height)
	{
		super(elements, width, height);

		this.chargeList = new ArrayList<Charge>();

		// Initialize chargeList to include any charges in the elements list
		for (IGraphElement e : elements)
		{
			if (e instanceof Charge)
			{
				this.chargeList.add((Charge) e);
			}
		}
	}

	/**
	 * Creates a new ChargeGraph2D.
	 * 
	 * @param width  integer width to assign to new ChargeGraph2D
	 * @param height integer height to assign to new ChargeGraph2D
	 */
	public ChargeGraph2D(int width, int height)
	{
		super(width, height);
		this.chargeList = new ArrayList<Charge>();
	}

	@Override
	public boolean addElement(IGraphElement element)
	{
		// Perform add operation like parent class
		boolean output = super.addElement(element);

		// Ensure chargeList stays up to date with main element list
		if (element instanceof Charge)
		{
			chargeList.add((Charge) element);
		}

		// Same output as parent
		return output;
	}

	@Override
	public boolean removeElement(IGraphElement element)
	{
		// Perform add operation like parent class
		boolean output = super.removeElement(element);

		// Ensure chargeList stays up to date with main element list
		if (chargeList.contains(element))
		{
			chargeList.remove(element);
		}

		// Same output as parent
		return output;
	}

	@Override
	public void clearElements()
	{
		super.clearElements();
		// Clear our charge list to keep data in sync
		chargeList.clear();
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

		for (Charge c : chargeList)
		{
			netVector.add(c.getFieldAtPoint(x, y));
		}

		return netVector;
	}

}
