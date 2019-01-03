import java.util.ArrayList;

/**
 * @author Adam Birch
 * @version October 2018

 */


public class Swap<E> {

    public ArrayList<E> Switch (ArrayList <E> array, int index1, int index2) throws IndexOutOfBoundsException{
        try {
            E temp1 = array.get(index1);
            E temp2 = array.get(index2);
            // Find the element at the two indexes
            array.set(index1, temp2);
            array.set(index2, temp1);
            //Swap the elements
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception");
            throw new IndexOutOfBoundsException("Index exceeds the bounds of the array. ");
        }

        return array;
    }
}
