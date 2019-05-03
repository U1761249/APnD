package complexity;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 * Adds a timer functionality to Method objects.
 *
 * @author Hugh Osborne
 * @version October 2019
 */

public abstract class Timer extends Thread implements Method
{
    private static long milliDelay = 0; // millisecond component of delay
    private static int nanoDelay = 0; // nanosecond component of delay

    /**
     * Reports the time taken by a call of this <tt>Timer</tt>'s <tt>method(n)</tt>, in nanoseconds.
     * @param n the value ("size of the problem") to be timed.
     * @return the time taken for this problem size, in nanoseconds.
     */
    public double time(int n) {
        double startTime = System.nanoTime();
        method(n);
        return System.nanoTime()-startTime;
    }

    /**
     * Reports the average time taken by a number of calls of this <tt>Timer</tt>'s <tt>method(n)</tt>, in nanoseconds.
     * @param n the value ("size of the problem") to be timed.
     * @param runs the number of times this time should be measured (and averaged).
     * @return the average time taken, in nanoseconds, for this problem size over the full set of runs.
     */
    public double time(int n,int runs) {
        double totalTime = 0;
        for (int run = 0; run < runs; run++) {
            totalTime += time(n);
        }
        totalTime = totalTime/runs;
        return totalTime;
    }

    static final long THOUSAND = 1000;
    static final long MILLION = 1000000;
    static final long MILLIARD = 1000000000;
    /**
     * Set the delay for slowing down processes
     * The same delay factor should be used for all processes, so this
     * method is static
     * @param delay the duration, in milliseconds, of a delay action
     */
    public static void setDelay(double delay) {
        milliDelay = (long) Math.floor(delay); // delay is in milliseconds, so millisecond component is whole part
        nanoDelay = (int) ((delay-milliDelay) * MILLION); // nanosecond component is 10^6 times fractional remainder
                                                      // (1ms = 10^6ns)
    }

    /**
     * The <tt>instruction</tt> method <i>must</i> be called in implementations of method()
     * to delay the process
     */
    public void instruction() {
        try {
            sleep(milliDelay,nanoDelay);
        } catch (InterruptedException e) {}
    }

    /**
     * Format a double representing a number of nanoseconds as a String representation of the time corresponding to
     * that number of nanoseconds.  The representation will be in terms of the largest subunit of a second that is
     * contained in that time.
     * @param time the number of nanoseconds in the time interval
     * @return a String representation of the time interval specified
     */
    public String formatTime(double time) {
        String timeReport;
        DecimalFormat timeformat = new DecimalFormat();
        timeformat.setMaximumFractionDigits(3);
        timeformat.setMinimumFractionDigits(3);
        if (time < THOUSAND) { // less than one microsecond
            return timeformat.format(time) + " nanoseconds";
        }
        time = time/THOUSAND;
        if (time < THOUSAND) { // less than one millisecond
            return timeformat.format(time) + " microseconds";
        }
        time = time/THOUSAND;
        if (time < THOUSAND) {
            return timeformat.format(time) + " milliseconds";
        }
        time = time/THOUSAND;
        if (time < 60) {
            return timeformat.format(time) + " seconds";
        }
        time = time/60;
        if (time < 60) {
            return timeformat.format(time) + " minutes";
        }
        return timeformat.format(time) + " hours";
    }

    /**
     * Report on the time taken by a call of <tt>method</tt>
     * @param size the size of the problem
     * @param expectedTime the length of time (in nanoseconds) that execution was expected to take
     * @param actualTime the length of time (in nanoseconds) that execution actually took
     */
    public void reportTime(int size,double expectedTime,double actualTime) {
        DecimalFormat paramformat = new DecimalFormat();
        Tester.report(getName() + "(" + paramformat.format(size) + ") took " + formatTime(actualTime) + ", expected time was " + formatTime(expectedTime),"Time taken, size "+ size);
    }
}
