package searcher;

/**
 * @author Adam Birch
 * @version October 2018

 */

public class CleverSearcher extends Searcher {

    CleverSearcher(int[] array, int k) {
        super(array, k);
    }


    @Override
    public int findElement() throws IndexingError {
        /**
         * This is the main search to be implemented
         * @return kth largest element
         * @exception IndexingError on bad input
         * @see IndexingError
         */
        int[] array = getArray();
        int k = getIndex();
        if (k <= 0 || k > array.length) { // Test that k is a valid search.
            throw new IndexingError();
        }
        int[] smallArray = new int[k];  // Create a small array of size k - the k'th largest is element 0
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            if (value > smallArray[0]){
                if (smallArray.length > 1){ // Only loop if there are many values in the Array.
                    int x = 0;
                    while (x < smallArray.length - 1 && value > smallArray[x+1]){
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
