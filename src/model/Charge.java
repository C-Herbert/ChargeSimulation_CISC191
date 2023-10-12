package model;

/**
 * Data representation of an electric charge.
 * 
 * @version 1.0
 * @author Charlie Herbert
 */

//TODO: Update to fit in inheritance hierarchy
public class Charge
{
	private double x, y;
	private double magnitude;
	
	public Charge(double x, double y, double magnitude)
	{
		this.x = x;
		this.y = y;
		this.magnitude = magnitude;
	}
	
	public double getFieldAtPoint(double pointX, double pointY)
	{
		double dX = pointX - x;
		double dY = pointY - y;
		
		return (8.99 * Math.pow(10, 9) * magnitude) / (Math.pow(dX, 2) + Math.pow(dY, 2));
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getMagnitude()
	{
		return this.magnitude;
	}
}
