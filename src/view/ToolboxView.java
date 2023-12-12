package view;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import assets.ProgramAssets;

/**
 * ToolboxView is a subclass of JPanel used by the program view to display
 * various IGraphElement creation options to the user.
 * 
 * References:
 * 1. “A Visual Guide to Layout Managers.” Oracle Java Documentation,
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html. Accessed
 * 10 Nov. 2023.
 * 2. “BoxLayout.” Oracle Java Documentation,
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/BoxLayout.html.
 * Accessed 10 Nov. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public class ToolboxView extends JPanel
{
	// ToolboxView is serializable, thus it needs a UID.
	private static final long serialVersionUID = -8444689130484954000L;

	// Element Buttons
	private JButton createChargeButton = new JButton("Add Charge");
	private JButton createArrowButton = new JButton("Add Arrow");

	// Tool Buttons
	private JButton potentiometerButton = new JButton("Potentiometer");

	/**
	 * Creates a new ToolboxView and sets up all of its menu components.
	 */
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

	/**
	 * Adds an action listener to this view's associated create charge button.
	 * 
	 * @param l The listener to add.
	 */
	public void addCreateChargeListener(ActionListener l)
	{
		createChargeButton.addActionListener(l);
	}

	/**
	 * Adds an action listener to this view's associated create arrow button.
	 * 
	 * @param l The listener to add.
	 */
	public void addCreateArrowListener(ActionListener l)
	{
		createArrowButton.addActionListener(l);
	}

	/**
	 * Adds an action listener to this view's associated potentiometer button.
	 * 
	 * @param l The listener to add.
	 */
	public void addPotentiometerListener(ActionListener l)
	{
		potentiometerButton.addActionListener(l);
	}
}
