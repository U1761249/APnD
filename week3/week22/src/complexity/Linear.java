package complexity;

/**
 * A linear implementation of <tt>Method</tt>.
 *
 * @author Hugh Osborne
 * @version 2019
 */
public class Linear extends Timer
{
    public Linear() {
        setName("Linear");
    }

    /**
     * This method will execute in linear time.
     */
    public void method(int n) {
        for (int i = 0; i < n; i++) {
            instruction();
        }
    }

    /**
     * This method will return a value that grows linearly.
     */
    public int complexity(int n) {
        return n;
    }

    /**
     * Uses <tt>Tester.testSequence</tt> to time a sequence of calls of <tt>method</tt> for increasing values of <tt>n</tt>.
     * The second parameter of the call of Tester.testSequence switches popups on or off.
     * @param args the run time arguments which can contain, in order, the problem size to be used to time for calculation
     *             of the base time unit, the number of times each value of <tt>n</tt> is timed (and averaged), the
     *             delay factor, and the limit for termination of the test sequence.  For the last, a positive value
     *             indicates a limiting execution time (in seconds), and a negative value a limiting problem size
     *             (given by the argument's absolute value).
     *             If any of these arguments are missing, the user will be prompted for them.
     */
    public static void main(String[] args)
    {
        Tester.testSequence(new Linear(),0,args);
    }
}
