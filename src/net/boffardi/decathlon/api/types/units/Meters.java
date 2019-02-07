package net.boffardi.decathlon.api.types.units;

import org.apache.derby.iapi.util.StringUtil;

/**
 * Wrapper class for results of events managed in Meters.
 * Used to provide common operation like toString and conversions.
 * 
 * @author mauro.boffardi
 *
 */
public class Meters implements EventResult {
	private Double meters = null;

	// default constructor 
	public Meters() {		
	}

	/**
	 * Constructor from string. if string is null or empty the object is created with "empty" status.
	 * @param metersString
	 * @throws NumberFormatException
	 */
	public Meters(String metersString) throws NumberFormatException {
		if (metersString != null && !metersString.isEmpty() ) {
			meters = Double.parseDouble(metersString);
		}
	}

	public Meters(Double meters) {
		this.meters = meters;
	}
	
	/**
	 * Used to check if the result of the event has been registered or not
	 */
	public boolean isEmpty() {
		return (meters == null || meters == 0.0);
	}


	public boolean isTimeBased() {
		return false;
	}
	
	// empty string if null, otherwise the normal localized representation for double
	public String toString() {
		if (this.isEmpty()) return "";
		return Double.toString(this.meters);
	}
	
	public Double getAsDouble() {
		return this.meters;
	}

}
