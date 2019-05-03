package searcher;

/**
 * @author Adam Birch
 * @version September 2018
 */

class CleverSearcherTest<T> extends SearcherTest {

       @Override
    protected Searcher createSearcher(Object[] array, int index) throws IndexingError {
        return new CleverSearcher(array,index);
    }
}