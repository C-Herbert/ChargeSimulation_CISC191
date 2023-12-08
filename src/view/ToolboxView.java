package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import assets.ProgramAssets;

// References:
// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
// https://docs.oracle.com/javase/8/docs/api/javax/swing/BoxLayout.html

public class ToolboxView extends JPanel
{

	// Element Buttons
	private JButton createChargeButton = new JButton("Add Charge");
	private JButton createArrowButton = new JButton("Add Arrow");

	// Tool Buttons
	private JButton potentiometerButton = new JButton("Potentiometer");

	public ToolboxView()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new BevelBorder(BevelBorder.RAISED));

		JLabel headerLabel = new JLabel("Toolbox:");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		createChargeButton.setIcon(
				new ImageIcon(ProgramAssets.getAsset("add_charge_icon")));
		createArrowButton
				.setIcon(new ImageIcon(ProgramAssets.getAsset("green_arrow")));
		potentiometerButton.setIcon(
				new ImageIcon(ProgramAssets.getAsset("potentiometer")));

		this.add(headerLabel);
		this.add(createChargeButton);
		this.add(createArrowButton);
		this.add(potentiometerButton);
	}

	public void addCreateChargeListener(ActionListener l)
	{
		createChargeButton.addActionListener(l);
	}

	public void addCreateArrowListener(ActionListener l)
	{
		createArrowButton.addActionListener(l);
	}
	
	public void addPotentiometerListener(ActionListener l)
	{
		potentiometerButton.addActionListener(l);
	}
}
