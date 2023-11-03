package testing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Charge;
import model.ChargeGraph2D;
import model.FieldArrow;
import model.IGraphElement;
import view.ProgramView;

/**
 * A test class used to demonstrate Arrow functionality.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class ArrowTester
{
	// Instance variables to hold view and graph, circumventing need for a
	// model.
	ProgramView view;
	ChargeGraph2D testGraph;

	// Run function for test program
	public void run()
	{
		view = new ProgramView();
		testGraph = new ChargeGraph2D(null, 1000, 700);

		// Add a 10 x 6 grid of arrows to the graph
		for (int x = 1; x < 10; ++x)
		{
			for (int y = 1; y < 6; ++y)
			{
				testGraph.addElement(
						new FieldArrow((testGraph.getWidth() / 10) * x,
								(testGraph.getHeight() / 6) * y, 1, 0));
			}
		}

		// Initialize the view to use the charge board we just setup
		view.initializeChargeBoard(testGraph);
		// Add a mouse listener to the graph view
		view.addGraphMouseListener(new ArrowPointTowardsListener());
	}

	// Small action listener class to handle mouse inputs
	private class ArrowPointTowardsListener implements MouseListener
	{
		// Unused
		@Override
		public void mouseClicked(MouseEvent e)
		{
		}

		// Unused
		@Override
		public void mousePressed(MouseEvent e)
		{
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			for (IGraphElement element : testGraph.getElements())
			{
				if (element instanceof FieldArrow)
				{
					((FieldArrow) element).pointTowardsLocation(e.getX(),
							e.getY());
				}
			}

			view.repaintGraph();
		}

		// Unused
		@Override
		public void mouseEntered(MouseEvent e)
		{
		}

		// Unused
		@Override
		public void mouseExited(MouseEvent e)
		{
		}
	}

	// Main method to run tester
	public static void main(String[] args)
	{
		new ArrowTester().run();
	}

}
