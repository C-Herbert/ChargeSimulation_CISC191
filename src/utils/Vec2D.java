package utils;

/**
 * The Vec2D class is used to represent 2D vector values.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

public class Vec2D
{		
	//2D Vectors have an x component
	private double xComp;
	//2D Vectors have a y component
	private double yComp;
	
	public Vec2D(double xComp, double yComp)
	{
		this.xComp = xComp;
		this.yComp = yComp;
	}
	
	public double getXComp()
	{
		return xComp;
	}
	
	public double getYComp()
	{
		return yComp;
	}
	
	public double getMagnitude()
	{
		return Math.sqrt(Math.pow(xComp, 2) + Math.pow(yComp, 2));
	}
	
	//Gets direction measured in radians from the positive x-axis
	public double getDirection()
	{
		return Math.atan2(yComp, xComp);
	}
	
	//TODO: add tests
	public void setDirection(double radians)
	{
		double mag = getMagnitude();
		this.xComp = mag * Math.cos(radians);
		this.yComp = mag * Math.sin(radians);
	}
	
	public double dotProduct(Vec2D other)
	{
		return (this.xComp * other.xComp) + (this.yComp * other.yComp);
	}
	
	//Using dot product to calculate angle between vectors
	//Reference: https://en.wikipedia.org/wiki/Dot_product
	//TODO: add more tests
	public double angleBetween(Vec2D other)
	{
		double absoluteAngle = Math.acos( this.dotProduct(other) / (this.getMagnitude() * other.getMagnitude()) );
		
		//TODO: double check sign/angle stuff
		if(this.yComp < other.yComp) 
		{
			//If angle exceeds 180 degrees, assign the appropriate sign
			return -absoluteAngle;
		}
		else
		{
			//Otherwise, return our value
			return absoluteAngle;
		}
	}
	
}
