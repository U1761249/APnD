package arrayGenerator;

/*
  A timer implementation for clever random listing generators that times the randomise method.

  @author Hugh Osborne
 * @version September 2018
 */

import timer.Timer;

public class CleverRandomListingGeneratorTimer extends CleverRandomListingGenerator implements Timer {

    private CleverRandomListingGeneratorTimer(int size) {
        super(size);
    }

    @Override
    public Timer getTimer(int size) {
        return new CleverRandomListingGeneratorTimer(size);
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
        CleverRandomListingGeneratorTimer timer = new CleverRandomListingGeneratorTimer(0);
        timer.timingSequence();
    }
}
