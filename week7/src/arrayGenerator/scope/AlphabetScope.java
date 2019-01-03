package arrayGenerator.scope;

import java.util.Random;
import java.util.Set;

/**
 * Implements {@link arrayGenerator.scope.Scope}, when the set (or <i>alphabet</i>) of possible values is specified
 * by a {@link Set}.
 *
 * @author Hugh Osborne
 * @version October 2018
 */

public class AlphabetScope<T> implements Scope<T> {

    // the alphabet (set of possible values) of this arrayGenerator.scope
    private Set<T> alphabet;

    private Random random = new Random();

    /**
     *
     * @param alphabet the alphabet of this arrayGenerator.scope.
     */
    public AlphabetScope(Set<T> alphabet) {
        this.alphabet = alphabet;
    }

    /**
     * Tests whether the given value within the arrayGenerator.scope of this Scope.
     * I.e., is it in the alphabet of this arrayGenerator.scope.
     *
     * @param value the value to be checked.
     * @return true iff the value is within the arrayGenerator.scope of this Scope.
     */
    @Override
    public boolean contains(T value) {
        return alphabet.contains(value);
    }

    /**
     * Get a value within the arrayGenerator.scope of this Scope.
     *
     * @return a random value from this arrayGenerator.scope's alphabet.
     */
    @Override
    public T getValue() {
        return alphabet.stream()
                .skip(random.nextInt(alphabet.size()))
                .findFirst().get();
    }

    /**
     * Get the size of this arrayGenerator.scope.
     * The size is the number of elements in the arrayGenerator.scope's alphabet.
     *
     * @return the size of this arrayGenerator.scope's alphabet..
     */
    @Override
    public int size() {
        return alphabet.size();
    }
}
