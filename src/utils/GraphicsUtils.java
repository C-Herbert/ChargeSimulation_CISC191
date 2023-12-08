package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;

/**
 * The GraphicsUtils class contains a variety of helper functions for drawing
 * images.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

// References:
// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html#rotate-double-
// https://stackoverflow.com/questions/8639567/java-rotating-images

public final class GraphicsUtils
{
	/**
	 * Adjusts the alpha value of a BufferedImage by multiplying each pixel's
	 * alpha by the provided alphaPercent.
	 * 
	 * @param img          The image to adjust the alpha of
	 * @param alphaPercent The value to multiply each pixel's current alpha by.
	 */
	public static void adjustImageAlpha(BufferedImage img, double alphaPercent)
	{
		if (alphaPercent >= 1) return;

		// Get the alpha raster of the passed image.
		WritableRaster alphaChannel = img.getAlphaRaster();
		// Ensure that the image actually has an alpha channel.
		if (alphaChannel == null) return;

		// Retrieve the data buffer for the image's alpha channel.
		DataBuffer alphaBuffer = alphaChannel.getDataBuffer();

		// Update each pixel's alpha value.
		for (int i = 0; i < alphaBuffer.getSize(); ++i)
		{
			alphaBuffer.setElemDouble(i, alphaBuffer.getElem(i) * alphaPercent);
		}
	}

	/**
	 * Draws a rotated instance of an image at a given position. Also adjusts
	 * the alpha of the image instance prior to drawing.
	 * 
	 * @param baseImage The base image to draw. Will not be modified by this
	 *                  function.
	 * @param g         The Graphics instance to use to draw the final image.
	 * @param x         The x position at which the image will be drawn.
	 * @param y         The y position at which the image will be drawn.
	 * @param alpha     The alpha adjustment to apply to the image.
	 * @param rotation  The rotation to apply to the image.
	 */
	public static void drawAlphaRotatedImage(BufferedImage baseImage,
			Graphics2D g, double x, double y, double alpha, double rotation)
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

		// Adjust the image's overall alpha value.
		adjustImageAlpha(newImage, alpha);

		// Draw the image we just made
		int xCenter = (int) (x - (newImage.getWidth() / 2));
		int yCenter = (int) (y - (newImage.getHeight() / 2));
		g.drawImage(newImage, null, xCenter, yCenter);
	}

	// Should not be making instances of GraphicsUtils.
	private GraphicsUtils()
	{
	}
}
