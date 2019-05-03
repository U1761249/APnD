package arraySorter;

/**
 * @author Adam Birch
 * @version November 2018
 *
 * This solution was adapted from that found at:
 * https://github.com/chrisloy/java-algorithms/blob/master/src/uk/co/chrisloy/sandpit/sort/QuickSort.java
 */

public class QuickSort<T extends Comparable<? super T>> implements ArraySort<T> {

    private T[] array;

    @Override
    public T[] sort(T[] array) {
        this.array = array;
        quicksort(0, array.length - 1);
        return array;
    }

    private void quicksort(int left, int right) {

        if(left >= right) {
            return;
        }

        T pivot = array[left + (right - left)/2];

        int i = left;
        int j = right;

        while (i <= j) {

            while(array[i].compareTo(pivot) < 0) {
                i++;
            }

            while(array[j].compareTo(pivot) > 0) {
                j--;
            }

            if(i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        quicksort(left, j);
        quicksort(i, right);
    }

    private void swap(int a, int b) {
        T x = array[a];
        array[a] = array[b];
        array[b] = x;
    }

}
