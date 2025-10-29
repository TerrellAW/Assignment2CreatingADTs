package utilities;

/**
 * QueueADT.java
 * 
 * @author Estefano Campana
 * @version 1.0
 * 
 * This abstract type interface serves to manage a collection of elements that are inserted at the start of the queue,
 * and pushes items forward in the list. Elements cannot be accessed directly by index and can be duplicate values.
 */
public interface QueueADT<T>
{
	/**
	 * Method to check the size of the queue.
	 * 
	 * Precondition: The queue must have at least 1 item.
	 * 
	 * Postcondition: Return the size of the queue.
	 * 
	 * @return An integer value representing the size of the queue.
	 * @author Estefano Campana
	 */
	public int size();
	/**
	 * Method to check if the queue is currently empty.
	 * 
	 * Precondition: The queue must exist.
	 * 
	 * Postcondition: Return a boolean value if the queue is empty.
	 * 
	 * @return True if the queue is empty.
	 * @author Estefano Campana
	 */
	public boolean isEmpty();
	/**
	 * Method to search if a specified item exists in the queue.
	 * 
	 * Precondition: There must be at least 1 element in the queue.
	 * 
	 * Postcondition: Returns a boolean value if the value is found.
	 * 
	 * @return True if the queue contains the specified item.
	 * @param item of any data type.
	 * @throws NullPointerException if the queue has no items.
	 * @author Estefano Campana
	 */
	public boolean contains(T item) throws NullPointerException;
	/**
	 * Converts the queue items into an array.
	 * 
	 * Precondition: The queue must exist and have at least 1 element in it.
	 * 
	 * Postcondition: The queue elements are copied to an array.
	 * 
	 * @return An array with all the elements in the queue.
	 * @author Estefano Campana
	 */
	public T[] toArray();
}