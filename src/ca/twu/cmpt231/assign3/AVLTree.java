package ca.twu.cmpt231.assign3;
/**
 * ThomasWilliamson #588206
 * This class is responsible for implementing the AVL search tree
 * 
 * The codes is copied from: http://www.java2s.com/ref/java/java-algorithms-binary-search-tree-animation.html
 */

import java.util.List;


class AVLTree<E extends Comparable<E>> implements Tree<E> {
	  protected AVLTreeNode<E> root;
	  protected int size = 0;

	  /**
	   * constructor
	   */
	  public AVLTree() {
	  }

	  /**
	   * constructor
	   * 
	   * @param objects
	   */
	  public AVLTree(E[] objects) {
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
		  AVLTreeNode<E> current = root; // Start from the root

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
	    	AVLTreeNode<E> parent = null;
	    	AVLTreeNode<E> current = root;
	    	while (current != null) {
	    		System.out.println("start "+current.getElement()+" "+current.getHightL()+" "+current.getHightR()+" input "+ e);
		        if (e.compareTo(current.getElement()) < 0) {
			        parent = current;
		        	current = current.getLeft();
		        } else if (e.compareTo(current.getElement()) > 0) {
		          parent = current;
		          current = current.getRight();
		        } else {
		        	return false; // Duplicate node not inserted
		        }
	      }      
	      // Create the new node and attach it to the parent node
	    	if (e.compareTo(parent.getElement()) < 0) {
	    		parent.setLeft(createNewNode(e));
	    		current = parent.getLeft();
	    		current.setParent(parent);
	    	}
	    	else {
	    		parent.setRight(createNewNode(e));
	    		current = parent.getRight();
	    		current.setParent(parent);
	    	}
	    	hightCascade(current);
	    	balance(current);
	    }
	    size++;
	    return true; // Element inserted successfully
	  }
	  private void balance(AVLTreeNode<E> node) {
		  AVLTreeNode<E> current = node;
		  
		  while (current != null) {

				if((current.getLeft() != null) && ((current.getBalanceNum()) <= (-2)) && (current.getLeft().getBalanceNum() == (1))) {
					System.out.println("leftRight");
		    		current = rotateLeftRight(current);

				}
				else if(current.getRight() != null && (current.getBalanceNum() >= 2) && (current.getRight().getBalanceNum() == (-1))) {
					System.out.println("Rightleft");

					current = rotateRightLeft(current);
					
				}
				
				else if(current.getLeft() != null && (current.getBalanceNum() <= -2) && (current.getLeft().getBalanceNum() == (-1))) {
					System.out.println("Right");

		    		current = rotateRight(current);

				}
				else if(current.getRight() != null && (current.getBalanceNum() >= 2) && (current.getRight().getBalanceNum() == (1))) {
					System.out.println("left");
		    		current = rotateLeft(current);

				}

				current = current.getParent();
		  }
	  }

	  private AVLTreeNode<E> rotateLeftRight(AVLTreeNode<E> node) {
		  AVLTreeNode<E> current;
		  rotateLeft(node.getRight());
			current = rotateLeft(node);
			return(current);
	  }

	  private AVLTreeNode<E> rotateRightLeft(AVLTreeNode<E> node) {
		  AVLTreeNode<E> current;
		  rotateRight(node.getRight());
			current = rotateLeft(node);
			return(current);
	  }
	  private AVLTreeNode<E> rotateLeft(AVLTreeNode<E> node) {
		  AVLTreeNode<E> right = node.getRight();
		  AVLTreeNode<E> parent = node.getParent();
		  AVLTreeNode<E> leftRight = right.getLeft();
		  
		  if( leftRight != null) {
			  node.setHightR(leftRight.getMaxHight()+1);
			  leftRight.setParent(node);
		  }
		  else {
			  node.setHightR(0);
		  }
		  
		  if(right != null) {
		  right.setParent(parent);
		  right.setLeft(node);
		  node.setParent(right);
		  }
		  node.setRight(leftRight); 
		  right.setHightL(node.getHightL()+1);
		  //System.out.println(node.getParent().getElement());
		  
		  if (parent == null) {
			  root = right;
		  }
		  else if(parent.getLeft() == node) {
			  parent.setLeft(right);
			  parent.setHightL(right.getMaxHight()+1);
			  System.out.println(parent.getHightL()+" "+parent.getHightR());

		  }
		  else if (parent.getRight() == node){
			  parent.setRight(right);
			  parent.setHightR(right.getMaxHight()+1);
			  System.out.println(parent.getHightL()+" "+parent.getHightR());

		  } 
		  return(right);
	  }
	  private AVLTreeNode<E> rotateRight(AVLTreeNode<E> node) {
		  AVLTreeNode<E> left = node.getLeft();
		  AVLTreeNode<E> parent = node.getParent();
		  AVLTreeNode<E> leftRight = left.getRight();
		  if( leftRight != null) {
			  node.setHightL(leftRight.getMaxHight()+1);
		  leftRight.setParent(node);
		  }
		  else {
			  node.setHightL(0);
		  }

		  if(left != null) {
		  left.setParent(parent);
		  left.setRight(node);
		  node.setParent(left);
		  //System.out.println(node.getParent().getElement());

		  }
		  node.setLeft(leftRight);
		  left.setHightR(node.getHightR()+1);
		 
		  if (parent == null) {
			  root = left;
		  }
		  else if(parent.getLeft() == node) {
			  parent.setLeft(left);
			  parent.setHightL(left.getMaxHight()+1);
		  }
		  else if (parent.getRight() == node){
			  parent.setRight(left);
			  parent.setHightR(left.getMaxHight()+1);
		  } 
		  return(left);
	  }
	  
	  /**
	   * create a new node
	   * @param e
	   * @return
	   */
	  protected AVLTreeNode<E> createNewNode(E e) {
	    return new AVLTreeNode<>(e);
	  }

	  /** Inorder traversal from the root */
	  public void inorder() {
	    inorder(root);
	  }

	  /** Inorder traversal from a subtree */
	  protected void inorder(AVLTreeNode<E> root) {
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
	  protected void postorder(AVLTreeNode<E> root) {
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
	  protected void preorder(AVLTreeNode<E> root) {
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
	  public AVLTreeNode<E> getRoot() {
	    return root;
	  }

	  /** Returns a path from the root leading to the specified element */
	  public List<AVLTreeNode<E>> path(E e) {
	    List<AVLTreeNode<E>> list = new java.util.ArrayList<>();
	    AVLTreeNode<E> current = root; // Start from the root

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
		  AVLTreeNode<E> parent = null;
		  AVLTreeNode<E> current = root;
		  
	    while (current != null) {
    		//System.out.println("start remove"+current.getElement()+" "+current.getHightL()+" "+current.getHightR()+" input "+ e);

	      if (e.compareTo(current.getElement()) < 0) {
	        parent = current;
	        current = current.getLeft();
	      } else if (e.compareTo(current.getElement()) > 0) {
	        parent = current;
	        current = current.getRight();
	      } else
	        break;
	    }
	    
	    if (current == null) {

        	return false; // Element is not in the tree
	    }
	    // Case 1: current has no left child
	    if (current.getLeft() == null) {
	      // Connect the parent with the right child of the current node
	      if (parent == null) {
	        root = current.getRight();

	      } else {
	        if (e.compareTo(parent.getElement()) < 0) {
	          parent.setLeft(current.getRight());
	          if( parent.getLeft() != null ) {
	        	  parent.getLeft().setParent(parent);
	          }
	          parent.setHightL(parent.getHightL()-1);
        	  hightCascade(parent);
        	  balance(parent);
	          
	        }
	   
	        else {
	          parent.setRight(current.getRight());
	          if(parent.getRight() != null) {
	        	 parent.getRight().setParent(parent);
	          }
	          parent.setHightR(parent.getHightR()-1);
	          hightCascade(parent);
	          balance(parent);
	         
	        }
	      }
	    }
	    else {
	      // Case 2: The current node has a left child
	      // Locate the rightmost node in the left subtree of
	      // the current node and also its parent
	    	AVLTreeNode<E> parentOfRightMost = current;
	    	AVLTreeNode<E> rightMost = current.getLeft();

	      while (rightMost.getRight() != null) {
	    	  
	    	  parentOfRightMost = rightMost;
	    	  rightMost = rightMost.getRight(); // Keep going to the right
	      }

	      // Replace the element in current by the element in rightMost
	      current.setElement(rightMost.getElement());

	      // Eliminate rightmost node
	      if (parentOfRightMost.getRight() == rightMost) {
	        parentOfRightMost.setRight(rightMost.getLeft());
	      	if(rightMost.getLeft() != null) 
	      		rightMost.getLeft().setParent(parentOfRightMost);
	      }
	      else {
	        // Special case: parentOfRightMost == current
	        parentOfRightMost.setLeft(rightMost.getLeft());
	        if(rightMost.getLeft() != null) 
	        rightMost.getLeft().setParent(parentOfRightMost);
	      }
	      parentOfRightMost.setHightR(parentOfRightMost.getHightR()-1);
	      hightCascade(parentOfRightMost);

	      balance(current);
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
	    private void inorder(AVLTreeNode<E> root) {
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

	  /* Remove all elements from the tree */
	  public void clear() {
	    root = null;
	    size = 0;
	  }

	  private void hightCascade(AVLTreeNode<E> node) {
		  AVLTreeNode<E> current = node;
		  
		  while (current.getParent() != null) {
	    		//System.out.println("start cascade"+current.getElement()+" "+current.getHightL()+" "+current.getHightR());

			  if(current == current.getParent().getLeft()) {  
				  current.getParent().setHightL(current.getMaxHight() +1);
			  }
			  if(current == current.getParent().getRight()) {  
				  current.getParent().setHightR(current.getMaxHight() +1);
			  }
			  current = current.getParent();
		  }
	  }
	}
