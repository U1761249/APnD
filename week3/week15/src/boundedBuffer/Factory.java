package boundedBuffer;


/**
 * Factories make things.  A Factory has a make() method that will create and return
 * an object of the required type/
 *
 * @param T the type of object made by this factory.
 *
 * @author Hugh Osborne 
 * @version January 2019
 */
public interface Factory<T>
{
    /**
     * Build and return a new item
     * @return item the new item created
     */
    public T make();
}
