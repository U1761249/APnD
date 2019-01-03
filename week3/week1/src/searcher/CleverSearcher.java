package searcher;

/**
 * @author Adam Birch
 * @version October 2018

 */

public class CleverSearcher<T extends Comparable<? super T>> extends Searcher {

    CleverSearcher(Object[] array, int k) {
        super(array, k);
    }


    @Override
    public T findElement() throws IndexingError {
        /**
         * This is the main search to be implemented
         * @return kth largest element
         * @exception IndexingError on bad input
         * @see IndexingError
         */
        T[] array = (T[]) getArray();
        int k = getIndex();
        if (k <= 0 || k > array.length) { // Test that k is a valid search.
            throw new IndexingError();
        }
        T[] smallArray = (T[])new Object[k];  // Create a small array of size k - the k'th largest is element 0
        for (Integer i = 0; i < array.length; i++) {
            T value = array[i];
            T temp = smallArray[0];
            if (value.compareTo(temp) > 0){
                if (smallArray.length > 1){ // Only loop if there are many values in the Array.
                    int x = 0;
                    while (x < smallArray.length - 1 && value.compareTo(smallArray[x+1]) > 0){
                        smallArray[x] = smallArray[x+1];
                        x++;
                    }
                    smallArray[x] = value; // Adds the new value to the Array once it is in the correct place.
                }
                else {
                    smallArray[0] = value;
                }
            }
        }
        return smallArray[0];
    }
}
