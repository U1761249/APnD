package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceCountTopologicalSortTest {

    ReferenceCountTopologicalSort<Integer> graph = new ReferenceCountTopologicalSort<>();

    /**
     * Setup an acyclic graph of size 5.
     * The graph uses Integers as nodes.
     * @throws GraphError
     */
    @BeforeEach
    void setUp() throws GraphError {

        graph = new ReferenceCountTopologicalSort<>();

       // Add the nodes to the graph
        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);
        graph.add(4);

        // Add the links between the nodes.
        graph.add(0,1);
        graph.add(0,2);
        graph.add(0,3);
        graph.add(1,4);
        graph.add(3,2);
        graph.add(2,4);

    }

    /**
     * Test that the graph was successfully created.
     * @return True if the graph was created.
     */
    @Test
    void setupTest(){

        Hashtable<Integer, Set<Integer>> expected = new Hashtable<>();

        for (Integer i = 0; i < 5; i ++){ expected.put(i, new HashSet<>()); }
        expected.get(0).add(1);
        expected.get(0).add(2);
        expected.get(0).add(3);
        expected.get(1).add(4);
        expected.get(3).add(2);
        expected.get(2).add(4);

        assertEquals(expected, graph.getGraph());
    }

    /**
     * Test that getSort() contains all correct nodes.
     * @throws GraphError
     */
    @Test
    void valueTest() throws GraphError {
        List<Integer> sort = graph.getSort();
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 5; i++){expected.add(i);}
        boolean success = true;
        for (Integer node : expected){
            if (!sort.contains(node)){
                success = false;
                break;
            }
        }
        assertTrue(success);
    }

    /**
     * Test that a GraphError is thrown for a cyclic graph.
     * @return True if the GraphError is thrown.
     * @throws GraphError due to cyclic graph.
     */
    @Test
    void cycleGraphTest() throws GraphError {
        graph.add(5);
        graph.add(0,5);
        graph.add(5,0);

        assertThrows(GraphError.class,()-> graph.getSort());
    }

    /**
     * Graph found at:
     * https://cs.stackexchange.com/questions/2186/dependency-graph-acyclic-graph
     * This link was used to find a large graph with Chars as nodes.
     *
     * Test that nodes can be of any type, and that larger
     * graphs than the previous test will work.
     * @return True if the nodes are all returned.
     * @throws GraphError thrown if the graph is cyclic.
     */
    @Test
    void largeDataTest() throws GraphError {
        graph = new ReferenceCountTopologicalSort<>();
        List<Character> expected = new ArrayList<>();
        for (char alphabet = 'A'; alphabet < 'N'; alphabet++){
            graph.add(alphabet);
            expected.add(alphabet);
        }

        graph.add('A', 'C');
        graph.add('A', 'D');
        graph.add('C', 'F');
        graph.add('C', 'G');
        graph.add('C', 'H');
        graph.add('G', 'K');
        graph.add('H', 'K');
        graph.add('D', 'I');
        graph.add('B', 'E');
        graph.add('E', 'H');
        graph.add('E', 'I');
        graph.add('E', 'J');
        graph.add('I', 'L');
        graph.add('I', 'M');
        graph.add('J', 'M');

        List<Character> sort = graph.getSort();System.out.println(sort);
        boolean success = true;
        for (char node : expected){
            if (!sort.contains(node)){
                success = false;
                break;
            }
        }
        assertTrue(success);
    }

    /**
     * Test the outcome of a mixed node graph (Integers and Chars).
     * This uses the same graph as the SetUp() but Even numbers are replaced with Chars.
     * Use of Object type is used in testing to check that a list can be
     * returned with multiple types.
     * @return True if the nodes are successfully sorted.
     * @throws GraphError if the graph is cyclic.
     */
    @Test
    void mixedNodeTest() throws GraphError {
        graph = new ReferenceCountTopologicalSort<>();
        List<Object> expected = new ArrayList<>();

        graph.add('a');
        expected.add('a');
        graph.add(1);
        expected.add(1);
        graph.add('b');
        expected.add('b');
        graph.add(3);
        expected.add(3);
        graph.add('c');
        expected.add('c');

        // Add the links between the nodes.
        graph.add('a',1);
        graph.add('a','b');
        graph.add('a',3);
        graph.add(1,'c');
        graph.add(3,'b');
        graph.add('b','c');

        List<Object> sort = graph.getSort();
        System.out.println(sort);
        boolean success = true;
        for (Object node : expected){
            if (!sort.contains(node)){
                success = false;
                break;
            }
        }
       assertTrue(success);

    }

    /**
     * Test the outcome of an empty grpah.
     * @return True if the Exception is thrown
     * @throws GraphError as there are no nodes in the graph.
     */
    @Test
    void emptyGraphTest() throws GraphError {
        graph = new ReferenceCountTopologicalSort<>();
        assertThrows(GraphError.class,()-> graph.getSort());
    }

    /**
     * Test the outcome of a search if a graph has 1 node and no edges.
     * @return True if the node is returned.
     * @throws GraphError if the graph is cyclic.
     */
    @Test
    void singleNodeTest() throws GraphError {
        graph = new ReferenceCountTopologicalSort<>();
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        graph.add(0);
        List<Integer> sort = graph.getSort();
        assertEquals(expected, sort);
    }

    /**
     * Test that a GraphError is thrown if a graph has 1 node and 1 edge.
     * @return True if the exception is thrown.
     * @throws GraphError as the graph is Cyclic.
     */
    @Test
    void singleNodeCycleTest() throws GraphError {
        graph = new ReferenceCountTopologicalSort<>();
        graph.add(0);
        graph.add(0,0);
        assertThrows(GraphError.class,()-> graph.getSort());
    }
}