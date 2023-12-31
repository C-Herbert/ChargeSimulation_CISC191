package controller.listeners;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import model.Charge;
import model.ChargeGraph2D;
import view.ProgramView;
import view.submenus.ChargeEditorFrame;

/**
 * AddChargeListener is an GraphActionListener responsible for handling
 * adding charges to the graph.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class AddChargeListener extends GraphActionListener
{
	/**
	 * AddChargeListener has a ChargeEditorFrame, which is used to gather user
	 * input.
	 */
	ChargeEditorFrame chargeFrame;

	/**
	 * Creates a new AddChargeListener, which handles charge creation.
	 * 
	 * @param graph the graph to assign to this GraphActionListener.
	 * @param view  the graph to assign to this GraphActionListener.
	 */
	public AddChargeListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
		// Setup the editor frame.
		chargeFrame = new ChargeEditorFrame("Add Charge");

		// Setup confirm/cancel inputs.
		chargeFrame.getConfirmButton().addActionListener(this);
		chargeFrame.getCancelButton().addActionListener(this);
	}

	/**
	 * Invoked when an action occurs. Adds a charge to this listener's graph
	 * using the inputs provided in the chargeFrame's fields.
	 * 
	 * @param e The event that prompted this listener.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// There are 3 possible cases
		if (e.getSource().equals(chargeFrame.getCancelButton()))
		{
			// User chose to exit, hide frame.
			chargeFrame.setVisible(false);
		}
		else if (e.getSource().equals(chargeFrame.getConfirmButton()))
		{
			// User chose to add the charge.
			Charge newCharge = chargeFrame.getChargeInput();

			if (newCharge != null)
			{
				graph.addElement(newCharge);
				graph.updateFieldArrows();
				view.repaintGraph();
			}

			// Finally, hide the frame.
			chargeFrame.setVisible(false);
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

				chargeFrame.setLocation(sourcePoint.x - chargeFrame.getWidth(),
						sourcePoint.y);
			}

			// Display the frame.
			chargeFrame.setVisible(true);
		}
	}

}
