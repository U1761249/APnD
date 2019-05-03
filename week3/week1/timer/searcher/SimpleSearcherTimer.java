package searcher;

/*
  A timer implementation for simple searchers that times the findElement method

  @author Hugh Osborne
 * @version September 2018
 */

import arrayGenerator.ArrayGenerator;
import arrayGenerator.CleverRandomListingGenerator;
import timer.Timer;

public class SimpleSearcherTimer extends SimpleSearcher implements Timer {

    // All timings will be done with an index of 5
    private final static int K = 5;

    private SimpleSearcherTimer(int[] array) {
        super(array, K);
    }

    @Override
    public void timedMethod() {
        try {
            findElement();
        } catch (IndexingError indexingError) {
            // simply ignore indexing errors here
            // with K at 5, and a minimum task size (array size) of 10, indexing errors should not occur
            // duirng timing
        }
    }

    @Override
    public long getMaximumRuntime() {
        return 1;
    }

    /**
     * Minimum task size (array size) is set to ten, to avoid indexing errors (index is always five)
     * @return minimum task size of ten
     */
    @Override
    public int getMinimumTaskSize() {
        return 10;
    }

    @Override
    public int getMaximumTaskSize() {
        return 100000000;
    }

    @Override
    public Timer getTimer(int size) {
        ArrayGenerator generator = new CleverRandomListingGenerator(size);
        return new SimpleSearcherTimer(generator.getArray());
    }

    public static void main(String[] args) throws IndexingError {
        SimpleSearcherTimer timer = new SimpleSearcherTimer(null);
        timer.timingSequence();
    }
}
