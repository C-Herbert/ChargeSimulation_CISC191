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

import model.ChargeGraph2D;
import model.FieldArrow;
import view.elements.FieldArrowView;

/*
 * References (TODO: Cleanup)
 * Using Graphics2D for a custom component:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/jcomponent.html
 * https://www.oracle.com/java/technologies/painting.html
 */

public class ChargeBoardView extends JFrame implements MouseListener
{
	private ChargeGraph2D graphReference;

	private FieldArrowView arrowView;
	
	public ChargeBoardView(ChargeGraph2D chargeGraph)
	{
		this.graphReference = chargeGraph;

		setSize(graphReference.getWidth(), graphReference.getHeight());
		setPreferredSize(getSize());

		arrowView = new FieldArrowView(new ArrayList<FieldArrow>());

		addMouseListener(this);
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
	
	//For Testing Purposes
	//TODO: Remove
	@Override
	public void mousePressed(MouseEvent e)
	{
		for (FieldArrow arrow : arrowView.getElements())
		{
			arrow.pointTowardsLocation(e.getX(), e.getY());
		}
		
		//Need to call this after updating the arrows
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
