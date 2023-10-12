package testing;

import javax.swing.*;

import view.ChargeBoardGUI;

public class ArrowTester
{
	ChargeBoardGUI boardGUI;
	
	public void run()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.add(panel);
		
		boardGUI = new ChargeBoardGUI(1500,1000);
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
