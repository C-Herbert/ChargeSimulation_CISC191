package utils.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Graph2D;
import model.IGraphElement;

/**
 * The GraphWriter class is used to write Graph2D data to a text file.
 * 
 * Work in progress, expect significant changes to file formatting.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */
public class GraphWriter
{
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

		// Open a new file writer. Note that we do not append to the file.
		try (FileWriter fw = new FileWriter(file, false))
		{
			// Write the width and height of the graph as the first line.
			fw.write(String.format("%d %d\n", graph.getWidth(),
					graph.getHeight()));

			// Write each element's save string.
			for (IGraphElement e : elements)
			{
				fw.write(e.toSaveString() + '\n');
			}
		}
	}
}
