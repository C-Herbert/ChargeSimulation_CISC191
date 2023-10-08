package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FieldArrow
{
	private int x, y;
	private double direction;
	private double magnitude;
	
	public FieldArrow(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setDirection(double radians)
	{
		this.direction = radians;
	}
	
	public void pointTowardsLocation(double targetX, double targetY)
	{
		//Calculate target vector
		double dX = targetX - x;
		double dY = targetY - y;
		
		double targetMagnitude = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		
		//Apply dot product to calculate angle between 'base' direction vector and target vector
		//'base' vector points straight right, target vector has components dX and dY
		//Note that the 'base' vector has a magnitude of 1 and no y component
		
		direction = Math.acos(dX / targetMagnitude);
		
		//If we've exceed 180 degrees, flip the angle
		if(dY < 0) direction *= -1;
	}
	
	
	//TODO: Separate gui?
	
	private static final String ARROW_FILEPATH = "src/assets/arrow.png";
	
	private static BufferedImage arrowImage;
	
	static
	{
		try
		{
			arrowImage = ImageIO.read(new File(ARROW_FILEPATH));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public BufferedImage getArrowImage()
	{
		//New image to hold rotated instance
		BufferedImage newArrow = new BufferedImage(arrowImage.getWidth(), arrowImage.getHeight(), arrowImage.getType());
		
		//Create Graphics2D so we can manipulate the image
		Graphics2D arrowGraphics = newArrow.createGraphics();
		
		//Draw the base image and rotate it
		arrowGraphics.rotate(direction, newArrow.getWidth() / 2, newArrow.getHeight() / 2);
		arrowGraphics.drawImage(arrowImage, null, 0, 0);
		
		//Cleanup Graphics object
		arrowGraphics.dispose();
		
		return newArrow;
	}
	
	public static void drawArrow(FieldArrow arrow, Graphics2D g)
	{
		BufferedImage arrowImage = arrow.getArrowImage();
		
		g.drawImage(arrowImage, null, arrow.getX() - (arrowImage.getWidth() / 2), arrow.getY()- (arrowImage.getHeight() / 2));
	}
	
}
