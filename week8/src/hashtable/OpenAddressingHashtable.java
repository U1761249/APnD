package hashtable;

import java.lang.reflect.Array;

public abstract class OpenAddressingHashtable<S,T> implements HuddersfieldHashtable<S,T> {

    protected src.hashtable.Record<S,T>[] table; // the hashtable

    /**
     * Construct an open addressing hashtable with the specified
     * initial size.
     * @param size the (intial) size of the hash table
     */
    public OpenAddressingHashtable(int size) {
        table = (src.hashtable.Record<S,T>[]) Array.newInstance(new src.hashtable.Record<S,T>(null,null).getClass(),size);
    }

    /**
     * Calculate an index in the hashtable for an entry with the given key.  Return the entry's
     * index if it exists.  If there is no such entry return the index of the
     * next free field in the hashtable (for this key).
     * @param key the key being looked for
     * @return the index of the entry with this key, if it exists
     * @return the address of the next free space if there is no such key in the table
     * @throws Error if the table is full and does not contain an entry with the given key
     */
    protected abstract int index(S key) throws Error;

    /**
     * Find the next available index in the case of an address collision
     * @param index the initial index returned by the raw hash function
     * @param n the number of this attempt (how many times have we probed since the first index calculation)
     * @return the address specified by the probing function for this type of hashtable
     */
    protected abstract int probe(int index,int n);
}
