package utils.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Graph2D;
import model.IGraphElement;

public class GraphWriter
{
	public static void writeGraphToFile(File file, Graph2D graph)
	{
		//Gather the graph's elements.
		List<IGraphElement> elements = graph.getElements();
		
		//Open a new file writer. Note that we do not append to the file.
		try(FileWriter fw = new FileWriter(file, false))
		{
			for(IGraphElement e : elements)
			{
				fw.write(e.toSaveString() + '\n');
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
