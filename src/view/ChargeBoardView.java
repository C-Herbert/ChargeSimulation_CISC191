package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ChargeGraph2D;
import model.FieldArrow;
import view.elements.FieldArrowView;

/*
 * References (TODO: Cleanup)
 * Using Graphics2D for a custom component:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/jcomponent.html
 * https://www.oracle.com/java/technologies/painting.html
 */

/**
 * The ChargeBoardView class will be used to draw and maintain a view model
 * for the ChargeGraph2D class. Currently, its only used for testing.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */
public class ChargeBoardView extends JPanel implements MouseListener
{
	//ChargeBoardView has a ChargeGraph2D
	private ChargeGraph2D graphReference;
	//ChargeBoardView has a arrowView (Will be removed)
	private FieldArrowView arrowView;

	/**
	 * Creates a new ChargeBoardField using the provided ChargeGraph2D object.
	 * @param chargeGraph The ChargeGraph2D displayed by this view.
	 */
	public ChargeBoardView(ChargeGraph2D chargeGraph)
	{
		this.graphReference = chargeGraph;

		setSize(graphReference.getWidth(), graphReference.getHeight());
		setPreferredSize(getSize());

		arrowView = new FieldArrowView(new ArrayList<FieldArrow>());

		addMouseListener(this);
	}

	// TODO: Remove
	// Temporary, will move this functionality over to controller later on
	public void addArrow(FieldArrow arrow)
	{
		if (arrow != null)
		{
			arrowView.addElement(arrow);
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		arrowView.draw((Graphics2D) g);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	// For Testing Purposes
	// TODO: Remove
	@Override
	public void mousePressed(MouseEvent e)
	{
		for (FieldArrow arrow : arrowView.getElements())
		{
			arrow.pointTowardsLocation(e.getX(), e.getY());
		}

		// Need to call this after updating the arrows
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

}
