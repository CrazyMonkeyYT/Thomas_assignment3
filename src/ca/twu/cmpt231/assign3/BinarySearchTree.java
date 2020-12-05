package ca.twu.cmpt231.assign3;
/**
 * This class is responsible for implementing the binary search tree
 * 
 * The codes is copied from: http://www.java2s.com/ref/java/java-algorithms-binary-search-tree-animation.html
 */

import java.util.List;


class BinarySearchTree<E extends Comparable<E>> implements Tree<E> {
	  protected BTNodeInt<E> root;
	  protected int size = 0;

	  /**
	   * constructor
	   */
	  public BinarySearchTree() {
	  }

	  /**
	   * constructor
	   * 
	   * @param objects
	   */
	  public BinarySearchTree(E[] objects) {
	    for (int i = 0; i < objects.length; i++)
	      insert(objects[i]);
	  }

	  /**
	   * search for element e
	   * 
	   * @param e
	   * @return TRUE if element found
	   */
	  public boolean search(E e) {
		  BTNodeInt<E> current = root; // Start from the root

	    while (current != null) {
	      if (e.compareTo(current.getElement()) < 0) {
	        current = current.getLeft();
	      } else if (e.compareTo(current.getElement()) > 0) {
	        current = current.getRight();
	      } else // element matches current.element
	        return true; // Element is found
	    }

	    return false;
	  }

	  /**
	   * insert element e
	   * 
	   * @param e
	   * @return TRUE if insert successful
	   */
	  public boolean insert(E e) {
	    if (root == null)
	      root = createNewNode(e); // Create a new root
	    else {
	      // Locate the parent node
	    	BTNodeInt<E> parent = null;
	    	BTNodeInt<E> current = root;
	      while (current != null)
	        if (e.compareTo(current.getElement()) < 0) {
	          parent = current;
	          current = current.getLeft();
	        } else if (e.compareTo(current.getElement()) > 0) {
	          parent = current;
	          current = current.getRight();
	        } else
	          return false; // Duplicate node not inserted

	      // Create the new node and attach it to the parent node
	      if (e.compareTo(parent.getElement()) < 0)
	        parent.setLeft(createNewNode(e));
	      else
	        parent.setRight(createNewNode(e));
	    }

	    size++;
	    return true; // Element inserted successfully
	  }

	  /**
	   * create a new node
	   * @param e
	   * @return
	   */
	  protected BTNodeInt<E> createNewNode(E e) {
	    return new TreeNode<>(e);
	  }

	  /** Inorder traversal from the root */
	  public void inorder() {
	    inorder(root);
	  }

	  /** Inorder traversal from a subtree */
	  protected void inorder(BTNodeInt<E> root) {
	    if (root == null)
	      return;
	    inorder(root.getLeft());
	    System.out.print(root.getElement() + " ");
	    inorder(root.getRight());
	  }

	  /** Postorder traversal from the root */
	  public void postorder() {
	    postorder(root);
	  }

	  /** Postorder traversal from a subtree */
	  protected void postorder(BTNodeInt<E> root) {
	    if (root == null)
	      return;
	    postorder(root.getLeft());
	    postorder(root.getRight());
	    System.out.print(root.getElement() + " ");
	  }

	  /** Preorder traversal from the root */
	  public void preorder() {
	    preorder(root);
	  }

	  /** Preorder traversal from a subtree */
	  protected void preorder(BTNodeInt<E> root) {
	    if (root == null)
	      return;
	    System.out.print(root.getElement() + " ");
	    preorder(root.getLeft());
	    preorder(root.getRight());
	  }

	  /** Get the number of nodes in the tree */
	  public int getSize() {
	    return size;
	  }

	  /** Returns the root of the tree */
	  public BTNodeInt<E> getRoot() {
	    return root;
	  }

	  /** Returns a path from the root leading to the specified element */
	  public List<BTNodeInt<E>> path(E e) {
	    List<BTNodeInt<E>> list = new java.util.ArrayList<>();
	    BTNodeInt<E> current = root; // Start from the root

	    while (current != null) {
	      list.add(current); // Add the node to the list
	      if (e.compareTo(current.getElement()) < 0) {
	        current = current.getLeft();
	      } else if (e.compareTo(current.getElement()) > 0) {
	        current = current.getRight();
	      } else
	        break;
	    }

	    return list;
	  }

	  /**
	   * delete an element
	   * 
	   * @param e
	   * @return TRUE if delete is successful
	   */
	  public boolean delete(E e) {
		  BTNodeInt<E> parent = null;
		  BTNodeInt<E> current = root;
	    while (current != null) {
	      if (e.compareTo(current.getElement()) < 0) {
	        parent = current;
	        current = current.getLeft();
	      } else if (e.compareTo(current.getElement()) > 0) {
	        parent = current;
	        current = current.getRight();
	      } else
	        break;
	    }

	    if (current == null)
	      return false; // Element is not in the tree

	    // Case 1: current has no left child
	    if (current.getLeft() == null) {
	      // Connect the parent with the right child of the current node
	      if (parent == null) {
	        root = current.getRight();
	      } else {
	        if (e.compareTo(parent.getElement()) < 0)
	          parent.setLeft(current.getRight());
	        else
	          parent.setRight(current.getRight());
	      }
	    } else {
	      // Case 2: The current node has a left child
	      // Locate the rightmost node in the left subtree of
	      // the current node and also its parent
	    	BTNodeInt<E> parentOfRightMost = current;
	    	BTNodeInt<E> rightMost = current.getLeft();

	      while (rightMost.getRight() != null) {
	        parentOfRightMost = rightMost;
	        rightMost = rightMost.getRight(); // Keep going to the right
	      }

	      // Replace the element in current by the element in rightMost
	      current.setElement(rightMost.getElement());

	      // Eliminate rightmost node
	      if (parentOfRightMost.getRight() == rightMost)
	        parentOfRightMost.setRight(rightMost.getLeft());
	      else
	        // Special case: parentOfRightMost == current
	        parentOfRightMost.setLeft(rightMost.getLeft());
	    }

	    size--;
	    return true; // Element deleted successfully
	  }

	  /** Obtain an iterator. Use in order. */
	  public java.util.Iterator<E> iterator() {
	    return new InorderIterator();
	  }

	  // Inner class InorderIterator
	  private class InorderIterator implements java.util.Iterator<E> {
	    // Store the elements in a list
	    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
	    private int current = 0; // Point to the current element in list

	    public InorderIterator() {
	      inorder(); // Traverse binary tree and store elements in list
	    }

	    /** Inorder traversal from the root */
	    private void inorder() {
	      inorder(root);
	    }

	    /** Inorder traversal from a subtree */
	    private void inorder(BTNodeInt<E> root) {
	      if (root == null)
	        return;
	      inorder(root.getLeft());
	      list.add(root.getElement());
	      inorder(root.getRight());
	    }

	    /** More elements for traversing? */
	    public boolean hasNext() {
	      if (current < list.size())
	        return true;

	      return false;
	    }

	    /** Get the current element and move to the next */
	    public E next() {
	      return list.get(current++);
	    }

	    // Remove the element returned by the last next()
	    public void remove() {
	      if (current == 0) // next() has not been called yet
	        throw new IllegalStateException();

	      delete(list.get(--current));
	      list.clear(); // Clear the list
	      inorder(); // Rebuild the list
	    }
	  }

	  /** Remove all elements from the tree */
	  public void clear() {
	    root = null;
	    size = 0;
	  }
	}
