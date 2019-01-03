package searcher;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.CleverRandomListingGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hugh Osborne
 * @version September 2018
 */

abstract class SearcherTest<T> {

    /**
     * Create a searcher of the right type
     */
    abstract protected Searcher createSearcher(T[] array, int index) throws IndexingError;

    /**
     * Test that the searcher finds the correct value.  The test uses a random listing generator to create
     * a random listing of the requited size.  Because of the properties of random listings, the kth largest
     * element of a random listing of size n must be n-k.
     * @param arraySize the size of the random listing to be generated (the "n" value)
     * @param index the index (the "k" value)
     * @throws IndexingError if k is out of bounds for n
     */
    private void testSearcher(int arraySize,int index) throws IndexingError {
        ArrayGenerator generator = new CleverRandomListingGenerator(arraySize);
        Searcher search = createSearcher((T[]) generator.getArray(), index);
        assertEquals(arraySize - index, search.findElement());
    }

    private void testNegativeSize(int arraySize) {

        Throwable e = assertThrows(IndexOutOfBoundsException.class, () -> {
            ArrayGenerator generator = new CleverRandomListingGenerator(arraySize);
        });
            assertEquals("Array size must be larger than 1.", e.getMessage());

    }

    private void testLargerIndex(int arraySize, int index) throws IndexingError {
        ArrayGenerator generator = new CleverRandomListingGenerator(arraySize);
        Searcher search = createSearcher((T[]) generator.getArray(), index);
        Throwable e = assertThrows(IndexingError.class, () -> {search.findElement();});

        assertEquals("Index out of bounds", e.getMessage());

    }

    @Test
    void test2ndIn10() throws IndexingError {
        testSearcher(10,2);
    }

    @Test
    void test5thIn10() throws IndexingError {
        testSearcher(10,5);
    }
    @Test
    void test3rdIn100() throws IndexingError {
        testSearcher(100,3);
    }

    @Test
    void test16thIn100() throws IndexingError {
        testSearcher(100,16);
    }

    @Test
    void test8thIn1000() throws IndexingError {
        testSearcher(1000,8);
    }

    @Test
    void test107thIn1000() throws IndexingError {
        testSearcher(1000,107);
    }

    @Test
    void test1stIn10000() throws IndexingError {
        testSearcher(10000,1);
    }

    @Test
    void test1003rdn10000() throws IndexingError {
        testSearcher(10000,1003);
    }

    @Test
    void test11thIn100000() throws IndexingError {
        testSearcher(100000,11);
    }

    @Test
    void test4thIn1000000() throws IndexingError {
        testSearcher(1000000,4);
    }

    @Test // Test that a negative input throws an IndexOutOfBoundsException.
    /**
     * @throws IndexOutOfBoundsException
     */
    void testminus1(){
        testNegativeSize(-1);
    }

    @Test // Test that a size of 0 throws an IndexOutOfBoundsException.
    /**
     * @throws IndexOutOfBoundsException
     */
    void testZero(){
        testNegativeSize(0);
    }

    @Test // Test that a larger index throws the IndexingException
    /**
     * @throws IndexingError
     */
    void test10in1() throws IndexingError {
        testLargerIndex(1,10);
    }

    @Test // Test the smallest value in the array.
    void test100in100() throws IndexingError {
        testSearcher(100,100);
    }

}