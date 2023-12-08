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

public class PotentiometerView extends ElementView<PotentiometerGraphTool>
{

	public PotentiometerView(Graph2D graph, int sortOrder)
	{
		super(graph, sortOrder);
	}

	@Override
	public Class<? extends IGraphElement> getDrawableType()
	{
		return PotentiometerGraphTool.class;
	}

	private static final Font TEXT_FONT = new Font("Helvetica", Font.PLAIN, 24);

	@Override
	public void drawElement(PotentiometerGraphTool tool, Graphics2D graphics)
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

		double reading = tool.getReading(charges);

		graphics.setColor(Color.WHITE);
		graphics.setFont(TEXT_FONT);
		graphics.drawString(String.format("%f V", reading), (int) tool.getX(), (int) tool.getY());

	}

}
