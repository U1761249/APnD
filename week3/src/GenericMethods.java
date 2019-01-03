import java.util.ArrayList;

/**
 * @author Adam Birch
 * @version October 2018
 */

public class GenericMethods {

    public ArrayList<String> strArray(Integer end, int index1, int index2) {
        //Creating a String array and using the Swap
        if(end >0){
            ArrayList<String> list = new ArrayList<>(end);
            for (int i = 0; i < end; i++){
                list.add(Integer.toString(i));
            }
            Swap<String> swap = new Swap<>();
            list = swap.Switch(list, index1, index2);
            return list;
        }
        else {
            throw new IndexOutOfBoundsException("Array size must be higher than 0.");
        }
    }

    public ArrayList<Integer> intArray(Integer end, int index1, int index2) {
        //Creating an Integer array and using the Swap
        if(end >0){
            ArrayList<Integer> list = new ArrayList<>(end);
            for (int i = 0; i < end; i++) {
                list.add(i);
            }
            Swap<Integer> swap = new Swap<>();
            list = swap.Switch(list, index1, index2);
            return list;
        }
        else {
            throw new IndexOutOfBoundsException("Array size must be higher than 0.");
        }
    }
}
