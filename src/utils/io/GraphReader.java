package utils.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Charge;
import model.FieldArrow;
import model.Graph2D;

/**
 * The GraphReader class is used to read Graph2D data from a text file.
 * 
 * Work in progress, expect significant changes to file formatting.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class GraphReader
{
	/**
	 * Reads a text file and produces a Graph2D from properly formatted data.
	 * 
	 * @param file The file to read, should be a text file.
	 * @return The Graph2D that was read from the file parameter
	 * @throws FileNotFoundException
	 */
	public static Graph2D readGraphFromFile(File file)
			throws FileNotFoundException
	{
		// Open a new Scanner
		try (Scanner fileInput = new Scanner(file))
		{
			// First two ints of file should be Graph's width and height.
			int width = fileInput.nextInt();
			int height = fileInput.nextInt();

			// Create a new graph with the previous width and height.
			Graph2D graph = new Graph2D(width, height);

			// While we have another ID to read, continue adding elements
			while (fileInput.hasNextInt())
			{
				// Basic input system, elements are read in based on a numerical
				// ID in the file.
				int elementID = fileInput.nextInt();

				switch (elementID)
				{
					// Charge
					case 0:
						graph.addElement(new Charge(fileInput.nextDouble(),
								fileInput.nextDouble(),
								fileInput.nextDouble()));
						break;
					// FieldArrow
					case 1:
						graph.addElement(new FieldArrow(fileInput.nextDouble(),
								fileInput.nextDouble(), fileInput.nextDouble(),
								fileInput.nextDouble()));
						break;
				}
			}

			return graph;
		}
		catch (FileNotFoundException e)
		{
			// Rethrow.
			throw e;
		}
	}
}
