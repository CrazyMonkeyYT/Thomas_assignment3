package ca.twu.cmpt231.assign3;

import java.util.Iterator;

/**
 * This interface specifies the functionality of a tree
 * 
 * The codes is copied from: http://www.java2s.com/ref/java/java-algorithms-binary-search-tree-animation.html 
 *
 * @param <E>
 */

//import java.util.Collection;

interface Tree<E> { //extends Collection<E> {
	  /** Return true if the element is in the tree */
	  public boolean search(E e);

	  /**
	   * Insert element e into the binary tree Return true if the element is inserted
	   * successfully
	   */
	  public boolean insert(E e);

	  /**
	   * Delete the specified element from the tree Return true if the element is
	   * deleted successfully
	   */
	  public boolean delete(E e);

	  /** Get the number of elements in the tree */
	  public int getSize();

	  /** Inorder traversal from the root */
	  public default void inorder() {
	  }

	  /** Postorder traversal from the root */
	  public default void postorder() {
	  }

	  /** Preorder traversal from the root */
	  public default void preorder() {
	  }

	  /** Return true if the tree is empty */
	  public default boolean isEmpty() {
	    return this.size() == 0;
	  }

	  /**
	   * Returns the number of elements in this collection.
	   * @return
	   */
	  public default int size() {
	    return getSize();
	  }

	  /**
	   * Returns an iterator over the elements in this collection. 
	   * @return
	   */
	  Iterator<E> iterator();
	  
	  /**
	   * Removes all of the elements from this collection (optional operation).
	   */
	  void clear();	
	  
	}