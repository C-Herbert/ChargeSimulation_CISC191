package view.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import assets.ProgramAssets;
import model.Arrow2D;
import model.Charge;
import model.DraggableFieldArrow;
import model.FieldArrow;
import model.Graph2D;
import model.IGraphElement;
import utils.GraphicsUtils;

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
	public void drawElement(Arrow2D arrow, Graphics2D graphics)
	{
		drawArrow(arrow, graphics);
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
	private static void drawArrow(Arrow2D arrow, Graphics2D g)
	{
		//Draw a rotated arrow.
		if(arrow instanceof DraggableFieldArrow) 
		{
			GraphicsUtils.drawRotatedImage(draggableArrowImage, arrow.getX(), arrow.getY(), arrow.getDirection(), g);
		}
		else if(arrow instanceof FieldArrow) 
		{
			GraphicsUtils.drawRotatedImage(arrowImage, arrow.getX(), arrow.getY(), arrow.getDirection(), g);
		}
	}

}