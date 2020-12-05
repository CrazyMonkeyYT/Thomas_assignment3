package ca.twu.cmpt231.assign3;

/**
 * Interface for a TreeNode
 * 
 * @author Samuel Leung
 *
 */
public interface BTNodeInt<E> {
	/**
	 * set element
	 * @param e
	 */
	public void setElement(E e);
	
	/**
	 * get element
	 * @param e
	 * @return
	 */
	public E getElement();
	
	/**
	 * set left child
	 * 
	 * @param node
	 */
	public void setLeft(BTNodeInt<E> node);
	
	/**
	 * set right child
	 * 
	 * @param node
	 */
	public void setRight(BTNodeInt<E> node);
	
	/**
	 * get left child
	 * @return
	 */
	public BTNodeInt<E> getLeft();
	
	/**
	 * get right child
	 * @return
	 */
	public BTNodeInt<E> getRight();
}
