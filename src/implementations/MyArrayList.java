package implementations;

import java.util.NoSuchElementException; // Iterator interface contract specifies this

import utilities.Iterator;
import utilities.ListADT;

/**
 * Dynamically allocated array data structure.
 * Stores elements in a list that uses a primitive array as the underlying data structure.
 *
 * @author TerrellAW
 * @version 19-11-2025
 * @param <E> The type of elements held in this list.
 */
public class MyArrayList<E> implements ListADT<E>
{
	private Object[] array;

	/**
	 * Constructor method.
	 *
	 * Precondition: None.
	 *
	 * Postcondition: Empty array is initialized when MyArrayList object is instantiated.
	 *
	 * @return A new MyArrayList object with initialized array.
	 * @author TerrellAW
	 */
	public MyArrayList() {
		this.array = new Object[0];
	}

	/**
	 * Returns the number of elements in the list.
	 *
	 * Precondition: None.
	 *
	 * Postcondition: The current size of the list is returned.
	 *
	 * @return The current size of the list.
	 * @author TerrellAW
	 */
	public int size() {
		int size = array.length;
		return size;
	}
	
	/**
	 * Clears all elements from the list by re-initializing it to an empty state.
	 *
	 * Precondition: None.
	 *
	 * Postcondition: The list is empty.
	 *
	 * @author TerrellAW
	 */
	public void clear() {
		this.array = new Object[0];
	}
	
	/**
	 * Adds a new element at the specified index in the list.
	 *
	 * Precondition: Element to add is not null and index is within bounds.
	 *
	 * Postcondition: The element is added at the specified index and the size of the list increases by one.
	 *
	 * @param index The position where the new element will be added.
	 * @param toAdd The element to add to the list.
	 * @return True if the addition is successful, otherwise throws exceptions.
	 * @throws NullPointerException if the element to add is null.
	 * @throws IndexOutOfBoundsException if the specified index is beyond the size of this list.
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
	 * Adds a new element to the end of the list.
	 *
	 * Precondition: Element to add is not null.
	 *
	 * Postcondition: The element is added to the end of the list and the size of the list increases by one.
	 *
	 * @param toAdd The element to add to the list.
	 * @return True if the addition is successful, otherwise throws an exception.
	 * @throws NullPointerException if the element to add is null.
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
	
	/**
	 * Adds all elements from another collection to this list.
	 *
	 * Precondition: The collection to add is not null.
	 *
	 * Postcondition: All elements from the specified collection are added to this list and the size of this list increases as needed.
	 *
	 * @param toAdd The collection of elements to add.
	 * @return True if the addition is successful, otherwise throws an exception.
	 * @throws NullPointerException if the list to add is null.
	 * @author TerrellAW
	 */
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
	
	/**
	 * Retrieves the element at the specified index in the list.
	 *
	 * Precondition: The index is within the bounds of this list.
	 *
	 * Postcondition: The element at the specified index is returned.
	 *
	 * @param index The position of the element to retrieve.
	 * @return The element at the specified index.
	 * @throws IndexOutOfBoundsException if the specified index is beyond the size of this list.
	 * @author TerrellAW
	 */
	public E get( int index ) throws IndexOutOfBoundsException {
		if (index >= this.size()) {
			throw new IndexOutOfBoundsException("Index (" + index + ") is out of bounds (" + this.size() + ")");
		}
		
		@SuppressWarnings("unchecked")
		E returnValue = (E)array[index];
		return returnValue;
	}
	
