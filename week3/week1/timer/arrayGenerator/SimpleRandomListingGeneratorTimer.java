package arrayGenerator;

/*
  A timer implementation for simple random listing generators that times the randomise method.

  @author Hugh Osborne
 * @version September 2018
 */

import timer.Timer;

public class SimpleRandomListingGeneratorTimer extends SimpleRandomListingGenerator implements Timer {

    private SimpleRandomListingGeneratorTimer(int size) {
        super(size);
    }

    @Override
    public Timer getTimer(int size) {
        return new SimpleRandomListingGeneratorTimer(size);
    }

    @Override
    public void timedMethod() {
        randomise();
    }

    @Override
    public long getMaximumRuntime() {
        return 5;
    }

    @Override
    public int getMinimumTaskSize() {
        return 1;
    }

    @Override
    public int getMaximumTaskSize() {
        return 1000000000;
    }

    public static void main(String[] args) {
        SimpleRandomListingGeneratorTimer timer = new SimpleRandomListingGeneratorTimer(0);
        timer.timingSequence();
    }
}
