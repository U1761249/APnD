package timer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;

/**
 * We are interested in how long our implemented methods take to execute. This
 * interface defines a time method that can be used to time a given method in
 * implementing classes.  This is known as the <tt>timed method</tt>.
 *
 * Each timer.Timer object is expected to have some <tt>int</tt> measure of the "size" of
 * the task the timed method has to undertake.
 *
 * @author Hugh Osborne
 * @version September 2018
 *
 */
public interface Timer {

    /**
     * Get a timer.Timer object with a task of the given size for the timed method.
     * @param size the size of the task to be timed.
     * @return a timer.Timer object with a task of that size
     *
     */
    public Timer getTimer(int size);

    /**
     * Nominate the timed method.  In an implementing class <tt>timedMethod</tt> will
     * be a wrapper method for the method to be timed.
     */
    public void timedMethod();

    /**
     * A timer.Timer object shoud specify the maximum length of time that a time test should run
     * @return the maximum time that a single time test should run, in seconds
     */
    public long getMaximumRuntime();

    /**
     * timer.Timer objects must also specify the minimum task size that they wish to see timed.
     * @return the maximum task size to be timed
     */
    public int getMinimumTaskSize();

    /**
     * timer.Timer objects must also specify the maximum task size that they wish to see timed.
     * @return the maximum task size to be timed
     */
    public int getMaximumTaskSize();

    /**
     * Run the timed method belonging to an implementing class, and time how
     * long it takes.
     *
     * @return the time taken to execute the method being timed, in nanoseconds
     */
    default Duration time() {
        long startTime = System.nanoTime();
        timedMethod();
        long endTime = System.nanoTime();
        return Duration.ofNanos(endTime - startTime);
    }

    /**
     * Run a sequence of time tests of increasing size. Task sizes of size 1,2,..,9,
     * then 10,20,..,90 and so on for increasing powers of ten are timed. The sequence of
     * tests terminates when the execution time of the randomisation reaches or
     * surpasses the time limit, or the size of the generator reaches or
     * surpasses the maximum size.
     */
    default void timingSequence() {
        NumberFormat formatter = new DecimalFormat("#,###");
        int counter = getMinimumTaskSize();
        int power = 1;
        while (counter >= 10) {
            counter = counter / 10;
            power = power * 10;
        }
        for (;; power = power * 10) {
            for (; counter < 10; counter++) {
                Timer timer = getTimer(counter * power);
                Duration time = timer.time();
                String timeString = time.toString().substring(2).replaceFirst("S", " seconds");
                System.out.println(timer.getClass() + " took " + timeString + " for a task of size " + formatter.format(counter * power));
                if (counter * power >= getMaximumTaskSize()) {
                    System.out.println("Maximum task size, " + getMaximumTaskSize() + ", reached. Ending timing sequence.");
                    return;
                }
                if (time.getSeconds() >= getMaximumRuntime()) {
                    System.out.println("Time limit of " + getMaximumRuntime() + " seconds reached.  Ending timing sequence.");
                    return;
                }
            }
            counter = 1;
        }
    }
}
