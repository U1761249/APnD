package complexity;

/**
 * Implementations of this class should have names suggesting a particular complexity class, and should implement
 * the <tt>method</tt> and <tt>complexity methods</tt> in a way that corresponds to the class name.
 * E.g. an implementation of this interface called <tt>Linear</tt> should implement <tt>method</tt> such that it
 * has linear (time) complexity (in its parameter <tt>n</tt>), and implement <tt>complexity</tt> such that it
 * implements a linear function of its parameter <tt>n</tt> (the obvious function being f(n)=n).
 *
 * @author Hugh Osborne
 * @version October 2019
 */

public interface Method
{
    /**
     * Implementations of <tt>method</tt> should have the complexity appropriate to the implementing class (as indicated by the
     * class's name).  E.g. an implementation of <tt>method</tt> in a class called <tt>Linear</tt> should have linear
     * (time) complexity.
     * @param n the "size" of the call to method
     */
    public void method(int n);

    /**
     * Implementations of <tt>complexity</tt> should implement a function of the parameter <tt>n</tt> that
     * is appropriate to the implementing class (as indicated by the class's name.  E.g. an implementation of
     * <tt>complexity</tt> in a class called <tt>Linear</tt> might implement, for example, f(n)=n).
     * @param n the "size" of the "problem"
     * @return a value that grows in line with the complexity class modelled
     */
    public int complexity(int n);
}
