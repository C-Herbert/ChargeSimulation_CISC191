package view.submenus;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ToolbarView is a subclass of JPanel used by the program view to display
 * various program options to the user.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class ToolbarView extends JPanel
{
	// ToolbarView is serializable, thus it needs a UID.
	private static final long serialVersionUID = -7854610468117286834L;

	/**
	 * ToolbarView has a JButton field for the button used to open the file
	 * output menu.
	 */
	private JButton fileSaveButton;
	/**
	 * ToolbarView has a JButton field for the button used to open the file
	 * input menu.
	 */
	private JButton fileOpenButton;
	/**
	 * ToolbarView has a JButton field for the button used to open the arrow
	 * pattern menu.
	 */
	private JButton arrowPatternButton;
	/**
	 * ToolbarView has a JButton field for the button used to open the help
	 * menu.
	 */
	private JButton helpButton;

	/**
	 * Creates a new ToolbarView and sets up all of its menu components.
	 */
	public ToolbarView()
	{
		// Setup panel's layout
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		layout.setHgap(0);
		layout.setVgap(0);
		setLayout(layout);

		// Add toolbar buttons
		fileSaveButton = new JButton("Save File");
		add(fileSaveButton);

		fileOpenButton = new JButton("Open File");
		add(fileOpenButton);

		arrowPatternButton = new JButton("Arrow Layout");
		add(arrowPatternButton);

		helpButton = new JButton("Help");
		add(helpButton);

		// Blend in with the window's own toolbar
		setBackground(Color.WHITE);
	}

	/**
	 * Adds an action listener to this view's associated save button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addFileSaveListener(ActionListener listener)
	{
		fileSaveButton.addActionListener(listener);
	}

	/**
	 * Adds an action listener to this view's associated open button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addFileOpenListener(ActionListener listener)
	{
		fileOpenButton.addActionListener(listener);
	}

	/**
	 * Adds an action listener to this view's associated arrow layout button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addArrowPatternListener(ActionListener listener)
	{
		arrowPatternButton.addActionListener(listener);
	}

	/**
	 * Adds an action listener to this view's associated save button.
	 * 
	 * @param listener The listener to add.
	 */
	public void addHelpPanelListener(ActionListener listener)
	{
		helpButton.addActionListener(listener);
	}
}