	/**
	 * Removes the element at the specified index from the list.
	 *
	 * Precondition: The index is within the bounds of this list.
	 *
	 * Postcondition: The element at the specified index is removed and the size of the list decreases by one.
	 *
	 * @param index The position of the element to remove.
	 * @return The removed element.
	 * @throws IndexOutOfBoundsException if the specified index is beyond the size of this list.
	 * @author TerrellAW
	 */
	public E remove( int index ) throws IndexOutOfBoundsException { 
		if (index < 0 || index > size()) { // Throw error if index is out of bounds
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}
		
		int newSize = array.length - 1;

		if (newSize < 0) {
			throw new IndexOutOfBoundsException("Index" + index + ", Size: " + size());
		}
		
		@SuppressWarnings("unchecked")
		E element = (E)array[index]; // Store element that will be removed

		if (array.length == 1 && index == 0) {
			this.clear();
			return element;
		}

		if (array.length == 1 && index == 0) {
			this.clear();
			return element; // Return removed element
		} else if (index == 0) {
			Object[] newArray = new Object[newSize]; // Create smaller array

			System.arraycopy(array, 1, newArray, 0, newSize); // Copy all but first element to new array
			array = newArray;
			return element;
		} else if (index == newSize) {
			Object[] newArray = new Object[newSize]; // Create smaller array

			System.arraycopy(array, 0, newArray, 0, newSize); // Copy all but last element to new array
			array = newArray;
			return element; // Return removed element
		}

		Object[] firstSegment = new Object[index]; // Create array to hold first segment
		Object[] secondSegment = new Object[newSize - index]; // Create array to hold second segment
		
		System.arraycopy(array, 0, firstSegment, 0, firstSegment.length); // Copy segment from before index
		System.arraycopy(array, index + 1, secondSegment, 0, secondSegment.length); // Copy segment from after index
		
		array = new Object[firstSegment.length + secondSegment.length]; // Create new array large enough for both segments
	    System.arraycopy(firstSegment, 0, array, 0, firstSegment.length); // Copy first segment into new array
		System.arraycopy(secondSegment, 0, array, firstSegment.length, secondSegment.length); // Copy second segment into new array

		return element; // Return removed element
	}
	
	/**
	 * Removes the specified element from the list.
	 *
	 * Precondition: The element to remove is not null.
	 *
	 * Postcondition: The specified element is removed from the list and the size of the list decreases by one.
	 *
	 * @param toRemove The element to remove from the list.
	 * @return The removed element, or null if the element was not found.
	 * @throws NullPointerException if the element to remove is null.
	 * @author TerrellAW
	 */
	public E remove( E toRemove ) throws NullPointerException {
		if (toRemove == null) { // Throw error if element is null
			throw new NullPointerException("Element to remove must exist");
		}

		int index = linearSearch(toRemove);
		int newSize = array.length - 1;

		if (array.length == 0 || index == -1) {
			return null;
		}

		@SuppressWarnings("unchecked")
		E element = (E)array[index]; // Store element that will be removed
		
		if (array.length == 1 && index == 0) {
			this.clear();
			return element; // Return removed element
		} else if (index == 0) {
			Object[] newArray = new Object[newSize]; // Create smaller array

			System.arraycopy(array, 1, newArray, 0, newSize); // Copy all but first element to new array
			array = newArray;
			return element;
		} else if (index == newSize) {
			Object[] newArray = new Object[newSize]; // Create smaller array

			System.arraycopy(array, 0, newArray, 0, newSize); // Copy all but last element to new array
			array = newArray;
			return element; // Return removed element
		}

		Object[] firstSegment = new Object[index]; // Create array to hold first segment
		Object[] secondSegment = new Object[newSize - index]; // Create array to hold second segment
		
		System.arraycopy(array, 0, firstSegment, 0, firstSegment.length); // Copy segment from before index
		System.arraycopy(array, index + 1, secondSegment, 0, secondSegment.length); // Copy segment from after index
		
		array = new Object[firstSegment.length + secondSegment.length]; // Create new array large enough for both segments
	    System.arraycopy(firstSegment, 0, array, 0, firstSegment.length); // Copy first segment into new array
		System.arraycopy(secondSegment, 0, array, firstSegment.length, secondSegment.length); // Copy second segment into new array

		return element; // Return removed element
	}
	
