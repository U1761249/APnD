/*
 * @author Adam_Birch
 *                 u1761249
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenericMethodsTest {


    void TestValidString(Integer end, int index1, int index2) {
        GenericMethods test = new GenericMethods();
        ArrayList expected = new ArrayList<>(end);
        for (int i = 0; i < end; i++){
            if(i==index1){expected.add(index2);}
            else if (i == index2){expected.add(index1);}
            else {expected.add(i);}
        }
        ArrayList data = test.strArray(end,index1,index2);
        for (int i = 0; i < 10; i++){
        assertEquals(expected.get(i).toString(),data.get(i).toString());
        }
    }


    void TestValidInteger(Integer end, int index1, int index2) {
        GenericMethods test = new GenericMethods();
        ArrayList expected = new ArrayList<>(end);
        for (int i = 0; i < end; i++){
            expected.add(i);
        }
        Object temp1 = expected.get(index1);
        Object temp2 = expected.get(index2);
        expected.set(index1,temp2);
        expected.set(index2,temp1);
        assertEquals(expected, test.intArray(end,index1,index2));
    }

    void TestStringException(Integer end, int index1, int index2){
        GenericMethods test = new GenericMethods();
        Throwable e = assertThrows(IndexOutOfBoundsException.class, () -> {test.strArray(end,index1,index2);});
        assertEquals("Index exceeds the bounds of the array. ", e.getMessage());
    }
    void TestIntegerException(Integer end, int index1, int index2){
        GenericMethods test = new GenericMethods();
        Throwable e = assertThrows(IndexOutOfBoundsException.class, () -> {test.intArray(end,index1,index2);});
        assertEquals("Index exceeds the bounds of the array. ", e.getMessage());
    }
    void TestStringSizeException(Integer end, int index1, int index2){
        GenericMethods test = new GenericMethods();
        Throwable e = assertThrows(IndexOutOfBoundsException.class, () -> {test.strArray(end,index1,index2);});
        assertEquals("Array size must be higher than 0.", e.getMessage());
    }
    void TestIntegerSizeException(Integer end, int index1, int index2){
        GenericMethods test = new GenericMethods();
        Throwable e = assertThrows(IndexOutOfBoundsException.class, () -> {test.intArray(end,index1,index2);});
        assertEquals("Array size must be higher than 0.", e.getMessage());
    }

    //Test that the program functions correctly

    @Test
    void testString(){
        TestValidString(10,2,7);
    }

    @Test
    void testInteger(){
        TestValidInteger(10,2,7);
    }

    //Test that the function works when the numbers are in reverse order

    @Test
    void testReverseString(){
        TestValidString(10,7,2);
    }

    @Test
    void testReverseInteger(){
        TestValidInteger(10,7,2);
    }

    //Test that the program makes no change and throws no error

    @Test
    void testSameString(){
        TestValidString(10,5,5);
    }

    @Test
    void testSameInteger(){
        TestValidInteger(10,5,5);
    }

    //Test that the correct exception is thrown

    @Test
    /**
     * @throws IndexOutOfBoundsException
     */
    void testStringOutOfBounds(){
        TestStringException(10,2,70);
    }

    @Test
    /**
     * @throws IndexOutOfBoundsException
     */
    void testIntegerOutOfBounds(){
        TestIntegerException(10,-2,7);
    }

    //Test that an exception is thrown if the input is 0.

    @Test
    /**
     * @throws IndexOutOfBoundsException
     */
    void testZeroString(){
        TestStringSizeException(0,0,0);
    }

    @Test
    /**
     * @throws IndexOutOfBoundsException
     */
    void testZeroInteger(){
        TestIntegerSizeException(0,0,0);
    }

}