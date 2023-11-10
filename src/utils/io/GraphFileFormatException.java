package utils.io;

import java.io.IOException;

/**
 * GraphFileFormatException serves as a generic exception type for all
 * formatting issues regarding Graph2D file input and output.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class GraphFileFormatException extends IOException
{
	// GraphFileFormatException is serializable, thus it needs a unique ID.
	private static final long serialVersionUID = -7784236624165039950L;

	/**
	 * Constructs a new GraphFileFormatException using the passed message
	 * 
	 * @param message the message to be assigned to this exception.
	 */
	public GraphFileFormatException(String message)
	{
		// Call IOException's constructor with the passed message.
		super(message);
	}
}
