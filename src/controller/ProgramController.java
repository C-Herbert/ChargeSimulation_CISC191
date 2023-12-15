package controller;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controller.actions.DeleteElementAction;
import controller.listeners.AddArrowListener;
import controller.listeners.AddChargeListener;
import controller.listeners.AddPotentiometerListener;
import controller.listeners.ArrowLayoutListener;
import controller.listeners.EditChargeListener;
import controller.listeners.ElementDragListener;
import controller.listeners.FileOpenListener;
import controller.listeners.FileSaveListener;
import controller.listeners.HelpPanelListener;
import model.ChargeGraph2D;
import utils.ArrowPatterns;
import view.ProgramView;

/**
 * ProgramController serves as a link between the model and view objects for the
 * Charge Simulation program. It updates the model in response to inputs
 * collected from the view.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class ProgramController
{
	/**
	 * ProgramController manages a graph.
	 */
	private ChargeGraph2D programGraph;
	/**
	 * ProgramController manages a view.
	 */
	private ProgramView programView;

	/**
	 * Constructs a new ProgramController with an empty graph using the
	 * specified width and height arguments.
	 * 
	 * @param graphWidth  The width of this controller's graph.
	 * @param graphHeight The height of this controller's graph.
	 */
	public ProgramController(int graphWidth, int graphHeight)
	{
		// Initialize the graph field.
		programGraph = new ChargeGraph2D(graphWidth, graphHeight);

		// Initialize the programView field.
		programView = new ProgramView();
		programView.initializeGraph(programGraph);

		// Add Graph Listeners
		programView.addGraphMouseListener(
				new ElementDragListener(programGraph, programView));
		programView.addGraphMouseListener(
				new EditChargeListener(programGraph, programView));

		// Add IO listeners
		programView.getToolbar().addFileOpenListener(
				new FileOpenListener(programGraph, programView));
		programView.getToolbar().addFileSaveListener(
				new FileSaveListener(programGraph, programView));

		// Add Arrow Listener
		programView.getToolbar().addArrowPatternListener(
				new ArrowLayoutListener(programGraph, programView));

		// Add Toolbox Listeners
		programView.getToolbox().addCreateChargeListener(
				new AddChargeListener(programGraph, programView));
		programView.getToolbox().addCreateArrowListener(
				new AddArrowListener(programGraph, programView));
		programView.getToolbox().addCreatePotentiometerListener(
				new AddPotentiometerListener(programGraph, programView));

		// Add Help Listener
		programView.getToolbar().addHelpPanelListener(new HelpPanelListener());

		// Delete listener
		programView.addGraphKeyBinding(
				KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete_element",
				new DeleteElementAction(programGraph, programView));

		// Setup a basic field arrow pattern.
		ArrowPatterns.createRectangularPattern(programGraph, 0.7);
		programView.repaintGraph();
	}
}
