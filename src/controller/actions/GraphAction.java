package controller.actions;

import javax.swing.AbstractAction;
import model.ChargeGraph2D;
import view.ProgramView;

// References:
// https://docs.oracle.com/javase/tutorial/uiswing/misc/action.html#actionapi
// https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html

/**
 * The GraphAction class acts as a parent class to all Actions that require a
 * ChargeGraph2D to perform their task.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public abstract class GraphAction extends AbstractAction
{
	protected ChargeGraph2D graph;
	protected ProgramView view;

	/**
	 * Constructs a new GraphAction using the provided graph and view
	 * parameters.
	 * 
	 * @param graph The ChargeGraph2D to assign to this GraphAction.
	 * @param view  The ProgramView to assign to this GraphAction.
	 */
	public GraphAction(ChargeGraph2D graph, ProgramView view)
	{
		this.graph = graph;
		this.view = view;
	}

}
