package linkedList.list;

import linkedList.node.ListNode;
import linkedList.node.SingleLinkNode;

/**
 * Allows nodes to form a Linked List
 * @param <T> tge type of objects stored in the nodes.
 *
 * @author Adam Birch
 * @version November 2018
 */

public class SingleLinkedList<T> extends BasicList implements List {
    int nodeCount = 0;

    public SingleLinkedList() {
        this.root = new SingleLinkNode(null);
    }


    /**
     * Add a node to the list
     * @param index the index at which the new entry should be added.
     * @param value the value to be added.
     * @throws ListAccessError
     */
    @Override
    public void add(int index, Object value) throws ListAccessError {
        SingleLinkNode node = new SingleLinkNode(value);
        if (index < 0 || index > nodeCount){
            throw new ListAccessError("Requested index was outside of the list bounds.");
        }
        else if (index == 0) // is Root
            {
                this.setRoot(node);
                if (nodeCount > 0){
                    SingleLinkNode nextNode = (SingleLinkNode) this.get(index + 1);
                    changeNext(index, nextNode);
                }

            }
            else if (index == nodeCount) // is Tail
            {
                addNext(index , node);
            }
        else {
            SingleLinkNode nextNode = (SingleLinkNode) this.get(index + 1);
            node = new SingleLinkNode(value, nextNode);
            changeNext(index - 1, node);
            }
        nodeCount++;
    }

    /**
     * Adds a new node to the tail of the list.
     * @param index - The position that the node is placed (nodeCount).
     * @param newNode - The node to be added.
     */

    private void addNext(int index, SingleLinkNode<T> newNode) {
        try {
            SingleLinkNode node = (SingleLinkNode) this.get(index);
            node.setNext(newNode);
        } catch (ListAccessError listAccessError) {
            listAccessError.printStackTrace();
        }
    }

    /**
     * responsible for changing the next value, for adding and deleting
     * @param index - The index of the node to be altered
     * @param nextNode - The new Next node
     */
        private void changeNext(int index, SingleLinkNode nextNode) {
        try {
            SingleLinkNode node = (SingleLinkNode) this.get(index);
            node.setNext(nextNode);
        } catch (ListAccessError listAccessError) {
            listAccessError.printStackTrace();
        }
    }

    /**
     * A method that takes only an object value as the new nodeCount node.
     * @param value - The value to be used as the new node.
     * @throws ListAccessError
     */
    public void add(Object value) throws ListAccessError {
        add(nodeCount, value);
    }


    /**
     * Remove a node from the linked list.
     * @param index the index of the entry to be removed.
     * @return the node removed
     * @throws ListAccessError
     */
    @Override
    public Object remove(int index) throws ListAccessError {

        if (index > 1 && index < nodeCount){
            SingleLinkNode nextNode = (SingleLinkNode) this.get(index + 1);
            changeNext(index - 1, nextNode);
        }
        else if (index == 0){
            root = get(1);
            this.setRoot(root);
        }
        else if (index == nodeCount){
            SingleLinkNode node = (SingleLinkNode) this.get(index - 1);
            node.setNext(null);
        }
        nodeCount--;
        return this.get(index);
    }

    public void removeAll() throws ListAccessError {
        this.setRoot(null);
        this.nodeCount = 0;
    }


    /**
     * Retrieve the node at an index
     * @param index the index of the entry to be accessed.
     * @return the node at the index.
     * @throws ListAccessError
     */
    @Override
    public ListNode get(int index) throws ListAccessError {
        ListNode current = root;
        if (index > nodeCount){throw new ListAccessError("Requested index was outside of the list bounds.");}
        else{
            boolean found = false;
            int i = 0;

            do {
                if (i == index){
                    found = true;
                }
                else{
                    i ++;
                    try {
                        current = current.getNext();
                    } catch (Exception e) {
                        break;
                    }
                }
            }
            while (!found && i <= nodeCount);
            if (!found){return null;}
        }
        return current;
    }

    /**
     * Output data about each node within the linked List.
     * @return all nodes within the linked List
     */
    public String allToString() {
        for (int i = 0; i < nodeCount; i++) {
            SingleLinkNode temp = null;
            try {
                temp = (SingleLinkNode) this.get(i);
            } catch (ListAccessError listAccessError) {
                listAccessError.printStackTrace();
            }
            System.out.println( "Index = " + i +
                    " Value = " + temp.getValue() +
                    " Next = " + temp.getNext() +
                    " Root = " + this.getRoot()
            );
        } return null;
    }

    @Override
    public String toString() {
        return String.valueOf(root);
    }
}
