package view.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import assets.ProgramAssets;
import model.Charge;
import model.FieldArrow;
import model.Graph2D;
import model.IGraphElement;

/**
 * The FieldArrowView class is an ElementView responsible for drawing all
 * FieldArrows on a graph using a provided Graphics object.
 * 
 * @author Charlie Herbert
 * @version 1.1
 */
public class FieldArrowView extends ElementView<FieldArrow>
{
	// Static field for arrow image.
	private static BufferedImage arrowImage = ProgramAssets
			.getAsset("field_arrow");

	/**
	 * Constructs a new FieldArrowView using the provided graph and assigns it
	 * the passed sortOrder.
	 * 
	 * @param graph     the graph to associate this view with.
	 * @param sortOrder the sortOrder to assign to this view.
	 */
	public FieldArrowView(Graph2D graph, int sortOrder)
	{
		super(graph, sortOrder);
	}

	@Override
	public void drawElement(FieldArrow arrow, Graphics2D graphics)
	{
		drawArrow(arrow, graphics);
	}

	@Override
	public Class<FieldArrow> getDrawableType()
	{
		return FieldArrow.class;
	}

	/**
	 * Draws a rotated arrow using the provided Graphics object
	 * 
	 * @param arrow Arrow model data to display
	 * @param g     Graphics to use for drawing
	 */
	private static void drawArrow(FieldArrow arrow, Graphics2D g)
	{
		// New image to hold rotated instance
		BufferedImage newArrow = new BufferedImage(arrowImage.getWidth(),
				arrowImage.getHeight(), arrowImage.getType());

		// Create Graphics2D so we can manipulate the image
		Graphics2D arrowGraphics = newArrow.createGraphics();

		// Draw the base image and rotate it
		// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html#rotate-double-
		// Also:
		// https://stackoverflow.com/questions/8639567/java-rotating-images
		arrowGraphics.rotate(arrow.getDirection(), newArrow.getWidth() / 2,
				newArrow.getHeight() / 2);
		arrowGraphics.drawImage(arrowImage, null, 0, 0);

		// Cleanup Graphics object
		arrowGraphics.dispose();

		// Draw the image we just made
		int xCenter = (int) (arrow.getX() - (newArrow.getWidth() / 2));
		int yCenter = (int) (arrow.getY() - (newArrow.getHeight() / 2));
		g.drawImage(newArrow, null, xCenter, yCenter);
	}

}
