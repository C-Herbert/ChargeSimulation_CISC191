package view.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import assets.ProgramAssets;
import model.Charge;
import model.Graph2D;
import model.IGraphElement;

/**
 * The ChargeView class is an ElementView responsible for drawing all Charges on
 * a graph using a provided Graphics object.
 * 
 * @author Charlie Herbert
 * @version 1.1
 */
public class ChargeView extends ElementView<Charge>
{
	// Static field for positive charge image.
	private static BufferedImage positiveChargeImage = ProgramAssets
			.getAsset("positive_charge");
	// Static field for positive charge image.
	private static BufferedImage negativeChargeImage = ProgramAssets
			.getAsset("negative_charge");

	/**
	 * Constructs a new ChargeView using the provided graph and assigns it
	 * the passed sortOrder.
	 * 
	 * @param graph     the graph to associate this view with.
	 * @param sortOrder the sortOrder to assign to this view.
	 */
	public ChargeView(Graph2D graph, int sortOrder)
	{
		super(graph, sortOrder);
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
	public Class<Charge> getDrawableType()
	{
		return Charge.class;
	}
}
