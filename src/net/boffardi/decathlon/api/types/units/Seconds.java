package net.boffardi.decathlon.api.types.units;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Wrapper class for results of events managed in seconds.
 * Used to provide common operation like toString and conversions.
 * Probably too overkill to store data internally in seconds, but easier to read in the db.
 * 
 * @author mauro.boffardi
 *
 */
public class Seconds {
	// internal representation is in seconds (and fraction of)
	// i.e 1214.45
	private Double seconds = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
	
	public Seconds() {		
	}

	public Seconds(Double seconds) {	
		this.seconds = seconds;
	}

	/**
	 * Parses time sent in format hh:mm:ss.ddd
	 * @param time
	 * @throws ParseException 
	 */
	public Seconds(String timeString) throws ParseException {			
		Date date = sdf.parse(timeString);
		Long milliseconds = date.getTime();
		this.seconds = milliseconds.doubleValue() / 1000;
	}

	// return the amount of seconds in hh:mm:ss.ddd
	public String toString() {
		if (this.seconds == null) return "N/A";
		// convert the decimal version of seconds into a long millisec 
		long millis = Math.round(this.seconds * 1000);
		
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
		            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
		            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		return hms;
	}
	
	public Double getAsDouble() {
		return this.seconds;
	}
}
