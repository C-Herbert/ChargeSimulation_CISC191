package view.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import assets.ProgramAssets;
import model.Charge;
import model.Graph2D;

/**
 * The ChargeView class is an ElementView responsible for drawing all Charges on
 * a graph using a provided Graphics object.
 * 
 * @author Charlie Herbert
 * @version 1.1
 */
public class ChargeView extends ElementView<Charge>
{
	/**
	 * ChargeView has a static field for the positive charge image.
	 */
	private static BufferedImage positiveChargeImage = ProgramAssets
			.getAsset("positive_charge");
	/**
	 * ChargeView has a static field for the negative charge image.
	 */
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

	/**
	 * Draws a charge using the provided graphics
	 * 
	 * @param charge   The charge to draw.
	 * @param graphics Graphics2D object to be used for drawing the elements
	 */
	@Override
	protected void drawElement(Charge charge, Graphics2D g)
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

	/**
	 * Gets the subclasses of IGraphElement that can be drawn by this view
	 * 
	 * @return The class object of the type that can be drawn by this view.
	 */
	@Override
	public Class<Charge> getDrawableType()
	{
		return Charge.class;
	}
}
