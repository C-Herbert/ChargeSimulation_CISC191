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
}
