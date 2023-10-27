package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.ChargeGraph2D;
import model.FieldArrow;
import model.IGraphElement;
import view.elements.ElementView;
import view.elements.FieldArrowView;

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
		// Add a field arrow view to it
		views.add(new FieldArrowView());

		setSize(graphReference.getWidth(), graphReference.getHeight());
		setPreferredSize(getSize());
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		Graphics2D graphics = (Graphics2D) g;

		for (IGraphElement element : graphReference.getElements())
		{
			//Find a view that can draw the element
			for (ElementView<?> view : views)
			{
				// Once we find a view that can draw the element, exit
				if (view.tryDrawElement(element, graphics)) break;
			}
		}
	}
}
