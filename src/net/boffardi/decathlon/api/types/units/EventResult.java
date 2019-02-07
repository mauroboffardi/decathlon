package net.boffardi.decathlon.api.types.units;

/**
 * Make sure that Meters, Centimeters, and Seconds all provide the same basic common methods.
 * @author mauro.boffardi
 *
 */
public interface EventResult {

	/**
	 * True if the object is instantiated but value is null
	 * @return
	 */
	boolean isEmpty();

	/**
	 * Return all internal representations as Double, so to allow calculations with the same method.
	 * @return
	 */
	Double getAsDouble();
	
	/**
	 *  Returns the type or event result. The formula to change points is different for time and length.
	 *  If isTimeBased is false, it means that is length based.
	 */	
	boolean isTimeBased();
	
}