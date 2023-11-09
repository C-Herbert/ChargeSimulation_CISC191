package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.ChargeGraph2D;
import model.IGraphElement;
import view.elements.ElementView;
import view.elements.FieldArrowView;
import view.elements.ChargeView;

/*
 * References (TODO: Cleanup)
 * Using Graphics2D for a custom component:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/jcomponent.html
 * https://www.oracle.com/java/technologies/painting.html
 */

/**
 * The ChargeGraphView class will be used to draw and maintain a view model
 * for the ChargeGraph2D class. Currently, its only used for testing.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */
public class ChargeGraphView extends JPanel
{
	// ChargeGraphView has a ChargeGraph2D
	private ChargeGraph2D graphReference;
	// ChargeGraphView has many ElementViews
	private List<ElementView<?>> views;

	/**
	 * Creates a new ChargeBoardField using the provided ChargeGraph2D object.
	 * 
	 * @param chargeGraph The ChargeGraph2D displayed by this view.
	 */
	public ChargeGraphView(ChargeGraph2D chargeGraph)
	{
		// Initialize charge graph reference
		this.graphReference = chargeGraph;

		// Create a new view list
		this.views = new ArrayList<ElementView<?>>();
		// Add a field arrow view
		addView(new FieldArrowView(chargeGraph, -100));
		// Add a charge view
		addView(new ChargeView(chargeGraph, 0));

		setSize(graphReference.getWidth(), graphReference.getHeight());
		setPreferredSize(getSize());
	}

	private boolean addView(ElementView<?> view)
	{
		// Check that parameter is not null
		if (view == null) return false;

		if (views.size() == 0)
		{
			// Special case for empty view list
			return views.add(view);
		}
		else
		{
			// Iterate through the views, comparing our sort order against all
			// existing views.
			for (int i = 0; i < views.size(); ++i)
			{
				if (view.getSortOrder() < views.get(i).getSortOrder())
				{
					// Found our position, add the view.
					views.add(i, view);
					return true;
				}
			}

			// Reached end of list without finding a higher sort order, append
			// view to end of list.
			return views.add(view);
		}

	}

	@Override
	//TODO: Cache element states so full draw routine isn't necessary on every repaint.
	public void paint(Graphics g)
	{
		super.paint(g);

		Graphics2D graphics = (Graphics2D) g;

		// First, cache all elements to their respective views.
		for (IGraphElement element : graphReference.getElements())
		{
			// Find a view that can draw the element
			for (ElementView<?> view : views)
			{
				// Once we find a view that can draw the element, exit
				if (view.tryCacheElement(element)) break;
			}
		}

		// Next, draw all elements according to their sort order. Note that the
		// views list is already sorted.
		for (ElementView<?> view : views)
		{
			view.draw(graphics);
		}
	}
}
