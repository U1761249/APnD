package src.hashtable;

/**
 * Models an entry in a hash table.
 * @author Hugh Osborne
 * @version November 2018
 *
 * @param <S> the type of key used in these records
 * @param <T> the type of value stored in these records
 */

public class Record<S,T> {
    S key; // this record's key
    T value; // this record's value

    protected Record(S key,T value) {
        this.key = key;
        this.value = value;
    }

    protected S getKey() {
        return key;
    }

    protected T getValue() {
        return value;
    }
}
