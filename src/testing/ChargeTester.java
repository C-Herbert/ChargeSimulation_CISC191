package testing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;

import model.Charge;
import model.ChargeGraph2D;
import model.FieldArrow;
import model.IGraphElement;
import utils.io.GraphIO;
import view.ProgramView;

/**
 * A test class used to demonstrate charge functionality.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class ChargeTester
{
	// Instance variables to hold view and graph, circumventing need for a
	// model.
	ProgramView view;
	ChargeGraph2D testGraph;

	// Run function for test program
	public void run()
	{
		// Initialize the view to use the charge board we just setup
		view = new ProgramView();
		testGraph = new ChargeGraph2D(1000, 700);

		// Add a 10 x 6 grid of arrows to the graph
		for (int x = 1; x < 20; ++x)
		{
			for (int y = 1; y < 12; ++y)
			{
				testGraph.addElement(
						new FieldArrow((testGraph.getWidth() / 20) * x,
								(testGraph.getHeight() / 12) * y, 1, 0));
			}
		}

		// Add two charges, one positive, one negative
		testGraph.addElement(new Charge(100, 100, 50));

		testGraph.addElement(new Charge(850, 350, -50));

		// Iterate through graph elements, updating arrow directions to match
		// their local net fields
		for (IGraphElement e : testGraph.getElements())
		{
			if (e instanceof FieldArrow)
			{
				((FieldArrow) e).setDirection(testGraph
						.getNetFieldAtPoint(e.getX(), e.getY()).getDirection());
			}
		}

		// Initialize the view to use the charge board we just setup
		view.initializeChargeBoard(testGraph);
	}

	// Main method to run tester
	public static void main(String[] args)
	{
		new ChargeTester().run();
	}

}
