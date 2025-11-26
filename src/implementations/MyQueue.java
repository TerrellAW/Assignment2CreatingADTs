package implementations;

import exceptions.EmptyQueueException;
import java.io.*;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.QueueADT;

/**
 * Class that holds MyQueue implementation. Implements QueueADT as its abstract interface.
 * @author Estefano Campana
 * @version  1.0
 */
public class MyQueue<E> implements QueueADT<E>
{
	/**
	 * Linked list object used as its underlying data.
	 */
	private MyDLL<E> linkedList;
	/**
	 * Integer that will determine the maximum capacity of the Queue.
	 */
	private int capacity;
	
	/**
	 * Constructor for class MyQueue
	 */
	public MyQueue(){
		linkedList = new MyDLL<E>();
		this.capacity = -1;
	}
	
	/**
	 * Constructor for class MyQueue with a fixed capacity.
	 * @param size Integer that represents the maximum capacity of the Queue.
	 */
	public MyQueue(int size) {
		linkedList = new MyDLL<E>();
		this.capacity = size;
	}
	
	/**
	 * Adds an item at the end of the Queue.
	 * @throws NullPointerException if a null value is intended to be inserted.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public void enqueue( E toAdd ) throws NullPointerException
	{
		if(toAdd == null)
		{
			throw new NullPointerException("Cannot add null values.");
		}
		linkedList.add(toAdd);
	}

	/**
	 * Removes the first item that was placed in the Queue.
	 * 
	 * @return The first item in the Queue.
	 * @throws EmptyQueueException raised when the queue's length is zero.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public E dequeue() throws EmptyQueueException
	{
		if(linkedList.size() == 0) 
		{
			throw new EmptyQueueException("Cannot dequeue from an empty Queue!");
		}
		return linkedList.remove(0);
	}

	/**
	 * Method used to check the first element in the Queue.
	 * 
	 * @return the first item in the queue.
	 * @throws EmptyQueueException raised when the queue's length is zero.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public E peek() throws EmptyQueueException
	{
		if(linkedList.size() == 0) {
			throw new EmptyQueueException("Cannot peek from an empty Queue!");
		}
		return linkedList.getHead().element;
	}
	
	/**
	 * Removes all elements in the Queue.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public void dequeueAll() 
	{
		if(linkedList.size() >= 1)
		{
			linkedList.clear();
		}
	}

	/**
	 * Method used to check if the Queue has currently no items.
	 * 
	 * @return <code>True</code> when queue length is zero.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public boolean isEmpty() 
	{
		if(linkedList.size() == 0) 
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns true if the Queue has the specified item.
	 * 
	 * @param toFind element to be checked inside this Queue.
	 * @return True if this list contains the specified element.
	 * @throws NullPointerException if the specified element is null.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public boolean contains( E toFind ) throws NullPointerException
	{
		if(toFind == null) 
		{
			throw new NullPointerException("Cannot search for null values!");
		}
		//Goes to the top of the Queue.
		MyDLLNode<E> curr = linkedList.getHead();
		//While the current node is not null.
		while(curr != null) 
		{
			if(curr.element.equals(toFind)) 
			{
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	/**
	 * Returns the index of the specified element in a position based on 1. In other words, the first element is considered to be in index 1.
	 * @param toFind The desired object.
	 * @return The index of the specified object.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public int search( E toFind ) 
	{
		//Goes to the top
		MyDLLNode<E> curr = linkedList.getHead();
		//Index starts at 1
		int counter = 1;
		//Traverse the list.
		while(curr != null) 
		{
			if(curr.element.equals(toFind)) 
			{
				return counter;
			}
			curr = curr.next;
			//For each iteration add 1 to counter.
			counter++;
		}
		//Return -1 if not found.
		return -1;
	}

	/**
	 * Method to invoke an iterator.
	 * 
	 * @return an iterator over the elements in this queue in proper sequence.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public Iterator<E> iterator()
	{
		return new QueueIterator();
	}

	/**
	 * Used to compare two Queue ADTs. To be equal two queues must contain equal
	 * items appearing in the same order.
	 * 
	 * @param that The other Queue ADT to be compared to this queue.
	 * @return <code>True</code> if the Queues are equal.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public boolean equals( QueueADT<E> that ) 
	{
		//if the other Queue is the same instance of this Queue.
		if(this == that) return true;
		//if the other Queue is null.
		if(that == null) return false;
		//If sizes not match.
		if(that.size() != this.size()) return false;
		//Initialize iterators.
		Iterator<E> it1 = new QueueIterator();
		Iterator<E> it2 = that.iterator();
		//While both iterators have a next value.
		while (it1.hasNext() && it2.hasNext()) {
			//initializing variables
			E elementA = it1.next();
			E elementB = it2.next();
			
			//if the first element is null
			if(elementA == null) 
			{
				//if the first element of the other Queue is not null.
				if(elementB != null) return false;
			}
			//if both elements are not equal.
			else if (!elementA.equals(elementB)) 
			{
				return false;
			}
		}
		//returns true if none of the above conditions are met.
		return true;
	}

	/**
	 * Method to convert this Queue into an Array.
	 * 
	 * @return An array containing all of the elements in this list in proper sequence.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public Object[] toArray() 
	{
		//Creates a new Object array with the same size as the linkedList.
		Object[] arr = new Object[linkedList.size()];

		//Goes to the top.
	    MyDLLNode<E> curr = linkedList.getHead();
	    int i = 0;
	    //Traverse through the Queue.
	    while (curr != null) 
	    {
	    	//Append items into the correct index.
	    	arr[i++] = curr.element;
	    	curr = curr.next;
	    }
	    return arr;
	}

	/**
	 * Method to convert this Queue into an array and appending it into another array.
	 * 
	 * @param toHold The array into which the elements of this queue are to be stored.
	 * @return An array containing the elements of this queue.
	 * @throws NullPointerException if the specified array is null.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public E[] toArray( E[] holder ) throws NullPointerException
	{
		//if the holder is null
	    if (holder == null) 
	    {
	        throw new NullPointerException("Holder cannot be null.");
	    }
	    //use the methods inside linkedList.
	    return linkedList.toArray(holder);
	}

	/**
	 * Method to check if a Queue is full. A Queue is considered full when the amount of items inside, matches the fixed capacity given.
	 * 
	 * @return <code>True</code> if queue is at capacity.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public boolean isFull() 
	{
		if(capacity < 0) return false;
		return linkedList.size() == capacity;
	}

	/**
	 * Method used to check the size of the queue.
	 * 
	 * @return The current size to the queue as an integer.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public int size() 
	{
		return linkedList.size();
	}
	
	/**
	 * Custom iterator implementation for MyQueue class.
	 */
	private class QueueIterator implements Iterator<E> {
		/**
		 * Represents the head of the linked list.
		 */
	    private MyDLLNode<E> cursor = linkedList.getHead();

	    /**
	     * Method used to check if there is a next item.
	     * @author Estefano Campana
	     * @version 1.0
	     */
	    @Override
	    public boolean hasNext() {
	    	return cursor != null;
	    }
	    
	    /**
	     * Method used to traverse through the Queue.
	     * @author Estefano Campana
	     * @version 1.0
	     */
	    @Override
	    public E next() throws NoSuchElementException {
	        if (!hasNext())
	            throw new NoSuchElementException("No more elements.");

	        E value = cursor.element;
	        cursor = cursor.next;
	        return value;
	    }
	}

}
