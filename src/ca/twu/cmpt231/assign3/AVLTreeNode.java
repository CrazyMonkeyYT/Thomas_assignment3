package ca.twu.cmpt231.assign3;

/**
 * ThomasWilliamson #588206
 * This class provides the implementation of a tree node
 * 
 * The codes is copied from:
 * http://www.java2s.com/ref/java/java-algorithms-binary-search-tree-animation.html
 *
 * @param <E>
 */

class AVLTreeNode<E> {
	public E element;
	public AVLTreeNode<E> left = null;
	public AVLTreeNode<E> right = null;
	public AVLTreeNode<E> parent = null;
	public int hightLeft = 0;
	public int hightRight = 0;
	public int maxHight = 0;
	public int balanceNum = 0;
	
	
	public AVLTreeNode(E e) {
		element = e;
	}

	/**
	 * set element
	 * 
	 * @param e
	 */
	public void setElement(E e) {
		this.element = e;
	}

	/**
	 * return element
	 * 
	 * @return
	 */
	public E getElement() {
		return element;
	}

	/**
	 * set left child node
	 */
	public void setLeft(AVLTreeNode<E> node) {
		left = node;

	}

	/**
	 * set right child node
	 */
	public void setRight(AVLTreeNode<E> node) {
		right = node;
	}
	/**
	 * set parent of node
	 */
	public void setParent(AVLTreeNode<E> node) {
		parent = node;
	}
	/**
	 * set hight
	 * @param h
	 */
	public void setHightR(int h) {
		hightRight = h;
		balanceNum = hightRight - hightLeft;
		setMaxHight();
	}
	public void setHightL(int h) {
		hightLeft = h;
		balanceNum = hightRight - hightLeft;
		setMaxHight();
	}
	public void setMaxHight() {
		maxHight = (hightLeft > hightRight) ? hightLeft : hightRight;
	}
	public void setBalanceNum(int b) {
		balanceNum = b;
	}
	/**
	 * return left child node
	 */
	public AVLTreeNode getLeft() {
		return left;
	}
	/**
	 * return right child node
	 */
	public AVLTreeNode getRight() {
		return right;
	}
	public int getMaxHight() {
		return maxHight;
	}
	/**
	 * @return parent
	 */
	public AVLTreeNode getParent() {
		return parent;
	}
	public int getHightR() {
		return (hightRight);
	}
	public int getHightL() {
		return (hightLeft);
	}
	public int getBalanceNum() {
		return (balanceNum);
	}	
}
