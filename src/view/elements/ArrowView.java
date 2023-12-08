package view.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import assets.ProgramAssets;
import model.Arrow2D;
import model.Charge;
import model.DraggableFieldArrow;
import model.FieldArrow;
import model.Graph2D;
import model.IGraphElement;
import utils.GraphicsUtils;
import utils.Vec2D;

/**
 * The FieldArrowView class is an ElementView responsible for drawing all
 * FieldArrows on a graph using a provided Graphics object.
 * 
 * @author Charlie Herbert
 * @version 1.1
 */
public class ArrowView extends ElementView<Arrow2D>
{
	// Static fields for arrow images.
	private static BufferedImage arrowImage = ProgramAssets
			.getAsset("field_arrow");

	private static BufferedImage draggableArrowImage = ProgramAssets
			.getAsset("green_arrow");
	
	/**
	 * Constructs a new FieldArrowView using the provided graph and assigns it
	 * the passed sortOrder.
	 * 
	 * @param graph     the graph to associate this view with.
	 * @param sortOrder the sortOrder to assign to this view.
	 */
	public ArrowView(Graph2D graph, int sortOrder)
	{
		super(graph, sortOrder);
	}
	
	@Override
	// Not used by this view
	public void drawElement(Arrow2D arrow, Graphics2D graphics)
	{
		drawArrow(arrow, graphics, 1);
	}
	
	/**
	 * Draws all Arrow2Ds that are contained in this view's associated graph.
	 * 
	 * This method first samples all charges on the graph, searching for the
	 * highest field magnitude. The magnitude is then used to calculate the
	 * alpha value for each arrow.
	 * 
	 * @param graphics The graphics object to use to draw the arrows.
	 */
	@Override
	public void draw(Graphics2D graphics)
	{
		// Temporary variables for the charge loop
		double maxAbsMagnitude = 0.0;
		double localMax = 0.0;

		for (IGraphElement c : graph.getElementsOfType(Charge.class))
		{
			// Sample a point 100pixels (1cm) away from the charge
			localMax = ((Charge) c).getFieldAtPoint(c.getX(), c.getY() + 100)
					.getMagnitude();

			// Is the sample greater than the previous max value?
			if (localMax > maxAbsMagnitude)
			{
				maxAbsMagnitude = localMax;
			}
		}

		// Temporary variable for the arrow loop.
		double alphaValue = 0;

		// Finally, draw the arrows.
		for (IGraphElement arrow : graph.getElementsOfType(Arrow2D.class))
		{
			// Note that the alpha value is a fraction of the maximum magnitude
			// present.
			// Casts are safe because getElementsOfType ensures only Arrow2Ds
			// appear here.
			alphaValue = (Math.sqrt(((Vec2D) arrow).getMagnitude())
					/ Math.sqrt(maxAbsMagnitude));
			drawArrow((Arrow2D) arrow, graphics, alphaValue);
		}
	}

	@Override
	public Class<Arrow2D> getDrawableType()
	{
		return Arrow2D.class;
	}
	
	/**
	 * Draws a rotated arrow using the provided Graphics object
	 * 
	 * @param arrow Arrow model data to display
	 * @param g     Graphics to use for drawing
	 */
	private static void drawArrow(Arrow2D arrow, Graphics2D g, double alpha)
	{
		// Draw a rotated arrow.
		if (arrow instanceof DraggableFieldArrow)
		{
			GraphicsUtils.drawAlphaRotatedImage(draggableArrowImage, g,
					arrow.getX(), arrow.getY(), 1, arrow.getDirection());
		}
		else if (arrow instanceof FieldArrow)
		{
			GraphicsUtils.drawAlphaRotatedImage(arrowImage, g, arrow.getX(),
					arrow.getY(), alpha, arrow.getDirection());
		}
	}

}
