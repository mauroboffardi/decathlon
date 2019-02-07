package net.boffardi.decathlon.api;

import net.boffardi.decathlon.api.types.units.EventResult;

/**
 * Miscellaneous utility static methods
 * @author mauro.boffardi
 *
 */
public class Utils {
	
	/** 
	 * just to avoid var1 == null && var2 == null && var3...
	 * @param args
	 * @return true if NONE of the passed variables are null.
	 */
	public static boolean notNull(Object... args) {
	    for (Object arg : args) {
	        if (arg == null) {
	            return false;
	        }
	    }
	    return true;
	}

	/** 
	 * just to avoid var1 == null && var2 == null && var3...
	 * @param args
	 * @return true if NONE of the passed variables are null.
	 */
	public static boolean notEmpty(EventResult... args) {
	    for (EventResult arg : args) {
	        if (arg.isEmpty()) {
	            return false;
	        }
	    }
	    return true;
	}

}
