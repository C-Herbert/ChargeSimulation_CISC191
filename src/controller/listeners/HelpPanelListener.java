package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Charge;

/**
 * HelpPanelListener is an ActionListener responsible for displaying the help
 * menu.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class HelpPanelListener implements ActionListener
{
	/**
	 * HelpPanelListener has a string that holds all the help information
	 */
	private static final String HELP_STRING = "How to use this program:\n"
			+ " 1. Add elements from the toolbox on the right.\n"
			+ " 2. Drag elements around the graph.\n"
			+ " 3. Right click on charges to open an editor panel.\n"
			+ " 4. Delete elements by pressing \"DELETE\" on a graph element.\n"
			+ "     Charges can also be deleted by setting their magnitude to zero.\n"
			+ " 5. Adjust the field (white) arrow layout using the arrow layout\n"
			+ "     button on the upper toolbar.\n"
			+ " 6. Save/Load graphs with the upper toolbar buttons.\n"
			+ "\nAdditional Info:\n" + "Pixels To Meters Conversion Factor: "
			+ Charge.CHARGE_PIXELS_TO_METERS + " pixels/m";

	/**
	 * Invoked when an action occurs. Displays the help string in a JOptionPane.
	 * 
	 * @param e The event that prompted this listener.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Display the help info to the user in a dialog box.
		JOptionPane.showMessageDialog(null, HELP_STRING);
	}

}
