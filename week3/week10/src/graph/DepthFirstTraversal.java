package graph;

import java.util.*;

public class DepthFirstTraversal<T> extends AdjacencyGraph<T> implements Traversal<T> {

    ArrayList<T> toDoList = new ArrayList<>();
    ArrayList<T> visited = new ArrayList<>();

    /**
     * This is the main traverse method being used to get the traversal list.
     * @param root that is currently being traversed.
     * @return list of nodes visited in order of being visited.
     * @throws GraphError
     */
    synchronized public List<T> traverse(T root) throws GraphError {
        Set<T> nodes = getNodes();
        if (!nodes.contains(root)){
            throw new IndexOutOfBoundsException("Root was not within the Graph.");
        }
        if (!visited.contains(root)) {
            visited.add(root);
            Set<T> children = getNeighbours(root);
            for (T childNode : children) {
                if (!visited.contains(childNode)) {
                    toDoList.add(childNode);
                }
            }
        }
        try {
            for(T node : toDoList){
                toDoList.remove(node);
                traverse(node);
            }
        } catch (ConcurrentModificationException e) {}
        if (toDoList.isEmpty()){
            traverse();
        }

        return visited;
    }

    /**
     * This is a generic traverse() method called if no node is given.
     * @return list of nodes visited in order of being visited.
     * @throws GraphError
     */
    @Override
    public List<T> traverse() throws GraphError {
        T node = getUnvisited();
        if (node != null){traverse(node); }
        return visited;
    }

    /**
     * This method gets a node that hasn't been visited.
     * @return a random node from within the unvisited list
     */
    private T getUnvisited() {
        int nodeCount = getNoOfNodes();
        ArrayList<T> unvisited = new ArrayList<T>();
        if (nodeCount > visited.size()){
            ArrayList<T> listNodes = new ArrayList<T>();
            listNodes.addAll(getNodes());
            for(T node : listNodes) {
                if (!visited.contains(node)) {
                    unvisited.add(node);
                }
            }
        }
        else if (nodeCount == visited.size()){unvisited = null;}
        try {
            return   unvisited.get(new Random().nextInt(unvisited.size()));
        } catch (Exception e) {
            return null;
        }
    }
}
