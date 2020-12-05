package ca.twu.cmpt231.assign3;

/**
 * This class provides the implementation of a tree node
 * 
 * The codes is copied from:
 * http://www.java2s.com/ref/java/java-algorithms-binary-search-tree-animation.html
 *
 * @param <E>
 */

class TreeNode<E> implements BTNodeInt<E> {
	public E element;
	public TreeNode<E> left;
	public TreeNode<E> right;

	public TreeNode(E e) {
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
	public void setLeft(BTNodeInt<E> node) {
		left = (TreeNode<E>) node;

	}

	/**
	 * set right child node
	 */
	public void setRight(BTNodeInt<E> node) {
		right = (TreeNode<E>) node;
	}

	/**
	 * return left child node
	 */
	public BTNodeInt getLeft() {
		return left;
	}

	/**
	 * return right child node
	 */
	public BTNodeInt getRight() {
		return right;
	}
}
