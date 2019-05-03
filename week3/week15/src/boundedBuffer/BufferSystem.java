package boundedBuffer;


import semaphore.*;

/**
 * Sets up and runs a buffer, producer and consumer.
 *
 * @author Hugh Osborne
 * @version January 2019
 */
public class BufferSystem extends Thread
{
    // Predefined delays.  A short delay means a fast process.
    private static final double FAST=0.5, MEDIUM=1.0, SLOW=2.0;

    /**
     * Attempt to parse a producer/consumer delay argument.  These can be "fast", "medium", "slow", or a (string
     * representation of) a double value representing the delay in seconds.
     * @param argument the delay specification
     * @return the delay, in seconds, specified by the argument
     * @throws NumberFormatException if the argument is not one of the predefined specifiers and is also not a validly
     *         formatted double value.
     */
    private static double readDelayArgument(String argument) throws NumberFormatException {
        if (argument.toLowerCase().equals("fast")) {
            return FAST;
        } else if (argument.toLowerCase().equals("medium")) {
            return MEDIUM;
        } else if (argument.toLowerCase().equals("slow")) {
            return SLOW;
        } else {
            return Double.valueOf(argument);
        }
    }
    /**
     * Start up a producer and a consumer.
     * A call of main can have up to four arguments.
     * The first argument should be an integer specifying the size of the buffer.
     * If this argument is missing, or if it is not an integer, the default value of 10 is used.
     * The second argument should be a floating point number specifying
     * the number of seconds the buffer is to stay open for.  If this argument is missing, 
     * or if it is not an integer, the default value of 20 is used.
     * The remaining two arguments should be floating point numbers, specifying maximum delays
     * to be applied to the producer and consumer, thereby affecting their speeds.  The first delay
     * is applied to the producer, the second to the consumer.  The delays can be specified as "fast",
     * "medium", or "slow", in which case the corresponding predefined values are used, or as a double
     * specifying the delay in seconds.
     *
     * @param args the command line arguments for this class.  There should be four of these - in order:
     *             <ul>
     *             <li> the size of the buffer,</li>
     *             <li> the length of time (in seconds) the system is to run</li>
     *             <li> the delay to be applied to the producer</li>
     *             <li> the delay to be applied to the consumer</li>
     *             </ul>
     */
    @SuppressWarnings("unchecked") // suppress warning from cast of argument of sleep
    public static void main(String[] args) {
        int bufferSize = 10; // default buffer size
        double bufferTimeToRun = 20.0, // default run time in seconds
               producerDelay = MEDIUM, // default producer delay
               consumerDelay = MEDIUM;  // default consumer delay
        if (args.length >= 1) {
            try {
                bufferSize = Integer.valueOf(args[0]);
                if (bufferSize < 1) {
                    bufferSize = 10;
                    System.out.println("***Buffer size must be at least 1.\n" +
                                       "***Using a buffer size of " + bufferSize);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("***Buffer size \"" + args[0] + "\" was in the wrong format.\n;" +
                                   "***Using a buffer size of " + bufferSize);
            }
        } else {
            System.out.println("***Buffer size was missing.\n" +
                               "***Using a buffer size of " + bufferSize);
        }
        if (args.length >= 2) {
            try {
                bufferTimeToRun = Double.valueOf(args[1]);
                if (bufferTimeToRun < 0) {
                    bufferTimeToRun = 20.0;
                    System.out.println("***Buffer run time may nt be negative.\n" +
                                       "***Using a buffer run time of " + bufferTimeToRun + " second(s)");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("***Buffer run time \"" + args[1] + "\" was in the wrong format.\n;" +
                                   "***Using a buffer run time of " + bufferTimeToRun + " second(s).");
            }
        } else {
            System.out.println("***Buffer run time was missing.\n" +
                               "***Using a buffer run time of " + bufferTimeToRun + "second(s).");
        }
        if (args.length >= 3) {
            try {
                producerDelay = readDelayArgument(args[2]);
                if (producerDelay < 0) {
                    producerDelay = MEDIUM;
                    System.out.println("***Producer delay cannot be negative.\n" +
                                       "***Using a producer delay of " + producerDelay + " second(s).");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("***Producer delay argument \"" + args[2] + "\" was in the wrong format.\n;" +
                                   "***Using a delay of " + producerDelay + " second(s).");
            }
        } else {
            System.out.println("***Producer delay argument was missing.\n" +
                               "***Using a delay of " + producerDelay + "second(s).");
        }
        consumerDelay = producerDelay;
        if (args.length >= 4) {
            try {
                consumerDelay = readDelayArgument(args[3]);
                if (consumerDelay < 0) {
                    consumerDelay = MEDIUM;
                    System.out.println("***Consumer delay cannot be negative.\n" +
                            "***Using a consumer delay of " + consumerDelay + " second(s).");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("***Consumer delay argument \"" + args[3] + "\" was in the wrong format.\n;" +
                                   "***Using a delay of " + consumerDelay + "second(s).");
            }
        } else {
            System.out.println("***Consumer delay argument was missing.\n" +
                               "***Using a delay of " + consumerDelay + "second(s).");
        }
        System.out.println("***System will run for " + bufferTimeToRun + "s.");
        System.out.println("***Buffer can hold up to " + bufferSize + " elements.");
        System.out.println("***The producer will take up to " + producerDelay + "s between each put() to the buffer.");
        System.out.println("***The consumer will take up to " + consumerDelay + "s between each get() from the buffer.");
        Buffer<Integer> buffer = new Buffer<Integer>(bufferSize);
        Producer<Integer> producer = new Producer(buffer,new IntegerFactory());
        producer.setDelay(producerDelay);
        Consumer<Integer> consumer = new Consumer(buffer);
        consumer.setDelay(consumerDelay);
        producer.start();
        consumer.start();
        try {
            sleep((int) (bufferTimeToRun*1000));
            System.out.println("***Run time ended. Closing the buffer. Starting to terminate the system.");
            try {
                buffer.close();
            } catch (BufferError be) {}
            consumer.join();
            producer.join();
            System.out.println("***System terminated.");
        } catch (InterruptedException ie) {
            System.out.println("***The system was interrupted: " + ie.getMessage());
        } catch (SemaphoreLimitError sle) {
            System.out.println(sle.getMessage());
        }
    }
        
}
