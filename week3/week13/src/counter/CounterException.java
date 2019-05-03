package counter;

/**
 * Exception class for counters.
 *
 * @author Hugh Osborne 
 * @version January 2019
 */
public class CounterException extends Exception
{
	/**
     * Reports errors occurring during the construction and use of counters.
     *
     * Various errors may occur when defining a counter.  E.g.
     * <ul>
     * <li> counting in the wrong direction (e.g. from 0 to 10 in steps of -1)
     * <li> using an increment value of 0
     * </ul>
     * @param s the error message for this exception.
     **/
     
     public CounterException(String s) {
         super(s);
    }
}
