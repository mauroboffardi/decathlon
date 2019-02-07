package test.net.boffard.decathlon.api.db;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import net.boffardi.decathlon.api.db.PerformanceImpl;
import net.boffardi.decathlon.api.types.Discipline;
import net.boffardi.decathlon.api.types.units.Centimeters;
import net.boffardi.decathlon.api.types.units.Meters;
import net.boffardi.decathlon.api.types.units.Seconds;

/**
 * Test unit for PerformanceImpl class.
 * Use the constructor instead of the Mgr to avoid the tests being dependent of the DB.
 * @author mauro.boffardi
 *
 */
public class PerformanceImplTest {

	/*
	 * Verifies getScore() calculations via the reference values in Wikipedia for 700,800,900,1000 points
	 * 
	 * There are some trouble with inconsistency of result between different online calculators 
	 * (i.e http://www.oocities.org/mdetting/sports/decathlon.html) and wikipedia, probably due to rounding. 
	 * Also, uncertainty between INT() e ROUND(). ROUND returns points closer to the reference values reported 
	 * in Wikipedia for 7000,8000,9000,10000 pts. 
	 * The most affected are events measured in centimeters (integer) 
	 * and with lower values (High Jump), where probably fraction of a centimeter generate more errors. 
	 * Unfortunately test cases for exact match to 7,8,9,10.000 points still fail.
	 * 
	 */
	
	@Test
	public void testGetScoreAlice() throws ParseException {
		
    	PerformanceImpl alice = new PerformanceImpl("Alice", "Allison", Discipline.WOMEN);
    	// reference values for 7000pts  (10 x 700)
    	alice.setSprint(new Seconds("11.756"));
    	alice.setLongJump(new Centimeters(651));
    	alice.setShotPut(new Meters(13.53));
    	alice.setHighJump(new Centimeters(188));
    	alice.setFourHundreds(new Seconds("52.58"));
    	alice.setHurdles(new Seconds("16.29"));
    	alice.setDiscus(new Meters(41.72));
    	alice.setPoleVault(new Centimeters(429));
    	alice.setJavelin(new Meters(57.45));
    	alice.setM1500sprint(new Seconds("4:36.96"));

    	assertEquals(new Integer(7000), alice.getScore());
	}

	@Test
	public void testGetScoreBob() throws ParseException {
		PerformanceImpl bob = new PerformanceImpl("Bob", "Burgersson", Discipline.MEN);
		// reference values for 9000pts  (10 x 900)
		bob.setSprint(new Seconds("10.827"));
		bob.setLongJump(new Centimeters(736));
		bob.setShotPut(new Meters(16.79));
		bob.setHighJump(new Centimeters(210));
		bob.setFourHundreds(new Seconds("48.19"));
		bob.setHurdles(new Seconds("14.59"));
		bob.setDiscus(new Meters(51.4));
		bob.setPoleVault(new Centimeters(496));
		bob.setJavelin(new Meters(70.67));
		bob.setM1500sprint(new Seconds("4:07.42"));
		assertEquals(new Integer(9000), bob.getScore());
	}
	
	@Test
	public void testGetScoreCarl() throws ParseException {
		PerformanceImpl carl = new PerformanceImpl("Carl", "Cristersson", Discipline.MEN);
		// reference values for 10000pts  (10 x 1000)
		carl.setSprint(new Seconds("10.395"));
		carl.setLongJump(new Centimeters(776));
		carl.setShotPut(new Meters(18.4));
		carl.setHighJump(new Centimeters(220)); 
		carl.setFourHundreds(new Seconds("46.17"));
		carl.setHurdles(new Seconds("13.8"));
		carl.setDiscus(new Meters(56.17));
		carl.setPoleVault(new Centimeters(528));
		carl.setJavelin(new Meters(77.19));
		carl.setM1500sprint(new Seconds("3:53.79"));
		assertEquals(new Integer(10000), carl.getScore());
	}

	@Test
	public void testGetScoreDaniel() throws ParseException {
		PerformanceImpl daniel = new PerformanceImpl("Daniel", "Danielsson", Discipline.MEN);
		// reference values for 8000pts (10 x 800)
		daniel.setSprint(new Seconds("11.278"));
		daniel.setLongJump(new Centimeters(694));
		daniel.setShotPut(new Meters(15.16));
		daniel.setHighJump(new Centimeters(199));
		daniel.setFourHundreds(new Seconds("50.32"));
		daniel.setHurdles(new Seconds("15.419"));
		daniel.setDiscus(new Meters(46.59));
		daniel.setPoleVault(new Centimeters(463));
		daniel.setJavelin(new Meters(64.09));
		daniel.setM1500sprint(new Seconds("4:21.77"));
		assertEquals(new Integer(8000), daniel.getScore());
	}

    	
}
