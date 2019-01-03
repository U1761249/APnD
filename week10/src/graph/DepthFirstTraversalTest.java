package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DepthFirstTraversalTest <T> {

    DepthFirstTraversal graph = new DepthFirstTraversal();

    /**
     * Setup a graph before each of the tests.
     */
    @BeforeEach
    void setUp(){
        graph = new DepthFirstTraversal();
        try {
            graph.add(0);
            graph.add(1);
            graph.add(2);
            graph.add(3);
            graph.add(4);
        } catch (GraphError graphError) {
            System.out.println("FAILED!");
            graphError.printStackTrace();
        }

        // Add the links between the nodes.
        try {
            graph.add(0,1);
            graph.add(0,2);
            graph.add(0,3);
            graph.add(1,4);
            graph.add(3,2);
            graph.add(2,4);
        } catch (GraphError graphError) {
            System.out.println("FAILED!");
            graphError.printStackTrace();
        }
    }

    /**
     * Add 9 to an empty graph.
     * @throws GraphError
     */
    @Test
    void addTest() throws GraphError {
        graph = new DepthFirstTraversal();
        graph.add(9);
        Set expected = new HashSet();
        expected.add(9);
        assertEquals(expected, graph.getNodes());
    }

    /**
     * Traverse the created graph without an initial node.
     * @return True if all nodes are traversed.
     * @throws GraphError
     */
    @Test
    void traverseTest() throws GraphError {

        Set<T> results = new HashSet<T>(graph.traverse());
        Set<T> expected = graph.getNodes();
        boolean passed = true;
        for (T node : expected){
            if (!results.contains(node)){
                passed = false;
                break;
            }
        }
        assertTrue(passed);
    }

    /**
     * Traverse the created graph with an initial node of 1.
     * @return True if all nodes are traversed.
     * @throws GraphError
     */
    @Test
    void traverse1Test() throws GraphError {
        Set<T> results = new HashSet<T>(graph.traverse(1));
        Set<T> expected = graph.getNodes();
        boolean passed = true;
        for (T node : expected){
            if (!results.contains(node)){
                passed = false;
                break;
            }
        }
        assertTrue(passed);
    }

    /**
     * Traverse the created graph with an initial node not in the graph (10).
     * @return True if an IndexOutOfBoundsException is thrown.
     * @throws IndexOutOfBoundsException
     */
    @Test
    void traverse10Test() {
        assertThrows(IndexOutOfBoundsException.class, ()->{
            graph.traverse(10);
        });
    }
}