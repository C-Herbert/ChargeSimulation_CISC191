package controller.listeners;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Charge;
import model.ChargeGraph2D;
import view.ProgramView;

public class AddChargeListener extends GraphActionListener
{
	JFrame creationDialogFrame = new JFrame("Add Charge");
	JPanel creationDialogPanel = new JPanel();

	JPanel inputPanel = new JPanel();
	JTextField xPositionField = new JTextField(15);
	JTextField yPositionField = new JTextField(15);
	JTextField magnitudeField = new JTextField(15);

	JButton cancelButton = new JButton("Cancel");
	JButton createButton = new JButton("Add Charge");

	public AddChargeListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);

		creationDialogPanel.setLayout(new BorderLayout());

		inputPanel.setLayout(new GridLayout(0, 2));
		inputPanel.add(new JLabel("X Position: "));
		inputPanel.add(xPositionField);
		inputPanel.add(new JLabel("Y Position: "));
		inputPanel.add(yPositionField);
		inputPanel.add(new JLabel("Magnitude: "));
		inputPanel.add(magnitudeField);
		creationDialogPanel.add(inputPanel, BorderLayout.CENTER);

		JPanel decisionPanel = new JPanel();
		createButton.addActionListener(this);
		cancelButton.addActionListener(this);
		decisionPanel.add(createButton);
		decisionPanel.add(cancelButton);
		creationDialogPanel.add(decisionPanel, BorderLayout.SOUTH);

		creationDialogFrame.add(creationDialogPanel);
		creationDialogFrame.setAlwaysOnTop(true);
		creationDialogFrame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{	
		// There are 3 possible cases
		if (e.getSource().equals(cancelButton))
		{
			// User chose to exit, hide frame.
			creationDialogFrame.setVisible(false);
		}
		else if (e.getSource().equals(createButton))
		{
			// User chose to add the charge.
			try
			{
				double x = Double.parseDouble(xPositionField.getText());
				double y = Double.parseDouble(yPositionField.getText());
				double mag = Double.parseDouble(magnitudeField.getText());

				// If inputs were successfully gathered, add a new charge to the
				// graph.
				Charge c = new Charge(x, y, mag);
				graph.addElement(c);
				view.repaintGraph();
			}
			catch (NumberFormatException e1)
			{
				// User entered invalid data, display warning
				JOptionPane.showMessageDialog(view.getFrame(),
						"Invalid inputs! Please confirm that all fields match the appropriate data type.",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
			// Finally, hide the frame.
			creationDialogFrame.setVisible(false);
		}
		else
		{
			// User hit a button that should open the frame (Any other source).

			// If the source was a JComponent, position the window such that
			// it is adjacent to the source.
			if (e.getSource() instanceof JComponent)
			{
				Point sourcePoint = ((JComponent) e.getSource())
						.getLocationOnScreen();

				creationDialogFrame.setLocation(
						sourcePoint.x - creationDialogFrame.getWidth(),
						sourcePoint.y);
			}

			// Display the frame.
			creationDialogFrame.setVisible(true);
		}
	}

}
