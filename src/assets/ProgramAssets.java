package assets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * The ProgramAssets class is responsible for loading all image assets built
 * into the program. Assets should be loaded in its static initializer, and
 * referenced using the getAsset function.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */

// References:
// https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
public class ProgramAssets
{
	private static HashMap<String, BufferedImage> imgAssets = new HashMap<>();

	// Charge Images
	public static final String POSITIVE_CHARGE_FILEPATH = "src/assets/positive_charge.png";
	public static final String NEGATIVE_CHARGE_FILEPATH = "src/assets/negative_charge.png";

	// Arrow Image
	public static final String ARROW_FILEPATH = "src/assets/arrow.png";

	// Static initializer to setup asset map.
	static
	{
		// Charges
		addEntry("positive_charge", POSITIVE_CHARGE_FILEPATH);
		addEntry("negative_charge", NEGATIVE_CHARGE_FILEPATH);

		// Arrows
		addEntry("field_arrow", ARROW_FILEPATH);
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

}
