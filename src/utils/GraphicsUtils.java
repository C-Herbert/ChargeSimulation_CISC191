package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;

public class GraphicsUtils
{
	public static void adjustImageAlpha(BufferedImage img, double alphaPercent)
	{
		if(alphaPercent >= 1) return;
		
		WritableRaster alphaChannel = img.getAlphaRaster();

		if (alphaChannel == null) return;

		DataBuffer alphaBuffer = alphaChannel.getDataBuffer();

		for (int i = 0; i < alphaBuffer.getSize(); ++i)
		{
			alphaBuffer.setElemDouble(i, alphaBuffer.getElem(i) * alphaPercent);
		}
	}

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
}
