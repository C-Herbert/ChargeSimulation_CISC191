package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph object used to store and manage  a list of IGraphElements
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class Graph2D
{
	//Graph2Ds have a width and height
	private int width, height;
	//Graph2Ds have many graph elements
	List<IGraphElement> graphElements;
	
	/**
	 * Creates a new Graph2D object with its elements initialized.
	 * @param elements list of IGraphElements to initialize this Graph2D with
	 * @param width integer width to assign to new Graph2D
	 * @param height integer height to assign to new Graph2D
	 */
	public Graph2D(List<IGraphElement> elements, int width, int height)
	{
		//No copy because we expect/want other parts of the program to modify these elements
		this.graphElements = elements;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Creates a new Graph2D with an empty element list
	 * @param width integer width to assign to new Graph2D
	 * @param height integer height to assign to new Graph2D
	 */
	public Graph2D(int width, int height)
	{
		this(new ArrayList<IGraphElement>(), width, height);
	}
	
	/**
	 * Gets this Graph2D object's width field
	 * @return width of Graph2D object
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Gets this Graph2D object's height field
	 * @return height of Graph2D object
	 */
	public int getHeight()
	{
		return height;
	}
	
}
