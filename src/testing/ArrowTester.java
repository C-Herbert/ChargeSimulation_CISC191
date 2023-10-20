package testing;

import javax.swing.*;

import model.ChargeGraph2D;
import model.FieldArrow;
import view.ChargeBoardView;

/**
 * The ArrowTester class is used to test the functionality of FieldArrows.
 * It includes its own main method to run small sample programs.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class ArrowTester
{
	//ArrowTester has a ChargeGraph2D
	ChargeGraph2D graphModel;
	//ArrowTester has a ChargeBoardView
	ChargeBoardView boardGUI;
	
	//Test a 10 x 6 grid of field arrows
	public void run()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.add(panel);
		
		//Initialize graphModel and boardGUI
		graphModel = new ChargeGraph2D(null, null, 1500, 1000);
		boardGUI = new ChargeBoardView(graphModel);
		
		//Add field arrows
		for(int x = 0; x < 10; ++x) 
		{
			for(int y = 0; y < 6; ++y)
			{
				//Temporary, add an arrow at each interval
				boardGUI.addArrow(new FieldArrow((graphModel.getWidth() / 10) * x, (graphModel.getHeight() / 6) * y, 1, 0));
			}
		}
		
		//Add GUI to panel
		panel.add(boardGUI);
		
		//Display frame
		frame.pack();
		frame.setVisible(true);
	}
	
	//Main method to run ArrowTester
	public static void main(String[] args)
	{
		ArrowTester testRunner = new ArrowTester();
		testRunner.run();
	}
}
