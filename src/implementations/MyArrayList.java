package implementations;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements Iterator<E>, ListADT<E>
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

		// TODO: Error handling for IndexOutOfBoundsException

		Object[] firstSegment = new Object[index + 1]; // Create array to hold first segment with empty space
		Object[] secondSegment = new Object[array.length - index]; // Create array to hold second segment
		System.arraycopy(array, 0, firstSegment, 0, index); // Copy first segment to new array
		System.arraycopy(array, index, secondSegment, 0, array.length - index); // Copy second segment to new array
		firstSegment[index] = toAdd; // Add element to empty space at the end of first segment
		Object[] newArray = new Object[firstSegment.length + secondSegment.length]; // Create new array large enough for both segments
	    System.arraycopy(firstSegment, 0, newArray, 0, firstSegment.length); // Copy first segment into new array
		System.arraycopy(secondSegment, 0, newArray, firstSegment.length, secondSegment.length); // Copy second segment into new array
		array = newArray; // Replace old array with new array
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

	}
	
	public E get( int index ) throws IndexOutOfBoundsException {

	}
	
	public E remove( int index ) throws IndexOutOfBoundsException {

	}
	
	public E remove( E toRemove ) throws NullPointerException {

	}
	
	public E set( int index, E toChange ) throws NullPointerException, IndexOutOfBoundsException {

	}
	
	public boolean isEmpty() {

	}
	
	public boolean contains( E toFind ) throws NullPointerException {

	}
	
	public E[] toArray( E[] toHold ) throws NullPointerException {

	}
	
	public Object[] toArray() {

	}
	
	public Iterator<E> iterator() {

	}
	
	public boolean hasNext() {

	}
	
	public E next() throws NoSuchElementException {

	}
}
