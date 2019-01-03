package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceCountTopologicalSortTest {

    ReferenceCountTopologicalSort<Integer> graph = new ReferenceCountTopologicalSort<>();

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
     * Test that nodes can be of any type, and that larger graphs than the previous test will work.
     * @return True if the
     * @throws GraphError
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

        List<Character> sort = graph.getSort();
        boolean success = true;
        for (char node : expected){
            if (!sort.contains(node)){
                success = false;
                break;
            }
        }
        assertTrue(success);
    }

}