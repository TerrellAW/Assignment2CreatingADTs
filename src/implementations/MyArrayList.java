package implementations;

import java.util.NoSuchElementException; // Iterator interface contract specifies this

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E>
{
	private Object[] array;

	/**
	 * 
	 *
	 * @author TerrellAW
	 */
	public int size() {
		int size = array.length;
		return size;
	}
	
	/**
	 *
	 *
	 * @author TerrellAW
	 */
	public void clear() {
		array = null;
	}
	
	/**
	 *
	 *
	 * @author TerrellAW
	 */
	public boolean add( int index, E toAdd ) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null) { // Throw error if element is null
			throw new NullPointerException("Element to add cannot be null");
		}
		if (index < 0 || index > size()) { // Throw error if index is out of bounds
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}

		Object[] firstSegment = new Object[index + 1]; // Create array to hold first segment with empty space
		Object[] secondSegment = new Object[array.length - index]; // Create array to hold second segment

		System.arraycopy(array, 0, firstSegment, 0, index); // Copy first segment to new array
		System.arraycopy(array, index, secondSegment, 0, array.length - index); // Copy second segment to new array

		firstSegment[index] = toAdd; // Add element to empty space at the end of first segment
		array = new Object[firstSegment.length + secondSegment.length]; // Create new array large enough for both segments

	    System.arraycopy(firstSegment, 0, array, 0, firstSegment.length); // Copy first segment into new array
		System.arraycopy(secondSegment, 0, array, firstSegment.length, secondSegment.length); // Copy second segment into new array

		return true; // Add successful
	}
	
	/**
	 *
	 *
	 * @author TerrellAW
	 */
	public boolean add( E toAdd ) throws NullPointerException {
		if (toAdd == null) { // Throw error if element is null
			throw new NullPointerException("Element to add cannot be null");
		}
		
		Object[] newArray = new Object[array.length + 1]; // Create new larger array
	   	System.arraycopy(array, 0, newArray, 0, array.length); // Copy data from old array
		newArray[newArray.length - 1] = toAdd; // Add element to the new array
		array = newArray; // Replace old array with new array

		return true; // Add successful
	}
	
	public boolean addAll( ListADT<? extends E> toAdd ) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add elements from a null list");
		}

		Iterator<? extends E> it = toAdd.iterator();
		while (it.hasNext()) {
			this.add(it.next());
		}
		return true;
	}
	
	public E get( int index ) throws IndexOutOfBoundsException {
		if (index >= this.size()) {
			throw new IndexOutOfBoundsException("Index (" + index + ") is out of bounds (" + this.size() + ")");
		}
		
		@SuppressWarnings("unchecked")
		return (E)array[index];
	}
	
	/**
	 *
	 *
	 * @author TerrellAW
	 */
	public E remove( int index ) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) { // Throw error if index is out of bounds
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}
		
		@SuppressWarnings("unchecked")
		E element = (E)array[index]; // Store element that will be removed

		Object[] firstSegment = new Object[index - 1]; // Create array to hold first segment
		Object[] secondSegment = new Object[array.length - index]; // Create array to hold second segment
		
		System.arraycopy(array, 0, firstSegment, 0, firstSegment.length); // Copy segment from before index
		System.arraycopy(array, index + 1, secondSegment, 0, secondSegment.length); // Copy segment from after index
		
		array = new Object[firstSegment.length + secondSegment.length]; // Create new array large enough for both segments
	    System.arraycopy(firstSegment, 0, array, 0, firstSegment.length); // Copy first segment into new array
		System.arraycopy(secondSegment, 0, array, firstSegment.length, secondSegment.length); // Copy second segment into new array

		return element; // Return removed element
	}
	
	/**
	 *
	 *
	 * @author TerrellAW
	 */
	public E remove( E toRemove ) throws NullPointerException {
		if (toRemove == null) { // Throw error if element is null
			throw new NullPointerException("Element to remove must exist");
		}

		@SuppressWarnings("unchecked")
		E element = (E)array[index]; // Store element that will be removed

		Object[] newArray = new Object[array.length - 1]; // Create shorter array
		System.arraycopy(array, 0, newArray, 0, newArray.length); // Copy all but last element to shorter array
		array = newArray; // Replace old array with new array

		return element; // Return removed element
	}
	
	public E set( int index, E toChange ) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null) {
			throw new NullPointerException("New value cannot be null");
		} else if (index >= this.size()) {
			throw new IndexOutOfBoundsException("Index (" + index + ") is out of bounds (" + this.size() + ")");
		}

		@SuppressWarnings("unchecked")
		E element = (E)array[index];

		array[index] = toChange;

		return element;
	}
	
	public boolean isEmpty() {
		if (this.size() != 0) {
			return false;
		} else {
			return true;
		}
	}

	public int linearSearch(E target) {
		if (array == null || size() == 0) {
			return -1;
		}

		Iterator<? extends E> it = this.iterator();
		while (it.hasNext()) {
			if (it.next().equals(target)) {
				return 1;
			}
		}

		return -1;
	}
	
	public boolean contains( E toFind ) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Can't search for null");
		}

		int searchResult = linearSearch(toFind);

		if (searchResult == 1) {
			return searchResult;
		} else {
			return false;
		}
	}
	
	public E[] toArray( E[] toHold ) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException("Can't use null array.");
		}

		if (toHold.length < size()) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) new Object[size()];
			toHold = newArray;
		}
		
		for (int i = 0; i < size(); i++) {
			toHold[i] = array[i];
		}

		return toHold;
	}
	
	public Object[] toArray() {
		return array;
	}
	
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<E> {
		private int cursor = 0;
		private int lastRet = -1;
		
		public boolean hasNext() {
			return cursor < size();
		}
		
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements");
			}
			lastRet = cursor;
			return get(cursor++);
		}
	}
}
