package complexity;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/**
 * This class provides a facility for timing execution of a <tt>Timer</tt>'s <tt>method</tt>, for increasing values of
 * <tt>method</tt>'s parameter (modelling the "size of the problem"), terminating the test sequence when a maximum
 * execution time is exceeded.
 *
 * @author  Hugh Osborne
 * @version October 2019
 */

public class Tester
{
    /**
     * Attempt to parse a string that should represent a double value.
     * If the string is null, or the parse fails prompt the user for input, and re-attempt the parse.
     * @param message a description of the input - used when prompting the user
     * @param input the string to be parsed
     * @return the double the input (argument or user prompted) parses to
     */
    private static double readDouble(String message,String input) {
        String prompt = message;
        boolean firstParse = true;
        do {
            // try the input provided as a parameter first
            try {
                double result = Double.parseDouble(input);
                return result;
            } catch (NullPointerException npe) { // input was null
                if (!firstParse) { // do not chide the user on the first parse - the input argument may have been null
                    prompt = "No value provided\n" + message;
                }
            } catch (NumberFormatException nfe) { // input was not a double
                prompt = "Value was incorrectly formatted\nPlease input a correctly formatted fp number\n" + message;
            }
            // parsing input failed - prompt for new input
            // first update prompt if needed - do this after the second failed attempt
            input = (String) JOptionPane.showInputDialog(prompt);
        } while (true); // code exits when a valid number is input
    }

    /**
     * Attempt to parse a string that should represent a double value.
     * If the string is null, or the parse fails prompt the user for input, and re-attempt the parse.
     * @param message a description of the input - used when prompting the user
     * @param input the string to be parsed
     * @return the double the input (argument or user prompted) parses to
     */
    private static int readInteger(String message,String input) {
        String prompt = message;
        boolean firstParse = true;
        do {
            // try the input provided as a parameter first
            try {
                int result = Integer.decode(input);
                return result;
            } catch (NullPointerException npe) { // input was null
                if (!firstParse) { // do not chide the user on the first parse - the input argument may have been null
                    prompt = "No value provided\n" + message;
                }
            } catch (NumberFormatException nfe) { // input was not a double
                prompt = "Value was incorrectly formatted\nPlease input a correctly formatted fp number\n" + message;
            }
            // parsing input failed - prompt for new input
            // first update prompt if needed - do this after the second failed attempt
            input = (String) JOptionPane.showInputDialog(prompt);
        } while (true); // code exits when a valid number is input
    }

