package testing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.ChargeGraph2D;
import model.FieldArrow;
import model.IGraphElement;
import view.ProgramView;

public class ArrowTester
{
	//Instance variables to hold view and graph, circumventing need for a model.
	ProgramView view;
	ChargeGraph2D testGraph;

	public void run()
	{
		view = new ProgramView();
		testGraph = new ChargeGraph2D(null, 1000, 700);

		//Add a 10 x 6 grid of arrows to the graph
		for (int x = 1; x < 10; ++x)
		{
			for (int y = 1; y < 6; ++y)
			{
				testGraph.addElement(
						new FieldArrow((testGraph.getWidth() / 10) * x,
								(testGraph.getHeight() / 6) * y, 1, 0));
			}
		}

		view.initializeChargeBoard(testGraph);
		view.addGraphMouseListener(new ArrowPointTowardsListener());
	}

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

	//Main method to run tester
	public static void main(String[] args)
	{
		new ArrowTester().run();
	}

}
