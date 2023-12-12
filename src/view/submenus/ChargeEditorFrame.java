package view.submenus;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Charge;

/**
 * The ChargeEditorFrame is a subclass of JFrame used to gather user inputs for
 * editing or creating Charge objects.
 * 
 * @author Charlie Herbert
 * @version 1.0.
 */

public class ChargeEditorFrame extends JFrame
{
	// ChargeEditorFrame is serializable, thus it needs a UID.
	private static final long serialVersionUID = -1505621340036308990L;

	// ChargeEditorFrame has a variety of Swing components for display fields to
	// the user.
	private JPanel creationDialogPanel = new JPanel();
	private JPanel inputPanel = new JPanel();
	private JTextField xPositionField = new JTextField(15);
	private JTextField yPositionField = new JTextField(15);
	private JTextField magnitudeField = new JTextField(15);
	private JButton cancelButton = new JButton("Cancel");
	private JButton confirmButton = new JButton("Confirm");

	/**
	 * Creates a new ChargeEditorFrame with the specified title.
	 * 
	 * @param title The title to assign to the frame.
	 */
	public ChargeEditorFrame(String title)
	{
		super(title);

		// Setup the fields for gathering user input.
		inputPanel.setLayout(new GridLayout(0, 2));
		inputPanel.add(new JLabel("X Position: "));
		inputPanel.add(xPositionField);
		xPositionField.setText("100");
		inputPanel.add(new JLabel("Y Position: "));
		inputPanel.add(yPositionField);
		yPositionField.setText("100");
		inputPanel.add(new JLabel("Magnitude: "));
		inputPanel.add(magnitudeField);
		magnitudeField.setText("100");

		// Setup the main panel.
		creationDialogPanel.setLayout(new BorderLayout());
		creationDialogPanel.add(inputPanel, BorderLayout.CENTER);

		// Setup the decision panel.
		JPanel decisionPanel = new JPanel();
		decisionPanel.add(confirmButton);
		decisionPanel.add(cancelButton);
		creationDialogPanel.add(decisionPanel, BorderLayout.SOUTH);

		// Prepare this frame for display.
		this.add(creationDialogPanel);
		this.setAlwaysOnTop(true);
		this.pack();
	}

	/**
	 * Returns the user's x input in the charge input frame.
	 * 
	 * @return a double x position value.
	 * @throws NumberFormatException if the user's input could not be parsed as
	 *                               a double.
	 */
	public double getXInput() throws NumberFormatException
	{
		return Double.parseDouble(xPositionField.getText());
	}

	/**
	 * Returns the user's y input in the charge input frame.
	 * 
	 * @return a double y position value.
	 * @throws NumberFormatException if the user's input could not be parsed as
	 *                               a double.
	 */
	public double getYInput() throws NumberFormatException
	{
		return Double.parseDouble(yPositionField.getText());
	}

	/**
	 * Returns the user's magnitude input in the charge input frame.
	 * 
	 * @return a double magnitude value.
	 * @throws NumberFormatException if the user's input could not be parsed as
	 *                               a double.
	 */
	public double getMagnitudeInput() throws NumberFormatException
	{
		return Double.parseDouble(magnitudeField.getText());
	}

	/**
	 * Creates a Charge object from the user's current inputs.
	 * 
	 * @return a new Charge object if all inputs are valid, null otherwise.
	 */
	public Charge getChargeInput()
	{
		try
		{
			// If inputs were successfully gathered, return a new charge.
			return new Charge(getXInput(), getYInput(), getMagnitudeInput());
		}
		catch (NumberFormatException e1)
		{
			// User entered invalid data, display warning.
			JOptionPane.showMessageDialog(this,
					"Invalid inputs! Please confirm that all fields match the appropriate data type.",
					"Warning", JOptionPane.WARNING_MESSAGE);
			// No data to return.
			return null;
		}
	}

	/**
	 * Gets the cancel button associated with this frame. It is recommended
	 * that you only use this function for assigning action listeners and
	 * checking event sources.
	 * 
	 * @return the cancel button associated with this frame.
	 */
	public JButton getCancelButton()
	{
		return cancelButton;
	}

	/**
	 * Gets the confirm button associated with this frame. It is recommended
	 * that you only use this function for assigning action listeners and
	 * checking event sources.
	 * 
	 * @return the confirm button associated with this frame.
	 */
	public JButton getConfirmButton()
	{
		return confirmButton;
	}

	/**
	 * Populates the editor frame with the passed Charge's information.
	 * 
	 * @param c the Charge to load information from.
	 */
	public void loadChargeInfo(Charge c)
	{
		xPositionField.setText("" + c.getX());
		yPositionField.setText("" + c.getY());
		magnitudeField.setText("" + c.getMagnitude());
	}
}
