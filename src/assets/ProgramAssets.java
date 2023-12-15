package assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * The ProgramAssets class is responsible for loading all image assets built
 * into the program. Assets should be loaded in its static initializer, and
 * referenced using the static getAsset function.
 * 
 * References:
 * 1. “Reading/Loading an Image.” Oracle Java Documentation,
 * https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html. Accessed 17
 * Nov. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

public final class ProgramAssets
{
	/**
	 * The ProgramAssets class has a map of BufferedImage assets bound to string
	 * identifiers.
	 */
	private static HashMap<String, BufferedImage> imgAssets = new HashMap<>();

	/**
	 * Filepaths used for charge images
	 */
	public static final String POSITIVE_CHARGE_FILEPATH = "src/assets/positive_charge.png",
			NEGATIVE_CHARGE_FILEPATH = "src/assets/negative_charge.png";
	/**
	 * Filepaths used for arrow images
	 */
	public static final String ARROW_FILEPATH = "src/assets/arrow.png",
			GREEN_ARROW_FILEPATH = "src/assets/green_arrow.png";
	/**
	 * Filepaths used for tool images
	 */
	public static final String POTENTIOMETER_FILEPATH = "src/assets/potentiometer.png";
	/**
	 * Filepaths used for UI images
	 */
	public static final String ADD_CHARGE_FILEPATH = "src/assets/add_charge_icon.png";

	// Static initializer to setup asset map.
	static
	{
		// Charges
		addEntry("positive_charge", POSITIVE_CHARGE_FILEPATH);
		addEntry("negative_charge", NEGATIVE_CHARGE_FILEPATH);

		// Arrows
		addEntry("field_arrow", ARROW_FILEPATH);
		addEntry("green_arrow", GREEN_ARROW_FILEPATH);

		// Tools
		addEntry("potentiometer", POTENTIOMETER_FILEPATH);

		// UI
		addEntry("add_charge_icon", ADD_CHARGE_FILEPATH);
	}

	/**
	 * Adds an entry to ProgramAsset's list of image assets.
	 * 
	 * @param key      the key to be given to the asset.
	 * @param filepath the filepath of the asset.
	 * @return true if the entry was successfully created, false otherwise.
	 */
	public static boolean addEntry(String key, String filepath)
	{
		// Declare a BufferedImage to hold the entry's value.
		BufferedImage newImg = null;

		// Attempt to load the asset.
		try
		{
			newImg = ImageIO.read(new File(filepath));
		}
		catch (IOException e)
		{
			// Failed to load image, return false.
			System.out.println(
					"Warning: Asset failed to load. Some images might not display");
			return false;
		}

		// Successfully loaded the image, add it to the asset list.
		imgAssets.put(key, newImg);
		return true;
	}

	/**
	 * Gets the asset associated with the key, or null if no asset exists.
	 * 
	 * @param key the string key value of the asset.
	 * @return the image asset associated with the key, or null if no such asset
	 *         exists.
	 */
	public static BufferedImage getAsset(String key)
	{
		return imgAssets.get(key);
	}

	/**
	 * Private constructor prevents instantiation of the ProgramAssets class.
	 */
	private ProgramAssets()
	{
		// Does nothing.
	}
}
