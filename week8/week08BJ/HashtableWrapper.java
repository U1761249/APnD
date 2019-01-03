 

/**
 * A wrapper for the Hashtable class
 * 
 * @author Hugh Osborne 
 * @version October 2018
 */
public class HashtableWrapper<S,T> extends java.util.Hashtable<S,T>
{
    public HashtableWrapper(int size) {
        super(size);
    }
    
    public T put(S key,T value) {
        return super.put(key,value);
    }
}
