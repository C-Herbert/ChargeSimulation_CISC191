package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
	protected int width, height;
	// Graph2Ds have many unique graph elements
	protected HashMap<Class<? extends IGraphElement>, List<IGraphElement>> graphElements;
	
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
		this.graphElements = new HashMap<>();
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
	public void addElement(IGraphElement element)
	{
		// No deep copy for element. Graph should be able to handle any state
		// of any element passed in.

		// Check if we already have a list that could hold element.
		if (graphElements.containsKey(element.getClass()))
		{
			// Add the element if we found a suitable list.
			graphElements.get(element.getClass()).add(element);
		}
		else
		{
			// If we don't, create a new list and pair it with the appropriate
			// key in graphElements.
			ArrayList<IGraphElement> newElementList = new ArrayList<IGraphElement>();
			newElementList.add(element);

			graphElements.put(element.getClass(), newElementList);
		}
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
		// Check if the elements list even contains a matching type.
		if (graphElements.containsKey(element.getClass()))
		{
			// Reference to the appropriate list
			List<IGraphElement> elementList = graphElements
					.get(element.getClass());

			// Check if list contains desired element
			if (elementList.contains(element))
			{
				// Successfully found element, remove it and return true
				elementList.remove(element);
				return true;
			}
			else
			{
				// The list in graphElements matching the type of element does
				// not contain element, thus it could not be removed.
				return false;
			}
		}
		else
		{
			// graphElements doesn't even contain a list for the passed type
			return false;
		}
	}

	/**
	 * Gets a list of the elements contained by this graph.
	 * 
	 * Note that the elements are not copies, only the list itself. This
	 * prevents unexpected adding/removing of elements but still allows element
	 * manipulation.
	 * 
	 * @return A list of the elements managed by this graph.
	 */
	public List<IGraphElement> getElements()
	{
		List<IGraphElement> totalElements = new ArrayList<IGraphElement>();

		for (List<IGraphElement> list : graphElements.values())
		{
			totalElements.addAll(list);
		}

		return totalElements;
	}

	/**
	 * Gets all elements contained by this graph that are of a specific type.
	 * 
	 * @return A list of elements matching the type parameter that are managed
	 *         by this graph.
	 */
	public List<IGraphElement> getElementsOfType(
			Class<? extends IGraphElement> type)
	{
		// First, we gather all elements that match the type
		List<IGraphElement> matchingElements = new ArrayList<>();

		// Iterate through all sub-lists of graphElements.
		for (Class<? extends IGraphElement> c : graphElements.keySet())
		{
			// Check if the sub-list's type matches or is a superclass of the
			// type parameter.
			if (type.isAssignableFrom(c))
			{
				// If so, add the sub-list's elements to our matching list
				matchingElements.addAll(graphElements.get(c));
			}
		}

		return matchingElements;
	}

	/**
	 * Clears all graph elements contained in this graph.
	 */
	public void clearElements()
	{
		this.graphElements.clear();
	}

	/**
	 * Adds a list of elements to this graph.
	 * 
	 * @param elements List of elements to add to graph
	 */
	public void addElements(List<IGraphElement> elements)
	{
		for (IGraphElement element : elements)
		{
			addElement(element);
		}
	}

	/**
	 * Prints the elements of this graph in a readable format.
	 */
	public void printElements()
	{
		for (Entry<Class<? extends IGraphElement>, List<IGraphElement>> entry : graphElements
				.entrySet())
		{
			System.out.print(
					"Entry \'" + entry.getKey().getName() + "\' contains: ");

			for (IGraphElement e : entry.getValue())
			{
				System.out.print(e + ", ");
			}

			System.out.println();
		}
	}
}