	/**
	 * Replaces the element at the specified index with a new element.
	 *
	 * Precondition: The new value is not null and the index is within bounds.
	 *
	 * Postcondition: The element at the specified index is replaced by the new element and the original element is returned.
	 *
	 * @param index The position of the element to replace.
	 * @param toChange The new element to set.
	 * @return The replaced element.
	 * @throws NullPointerException if the new value is null.
	 * @throws IndexOutOfBoundsException if the index is beyond the size of this list.
	 * @author TerrellAW
	 */
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
	
	/**
	 * Checks if the list is empty.
	 *
	 * Precondition: None.
	 *
	 * Postcondition: True is returned if the list has no elements, otherwise false.
	 *
	 * @return True if list is empty, false if not.
	 * @author TerrellAW
	 */
	public boolean isEmpty() {
		if (this.size() != 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Searches for a target element in the list using linear search algorithm.
	 *
	 * Precondition: The array is not null and has elements.
	 *
	 * Postcondition: Index is returned if the element is found, otherwise negative one.
	 *
	 * @param target The element to search for.
	 * @return Index if the element is found, otherwise -1.
	 * @author TerrellAW
	 */
	public int linearSearch( E target ) { 
		if (array == null || size() == 0) {
			return -1;
		}

		int index = 0;

		Iterator<? extends E> it = this.iterator();
		while (it.hasNext()) {
			if (it.next().equals(target)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	/**
	 * Checks if the list contains a specific element.
	 *
	 * Precondition: The element to find is not null.
	 *
	 * Postcondition: True is returned if the element is found, otherwise false.
	 *
	 * @param toFind The element to search for in the list.
	 * @return True if the element is found, otherwise false.
	 * @throws NullPointerException if the element to find is null.
	 * @author TerrellAW
	 */
	public boolean contains( E toFind ) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Can't search for null");
		}

		int result = linearSearch(toFind);

		if (result == -1) {
			return false;
		}
		return true;
	}
	
	/**
	 * Converts the list into an array.
	 *
	 * Precondition: The provided array is not null.
	 *
	 * Postcondition: An array containing all the elements of the list is returned. Array will be enlarged as needed.
	 *
	 * @param toHold The array to hold the elements. If too small, a new one will be created.
	 * @return An array containing all the elements of the list.
	 * @throws NullPointerException if the provided array is null.
	 * @author TerrellAW
	 */
	public E[] toArray( E[] toHold ) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException("Can't use null array.");
		}

		if (toHold.length < size()) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) java.lang.reflect.Array.newInstance(
				toHold.getClass().getComponentType(),
				size()
			);
			toHold = newArray;
		}
		
		for (int i = 0; i < size(); i++) {
			toHold[i] = this.get(i);
		}

		return toHold;
	}
	
	/**
	 * Returns the internal array representation of the list.
	 *
	 * Precondition: None.
	 *
	 * Postcondition: The internal array is returned.
	 *
	 * @return The internal array representation of the list.
	 * @author TerrellAW
	 */
	public Object[] toArray() {
		return array;
	}
	
	/**
	 * Returns an iterator.
	 *
	 * Precondition: None.
	 *
	 * Postcondition: An iterator is returned for traversing the list.
	 *
	 * @return An iterator for the list.
	 * @author TerrellAW
	 */
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	
	/**
	 * Private subclass for iterating through <code>MyArrayList</code>.
	 *
	 * @author TerrellAW
	 * @version 19-11-2025
	 */
	private class ArrayIterator implements Iterator<E> {
		private int cursor = 0;
		
		/**
		 * Checks if there are more elements after the cursor.
		 *
		 * Precondition: None.
		 *
		 * Postcondition: True is returned if there are more elements, otherwise false.
		 *
		 * @return True if there are more elements, otherwise false.
		 * @author TerrellAW
		 */
		public boolean hasNext() {
			return cursor < size();
		}
		
		/**
		 * Returns the next element in the iteration.
		 *
		 * Precondition: There is a next element.
		 *
		 * Postcondition: The next element is returned and the cursor advances.
		 *
		 * @return The next element in the iteration.
		 * @throws NoSuchElementException if there is no next element.
		 * @author TerrellAW
		 */
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements");
			}
			return get(cursor++);
		}
	}
}
