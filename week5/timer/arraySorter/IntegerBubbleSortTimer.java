package arraySorter;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.IntegerArrayGenerator;
import scope.IntegerScope;
import timer.Timer;

public class IntegerBubbleSortTimer extends BubbleSortTimer<Integer> {
    @Override
    public Timer getTimer(int size) {
        ArrayGenerator<Integer> generator = new IntegerArrayGenerator(new IntegerScope());
        setArray(generator.getArray(size));
        return this;
    }

    public static void main(String[] args) {
        BubbleSortTimer timer = new IntegerBubbleSortTimer();
        timer.timingSequence();
    }
}
