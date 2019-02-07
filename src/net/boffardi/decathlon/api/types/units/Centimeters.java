package net.boffardi.decathlon.api.types.units;

/**
 * Wrapper class for results of events managed in Centimeters (jumps).
 * Used to provide common operation like toString and conversions.
 * 
 * @author mauro.boffardi
 *
 */
public class Centimeters implements EventResult {
	private Integer centimeters = null;

	// default constructor 
	public Centimeters() {		
	}

	/**
	 * Constructor from string. if string is null or empty the object is created with "empty" status.
	 * @param metersString
	 * @throws NumberFormatException
	 */
	public Centimeters(String centimetersString) throws NumberFormatException {
		if (centimetersString != null && !centimetersString.isEmpty() ) {
			centimeters = Integer.parseInt(centimetersString);
		}
	}

	public Centimeters(Integer centimeters) {
		this.centimeters = centimeters;
	}
	
	public Centimeters(Double centimeters) {
		this.centimeters = centimeters.intValue();
	}
	
	public boolean isTimeBased() {
		return false;
	}
	

	@Override
	/**
	 * Used to check if the result of the event has been registered or not
	 */
	public boolean isEmpty() {
		return (centimeters == null || centimeters == 0);
	}

	
	// empty string if null, otherwise the normal localized representation for double
	public String toString() {
		if (this.isEmpty()) return "";
		return Integer.toString(this.centimeters);
	}
	
	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.types.units.EventResultAbstractInterface#getAsDouble()
	 */
	@Override
	public Double getAsDouble() {
		return this.centimeters.doubleValue();
	}

}
