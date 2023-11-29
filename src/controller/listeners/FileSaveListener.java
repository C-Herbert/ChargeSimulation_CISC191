package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import model.ChargeGraph2D;
import model.Graph2D;
import utils.io.GraphIO;
import view.ProgramView;

/**
 * FileSaveListener is a GraphActionListener responsible for handling saving
 * files to the system from the program.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

// References:
// https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html

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
		int response = view.getFileChooser().showSaveDialog(null);

		if (response == JFileChooser.APPROVE_OPTION)
		{
			File f = view.getFileChooser().getSelectedFile();
			// TODO: Better error handling
			try
			{
				GraphIO.writeGraphToFile(f, graph);
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
}
