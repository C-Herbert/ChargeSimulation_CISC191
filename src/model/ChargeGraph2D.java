package model;

import java.util.ArrayList;
import java.util.List;

public class ChargeGraph2D extends Graph2D
{
	//A ChargeGraph2D has many charges
	List<Charge> chargeList;
	
	/**
	 * Creates a new ChargeGraph2D object with its elements and charges initialized.
	 * @param elements list of IGraphElements to initialize this ChargeGraph2D with
	 * @param charges list of charges to assign to new ChargeGraph2D
	 * @param width integer width to assign to new ChargeGraph2D
	 * @param height integer height to assign to new ChargeGraph2D
	 */
	public ChargeGraph2D(List<IGraphElement> elements, List<Charge> charges, int width, int height)
	{
		super(elements, width, height);
		
		if(elements != null)
		{
			this.chargeList = charges;
		}
		else
		{
			//Fallback on an empty list if the programmer doesn't provide one
			this.chargeList = new ArrayList<Charge>();
		}

	}
	
	/**
	 * Creates a new ChargeGraph2D with an initialized charge list and empty element list
	 * @param charges list of charges to assign to new ChargeGraph2D
	 * @param width integer width to assign to new ChargeGraph2D
	 * @param height integer height to assign to new ChargeGraph2D
	 */
	public ChargeGraph2D(List<Charge> charges, int width, int height)
	{
		super(width, height);
		this.chargeList = charges;
	}
	
	/**
	 * Gets the list of Charges associated with this graph.
	 * @return The list of Charge objects contained by this ChargeGraph2D
	 */
	public List<Charge> getChargeList()
	{
		//TODO: maybe deep copy?
		return chargeList;
	}
	
}
