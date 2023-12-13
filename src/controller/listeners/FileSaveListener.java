package controller.listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.ChargeGraph2D;
import utils.io.GraphIO;
import view.ProgramView;

/**
 * FileSaveListener is a GraphActionListener responsible for handling saving
 * files to the system from the program.
 * 
 * References:
 * “JFileChooser.” Oracle Java Documentation, 4 2023,
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html.
 * Accessed 2 Nov. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public class FileSaveListener extends GraphActionListener
{
	/**
	 * Creates a new FileSaveListener using the provided graph and view
	 * objects.
	 * 
	 * @param graph The graph to assign to this listener.
	 * @param view  The view to assign to this listener.
	 */
	public FileSaveListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Prompt the user to select a file and hold their response in a
		// variable
		int response = view.getFileChooser().showSaveDialog(null);

		if (response == JFileChooser.APPROVE_OPTION)
		{
			// Declare a variable to hold the file selected by the user.
			File file = view.getFileChooser().getSelectedFile();
			
			try
			{
				// Attempt to write the current graph to the file.
				GraphIO.writeGraphToFile(file, graph);
			}
			catch (FileNotFoundException e1)
			{
				// User selected an invalid file, warn them.
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
