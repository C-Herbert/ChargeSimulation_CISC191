package view.elements;

import java.awt.Graphics2D;
import java.util.List;

import model.IGraphElement;

/**
 * Subclasses of ElementView allow a given IGraphElementType to be displayed on a Graph.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

//Trying to follow the model-view-controller approach,
//https://en.wikipedia.org/wiki/Model-view-controller

//Generic allows subclasses to work on only specific types of graph elements
public abstract class ElementView<T extends IGraphElement>
{
	//TODO: decide on collection type
	private List<T> elements;
	
	/**
	 * Creates a new element view using the provided list of elements.
	 * @param elements IGraphElements to include in this view.
	 */
	//Protected because we don't want this to be called on its subclasses, but they will need to call it
	protected ElementView(List<T> elements)
	{
		//No copy because we need to draw the elements as they change
		this.elements = elements;
	}
	
	public List<T> getElements()
	{
		//Again, no copy because we want to be able to modify the elements
		return elements;
	}
	
	/**
	 * Draws the elements included in this view using the provided graphics
	 * @param graphics Graphics2D object to be used for drawing the elements
	 */
	public abstract void draw(Graphics2D graphics);
}
