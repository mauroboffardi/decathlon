package net.boffardi.decathlon.api.types;

public enum Discipline {
    MEN, WOMEN;
    
	/**
	 * Convert the enum in the character representation
	 * to use either in the form or in the DB representation.
	 */
	public String toString() {
        if (this == Discipline.MEN) {
        	return "M";
        } else {
        	return "W";
        }
	}
}
