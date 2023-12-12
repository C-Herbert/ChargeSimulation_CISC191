package controller;

/**
 * This class is solely responsible for running the program. Currently, this
 * consists of creating a new controller object.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public class ChargeSimulation
{
	/**
	 * Creates a new ProgramController to start the program.
	 * 
	 * @param args An array of strings used to set the program's arguments.
	 *             Unused.
	 */
	public static void main(String[] args)
	{
		new ProgramController(1000, 700);
	}
}
