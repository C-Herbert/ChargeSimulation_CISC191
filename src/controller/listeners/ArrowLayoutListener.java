package controller.listeners;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import model.ChargeGraph2D;
import utils.ArrowPatterns;
import view.ProgramView;

/**
 * The ArrowLayoutListener is a GraphActionListener responsible for handling
 * field arrow creation and patterning. It manages a submenu which allows the
 * user to specify an arrow density and select from circular and rectangular
 * patterns.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class ArrowLayoutListener extends GraphActionListener
{
	// ArrowLayoutListeners have a JFrame for the input menu.
	JFrame inputFrame = new JFrame("Arrow Layout");
	// ArrowLayoutListeners have a main JPanel for the input menu.
	JPanel inputPanel = new JPanel();
	// ArrowLayoutListeners have a JSlider for the input menu.
	JSlider densitySlider = new JSlider(JSlider.HORIZONTAL, 40, 130, 80);
	// ArrowLayoutListeners have a JPanel for the confirmation options menu.
	JPanel optionPanel = new JPanel();
	// ArrowLayoutListeners have a set of JButtons for the confirmation options
	// menu.
	JButton createRectPatternButton = new JButton("Create Rectangular Pattern");
	JButton createCirclePatternButton = new JButton("Create Circular Pattern");
	JButton clearPatternButton = new JButton("Clear Arrows");

	/**
	 * Creates a new ArrowLayoutListener using the provided graph and view
	 * objects.
	 * 
	 * @param graph The graph to assign to this listener.
	 * @param view  The view to assign to this listener.
	 */
	public ArrowLayoutListener(ChargeGraph2D graph, ProgramView view)
	{
		super(graph, view);
		// Setup the slider.
		densitySlider.setPaintTicks(true);
		densitySlider.setPaintLabels(true);
		densitySlider.setMajorTickSpacing(10);

		// Setup the buttons.
		createRectPatternButton.addActionListener(this);
		createCirclePatternButton.addActionListener(this);
		clearPatternButton.addActionListener(this);

		// Setup the main panel and add the slider.
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(new JLabel("Layout Density (% Spacing):"),
				BorderLayout.NORTH);
		inputPanel.add(densitySlider, BorderLayout.CENTER);

		// Setup the confirm options panel.
		optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		optionPanel.add(createRectPatternButton);
		optionPanel.add(createCirclePatternButton);
		optionPanel.add(clearPatternButton);

		// Add the confirm panel to the main panel.
		inputPanel.add(optionPanel, BorderLayout.SOUTH);

		// Finally, prepare the main frame for display.
		inputFrame.add(inputPanel);
		inputFrame.setAlwaysOnTop(true);
		inputFrame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// This action listener is used in four cases. The first three
		// correspond to buttons on the options panel. The last case is used
		// when any other element wants to display the menu.
		if (e.getSource().equals(clearPatternButton))
		{
			// User selected the clear option, remove all arrows from the graph.
			ArrowPatterns.clearGraphArrows(graph);
			// Update the graph.
			view.repaintGraph();
		}
		else if (e.getSource().equals(createRectPatternButton))
		{
			// User selected a rectangular pattern, clear old arrows and create
			// the new pattern.
			ArrowPatterns.clearGraphArrows(graph);
			ArrowPatterns.createRectangularPattern(graph,
					(double) (densitySlider.getValue()) / 100.0);

			// Update the field arrows' alignments and the graph display.
			graph.updateFieldArrows();
			view.repaintGraph();
		}
		else if (e.getSource().equals(createCirclePatternButton))
		{
			// User selected a circular pattern, clear old arrows and create
			// the new pattern.
			ArrowPatterns.clearGraphArrows(graph);
			ArrowPatterns.createCircularPattern(graph,
					(double) (densitySlider.getValue()) / 100.0);

			// Update the field arrows' alignments and the graph display.
			graph.updateFieldArrows();
			view.repaintGraph();
		}
		else
		{
			// Some other element caused an event tied to this listener, show
			// the menu.

			// If the source was a JComponent, position the window such that
			// it is adjacent to the source.
			if (e.getSource() instanceof JComponent)
			{
				Point sourcePoint = ((JComponent) e.getSource())
						.getLocationOnScreen();

				inputFrame.setLocation(sourcePoint.x, sourcePoint.y
						+ ((JComponent) e.getSource()).getHeight());
			}

			inputFrame.setVisible(true);
		}
	}

}
