package utils;

import model.FieldArrow;
import model.Graph2D;

/**
 * ArrowPatterns is a utility class used to generate various patterns of
 * FieldArrows for a graph object.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public final class ArrowPatterns
{
	// Constant to represent default size of field arrows.
	private static final int ARROW_SIZE = 64;

	/**
	 * Generates a rectangular pattern of FieldArrows on the graph using the
	 * provided density argument.
	 * 
	 * @param graph   the graph to add the arrows to.
	 * @param density the density of the pattern. Should be less than 1 and must
	 *                be more than 0.
	 * @throws IllegalArgumentException if density is less than 0.
	 */
	public static void createRectangularPattern(Graph2D graph, double density)
	{
		if (density < 0) throw new IllegalArgumentException(
				"Pattern density cannot be less than 0.");

		// Calculate pattern parameters
		int xCount = (int) ((graph.getWidth() / ARROW_SIZE) * density);
		int yCount = (int) ((graph.getHeight() / ARROW_SIZE) * density);

		int xInterval = graph.getWidth() / xCount;
		int yInterval = graph.getHeight() / yCount;

		// Temporary loop variables
		double xPos = 0.0;
		double yPos = 0.0;
		FieldArrow fArrow = null;

		for (int y = 0; y < yCount; ++y)
		{
			yPos = (yInterval * y) + (ARROW_SIZE / 2);

			for (int x = 0; x < xCount; ++x)
			{
				xPos = (xInterval * x) + (ARROW_SIZE / 2);

				fArrow = new FieldArrow(xPos, yPos, 1, 0);
				
				graph.addElement(fArrow);
			}
		}
	}
}
