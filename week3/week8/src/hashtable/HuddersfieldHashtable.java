package hashtable;

/**
 * Defines the interface for locally implemented hashtables
 *
 * @author Hugh Osborne
 * @version November 2018
 */

public interface HuddersfieldHashtable<S,T> {

    /** This method should store the given data in the array at the position given by the
     *  key's hash code (taken modulo the table's size).
     *  @param key the key to be used to access the hash table
     *  @param data the data to be inserted
     *  @throws Error if the table is full
     */

    public void insert(S key,T data) throws Error;

    /**
     * This method should retrieve a value from a hash table
     * @param key the key to be used to retrieve the object
     * @returns the object specified by the key (if present in the table)
     * @throws Error if no object with that key is present in the table
     */
    public T retrieve(S key) throws Error;

    /**
     * Simple error reporting for hashtables
     */
    public class Error extends Exception
    {
        public Error() {
            super();
        }

        public Error(String message) {
            super(message);
        }
    }
}
