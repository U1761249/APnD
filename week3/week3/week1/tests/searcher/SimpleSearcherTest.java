package searcher;

/**
 * @author Hugh Osborne
 * @version September 2018
 */

class SimpleSearcherTest<T> extends SearcherTest {

    @Override
    protected Searcher createSearcher(Object[] array, int index) throws IndexingError {
        return new SimpleSearcher(array,index);
    }
}