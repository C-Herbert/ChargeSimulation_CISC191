package view.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import assets.ProgramAssets;
import model.Charge;
import model.Graph2D;
import model.IGraphElement;
import model.tools.PotentiometerGraphTool;

/**
 * The PotentiometerView class is an ElementView responsible for drawing all
 * PotentiometerGraphTools on a graph using a provided Graphics object.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class PotentiometerView extends ElementView<PotentiometerGraphTool>
{
	/**
	 * PotentiometerView has a font used for text display.
	 */
	private static final Font TEXT_FONT = new Font("Helvetica", Font.PLAIN, 24);

	/**
	 * Constructs a new PotentiometerView using the provided graph and assigns
	 * it the passed sortOrder.
	 * 
	 * @param graph     the graph to associate this view with.
	 * @param sortOrder the sortOrder to assign to this view.
	 */
	public PotentiometerView(Graph2D graph, int sortOrder)
	{
		super(graph, sortOrder);
	}

	/**
	 * Gets the subclasses of IGraphElement that can be drawn by this view
	 * 
	 * @return The potentiometer class.
	 */
	@Override
	public Class<? extends IGraphElement> getDrawableType()
	{
		return PotentiometerGraphTool.class;
	}

	/**
	 * Draws a potentiometer on a graph using the provided Graphics2D object.
	 * 
	 * @param tool     The potentiometer to draw.
	 * @param graphics The Graphics2D object to use when drawing.
	 */
	@Override
	protected void drawElement(PotentiometerGraphTool tool, Graphics2D graphics)
	{
		// Draw the base potentiometer image.
		graphics.drawImage(ProgramAssets.getAsset("potentiometer"), null,
				(int) tool.getX(), (int) tool.getY());

		// Gather all charges on the graph.
		List<IGraphElement> elements = graph.getElementsOfType(Charge.class);

		// Convert variable types so we can pass it to the potentiometer.
		List<Charge> charges = new ArrayList<Charge>(elements.size());
		for (IGraphElement e : elements)
		{
			charges.add((Charge) e);
		}

		// Get the voltage reading.
		double reading = tool.getReading(charges);

		// Draw the voltage display.
		graphics.setColor(Color.WHITE);
		graphics.setFont(TEXT_FONT);
		graphics.drawString(String.format("%f V", reading), (int) tool.getX(),
				(int) tool.getY());
	}
}
