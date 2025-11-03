package utilities;

import java.util.NoSuchElementException;

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
	 * Retrieves / Removes element at front queue
	 * 
	 * Precondition: Queue not empty
	 * 
	 * Postcondition: Element removed and returned
	 * 
	 * @return the element at front of queue
	 * @throws NoSuchElementException if the queue is empty
	 * @author Riley Yonda
	 */
	public T remove() throws NoSuchElementException;

	/**
	 * Adds element to end of queue
	 * 
	 * Precondition: Queue capacity cannot be exceeded
	 * 
	 * Postcondition: Added to end of queue
     * @param item to be added to the queue
	 * @return true if the adding of element is successful
	 * @throws IllegalStateException if elements of queue exceeds capacity / Queue full
	 * @author Riley Yonda
	 */
	public boolean add(T item) throws IllegalStateException;

	/**
	 * Insert element at end of queue
	 * 
	 * Precondition: Container cannot be full
	 * 
	 * Postcondition: As long as capacity isn't exceeded, element is added 
	 * 
	 * @param item to be added to the queue
	 * @return if element is added to queue, returns true, otherwise false
	 * @author Riley Yonda
	 */
	public boolean offer(T item);

	/**
	 * Removes / Returns element at front of queue
	 * 
	 * Precondition: no existing elements needed as it returns null if empty
	 * 
	 * Postcondition: Element removed
	 * 
	 * @return Element at front of queue, null if empty
	 * @author Riley Yonda
	 */
	public T poll();
	
	/**
	 * Returns element at front of queue
	 * 
	 * Precondition: no existing elements needed as it returns null if empty
	 * 
	 * Postcondition: First element of container is returned
	 * 
	 * @return Element at front of queue, null if empty
	 * @author Riley Yonda
	 */
	public T peek();

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