package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

/**
 * @author Riley Yonda
 * @version 1.8
 * A doubly linked list implementation.
 */
public class MyDLL<E> implements Iterator<E>, ListADT<E> {

	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;
	private MyDLLNode<E> cursor;

    /**
     * Default constructor.
     * Starts with an empty list.
     */
	public MyDLL() {
		head = null;
		tail = null;
		size = 0;
		cursor = null;
	}

	/**
	 * Returns number of elements in list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes all elements from the list.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
		cursor = null;

	}

	/**
	 * Inserts an element at specific index
	 * @param index position to insert at (0 to size)
     * @param toAdd element to insert
     * @return true if the element was added
     * @throws NullPointerException if toAdd is null
     * @throws IndexOutOfBoundsException if index is out of range
	 */
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add a null value");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index not accepted");
		}

		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

		if (size == 0) {
			head = newNode;
			tail = newNode;
		}

		else if (index == 0) {
			head.setPrev(newNode);
			newNode.setNext(head);
			head = newNode;
		}

		else if (index == size) {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}

		else {
			MyDLLNode<E> current = head;

			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}

			MyDLLNode<E> prev = current.getPrev();

			prev.setNext(newNode);
			newNode.setPrev(prev);
			newNode.setNext(current);
			current.setPrev(newNode);
		}
		size++;
		return true;

	}

    /**
     * Adds an element to the end of the list.
     * @param toAdd element to add
     * @return true if the element was added
     * @throws NullPointerException if toAdd is null
     */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null element");
		}

		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

		if (size != 0) {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		} else {
			head = newNode;
			tail = newNode;
		}

		size++;
		return true;
	}

    /**
     * Adds all elements from another list to the end of this list.
     * @param toAdd list whose elements will be added
     * @return true if elements were added
     * @throws NullPointerException if toAdd is null
     */
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {

		if (toAdd == null) {
			throw new NullPointerException("Cannot add from a null list");
		}

		for (int i = 0; i < toAdd.size(); i++) {
			this.add(toAdd.get(i));
		}

		return true;

	}

    /**
     * Returns the element at a given index.
     * @param index index of the element to return
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index is out of range
     */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
		MyDLLNode<E> current = head;

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getElement();
	}

    /**
     * Removes and returns the element at a given index.
     * @param index index of element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is out of range
     */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {

		if (index < 0 || index >= size) {

			throw new IndexOutOfBoundsException("Index: " + index);

		}

		MyDLLNode<E> current = head;

		for (int i = 0; i < index; i++) {

			current = current.getNext();

		}

		E data = current.getElement();

		if (current == head && current == tail) {

			head = null;

			tail = null;

		} else if (current == head) {

			head = head.getNext();

			head.setPrev(null);

		} else if (current == tail) {

			tail = tail.getPrev();

			tail.setNext(null);

		} else {

			current.getPrev().setNext(current.getNext());

			current.getNext().setPrev(current.getPrev());

		}

		size--;

		return data;

	}

    /**
     * Removes the first occurrence of the given element from the list, if present.
     * @param toRemove element to remove
     * @return the removed element, or null if not found
     * @throws NullPointerException if toRemove is null
     */
	@Override
	public E remove(E toRemove) throws NullPointerException {

		if (toRemove == null) {

			throw new NullPointerException("Cannot remove null element");

		}

		MyDLLNode<E> current = head;

		while (current != null) {

			if (toRemove.equals(current.getElement())) {

				E data = current.getElement();

				if (current == head && current == tail) {
					head = null;
					tail = null;
				} 
				else if (current == head) {
					head = head.getNext();
					head.setPrev(null);

				} else if (current == tail) {
					tail = tail.getPrev();
					tail.setNext(null);
				} 
				else {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());
				}
				size--;
				return data;
			}
			current = current.getNext();
		}
		return null;
	}

    /**
     * Replaces the element at a given index with a new value.
     * @param index index of the element to change
     * @param toChange new element
     * @return the old element that was replaced
     * @throws NullPointerException if toChange is null
     * @throws IndexOutOfBoundsException if index is out of range
     */
	@Override
	public E set(int index, E toChange)
			throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null) {
			throw new NullPointerException("Cannot store null element");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
		MyDLLNode<E> current = head;

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		E old = current.getElement();
		current.setElement(toChange);
		
		return old;
	}
	
    /**
     * Returns true if the list has no elements.
     */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
    /**
     * Checks whether the list contains a given element.
     * @param toFind element to look for
     * @return true if found, false otherwise
     * @throws NullPointerException if toFind is null
     */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null");
		}
		MyDLLNode<E> current = head;

		while (current != null) {
			if (toFind.equals(current.getElement())) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

    /**
     * Copies the list elements into the given array.
     * If the array is too small, a new one is created.
     * @param toHold array to hold the elements
     * @return an array containing all elements in order
     * @throws NullPointerException if toHold is null
     */
	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] toHold) throws NullPointerException {

		if (toHold == null) {
			throw new NullPointerException("Array cannot be null");
		}

		if (toHold.length < size) {
			toHold = (E[]) new Object[size];
		}
		
		MyDLLNode<E> current = head;
		
		int i = 0;
		while (current != null && i < size) {
			toHold[i++] = current.getElement();
			current = current.getNext();
		}
		if (i < toHold.length) {
			toHold[i] = null;
		}
		return toHold;
	}

    /**
     * Returns a new Object[] containing all elements in order.
     */
	@Override
	public Object[] toArray() {

		Object[] result = new Object[size];
		MyDLLNode<E> current = head;

		int i = 0;
		while (current != null) {
			result[i++] = current.getElement();
			current = current.getNext();
		}
		return result;
	}

    /**
     * Returns an iterator over the elements in this list.
     * The iterator uses the internal cursor.
     */
	@Override
	public Iterator<E> iterator() {
		cursor = head; 
		return this; 
	}

    /**
     * Returns true if there is another element when iterating.
     */
	@Override
	public boolean hasNext() {
		return cursor != null;
	}
	
    /**
     * Returns the next element in the iteration.
     * @return next element
     * @throws NoSuchElementException if there are no more elements
     */
	@Override
	public E next() throws NoSuchElementException {

		if (!hasNext()) {
			throw new NoSuchElementException("No more elements");
		}
		E data = cursor.getElement();
		cursor = cursor.getNext();
		return data;
	}
}
