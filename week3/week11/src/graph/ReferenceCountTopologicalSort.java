package graph;

import java.util.*;

public class ReferenceCountTopologicalSort <T> extends AdjacencyGraph implements TopologicalSort {

    private HashMap <T, Integer> referenceCounts = null;
    private ArrayList<T> visited = new ArrayList<>();

    /**
     * Creates a referenceCounts HashMap to store the number of times a
     * node is connected to from another.
     * Requires a graph to be created so that it can generate
     * @throws GraphError
     */
    private void setupReferenceCounts() throws GraphError {
        referenceCounts = new HashMap<>();
        ArrayList<T> nodes = new ArrayList<>();
        nodes.addAll(this.getNodes());
        if (nodes.isEmpty()){throw new GraphError("There were no nodes in the graph");}
        for (T node : nodes){
            referenceCounts.put(node, 0);
        }

        for (int i = 0; i < nodes.size(); i++){
            ArrayList<T> neighbours = new ArrayList<>();
            neighbours.addAll(getNeighbours(nodes.get(i)));
            for (T neighbour : neighbours){
                Integer value = referenceCounts.get(neighbour) + 1;
                referenceCounts.replace(neighbour, value);
            }
        }
    }

    /**
     * Find any node with 0 connections that isn't within the visited ArrayList.
     * If no nodes conform then the graph is cyclic.
     * @return T Node
     * @throws GraphError if the graph is Cyclic.
     */
    private T getZeroReferenceCount() throws GraphError {
        T nextNode = null;
        ArrayList<T> nodes = new ArrayList<>();
        nodes.addAll(this.getNodes());
        for (int i = 0; i < referenceCounts.size(); i++){
            if (referenceCounts.get(nodes.get(i)) == 0 && !visited.contains(nodes.get(i))){
                nextNode = nodes.get(i);
                break;
            }
        }
        if (nextNode == null){
            throw new GraphError("The graph was Cyclic! ");
        }
        return nextNode;
    }

    /**
     * Creates a ReferenceCountTopologicalSort of an Acyclic graph.
     * @param 'an Acyclic graph'
     * @return A Topological sort of a graph
     * @throws GraphError
     */
    @Override
    public List getSort() throws GraphError {
        setupReferenceCounts();
        ArrayList <T> sorted = new ArrayList<>();
        while (sorted.size() < getNoOfNodes()) {
            T currentNode = getZeroReferenceCount();
            sorted.add(currentNode);
            UpdateReferenceCounts(currentNode);

        }
        return sorted;
    }

    /**
     * Updates the reference count to store the number of
     * unprocessed references to a node.
     * Removes the references from the currentNode.
     * Remove the old data and store the new value as there is no Update() method.
     * @param currentNode as the current node as the target.
     * @throws GraphError
     */
    private void UpdateReferenceCounts(T currentNode) throws GraphError {
        ArrayList<T> neighbours = new ArrayList<>();
        neighbours.addAll(getNeighbours(currentNode));
        visited.add(currentNode);
        for (T neighbor : neighbours){
            Integer value = referenceCounts.get(neighbor);
            referenceCounts.remove(neighbor);
            referenceCounts.put(neighbor, value-1);
        }
    }
}
