package testing;

import javax.swing.*;

import model.ChargeGraph2D;
import view.ChargeBoardView;

public class ArrowTester
{
	ChargeGraph2D graphModel;
	
	ChargeBoardView boardGUI;
	
	public void run()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.add(panel);
		
		for(int x = 0; x < 15; ++x) 
		{
			for(int y = 0; y < 10; ++y)
			{
				//TODO: finish tester changes
			}
		}
		
		
		graphModel = new ChargeGraph2D(null, null, 1500, 1000);
		
		boardGUI = new ChargeBoardView(graphModel);
		panel.add(boardGUI);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		ArrowTester testRunner = new ArrowTester();
		testRunner.run();
	}
}
