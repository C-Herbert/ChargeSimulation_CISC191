package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

// References:
// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
// https://docs.oracle.com/javase/8/docs/api/javax/swing/BoxLayout.html

public class ToolboxView extends JPanel
{
	private JButton createChargeButton = new JButton("Add Charge");
	private JButton createArrowButton = new JButton("Add Arrow");

	public ToolboxView()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new BevelBorder(BevelBorder.RAISED));

		JLabel headerLabel = new JLabel("Toolbox:");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		this.add(headerLabel);

		this.add(createChargeButton);
		this.add(createArrowButton);
	}

	public void addCreateChargeListener(ActionListener l)
	{
		createChargeButton.addActionListener(l);
	}

	public void addCreateArrowListener(ActionListener l)
	{
		createArrowButton.addActionListener(l);
	}
}
