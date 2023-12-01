package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GraphicsUtils
{
	public static void drawRotatedImage(BufferedImage baseImage, double x,
			double y, double rotation, Graphics2D g)
	{
		// New image to hold rotated instance
		BufferedImage newImage = new BufferedImage(baseImage.getWidth(),
				baseImage.getHeight(), baseImage.getType());

		// Create Graphics2D so we can manipulate the image
		Graphics2D newGraphics = newImage.createGraphics();

		// Draw the base image and rotate it
		// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html#rotate-double-
		// Also:
		// https://stackoverflow.com/questions/8639567/java-rotating-images
		newGraphics.rotate(rotation, newImage.getWidth() / 2,
				newImage.getHeight() / 2);
		newGraphics.drawImage(baseImage, null, 0, 0);

		// Cleanup Graphics object
		newGraphics.dispose();

		// Draw the image we just made
		int xCenter = (int) (x - (newImage.getWidth() / 2));
		int yCenter = (int) (y - (newImage.getHeight() / 2));
		g.drawImage(newImage, null, xCenter, yCenter);
	}
}
