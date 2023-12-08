package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import model.Charge;
import model.ChargeGraph2D;
import model.IGraphElement;
import view.ProgramView;
import view.submenus.ChargeEditorFrame;

/**
 * The ChargeEditListener is a GraphMouseListener responsible for handling
 * charge edit inputs on a graph.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class EditChargeListener extends GraphMouseListener
{
	// ChargeEditListeners have a frame for collecting input.
	private ChargeEditorFrame inputFrame = new ChargeEditorFrame(
			"Edit Charge Info");
	// ChargeEditListeners have an EditorInputListener for collecting input.
	private EditorInputListener inputListener = new EditorInputListener();

	private Charge targetedElement;

	/**
	 * Creates a new ChargeEditListener using the provided graph and view
	 * objects.
	 * 
	 * @param graph The graph to assign to this listener.
	 * @param view  The view to assign to this listener.
	 */
	public EditChargeListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);

		inputFrame.getConfirmButton().addActionListener(inputListener);
		inputFrame.getCancelButton().addActionListener(inputListener);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getButton() != MouseEvent.BUTTON3) return;

		// Clear any previous target.
		targetedElement = null;

		List<IGraphElement> potentialTargets = view.getGraphView()
				.getInteractablesAtPoint(e.getX(), e.getY());

		// Find the first charge interactable on the graph.
		for (IGraphElement element : potentialTargets)
		{
			if (element instanceof Charge)
			{
				// If we found a charge, load its info and show the input frame.
				targetedElement = (Charge) element;
				inputFrame.loadChargeInfo(targetedElement);
				inputFrame.setLocation(e.getXOnScreen(), e.getYOnScreen());
				inputFrame.setVisible(true);
				return;
			}
		}

		// If no charge could be found, ensure the frame stays hidden.
		inputFrame.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// Unused by this listener.

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// Unused by this listener.

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Unused by this listener.

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// Unused by this listener.

	}

	/**
	 * Internal class for handling inputs on the editor frame.
	 */
	private class EditorInputListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource().equals(inputFrame.getConfirmButton()))
			{
				// User pressed the confirm button.

				// Check that we actually have a target element.
				if (targetedElement == null) return;

				if (inputFrame.getMagnitudeInput() == 0)
				{
					// We interpret a zero magnitude as a request to delete the
					// charge.
					graph.removeElement(targetedElement);
				}
				else
				{
					// Otherwise, edit the charge as usual.
					// TODO: Validation to prevent out of bounds.
					targetedElement.setX(inputFrame.getXInput());
					targetedElement.setY(inputFrame.getYInput());
					targetedElement
							.setMagnitude(inputFrame.getMagnitudeInput());
				}

				// Update UI
				graph.updateFieldArrows();
				view.repaintGraph();

				// Clear the targeted element and hide the frame.
				targetedElement = null;
				inputFrame.setVisible(false);
			}
			else if (e.getSource().equals(inputFrame.getCancelButton()))
			{
				// User pressed the cancel button, hide the input frame.
				targetedElement = null;
				inputFrame.setVisible(false);
			}
		}
	}
}