    /**
     * Set the font size used for popups.
     * @param fontSize the font size to be used for popups.
     */
    private static void setUIFont(int fontSize) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource original = (FontUIResource) value;
                Font font = new Font(original.getFontName(),original.getStyle(),fontSize);
                UIManager.put(key,new FontUIResource(font));
            }
        }
    }

    private static JFrame popupRoot; // used to root popups
    private static int fontSize = 0;

    /**
     * Print an information message.
     * If popups are being used then a popup will be used.  Otherwise the message is printed to the console.
     * @param message the message
     * @param header the message title
     */
    public static void report(String message,String header) {
        if (fontSize > 0) {
            JOptionPane.showMessageDialog(popupRoot,message,header,JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println(header + ":\n\t" + message);
        }
    }

    /**
     * Run through a sequence of tests.
     * Code is tested for <tt>n</tt> = 1,2,..,9, then 10,20,..,90 and so on for
     * increasing powers of ten.
     * The sequence of tests terminates when the execution time of the method exceeds the time limit, or the size
     * of <tt>n</tt> reaches 10<sup>9</sup>.
     * @param timer the Timer object to be timed.
     * @param font specifies the the font size to be used in popups.  A value of zero will disable popups.
     * @param args possibly passed from a call of testSequence in a main method, and may contain, in order,
     *             Strings determining:
     *             <ul>
     *             <li> the problem size to be timed to establish a base for time estimates</li>
     *             <li> the number of times the timer is timed in each test run for any one problem size</li>
     *             <li> the delay (in milliseconds) to be used for the timer, determining its basic speed</li>
     *             <li> the limit at or beyond which the test sequence will terminate</li>
     *             </ul>
     * With the limit argument, a positive value indicates a limiting execution time (in seconds), a negative value
     * indicates a limiting problem size (given by the absolute value of the limit).
     *
     * If any of these arguments are not provided in <tt>args</tt> the user will be prompted for them.
     */
    public static void testSequence(Timer timer, int font,String[] args) {
        DecimalFormat format = new DecimalFormat();
        String baseSizeString = null, repeatString = null, delayString = null, limitString = null;
        StringBuilder builder = new StringBuilder("\n\nSpreadsheet friendly results\n\n");
        fontSize = font;
        switch (args.length) {
            case 4:
                limitString = args[3];
            case 3:
                delayString = args[2];
            case 2:
                repeatString = args[1];
            case 1:
                baseSizeString = args[0];
        }
        popupRoot = new JFrame();
        popupRoot.setAlwaysOnTop(true);
        int baseSize = readInteger("Please input a problem size for establishing the base time unit for time estimates.\nA value that is expected to lead to a runtime of approximately 1 second is recommended.",baseSizeString);
        int repeat = readInteger("Please enter the number of times each \"problem size\" must be timed.",repeatString);
        double delay = readDouble("Please set the delay for these timer tests (in milliseconds).\nThis may include a fractional amount.",delayString);
        Timer.setDelay(delay);
        double timeLimit; long sizeLimit;
        double limit = readDouble("Please set the limit after which testing should terminate.\n" +
                                           "Use a positive number to set a time limit in seconds.\n" +
                                           "Use an to set a limit in terms of the problem size\n" +
                                           "E.g. a value of 10 will cause testing to terminate when a test takes 10 or more seconds,\n" +
                                           "\ta value of -100 will cause testing to terminate when the \"problem size\" reaches or surpasses 100",limitString);
        if (limit >= 0) {
            timeLimit = limit*Timer.MILLIARD; // translate time limit to nanoseconds
            sizeLimit = Timer.MILLIARD; // default size limit
        } else {
            timeLimit = 60*Timer.MILLIARD; // default time limit (1 minute, in nanoseconds)
            sizeLimit = (int) -limit;
        }
        setUIFont(fontSize);
        report("Running " + timer.getName() + ": base size " + baseSize + ", run size " + repeat + ", delay " + delay + "ms, time limit " + timer.formatTime(timeLimit) + ", size limit " + format.format(sizeLimit),"Run info");
        double baseTime = timer.time(baseSize,repeat);
        int baseComplexity = timer.complexity(baseSize);
        double baseTimeUnit = baseTime/baseComplexity;
        for (int power = 1; true; power = power*10) {
            builder.append(timer.getName() + ": " + format.format(power) + "-" + format.format(10*power) + "\tExpected\tActual\n"); // add header for this range to trace
            for (int counter = 1; counter <= 10; counter++) { // complete the rest of this range
                double timeTaken = timer.time(counter*power,repeat);
                double expectedTime = baseTimeUnit*timer.complexity(counter*power);
                builder.append(format.format(counter*power) + "\t" + format.format(expectedTime/Timer.MILLION) + "\t" + format.format(timeTaken/Timer.MILLION) + "\n"); // times translated to milliseconds
                timer.reportTime(counter*power,expectedTime,timeTaken);
                if (timeTaken >= timeLimit) {
                    report("Last test took " + timer.formatTime(timeLimit)  + ".\n\tThis meets or exceeds the time limit.\n\tTests ended.","End of timing");
                    System.out.println(builder);
                    popupRoot.dispose();
                    return;
                } else if (counter*power >= sizeLimit) {
                    report("Last test size was " + format.format(sizeLimit)  + ".\n\tThis meets or exceeds the size limit.\n\tTests ended.","End of timing");
                    System.out.println(builder);
                    popupRoot.dispose();
                    return;
                }
            }
        }
    }
}
