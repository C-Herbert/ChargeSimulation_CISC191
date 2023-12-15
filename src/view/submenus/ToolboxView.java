package view.submenus;

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
 * various IGraphElement creation options to the user. It is used as the
 * vertical, right-side panel of the program.
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

	/**
	 * ToolboxView has a JButton field for the button used to open the charge
	 * creation menu.
	 */
	private JButton createChargeButton = new JButton("Add Charge");
	/**
	 * ToolboxView has a JButton field for the button used to add an arrow to
	 * the graph.
	 */
	private JButton createArrowButton = new JButton("Add Arrow");
	/**
	 * ToolboxView has a JButton field for the button used to add a
	 * potentiometer to the graph.
	 */
	private JButton createPotentiometerButton = new JButton("Potentiometer");

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
		createPotentiometerButton.setIcon(
				new ImageIcon(ProgramAssets.getAsset("potentiometer")));

		this.add(headerLabel);
		this.add(createChargeButton);
		this.add(createArrowButton);
		this.add(createPotentiometerButton);
	}

	/**
	 * Adds an action listener to this view's associated create charge button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addCreateChargeListener(ActionListener listener)
	{
		createChargeButton.addActionListener(listener);
	}

	/**
	 * Adds an action listener to this view's associated create arrow button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addCreateArrowListener(ActionListener listener)
	{
		createArrowButton.addActionListener(listener);
	}

	/**
	 * Adds an action listener to this view's associated create potentiometer
	 * button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addCreatePotentiometerListener(ActionListener listener)
	{
		createPotentiometerButton.addActionListener(listener);
	}
}
