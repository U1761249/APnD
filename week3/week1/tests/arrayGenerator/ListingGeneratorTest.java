package arrayGenerator;

/**
 * @author Hugh Osborne
 * @version September 2018
 */

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.fail;

abstract class ListingGeneratorTest extends ArrayGeneratorTest {

    @Override
    abstract protected ListingGenerator createArrayGenerator(int size);

    /**
     * test whether the lisitng contains the corrent values.
     *
     * The method:
     * <ul>
     *     <li> for each entry in the array</li>
     *     <ul>
     *          <li> checks that the entry is in the correct value range</li>
     *          <li> checks that the value has not already been encountered</li>
     *          <li> notes that this value has been encountered</li>
     *          </ul>
     *     <li>finally, somewhat superfluously, checks that all the expected values are in the array</li>
     * </ul>
     * @param generator the generator to be tested
     */
    private void testContents(ListingGenerator generator) {
        boolean[] inList = new boolean[generator.getSize()];
        Arrays.fill(inList, false);
        ;
        for (int i = 0; i < generator.getSize(); i++) {
            int entry = generator.getArray()[i];
            if (entry < 0 || entry >= generator.getSize()) {  // is entry in the correct value range?
                fail("incorrect entry " + entry + " in array");  // if so, the test fails
            }
            if (inList[i]) { // have we encountered this value befotre
                fail(entry + " occurs more than once in array");  // if so, the test fails
            }
            inList[i] = true;  // make a note that we've encountered this value
        }
        for (int i = 0; i < inList.length; i++) {  // check that all expected values are in the list
            if (!inList[i]) {  // if this value is not in the list
                fail(i + " is not in the array");  // the test fails
            }
        }
    }

    @Test
    void testOneContents() {
        testContents(createArrayGenerator(1));
    }

    @Test
    void testTwoContents() {
        testContents(createArrayGenerator(2));
    }

    @Test
    void testFourContents() {
        testContents(createArrayGenerator(4));
    }

    @Test
    void testHundredContents() { testContents(createArrayGenerator(100)); }

    @Test
    void testThousandContents() {
        testContents(createArrayGenerator(1000));
    }

   @Test
   void testMillionContents() {
        testContents(createArrayGenerator(1000000));
    }
}