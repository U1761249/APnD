package searcher;

/*
  Implements the find (kth) element method by sorting and indexing.
 */

import java.util.Arrays;

public class SimpleSearcher extends Searcher {

    SimpleSearcher(int[] array, int k) {
        super(array, k);
    }

    /**
     * Find the kth largest element in an array of ints using the "obvious"
     * solution from the lecture
     *
     * <ul>
     *     <li> Sort the array</li>
     *     <li> Return the entry k spaces from the end</li>
     * </ul>
     *
     * @return kth largest element of array
     */
    public int findElement() throws IndexingError {
        int[] array = getArray();
        int k = getIndex();
        if (k <= 0 || k > array.length) {
            throw new IndexingError();
        }
        Arrays.sort(array); // sort the whole array
        return array[array.length - k]; // desired element is kth from the end
    } // end of obvious solution method
}
