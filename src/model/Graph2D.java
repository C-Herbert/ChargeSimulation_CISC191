package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
	// Graph2Ds have many unique graph elements (Note: HashSet instead of Map
	// because key doesn't matter)
	private HashSet<IGraphElement> graphElements;

	/**
	 * Creates a new Graph2D with an empty element list
	 * 
	 * @param width  integer width to assign to new Graph2D
	 * @param height integer height to assign to new Graph2D
	 */
	public Graph2D(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.graphElements = new HashSet<IGraphElement>();
	}

	/**
	 * Creates a new Graph2D object with its elements initialized.
	 * 
	 * @param elements list of IGraphElements to initialize this Graph2D with
	 * @param width    integer width to assign to new Graph2D
	 * @param height   integer height to assign to new Graph2D
	 */
	public Graph2D(List<IGraphElement> elements, int width, int height)
	{
		this(width, height);

		// Initialize elements to provided list of IGraphElements
		for (IGraphElement element : elements)
		{
			// Don't copy the element as we want it to be modifiable by other
			// parts of the program
			graphElements.add(element);
		}
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
		// No deep copy. Graph should be able to handle any state of any
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
	
	public List<IGraphElement> getElements()
	{
		ArrayList<IGraphElement> elements = new ArrayList<IGraphElement>(graphElements.size());		
		
		//Only need to make a deep copy of the list, external modification of the elements themselves is okay
		for(IGraphElement e : graphElements)
		{
			elements.add(e);
		}

		return elements;
	}
	
}
