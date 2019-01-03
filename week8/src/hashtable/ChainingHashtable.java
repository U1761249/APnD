package hashtable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class ChainingHashtable<S,T> implements HuddersfieldHashtable<S,T> {
    /**
     * The hash table consists of an array of ArrayLists in which we can store
     * our data.
     */
    protected ArrayList<src.hashtable.Record<S,T>>[] table; // the hashtable

    /**
     * Construct an new hashtable with the specified initial size
     * @param size the initial size of the hashtable
     */
    public ChainingHashtable(int size) {
        table = (ArrayList<src.hashtable.Record<S,T>>[]) Array.newInstance(
                new ArrayList<src.hashtable.Record<S,T>>().getClass(),size);
    }
}
