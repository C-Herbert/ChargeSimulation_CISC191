package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Graph class is used to store and manage a list of IGraphElements.
 * Responsibilities of the Graph class include adding and removing elements
 * as well as knowing its own width and height dimensions.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class Graph2D
{
	// Graph2Ds have a width and height
	private int width, height;
	// Graph2Ds have many graph elements
	private List<IGraphElement> graphElements;

	/**
	 * Creates a new Graph2D object with its elements initialized.
	 * 
	 * @param elements list of IGraphElements to initialize this Graph2D with
	 * @param width    integer width to assign to new Graph2D
	 * @param height   integer height to assign to new Graph2D
	 */
	public Graph2D(List<IGraphElement> elements, int width, int height)
	{
		if (elements != null)
		{
			// No copy because we expect/want other parts of the program to
			// modify these elements
			this.graphElements = elements;
		}
		else
		{
			// Fallback on an empty list if the programmer doesn't provide one
			this.graphElements = new ArrayList<IGraphElement>();
		}
		this.width = width;
		this.height = height;
	}

	/**
	 * Creates a new Graph2D with an empty element list
	 * 
	 * @param width  integer width to assign to new Graph2D
	 * @param height integer height to assign to new Graph2D
	 */
	public Graph2D(int width, int height)
	{
		this(new ArrayList<IGraphElement>(), width, height);
	}

	/**
	 * Gets this Graph2D object's width field
	 * 
	 * @return width of Graph2D object
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Gets this Graph2D object's height field
	 * 
	 * @return height of Graph2D object
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Adds a specific element to this graph.
	 * 
	 * @param element the IGraphElement to add to this graph
	 */
	public boolean addElement(IGraphElement element)
	{
		// Again, no deep copy. Graph should be able to handle any state of any
		// element passed in.
		return graphElements.add(element);
	}

	/**
	 * Removes a specific element from this graph.
	 * 
	 * @param element the IGraphElement to remove from this graph
	 * @return true if the graph contained the parameter element, false
	 *         otherwise
	 */
	public boolean removeElement(IGraphElement element)
	{
		return graphElements.remove(element);
	}
}
