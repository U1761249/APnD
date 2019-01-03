package linkedList.list;

import arrayGenerator.generator.ArrayGenerator;
import arrayGenerator.generator.StringArrayGenerator;
import linkedList.node.SingleLinkNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {

    ArrayGenerator generator = new StringArrayGenerator();
    SingleLinkedList list = new SingleLinkedList();
    ArrayList expected = new ArrayList();


    @BeforeEach
    void setUp() throws ListAccessError {
        list = new SingleLinkedList();
        expected = new ArrayList();
        Object[] temp = generator.getArray(10);
        for (int i = 0; i < temp.length; i++){
            System.out.println(temp[i]);
            list.add(temp[i]);
            expected.add(new SingleLinkNode(temp[i]));
        }
    }
    private String print(ArrayList data){
        String values = data.get(0).toString();
        for (int i = 1; i < data.size(); i++){
            values = ", " + data.get(i).toString();
        }
        return values;
    }

    @Test
    void addToRoot() {
    }

    @Test
    void addToEnd() {
    }

    @Test
    void remove() {
    }

    @Test
    void getFith() throws ListAccessError {
        setUp();
        assertEquals(expected.get(5), list.get(5));
    }

}