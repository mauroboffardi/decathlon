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

	public Centimeters(String centimetersString) throws NumberFormatException {
		centimeters = Integer.parseInt(centimetersString);
	}

	public Centimeters(Integer centimeters) {
		this.centimeters = centimeters;
	}
	
	public boolean isTimeBased() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.types.units.EventResultAbstractInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (centimeters == null);
	}

	
	// empty string if null, otherwise the normal localized representation for double
	public String toString() {
		if (centimeters == null) return "";
		return Double.toString(this.centimeters);
	}
	
	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.types.units.EventResultAbstractInterface#getAsDouble()
	 */
	@Override
	public Double getAsDouble() {
		return this.centimeters.doubleValue();
	}

}
