package view.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.FieldArrow;
import model.IGraphElement;

/**
 * Responsible for drawing FieldArrows on a Graph
 * 
 * @version 1.0
 * @author Charlie Herbert
 */
public class FieldArrowView extends ElementView<FieldArrow>
{
	// File path to field arrow image.
	private static final String ARROW_FILEPATH = "src/assets/arrow.png";
	// Static field for arrow image once loaded.
	private static BufferedImage arrowImage;

	// TODO: better alternative?
	static
	{
		try
		{
			// https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
			arrowImage = ImageIO.read(new File(ARROW_FILEPATH));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void drawElement(FieldArrow arrow, Graphics2D graphics)
	{
		drawArrow(arrow, graphics);
	}
	
	@Override
	public boolean canDraw(IGraphElement element)
	{
		return element instanceof FieldArrow;
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
