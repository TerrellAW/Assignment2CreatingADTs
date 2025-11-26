package implementations;

/**
 * Creating Node for double-linked list, storing and referencing element, 
 * next and previous nodes
 * 
 * @author Riley Yonda
 * @version 1.8
 * @param <E> type of element stored
 */
public class MyDLLNode<E> {
	
    E element;
    MyDLLNode<E> next;
    MyDLLNode<E> prev;

	/**
	 * Description: Construct new node with element
	 * next / prev automatically stored as null
	 * @param element the element to be stored in this node
	 */
	public MyDLLNode(E element) {
		this.element = element;
	}
	
	/**
	 * Description: return element stored in node
	 * @return element stored in node
	 */
	public E getElement() {return this.element;}
	
	/**
	 * Description: Sets the element stored in this node
	 * @param: element the new element stored in this node
	 */
	public void setElement(E element) {this.element = element;}
	
	/**
	 * Description: Returns the next node in the list.
	 * @return the next node, or null if this is the last node
	 */
	public MyDLLNode<E> getNext() {return this.next;}
	
	/**
	 * Description: Sets the next node reference.
	 * @param next the node that should follow this node in the list
	 */
	public void setNext (MyDLLNode<E> next) {this.next = next;}
	
	/**
	 * Description: Returns the previous node in the list.
	 * @return the previous node, or null if this is the first node
	 */
	public MyDLLNode<E> getPrev() {return this.prev;}
	
	/**
	 * Description: Sets the previous node reference.
	 * @param prev the node that should precede this node in the list
	 */
	public void setPrev(MyDLLNode<E> prev) {this.prev = prev;}
}
    
