package arraySorter;

/**
 * @author Adam Birch
 * @version November 2018
 *
 * This solution was adapted from that found at:
 * https://stackoverflow.com/questions/12128066/selection-sort-with-generics
 */

public class SelectionSort<T extends Comparable<? super T>> implements ArraySort<T> {
    @Override
    public T[] sort(T[] array) {

        for(int i=0; i < array.length -1; i++)
        {
            int iSmallest = i;

            for(int j=i+1; j < array.length; j++)
            {
                if(array[iSmallest].compareTo((array[j])) > 0  )
                {
                    iSmallest = j;
                }
            }
            T iSwap = array[iSmallest];
            array[iSmallest] = array[i];
            array[i] = iSwap;

        }
        return array;
    }

    public static <T> void printArray(T[] array)
    {

        for(int i=0; i < array.length; i++)
        {
            System.out.print(array[i] + ", ");
        }
    }
}
