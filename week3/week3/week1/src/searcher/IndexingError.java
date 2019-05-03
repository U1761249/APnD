package searcher;

/**
 * Use to report index out of bounds errors
 *
 * @author Hugh Osborne
 * @version September 2016
 **/
class IndexingError extends Exception
{
    /**
     * Used to report an index out of bounds error
     **/
    IndexingError() {
        super("Index out of bounds");
    }
}

