package net.boffardi.decathlon.api;

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


}
