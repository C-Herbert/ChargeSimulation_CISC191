package controller;

import controller.listeners.AddChargeListener;
import controller.listeners.ElementDragListener;
import controller.listeners.FileOpenListener;
import controller.listeners.FileSaveListener;
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
	// ProgramController manages a graph
	ChargeGraph2D programGraph;
	// ProgramController manages a view
	ProgramView programView;

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

		// Add IO listeners
		programView.addFileOpenListener(
				new FileOpenListener(programGraph, programView));
		programView.addFileSaveListener(
				new FileSaveListener(programGraph, programView));

		// Add Toolbox Listeners
		programView.getToolbox().addCreateChargeListener(
				new AddChargeListener(programGraph, programView));

		ArrowPatterns.createRectangularPattern(programGraph, 0.7);
		
	}
}
