package arrayGenerator.scope;

/**
 * A arrayGenerator.scope represents a set of values - e.g. an alphabet of characters, or a range of integers.
 *
 * @param <T> the type scoped by this Scope
 */
public interface Scope<T> {

    /**
     * Is the given value within the arrayGenerator.scope of this Scope?
     * 
     * @param value the value to be checked.
     * @return true iff the value is within the arrayGenerator.scope of this Scope.
     */
    public boolean contains(T value);

    /**
     * Get a value within the arrayGenerator.scope of this Scope.
     * 
     * @return a value within this arrayGenerator.scope.
     */
    public T getValue();

    /**
     * How many values are there within this arrayGenerator.scope?
     * 
     * @return the number of possible distinct values within this arrayGenerator.scope.
     */
    public int size();
}
