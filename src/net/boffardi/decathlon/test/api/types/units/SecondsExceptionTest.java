/**
 * 
 */
package net.boffardi.decathlon.test.api.types.units;

import java.text.ParseException;

import org.junit.Test;

import net.boffardi.decathlon.api.types.units.Seconds;

/**
 * @author mauro.boffardi
 *
 */
public class SecondsExceptionTest {
	
	/**
	 * Test method for {@link net.boffardi.decathlon.api.types.units.Seconds#Seconds(java.lang.String)}.
	 * Verify that invalid formats are sent
	 */
	@Test(expected = ParseException.class)
	public void testParseExceptionSecondsString() throws ParseException {		
		// invalid letter or format
		new Seconds("A0:00:01.000");
		new Seconds("00:0A:01.000");
		new Seconds("00:00:P1.000");
		new Seconds("00:00:01.000X");

		
		// invalid format
		new Seconds("291");
		new Seconds("66.123");
		new Seconds("00:00:00:01.000");
		new Seconds("00:00:01.");
		new Seconds("00:1:01.00");
		new Seconds("00:1:1.00");


	}
	

}
