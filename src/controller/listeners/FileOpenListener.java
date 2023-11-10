package controller.listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Graph2D;
import utils.io.GraphFileFormatException;
import utils.io.GraphIO;
import view.ProgramView;

/**
 * FileOpenListener is a GraphActionListener responsible for handling loading
 * files into the program.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

// References:
// https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html

public class FileOpenListener extends GraphActionListener
{
	public FileOpenListener(Graph2D graph, ProgramView view)
	{
		super(graph, view);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int response = view.getFileChooser().showOpenDialog(null);

		if (response == JFileChooser.APPROVE_OPTION)
		{
			// Display warning before overwriting current graph
			response = JOptionPane.showConfirmDialog(null,
					"Loading a graph will overwrite any current graph data, are you sure you want to continue?");

			if (response != JOptionPane.YES_OPTION)
			{
				// User canceled before committing to load operation, quit out.
				return;
			}

			File f = view.getFileChooser().getSelectedFile();

			try
			{
				Graph2D fileGraph = GraphIO.readGraphFromFile(f);

				// Update testGraph with the file graph.
				// TODO: load size
				graph.clearElements();
				graph.addElements(fileGraph.getElements());

				view.repaintGraph();
			}
			catch (GraphFileFormatException e1)
			{
				// File format was incorrect, inform user of the issue.
				JOptionPane.showMessageDialog(null,
						"File could not be loaded due to a formatting error. See below for details: \n"
								+ e1.getMessage(),
						"Error Loading File", JOptionPane.ERROR_MESSAGE);
			}
			catch (FileNotFoundException e1)
			{
				JOptionPane.showMessageDialog(null, "That file doesn't exist!",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
			catch (IOException e1)
			{
				// Misc exception, print stack trace.
				e1.printStackTrace();
			}
		}
	}

}
