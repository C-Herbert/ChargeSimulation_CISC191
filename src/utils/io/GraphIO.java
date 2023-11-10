package utils.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import model.Charge;
import model.FieldArrow;
import model.Graph2D;
import model.IGraphElement;

/**
 * The GraphIO class is used to write Graph2D data to a text file.
 * 
 * Work in progress, expect significant changes to file formatting.
 * 
 * @version 1.0
 * @author Charlie Herbert
 * 
 *         References: Java, Java, Java 3rd Edition, Chapter 11
 * 
 *         https://docs.oracle.com/javase/8/docs/technotes/guides/serialization/examples/custom/CustomDataExample.java
 */
public final class GraphIO
{
	/**
	 * Reads a text file and produces a Graph2D from properly formatted data.
	 * 
	 * @param file The file to read, should be a text file.
	 * @return The Graph2D that was read from the file parameter
	 * @throws
	 * @throws FileNotFoundException
	 */
	public static Graph2D readGraphFromFile(File file)
			throws GraphFileFormatException, IOException
	{
		// Open a new Scanner
		try (DataInputStream fileInput = new DataInputStream(
				new FileInputStream(file)))
		{
			// First two ints of file should be Graph's width and height.
			// int width = fileInput.readInt();
			// int height = fileInput.readInt();
			//
			// // Create a new graph with the previous width and height.
			// Graph2D graph = new Graph2D(width, height);

			// While we have another ID to read, continue adding elements
			ObjectInputStream objectStream = new ObjectInputStream(fileInput);
			// try
			// {
			// while (true)
			// {
			// Object o = objectStream.readObject();
			//
			// if (o instanceof IGraphElement)
			// {
			// graph.addElement((IGraphElement)o);
			// }
			// }
			// }
			// catch (EOFException e)
			// {
			//
			// }
			// catch (ClassNotFoundException e)
			// {
			// e.printStackTrace();
			// }

			return (Graph2D) objectStream.readObject();
		}
		catch (FileNotFoundException e)
		{
			// Rethrow.
			throw e;
		}
		catch (InvalidClassException e)
		{
			// Rethrow as a graph file format exception
			throw new GraphFileFormatException(e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Writes a Graph2D to a text file.
	 * 
	 * @param file  The file to write data to.
	 * @param graph The graph data to write to the file parameter.
	 * @throws IOException
	 */
	public static void writeGraphToFile(File file, Graph2D graph)
			throws IOException
	{
		// Gather the graph's elements.
		List<IGraphElement> elements = graph.getElements();

		// Open a new data output stream. Note that we do not append to the
		// file.
		try (DataOutputStream fileOutput = new DataOutputStream(
				new FileOutputStream(file, false)))
		{
			// // Write the width and height of the graph as the first line.
			// fileOutput.writeInt(graph.getWidth());
			// fileOutput.writeInt(graph.getHeight());

			// Write each element's save string.
			ObjectOutputStream objectStream = new ObjectOutputStream(
					fileOutput);

			objectStream.writeObject(graph);

			// for (IGraphElement e : elements)
			// {
			// if (e instanceof Serializable)
			// {
			// objectStream.writeObject(e);
			// objectStream.flush();
			// }
			// }
		}
	}

	// Shouldn't be making objects of GraphIO.
	private GraphIO()
	{
	}
}
