package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utils.Vec2D;

public class FieldArrow extends Arrow2D
{

	public FieldArrow(int xPos, int yPos, int xMag, int yMag)
	{
		super(xPos, yPos, xMag, yMag);
	}

	public void pointTowardsLocation(double targetX, double targetY)
	{
		//Calculate target vector
		Vec2D targetVector = new Vec2D(targetX - this.getX(), targetY - this.getY());
		
		//Assign our new direction
		setDirection(targetVector.angleBetween(new Vec2D(1,0)));
	}
	
	
	//TODO: Separate gui?
	
	private static final String ARROW_FILEPATH = "src/assets/arrow.png";
	
	private static BufferedImage arrowImage;
	
	static
	{
		try
		{
			//https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
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
		//https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html#rotate-double-
		System.out.println(this.getDirection());
		arrowGraphics.rotate(this.getDirection(), newArrow.getWidth() / 2, newArrow.getHeight() / 2);
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
