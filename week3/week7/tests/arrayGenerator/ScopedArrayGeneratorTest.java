package arrayGenerator;


import arrayGenerator.generator.ScopedArrayGenerator;
import arrayGenerator.scope.Scope;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Implements additional tests, above those defined in {@link arrayGenerator.ArrayGeneratorTest}, to
 * check that:
 * <ul>
 *     <li> Values in generated arrays are within the specified arrayGenerator.scope</li>
 *     <li> All values in the arrayGenerator.scope are generated.  Note: this is a statistical test, and may produce a
 *          false fail.  See {@link #testCoversRange(Scope)} for details.</li>
 * </ul>
 */

abstract class ScopedArrayGeneratorTest<T> extends ArrayGeneratorTest<T> {

    abstract ScopedArrayGenerator<T> getGenerator(Scope<T> scope);

    /**
     * Check that the array contents are within the specified range.
     * @param scope the arrayGenerator.scope to be tested
     * @param size the size of the array
     */
    void testWithinRange(Scope<T> scope, int size) {
        ScopedArrayGenerator<T> generator = getGenerator(scope);
        T[] array = generator.getArray(size);
        for (T entry: array) {
            if (!scope.contains(entry)) {
                fail("Array entry is not within the expected bounds");
            }
        }
    }


    /**
     * When testing that a generated array contains all the values within the generator's arrayGenerator.scope,
     * only a statistical check can be carried out.  Since values are generated at random, there is
     * always a chance that a generated array, however large, may not contain all the values.
     *
     * However, the larger the generated array, the smaller this chance of a "false fail" is.
     *
     * ACCEPTABLE_FALSE_FAIL specifies the acceptable probability for such a false fail occuring.
     */
    private final static double ACCEPTABLE_FALSE_FAIL = 0.01;

    /**
     * Check that a generated array contains all the expected values.
     *
     * Note: since values are generated at random, there is always a chance that a
     * generated array may not contain all values within the range.
     *
     * The size of the generated array is set to
     *     1/c(n*H(n))
     * where
     *    c    is the probability that the array will not contain all values (i.e. the
     *         chance of a "false fail") - so the ACCEPTABLE_FALSE_FAIL above.
     *    n    is the number of values within the arrayGenerator.scope of this generator, and
     *    H(n) is the nth harmonic number (ie. 1/1 + 1/2 + ... + 1/n)
     *  H(n), in turn is approximately n*ln(n) + gamma*n, where gamma is approximately
     *  0.5772156649.
     *  See See <a href="https://en.wikipedia.org/wiki/Coupon_collector%27s_problem">https://en.wikipedia.org/wiki/Coupon_collector%27s_problem</a>.
     *
     * @param scope the arrayGenerator.scope to be tested.
     */
    void testCoversRange(Scope<T> scope) {
        double gamma = 0.5772156647;
        ScopedArrayGenerator<T> generator = getGenerator(scope);
        int testArraySize = (int)
            Math.ceil(
                    (1/ACCEPTABLE_FALSE_FAIL)*(scope.size()*Math.log(scope.size())+gamma*scope.size())
            );
        T[] array = generator.getArray(testArraySize);
        Set<T> seen = new HashSet<T>();
        for (T entry: array) {
            if (!seen.contains(entry)) {
                if (!scope.contains(entry)) {
                    fail("Array entry is not within expected bounds.");
                } else {
                    seen.add(entry);
                    if (seen.size() == scope.size()) {
                        break;
                    }
                }
            }
        }
        if (seen.size() < scope.size()) {
            fail("Array did not contain all expected values.\n"
                 + "Array is " + Arrays.toString(array) + "\n"
                 + "Scope is " + scope + "\n"
                 + "Saw " + seen +"\n"
                 + "Note: there is a " + ACCEPTABLE_FALSE_FAIL + " chance that this is a false fail.  See the method documentation for details.");
        }
    }
}