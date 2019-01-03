package arraySorter;

/**
 * Defines the interface for array sorting algorithms.
 *
 * @author Hugh Osborne
 * @version October 2018
 */

public interface ArraySort<T extends Comparable<? super T>>
{
    /**
     * Sort an array.
     *
     * @param array the array to be sorted.
     * @return the sorted array
     */
    T[] sort(T[] array);
}
