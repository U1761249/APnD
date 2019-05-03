package arrayGenerator;

import java.util.Arrays;

/**
 * Implements the randomise() method to complete RandomListingGenerator.
 *
 * This simple implementation does not use a very efficient method of randomisation.
 *
 * @author Hugh Osborne
 * @version September 2018
 */

public class SimpleRandomListingGenerator extends RandomListingGenerator {
    /**
     * Constructor
     */
    SimpleRandomListingGenerator(int size) {
        super(size);
    }

    /**
     * Randomise the array.
     *
     * The algorithm is:
     * <ul>
     *     <li> Access the sorted array</li>
     *     <li> Create a new array of the same size (this will be the randomised array)</li>
     *     <li> For each entry in the new array</li>
     *     <ul>
     *         <li> Select a random, unused element of the sorted array</li>
     *         <li> Enter it into the new array</li>
     *         <li> Note that this entry in the sorted array has now been used</li>
     *     </ul>
     *     <li> Once all the entries in the sorted array have been entered, in a random order, in the
     *          new array, copy the randomised array back to the Listing's main array</li>
     * </ul>
     * The use of entries from the sorted array is managed by creating a third array, this time
     * of booleans, of the same size as the sorted array.  All values in this array are initialised
     * to false (no entries have yet been used).  Each time an entry in the sorted array is used, the
     * corresponding boolean is set to true.  We can then check whether an entry has been used
     * by checking whether its corresponding boolean is true.
     */
    protected void randomise() {
        // create a new int array of the same size as the sorted array
        int[] copy = new int[getArray().length];
        // create a boolean array of the same size -  used to indicate if elements have been used
        boolean[] used = new boolean[getArray().length];
        // initialise all values in the boolean array to false - no sorted values have been used yet
        Arrays.fill(used, false);
        for (int index = 0; index < getArray().length; index++) {  // for each entry in the new array
            int randomIndex;
            do {  // repeatedly
                randomIndex = getRandomIndex();  // pick a random index in the sorted array
            } while (used[randomIndex]);  // until you find a value that hasn't been used
            // copy the entry, in the sorted array, at this unused random index, to the new array
            copy[index] = getArray()[randomIndex];
            used[randomIndex] = true;  // note that this entry has been used
        }
        // now copy the random array back to the Listing's main array
        for (int index = 0; index < getArray().length; index++) {
            getArray()[index] = copy[index];
        }
    }
}
