package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolbarView extends JPanel
{
	// ToolbarView has buttons to control file io.
	private JButton fileSaveButton;
	private JButton fileOpenButton;

	// ToolbarView has a button to open the arrow pattern menu.
	private JButton arrowPatternButton;

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
}
