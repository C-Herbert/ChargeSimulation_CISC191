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
	JFrame inputFrame = new JFrame("Arrow Layout");
	JPanel inputPanel = new JPanel();

	JSlider densitySlider = new JSlider(JSlider.HORIZONTAL, 40, 130, 80);

	JPanel optionPanel = new JPanel();
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

		densitySlider.setPaintTicks(true);
		densitySlider.setPaintLabels(true);
		densitySlider.setMajorTickSpacing(10);

		createRectPatternButton.addActionListener(this);
		createCirclePatternButton.addActionListener(this);
		clearPatternButton.addActionListener(this);

		inputPanel.setLayout(new BorderLayout());

		inputPanel.add(new JLabel("Layout Density (% Spacing):"),
				BorderLayout.NORTH);
		inputPanel.add(densitySlider, BorderLayout.CENTER);

		optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		optionPanel.add(createRectPatternButton);
		optionPanel.add(createCirclePatternButton);
		optionPanel.add(clearPatternButton);

		inputPanel.add(optionPanel, BorderLayout.SOUTH);

		inputFrame.add(inputPanel);
		inputFrame.setAlwaysOnTop(true);
		inputFrame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(clearPatternButton))
		{
			ArrowPatterns.clearGraphArrows(graph);
			view.repaintGraph();
		}
		else if (e.getSource().equals(createRectPatternButton))
		{
			ArrowPatterns.clearGraphArrows(graph);
			ArrowPatterns.createRectangularPattern(graph,
					(double) (densitySlider.getValue()) / 100.0);
			graph.updateFieldArrows();
			view.repaintGraph();
		}
		else
		{
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
