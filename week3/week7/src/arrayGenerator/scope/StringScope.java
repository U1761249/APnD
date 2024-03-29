package arrayGenerator.scope;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of an {@link arrayGenerator.scope.AlphabetScope} for string.
 *
 * @author Hugh Osborne
 * @version October 2018
 */
public class StringScope extends AlphabetScope<String> {

    private static final Set<String> DNA_ALPHABET = new HashSet<String>(Arrays.asList("A","C","T","G"));

    /**
     * Specify the arrayGenerator.scope by means of a set of values.
     *
     * @param alphabet the set of permitted values for this arrayGenerator.scope.
     */
    public StringScope(Set<String> alphabet) {
        super(alphabet);
    }

    /**
     * Default constructor uses the DNA alphabet.
     */
    public StringScope() {
        super(DNA_ALPHABET);
    }
}
