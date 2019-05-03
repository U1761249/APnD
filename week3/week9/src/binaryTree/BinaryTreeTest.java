package binaryTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest extends BinaryTree {

    BinaryTree tree = new BinaryTree();
    ArrayList values = new ArrayList();
    ArrayList insertValues = new ArrayList();

    /**
     * Set up a new binary tree for testing.
     */
    @BeforeEach
    void setUp() {
        tree = new BinaryTree();
        values = new ArrayList();
        insertValues = new ArrayList();
        for (int i = 0; i < 50; i++) {
            if (i != 19){ // Don't add 19 - reserved for insert test.
                values.add(i);
            }
        }
        treeSetup(0, values.size());
    }

    /**
     * Create an unbalanced sorted binary tree for testing.
     * @param pointerLeft the leftmost value to insert.
     * @param pointerRight the rightmost value to insert
     */
    void treeSetup(int pointerLeft, int pointerRight) {
        double diff = pointerRight - pointerLeft;
        double midpoint = pointerLeft + Math.floor(diff / 2);
        if (diff > 0){
            Object newroot = values.get((int) midpoint);
            tree.insert((Comparable) newroot);
            insertValues.add(newroot);
            treeSetup(pointerLeft, (int) midpoint);
            treeSetup((int) (midpoint + 1), pointerRight);
        }
    }


    /** Test that 5 is within the tree
     * @return True if 5 is found.
     */
    @Test
        void contains5Test() {
        assertTrue (tree.contains(5));
    }

    /** Test that 0 is within the tree
     * @return True if 0 is found.
     */
    @Test
    void contains0Test() {
        assertTrue (tree.contains(0));
    }

    /** Test that 17 is within the tree
     * @return True if 17 is found.
     */
    @Test
    void contains17Test() {
        assertTrue (tree.contains(17));
    }

    /** Test that 50 is not within the tree
     * @return True if 50 is NOT found. (assertFalse = True if condition is false)
     */
    @Test
    void contains50Test() {
        assertFalse (tree.contains(50));
    }

    /**
     * test that 50 was correctly inserted into the tree.
     * @return True if 50 is found after being added.
     */
    @Test
    void insert50Test(){
        tree.insert(50);
        assertTrue(tree.contains(50));
    }

    /**
     * Test that 19 was correctly added to the tree.
     * @return True if 19 is found after being added.
     */
    @Test
    void insert19Test(){
        tree.insert(19);
        assertTrue(tree.contains(19));
    }

    /**
     * Test that the correct order is returned when the tree is traversed
     * No parameter is used. The traversal will always begin at the root node of the tree.
     * @return True if the traverse() matches the literal.
     */
    @Test
    void traverseTest() {
        List returned = tree.traverse();
        List<String> expected = Arrays.asList("25", "12", "6", "3", "1", "0", "2", "5", "4", "9", "8", "7",
                "11", "10", "18", "15", "14", "13", "17", "16", "22", "21", "20", "24", "23", "38", "32", "29",
                "27", "26", "28", "31", "30", "35", "34", "33", "37", "36", "44", "41", "40", "39", "43", "42",
                "47", "46", "45", "49", "48");   // Literal expected output.

        assertEquals(expected , returned);

    }

}