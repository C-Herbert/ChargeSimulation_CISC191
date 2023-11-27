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
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Charge;
import model.ChargeGraph2D;
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
	 * Reads a text file and produces a ChargeGraph2D from properly formatted
	 * data.
	 * 
	 * @param file The file to read, should be a text file.
	 * @return The ChargeGraph2D that was read from the file parameter
	 * @throws
	 * @throws FileNotFoundException
	 */
	public static ChargeGraph2D readGraphFromFile(File file)
			throws GraphFileFormatException, IOException
	{
		// Open a new Scanner
		try (Scanner fileInput = new Scanner(new FileInputStream(file)))
		{
			// First two ints of file should be Graph's width and height.
			int width = fileInput.nextInt();
			int height = fileInput.nextInt();

			// Create a new graph with the previous width and height.
			ChargeGraph2D graph = new ChargeGraph2D(width, height);

			// Temporary loop variables;
			int inputID;
			double inputX;
			double inputY;

			// While we have another ID to read, continue adding elements
			while (fileInput.hasNextInt())
			{
				inputID = fileInput.nextInt();

				// First two inputs should always be x then y, regardless of
				// element type.
				inputX = fileInput.nextDouble();
				inputY = fileInput.nextDouble();

				switch (inputID)
				{
					case 1: // Charge
						// If we're reading a charge, there should be another
						// double available.
						graph.addElement(new Charge(inputX, inputY,
								fileInput.nextDouble()));
						break;
					case 2: // Arrow
						// If we're reading an arrow, we can ignore the
						// magnitude.
						graph.addElement(new FieldArrow(inputX, inputY, 1, 0));
						break;
					default:
						throw new GraphFileFormatException(
								"Invalid element id in file.");
				}
			}

			graph.updateFieldArrows();

			return graph;
		}
		catch (FileNotFoundException e)
		{
			// Rethrow.
			throw e;
		}
		catch (InputMismatchException e)
		{
			throw new GraphFileFormatException("Unexpected data type in file.");
		}
		catch (NoSuchElementException e)
		{
			throw new GraphFileFormatException(
					"File missing critical elements.");
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
		try (PrintWriter fileOutput = new PrintWriter(file))
		{
			// // Write the width and height of the graph as the first line.
			fileOutput.print(graph.getWidth() + " ");
			fileOutput.println(graph.getHeight());

			// Write each element's save data.
			for (IGraphElement element : elements)
			{
				// Not the most elegant, though I'd rather avoid reflection for
				// now.
				if (element instanceof Charge)
				{
					fileOutput.print(1 + " ");
					fileOutput.print(element.getX() + " ");
					fileOutput.print(element.getY() + " ");
					fileOutput.println(((Charge) element).getMagnitude());
				}
				else if (element instanceof FieldArrow)
				{
					fileOutput.print(2 + " ");
					fileOutput.print(element.getX() + " ");
					fileOutput.println(element.getY());
				}
			}
		}
	}

	// Shouldn't be making objects of GraphIO.
	private GraphIO()
	{
	}
}
