/**
 * 
 */
package test.net.boffard.decathlon.api.types.units;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import net.boffardi.decathlon.api.types.units.Seconds;

/**
 * @author mauro.boffardi
 *
 */
public class SecondsTest {

	/**
	 * Test method for {@link net.boffardi.decathlon.api.types.units.Seconds#Seconds()}.
	 *
	@Test
	public void testSeconds() {
	//	fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link net.boffardi.decathlon.api.types.units.Seconds#Seconds(java.lang.Double)}.
	 *
	@Test
	public void testSecondsDouble() {
	//	fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link net.boffardi.decathlon.api.types.units.Seconds#Seconds(java.lang.String)}.
	 * 
	 * Test all cases where an exception should NOT be thrown.
	 * See SecondsExceptionTest for tests where exception should be thrown.
	 */
	@Test
	public void testValidSecondsString() throws ParseException {
		
			// simple cases, full hhMMss.SSS are sent
			assertEquals(new Double(1D), new Seconds("00:00:01.000").getAsDouble());
			assertEquals(new Double(1.001D), new Seconds("00:00:01.001").getAsDouble());
			// 61 Secs
			assertEquals(new Double(61D), new Seconds("00:01:01.000").getAsDouble());
			// 3661 Secs
			assertEquals(new Double(3661D), new Seconds("01:01:01.000").getAsDouble());

			
			// Only mm:ss.SSS are sent
			assertEquals(new Double(1D), new Seconds("00:01.000").getAsDouble());
			assertEquals(new Double(1.001D), new Seconds("00:01.001").getAsDouble());			
			// 61 Secs
			assertEquals(new Double(61D), new Seconds("01:01.000").getAsDouble());

			
			// Only ss.SSS are sent
			assertEquals(new Double(1.001D), new Seconds("01.001").getAsDouble());
			assertEquals(new Double(1.020D), new Seconds("01.02").getAsDouble());
			assertEquals(new Double(1.300D), new Seconds("01.3").getAsDouble());

			assertEquals(new Double(1D), new Seconds("01.000").getAsDouble());
			assertEquals(new Double(1D), new Seconds("1.000").getAsDouble());
			assertEquals(new Double(1D), new Seconds("1.00").getAsDouble());
			assertEquals(new Double(1D), new Seconds("1.0").getAsDouble());
			// 61 Secs
			assertEquals(new Double(61D), new Seconds("01:01.000").getAsDouble());

			// no .SSS sent
			assertEquals(new Double(17D), new Seconds("17").getAsDouble());			

			// no .SSS sent, one digit
			assertEquals(new Double(3D), new Seconds("3").getAsDouble());			

	}


	
	/**
	 * Test method for {@link net.boffardi.decathlon.api.types.units.Seconds#toString()}.
	 */
	/*
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link net.boffardi.decathlon.api.types.units.Seconds#getAsDouble()}.
	 *
	@Test
	public void testGetAsDouble() {
	//	fail("Not yet implemented");
	}
	*/

}
