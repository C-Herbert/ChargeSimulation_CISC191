package controller.listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.ChargeGraph2D;
import model.Graph2D;
import utils.io.GraphFileFormatException;
import utils.io.GraphIO;
import view.ProgramView;

/**
 * FileOpenListener is a GraphActionListener responsible for handling loading
 * files into the program.
 * 
 * References:
 * “JFileChooser.” Oracle Java Documentation, 4 2023,
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html.
 * Accessed 2 Nov. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class FileOpenListener extends GraphActionListener
{
	/**
	 * Creates a new FileOpenListener using the provided graph and view
	 * objects.
	 * 
	 * @param graph The graph to assign to this listener.
	 * @param view  The view to assign to this listener.
	 */
	public FileOpenListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	/**
	 * Invoked when an action occurs. Loads a file selected by the user to the
	 * graph display.
	 * 
	 * @param e The event that prompted this listener.
	 */
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

			// Declare a variable to hold the file selected by the user.
			File file = view.getFileChooser().getSelectedFile();

			try
			{
				Graph2D fileGraph = GraphIO.readGraphFromFile(file);

				// Update testGraph with the file graph.
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
		}
	}

}
