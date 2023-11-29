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
public class ChargeEditListener extends GraphMouseListener
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
	 * @param view The view to assign to this listener.
	 */
	public ChargeEditListener(ChargeGraph2D graph, ProgramView view)
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

		IGraphElement potentialTarget = view.getGraphView()
				.getInteractableAtPoint(e.getX(), e.getY());

		if (potentialTarget instanceof Charge)
		{
			targetedElement = (Charge) potentialTarget;
			inputFrame.loadChargeInfo(targetedElement);
			inputFrame.setLocation(e.getXOnScreen(), e.getYOnScreen());
			inputFrame.setVisible(true);
		}
		else
		{
			inputFrame.setVisible(false);
		}
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

	private class EditorInputListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource().equals(inputFrame.getConfirmButton()))
			{
				if (targetedElement == null) return;

				// TODO: Validation to prevent out of bounds.
				targetedElement.setX(inputFrame.getXInput());
				targetedElement.setY(inputFrame.getYInput());
				targetedElement.setMagnitude(inputFrame.getMagnitudeInput());

				graph.updateFieldArrows();
				view.repaintGraph();

				targetedElement = null;
				inputFrame.setVisible(false);
			}
			else if (e.getSource().equals(inputFrame.getCancelButton()))
			{
				targetedElement = null;
				inputFrame.setVisible(false);
			}
		}
	}
}
