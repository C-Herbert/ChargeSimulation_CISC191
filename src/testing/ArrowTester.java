package testing;

import javax.swing.*;

import model.ChargeGraph2D;
import model.FieldArrow;
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
		
		graphModel = new ChargeGraph2D(null, null, 1500, 1000);
		
		boardGUI = new ChargeBoardView(graphModel);
		
		for(int x = 0; x < 10; ++x) 
		{
			for(int y = 0; y < 6; ++y)
			{
				//Temporary
				boardGUI.addArrow(new FieldArrow((graphModel.getWidth() / 10) * x, (graphModel.getHeight() / 6) * y, 1, 0));
			}
		}
		
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
