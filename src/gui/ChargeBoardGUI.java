package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import javax.swing.JComponent;
import javax.swing.JLabel;

import model.FieldArrow;

/*
 * References (TODO: Cleanup)
 * Using Graphics2D for a custom component:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/jcomponent.html
 */

public class ChargeBoardGUI extends JComponent implements MouseListener
{
	public final int width;
	public final int height;

	private BufferedImage board;

	private ArrayList<FieldArrow> fieldArrows;

	public ChargeBoardGUI(int width, int height)
	{
		this.width = width;
		this.height = height;

		setSize(this.width, this.height);
		setPreferredSize(getSize());

		fieldArrows = new ArrayList<FieldArrow>();

		fieldArrows.add(new FieldArrow(100, 100));
		fieldArrows.get(0).pointTowardsLocation(width, height);
		
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D graphics = (Graphics2D) g;

		for (FieldArrow arrow : fieldArrows)
		{
			FieldArrow.drawArrow(arrow, graphics);
		}

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
		for (FieldArrow arrow : fieldArrows)
		{
			arrow.pointTowardsLocation(e.getX(), e.getY());
		}
		
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
