package net.boffardi.decathlon.api.types.units;

/**
 * Wrapper class for results of events managed in Meters.
 * Used to provide common operation like toString and conversions.
 * 
 * @author mauro.boffardi
 *
 */
public class Meters {
	private Double meters = null;

	// default constructor 
	public Meters() {		
	}

	public Meters(String metersString) throws NumberFormatException {
		meters = Double.parseDouble(metersString);
	}

	public Meters(Double meters) {
		this.meters = meters;
	}
	
	// empty string if null, otherwise the normal localized representation for double
	public String toString() {
		if (meters == null) return "";
		return Double.toString(this.meters);
	}
	
	public Double getAsDouble() {
		return this.meters;
	}

}
