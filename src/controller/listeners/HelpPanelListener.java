package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * FileSaveListener is an ActionListener responsible for displaying the help
 * menu.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class HelpPanelListener implements ActionListener
{
	// String that holds all the help information
	private static final String HELP_STRING = "How to use this program:\n"
			+ " 1. Add elements from the toolbox on the right.\n"
			+ " 2. Drag elements around the graph.\n"
			+ " 3. Right click on charges to open an editor panel.\n"
			+ " 4. Delete elements _TODO_\n"
			+ " 5. Save/Load graphs with the upper toolbar buttons.";

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Display the help info to the user in a dialog box.
		JOptionPane.showMessageDialog(null, HELP_STRING);
	}

}