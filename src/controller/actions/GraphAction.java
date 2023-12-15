package controller.actions;

import javax.swing.AbstractAction;
import model.ChargeGraph2D;
import view.ProgramView;

/**
 * The GraphAction class acts as a parent class to all Actions that require a
 * ChargeGraph2D to perform their task.
 * 
 * References:
 * 1. “How to Use Actions.” Oracle Java Documentation,
 * https://docs.oracle.com/javase/tutorial/uiswing/misc/action.html#actionapi.
 * Accessed 8 Dec. 2023.
 * 2. “How to Use Key Bindings.” Oracle Java Documentation,
 * https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html.
 * Accessed 8 Dec. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class GraphAction extends AbstractAction
{
	/**
	 * GraphActions have an associated graph field for manipulating the graph's
	 * data.
	 */
	protected ChargeGraph2D graph;

	/**
	 * GraphActions have an associated view field used to update the view.
	 */
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
