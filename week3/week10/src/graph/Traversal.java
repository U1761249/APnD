package graph;

/**
 * Defines the interface for graph traversals
 * 
 * @author Hugh Osborne
 * @version November 2018
 */

import java.util.List;

public interface Traversal<T> extends Graph<T>
{
    /**
     * Perform a traversal of the graph, and return the nodes in the order in whixch they are visited.
     *
     * @return a traversal of the graph in which each node is visited exactly once
     */
    public List<T> traverse() throws GraphError;
}