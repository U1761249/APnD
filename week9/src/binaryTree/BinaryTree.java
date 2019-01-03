package binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the Binary Tree work, extending class BTree
 * @author Adam Birch
 * @version December 2018
 */

public class BinaryTree<T extends Comparable<? super T>> implements BTree<T> {

    private static List traversalList = new ArrayList();
    private static int nodeCount = 0;

    private TreeNode<T> root; // the root node
    private BTree<T> left, right;

    @Override
    public String toString() {
        return "" + root;
    }

    /**
     * Construct an empty tree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a singleton tree.
     * A singleton tree contains a value in the root, but the left and right subtrees are
     * empty.
     * @param value the value to be stored in the tree.
     */
    public BinaryTree(T value) {
        root = new TreeNode(value);
    }

    /**
     * Construct a tree with a root value, and left and right subtrees.
     * @param value the value to be stored in the root of the tree.
     * @param left the tree's left subtree.
     * @param right the tree's right subtree.
     */
    public BinaryTree(T value,BinaryTree<T> left,BinaryTree<T> right) {
        root = new TreeNode(value,left,right);
    }

    /**
     * Check if the tree is empty.
     * @return true if the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert a new value in the binary tree at a position determined by the current contents
     * of the tree, and by the ordering on the type T.
     * @param value the value to be inserted into the tree.
     */
    @Override
    public void insert(T value) {
        if(isEmpty()){
            root = new TreeNode(value);
            nodeCount ++;
        }
        else{
            if (value.compareTo(root.value) <= 0){
                    BTree<T> tree = getLeft();
                    tree.insert(value);
            }

            else if (value.compareTo(root.value) > 0){
                    BTree<T> tree = getRight();
                    tree.insert(value);
            }
        }
    }

    /**
     * Get the value stored at the root of the tree.
     * @return the value stored at the root of the tree.
     */
    @Override
    public T getValue() throws NullPointerException {
        // Note: it might make sense to define getValue() to throw a (custom) exception if an attempt
        // is made to access a value from an empty tree.
        // However, since a tree is empty iff it's root node is null, it is also acceptable to rely
        // on Java's NullPointerException.
        // This comment also applies to the other get and set methods defined in this interface.

        // placeholder return value below - replace with implementation of getValue()
        return root.getValue();
    }

    /**
     * Change the value stored at the root of the tree.
     * @param value the new value to be stored at the root of the tree.
     */
    @Override
    public void setValue(T value) {
        // implement setValue(T value) here
        root.setValue(value);
    }

    /**
     * Get the left subtree of this tree.
     * @return  This tree's left subtree.
     */
    @Override
    public BTree<T> getLeft() throws NullPointerException {
        // placeholder return value below - replace with implementation of getLeft()
        return root.left;
    }

    /**
     * Change the left subtree of this tree.
     * @param tree the new left subtree.
     */
    @Override
    public void setLeft(BTree<T> tree) {
        // implement setLeft(BTree<T> tree) here
        root.left = tree;
    }

    /**
     * Get the right subtree of this tree.
     * @return this tree's right subtree.
     */
    @Override
    public BTree<T> getRight() throws NullPointerException{
        // placeholder return value below - replace with implementation of getRight()
        return root.right;
    }

    /*
     * Change the right subtree of this tree.
     * @param tree the new right subtree.
     */
    @Override
    public void setRight(BTree<T> tree) {
        // implement setRight(BTree<T> tree) here
        root.right = tree;
    }

    /**
     * Check if the tree contains a given value.
     * @using Preorder traversal.
     * @param target the value to be checked.
     * @return true iff the value is in the tree.
     */
    @Override
    public boolean contains(T target) {
        T rootValue = null;
        try {
            rootValue = this.root.getValue();
        } catch (NullPointerException e) { // null = value not found
            return false;
        }
        boolean found = false;
         if (target.compareTo(rootValue) == 0){found = true;}
        else{
            if (rootValue.compareTo(target) > 0){ // If target is smaller than the root
                BTree next = root.getLeft();
                found = next.contains(target);
            }
            else if (rootValue.compareTo(target) < 0){ // if target is larger than the root
                BTree next = root.getRight();
                found = next.contains(target);
            }
        }
        return found;
    }

    /**
     * Traverse the tree, producing a list of all the values contained in the tree.
     * @using Preorder traversal.
     * @return a list of all the values in the tree.
     */
    @Override
    public List<T> traverse() {

        try {
            traversalList.add(root.value.toString());
        } catch (NullPointerException e) { }

        if (traversalList.size() < nodeCount){}

        BTree currentLeft = null;
        BTree currentRight = null;
        try {

            currentLeft = this.getLeft();
            currentRight = this.getRight();
        } catch (NullPointerException e) {

        }
        if (currentLeft != null){
            currentLeft.traverse();
        }
        if (currentRight != null){
            currentRight.traverse();
        }
        return traversalList;

    }
}

