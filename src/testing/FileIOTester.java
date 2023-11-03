package testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

import model.Charge;
import model.ChargeGraph2D;
import model.FieldArrow;
import model.Graph2D;
import model.IGraphElement;
import utils.io.GraphReader;
import utils.io.GraphWriter;
import view.ProgramView;

/**
 * A test class used to demonstrate file IO functionality.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class FileIOTester
{
	// Instance variables to hold view and graph, circumventing need for a
	// model.
	ProgramView view;
	ChargeGraph2D testGraph;

	// Run function for test program
	public void run()
	{
		// Initialize the view to use the charge board we just setup
		view = new ProgramView();
		Graph2D fileGraph = null;

		// TODO: Better error handling
		try
		{
			fileGraph = GraphReader
					.readGraphFromFile(new File("src/testing/TestGraph.txt"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		// Create a charge graph from the Graph2D we loaded in
		testGraph = new ChargeGraph2D(fileGraph.getElements(),
				fileGraph.getWidth(), fileGraph.getHeight());

		// Iterate through graph elements, updating arrow directions to match
		// their local net fields
		for (IGraphElement e : testGraph.getElements())
		{
			if (e instanceof FieldArrow)
			{
				((FieldArrow) e).setDirection(testGraph
						.getNetFieldAtPoint(e.getX(), e.getY()).getDirection());
			}
		}

		// Initialize the view to use the charge board we just setup
		view.initializeChargeBoard(testGraph);
		view.addFileSaveListener(new FileSaveListener());
		view.addFileOpenListener(new FileOpenListener());
	}

	// Listener classes for handling file IO.
	private class FileSaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Reference:
			// https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html
			int response = view.getFileSelector().showOpenDialog(null);

			if (response == JFileChooser.APPROVE_OPTION)
			{
				File f = view.getFileSelector().getSelectedFile();
				// TODO: Better error handling
				try
				{
					GraphWriter.writeGraphToFile(f, testGraph);
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	private class FileOpenListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Reference:
			// https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html
			int response = view.getFileSelector().showOpenDialog(null);

			if (response == JFileChooser.APPROVE_OPTION)
			{
				File f = view.getFileSelector().getSelectedFile();
				// TODO: Better error handling
				try
				{
					Graph2D fileGraph = GraphReader.readGraphFromFile(f);

					// Update testGraph with the file graph.
					testGraph.clearElements();
					testGraph.addElements(fileGraph.getElements());

					view.repaintGraph();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	// Main method to run tester
	public static void main(String[] args)
	{
		new FileIOTester().run();
	}
}
