package testing;

import javax.swing.*;

import gui.ChargeBoardGUI;

public class ArrowTester
{
	ChargeBoardGUI boardGUI;
	
	public void run()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.add(panel);
		
		boardGUI = new ChargeBoardGUI(500,500);
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
