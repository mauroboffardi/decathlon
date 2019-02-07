package net.boffardi.decathlon.api.types.units;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wrapper class for results of events managed in seconds.
 * Used to provide common operation like toString and conversions.
 * Probably too overkill to store data internally in seconds, but easier to read in the db.
 * Actually there is not an out of the box way to manage seconds in hh:mm:ss.SSS without being a date,
 * hence the uncommon solutions for parse/tostring. 
 * I kept the regexp approach for the formal validation, and the ParseFormat for time validation
 * 
 * If i should say i am proud this, i wouldn't. But it works and is understandable.
 * 
 * @author mauro.boffardi
 *
 */
public class Seconds implements EventResult {
	
	// logger
    private static final Logger log = Logger
            .getLogger(Seconds.class.getName());

	// internal representation is in seconds (and fraction of)
	// i.e 1214.45
	private Double seconds = null;
	
	public Seconds() {
	}

	public Seconds(Double seconds) {	
		this.seconds = seconds;
	}
	
	/**
	 * Used to check if the result of the event has been registered or not
	 */
	public boolean isEmpty() {
		return (seconds == null || seconds == 0.0);
	}
	
	public boolean isTimeBased() {
		return true;
	}

	/**
	 * Parses time sent in format hh:mm:ss.ddd
	 * Does first a formal validation via regex, and then a parse validation to check housr/minutes/seconds.
	 * @param time
	 * @throws ParseException 
	 */
	public Seconds(String timeString) throws ParseException {
		// if string is not provided, create the object with the default "empty" value
		if (timeString == null || timeString.isEmpty()) {
			return;
		}
		log.info("Second(String): Trying to parse input time as string [" + timeString + "]");
		
		// ALWAYS a mess with dates/times. I could force the app to use "hh:mm:ss.SSS" format always, but
		// probably it is easier to live with some regex check here in the parser and give the use more
		// freedom in the way they provide times (11.234 or 1:23.34 or 00:01:12.345)
		timeString = timeString.trim();
		
		Pattern pattern = Pattern.compile("(\\d{1,2}:)?(\\d{1,2}:)?(\\d{1,2})(\\.\\d{1,3})?");

		Matcher matcher = pattern.matcher(timeString);
		if (!matcher.matches()) throw new ParseException("String does not matches ##:##:#0.### pattern", -1);
		
		String hh = matcher.group(1);
		String mm = matcher.group(2);
		String ss = matcher.group(3);
		String SSS = matcher.group(4);

		// if the string is sent as hh:mm:ss.SSS it is possible to just proceed to the parse
		// Otherwise, check all possible formats (form right to left)
		
		// Special case 1: no SSS
		if (SSS == null) {
			timeString += ".000";
			log.info("Second(String): SSS missing, added .000: [" + timeString + "]");
		} else {

			// Special case 1a: SSS only 2 digits + dot
			if (SSS.length() == 3) {
				timeString += "0";
				log.info("Second(String): SSS is 2 digits, added trailing 0: [" + timeString + "]");
			} 

			// Special case 1a: SSS only 1 digits + dot
			if (SSS.length() == 2) {
				timeString += "00";
				log.info("Second(String): SSS 1 digits, added trailing 00: [" + timeString + "]");
			} 

		}

		
		// Special case 2: if ss is provided with only one digit add a leading 0
		if (ss.length() == 1) {
			timeString = timeString.replace(ss + ".", "0" + ss + ".");
			log.info("Second(String): ss only one digit, added leading 0: [" + timeString + "]");
		}

		// Special case 3: no hh or mm provided
		if ((hh == null) && (mm == null)) {
			timeString = "00:00:" + timeString;
			log.info("Second(String): no hh:mm, provided, added leading 00:00: [" + timeString + "]");
		}

		// Special case 3: no hh provided. Regex assigns the first group, so hh is provided but not mm
		if (hh != null && mm == null) {
			String leading0 = "";
			if (hh.length() == 1) {
				leading0 = "0";
			} 
			timeString = "00:" + leading0 + timeString;
			log.info("Second(String): hh only one digit, added leading 00: [" + timeString + "]");
		}

	
	
		// Format used to parse the input string
		// initialize the sdf. It has to be done locally and not at instance level because in order to
		// correctly manage parsing without an actual date, it needs GMT timezone.
		// see https://stackoverflow.com/questions/8826270/how-to-convert-hhmmss-sss-to-milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); 
		// add epoch in front of the time to make SDF happy.
		timeString = "1970-01-01 " + timeString;

		Date date = sdf.parse(timeString);
		Long milliseconds = date.getTime();
		this.seconds = milliseconds.doubleValue() / 1000;
	}

	/**
	 * formats the internal representation of seconds to hh:mm:ss.SSS
	 */
	public String toString() {
		if (this.isEmpty()) return "";
		
		Long l = new Double(this.seconds * 1000).longValue();
		
        long hr = TimeUnit.MILLISECONDS.toHours(l);
        long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
        long sec = TimeUnit.MILLISECONDS.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        long ms = TimeUnit.MILLISECONDS.toMillis(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
        String formatted = String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);

        // Strips not-significative 0 and ":"
        // BUG: it will remove the leading "0" in case time is 0.123 seconds
        formatted = formatted.replaceFirst("[0:]+", "");
		return  formatted;
	}
	
	public Double getAsDouble() {
		return this.seconds;
	}
}
