package view.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Charge;
import model.IGraphElement;

public class ChargeView extends ElementView<Charge>
{
	// File path to positive charge image.
	private static final String POSITIVE_CHARGE_FILEPATH = "src/assets/positive_charge.png";
	// Static field for positive charge image once loaded.
	private static BufferedImage positiveChargeImage;

	// File path to positive charge image.
	private static final String NEGATIVE_CHARGE_FILEPATH = "src/assets/negative_charge.png";
	// Static field for positive charge image once loaded.
	private static BufferedImage negativeChargeImage;

	// TODO: better alternative?
	static
	{
		try
		{
			// https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
			positiveChargeImage = ImageIO
					.read(new File(POSITIVE_CHARGE_FILEPATH));
			negativeChargeImage = ImageIO
					.read(new File(NEGATIVE_CHARGE_FILEPATH));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void drawElement(Charge charge, Graphics2D g)
	{
		// Select the correct image for the charge's magnitude
		BufferedImage baseImage = charge.getMagnitude() < 0
				? negativeChargeImage
				: positiveChargeImage;

		// Calculate the where the image should be drawn to center it on the
		// charge's position
		int xCenter = (int) (charge.getX() - (baseImage.getWidth() / 2));
		int yCenter = (int) (charge.getY() - (baseImage.getHeight() / 2));

		// Draw the image
		g.drawImage(baseImage, null, xCenter, yCenter);
	}

	@Override
	public boolean canDraw(IGraphElement element)
	{
		return element instanceof Charge;
	}

}
