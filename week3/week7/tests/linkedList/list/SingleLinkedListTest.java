package linkedList.list;

import arrayGenerator.generator.ArrayGenerator;
import arrayGenerator.generator.IntegerArrayGenerator;
import arrayGenerator.generator.StringArrayGenerator;
import linkedList.node.SingleLinkNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {

    SingleLinkedList list = new SingleLinkedList();
    ArrayList expected = new ArrayList();

    /**
     * Create the String List.
     * @return NULL as List and Expected are already defined
     * @override List with new values.
     * @throws ListAccessError
     */
    void setUpString() throws ListAccessError {
        StringArrayGenerator strGenerator = new StringArrayGenerator();
        list = new SingleLinkedList();
        expected = new ArrayList();
        String[] temp = strGenerator.getArray(10);
        for (int i = 0; i < temp.length; i++){
            list.add(temp[i]);
            expected.add(temp[i]);
        }
    }

    /**
     * Create the Integer List.
     * @return NULL as List and Expected are already defined
     * @override List with new values.
     * @throws ListAccessError
     */
    void setUpInteger() throws ListAccessError {
        IntegerArrayGenerator intGenerator = new IntegerArrayGenerator();
        list = new SingleLinkedList();
        expected = new ArrayList();
        Integer[] temp = intGenerator.getArray(10);
        for (int i = 0; i < temp.length; i++){
            list.add(temp[i]);
            expected.add(temp[i]);
        }
    }

       /**
     * Test that the Get function will return the expected value.
     *
     * @throws ListAccessError
     */

    @Test
    void getFithStringtest() throws ListAccessError {
        setUpString();
        assertEquals("" + expected.get(5), list.getString(5));
    }

    /**
     * Test that a value can be added and that it is added to the correct position.
     * @return True if 'a' is added to position 5 and is stored correctly.
     * @throws ListAccessError
     */
    @Test
    void addTo5Test() throws ListAccessError {
        setUpString();
        list.add(5, 'a');
        assertEquals("a", list.getString(5) );
    }

    /**
     * Test to check that the list works with Integer values.
     * This runs the same test as the getFifthStringTest.
     * @return True if the 5th Integer is correctly identified.
     * @throws ListAccessError
     */
    @Test
    void getFithIntegerTest() throws ListAccessError {
        setUpInteger();
        assertEquals("" + expected.get(5), list.getString(5));
    }

    /**
     * Test that a ListAccessError is thrown for Integers.
     * @return True if the error is thrown
     * @throws ListAccessError as 12 is out of bounds.
     */
    @Test
    void getOutOfBoundIntegerTest() throws ListAccessError {
        setUpInteger();
        assertThrows(ListAccessError.class, ()->{list.get(12);}); // 12 is out of bounds
    }

    /**
     * Test that a ListAccessError is thrown for Strings.
     * @return True if the error is thrown
     * @throws ListAccessError as 12 is out of bounds.
     */
    @Test
    void getOutOfBoundStringTest() throws ListAccessError {
        setUpString();
        assertThrows(ListAccessError.class, ()->{list.get(12);}); // 12 is out of bounds
    }
}