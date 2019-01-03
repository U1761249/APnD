package arraySorter;

public abstract class BubbleSortTest<T extends Comparable<? super T>> extends ArraySortTest<T> {
    @Override
    ArraySort<T> getSorter() {
        return new BubbleSort<T>();
    }
}

