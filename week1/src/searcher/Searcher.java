package searcher;

/**
 * A class that owns an int array, and an index.  The array may, or may not be sorted.
 *
 * Implementing classes must implement a findElement method, which will return the kth largest
 * element in the array.
 *
 * @author Hugh Osborne
 * @version September 2018
 *
 */
public abstract class Searcher {
    private int[] array; // the array in which this Searcher object will search
    private int k; // this Searcher object searches for the kth largest entry in the array

    Searcher(int[] array, int k) {
        this.array = array;
        this.k = k;
    }

    /**
     * Get this Searcher's array
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Get this Searcher's index
     */
    int getIndex() { return k; }

    /**
     * Find the kth largest entry in the array
     */
    abstract public int findElement() throws IndexingError;
}
