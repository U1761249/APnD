package arrayGenerator.generator;

import arrayGenerator.scope.IntegerScope;
import arrayGenerator.scope.Scope;

/**
 * Class for generating areays of random Integers.  The values will be chosen
 * from within the range of the IntegerArrayGenerator object's minimum and
 * maximum fields (inclusive).
 *
 * @author Hugh Osborne
 * @version October 2016
 */
public class IntegerArrayGenerator extends ScopedArrayGenerator<Integer>
{
    /**
     * @param scope the arrayGenerator.scope of values that may appear in the array.
     */
    public IntegerArrayGenerator(Scope<Integer> scope) {
        super(scope);
   }

    /**
     * If no integer arrayGenerator.scope is given, use the default arrayGenerator.scope provided by {@link arrayGenerator.scope.IntegerScope}.
     */
    public IntegerArrayGenerator()
    {
        super(new IntegerScope());
    }

    /**
     * A createArray method is required for each non-generic class implementing the generic
     * {@link ScopedArrayGenerator} class with a specific type.  This is because we cannot
     * create generic arrays in Java (e.g. <tt>T[] array = <b>new</b> T[size]</tt>).
     * @param size the size of the array to be created
     * @return an array of {@link Integer}s of the required size.  The values of the Integers
     *         will be uninstantiated.
     */
    @Override
    Integer[] createArray(int size) {
        return new Integer[size];
    }
}
