package model;

/**
 * The DraggableFieldArrow class is a specific type of Arrow2D used to
 * represent field vectors on a graph.
 * 
 * The DraggableFieldArrow can be dragged by the user, unlike FieldArrow.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */
public class DraggableFieldArrow extends FieldArrow
{
	/**
	 * Constructs a new DraggableFieldArrow object with the given position and
	 * component arguments.
	 * 
	 * @param xPos the x position of this arrow on the graph
	 * @param yPos the y position of this arrow on the graph
	 * @param xMag the magnitude of this arrow's vector x component
	 * @param yMag the magnitude of this arrow's vector y component
	 */
	public DraggableFieldArrow(double xPos, double yPos, double xMag,
			double yMag)
	{
		super(xPos, yPos, xMag, yMag);
	}

}
