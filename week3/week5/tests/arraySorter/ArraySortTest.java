package arraySorter;

import arrayGenerator.ArrayGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Provides a basic package of tests for array sorters.
 * These tests generate random arrays, sort them, and then test the sorted arrays' properties.
 * <ul>
 *     <li> Is the array sorted?</li>
 *     <li> Does the array still contain the same values it started with?</li>
 * </ul>
 */

abstract class ArraySortTest<T extends Comparable<? super T>> {

    abstract ArraySort<T> getSorter();
    abstract ArrayGenerator<T> getGenerator();

    /**
     * Check that the array is sorted after sorting.
     * @param size the size of the array to be generated and sorted.
     */
    void testSorted(int size) {
        T[] array = getGenerator().getArray(size);
        array = getSorter().sort(array);
        for (int index = 1; index < array.length; index++) {
            if (array[index-1].compareTo(array[index]) > 0) {
                for (int i = 0; i < array.length; i++){
                    System.out.print(array[i]);
                }
                fail(array[index-1] + " at " + (index-1) + " and " + array[index] + " at " + index + " are out of order");
            }
        }
    }

    /**
     * Check that the contents of the array are not changed by sorting.
     * @param size the size of the array to be generated and sorted.
     */
    void testContents(int size) {
        T[] array = getGenerator().getArray(size);
        T[] copy = array.clone();
        array = getSorter().sort(array);
        for (T entry: array) {
            if (numberIn(entry, array) != numberIn(entry, copy)) {
                fail("Array contents have changed.");
            }
        }
    }

    private int numberIn(T value,T[] array) {
        int numberIn = 0;
        for (T entry: array) {
            if (entry.equals(value)) {
                numberIn++;
            }
        }
        return numberIn;
    }

    @Test
    void testSortedOne() {
        testSorted(1);
    }

    @Test
    void testSortedTwelve() {
        testSorted(12);
    }

    @Test
    void testSortedThousand() {
        testSorted(1000);
    }

    @Test
    void testContentsOne() {
        testContents(1);
    }

    @Test
    void testContentsTwelve() {
        testContents(12);
    }

    @Test
    void testContentsThousand() {
        testContents(1000);
    }

    // Additional tests for larger values
    @Test
    void testSortedTenThousand() {
        testSorted(10000);
    }
    @Test
    void testSortedFiftyThousand() {
        testSorted(50000);
    }
    @Test
    void testSortedTwoThousand() {
        testSorted(2000);
    }

    @Test
    void testContentsTenThousand() {
        testContents(10000);
    }
    @Test
    void testContentsFiftyThousand() {
        testContents(50000);
    }
    @Test
    void testContentsTwoThousand() {
        testContents(2000);
    }


}