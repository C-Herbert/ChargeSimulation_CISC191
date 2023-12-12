package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.Vec2D;

/**
 * Vec2DTestCases is used to test the Vec2D implementation.
 * 
 * References:
 * “JUnit 4.13.2 API.” Javadoc.Io,
 * https://javadoc.io/doc/junit/junit/latest/index.html. Accessed 8 Oct. 2023.
 * 
 * @author Charlie Herbert
 * @version 1.0
 */
public class Vec2DTestCases
{
	// Used when comparing doubles, since floating-point math isn't always
	// perfectly precise
	private static final double DOUBLE_CALC_PRECISION = 0.0000001;

	// Reference for double test cases:
	//
	@Test
	public void UnitVectorTest()
	{
		// Angle between y and x unit vectors should be 90 degrees
		Vec2D vec1 = new Vec2D(0, 1);
		Vec2D vec2 = new Vec2D(1, 0);
		assertEquals(Math.PI / 2, vec1.angleBetween(vec2),
				DOUBLE_CALC_PRECISION);

		// Angle between positive and negative unit vectors should be 180
		// degrees
		Vec2D vec3 = new Vec2D(-1, 0);
		assertEquals(Math.PI, vec2.angleBetween(vec3), DOUBLE_CALC_PRECISION);
	}

}
