package arraySorter;

public abstract class QuickSortTest<T extends Comparable<? super T>> extends ArraySortTest<T> {
    @Override
    ArraySort<T> getSorter() {
        return new QuickSort<T>();
    }
}