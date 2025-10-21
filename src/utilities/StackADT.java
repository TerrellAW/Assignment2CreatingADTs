package utilities;

/**
 * Interface for defining methods for manipulating stack datastructures.
 * 
 * TODO: Consider exceptions, more pre-conditions and post-conditions.
 *
 * @author TerrellAW
 * @version 21-10-2025
 */
public interface StackADT<E>
{
	/**
	 * Adds an item to the top of the stack.
	 *
	 * @author TerrellAW
	 * @return True for a successful push, false for a failed push.
	 */
	public Boolean push();

	/**
	 * Removes and returns the item at the top of the stack.
	 *
	 * @author TerrellAW
	 * @return The item removed from the top of the stack.
	 */
	public E pop();

	/**
	 * Returns the item at the top of the stack without removing it.
	 *
	 * @author TerrellAW
	 * @return The item at the top of the stack.
	 */
	public E peek();

	/**
	 * Count the amount of items in the stack and return the number.
	 *
	 * @author TerrellAW
	 * @return Integer containing total amount of items in the stack.
	 */
	public int size();

	/**
	 * Clears the entire stack.
	 *
	 * @author TerrellAW
	 */
	public void clear();

	/**
	 * Check if the stack contains the given value.
	 * Return true if the value exists in the stack, false if it does not.
	 *
	 * @author TerrellAW
	 * @param value is the search term the method will try to find.
	 * @return <code>True</code> if the <code>value</code> exist in the stack, <code>False</code> if it does not.
	 */
	public Boolean contains(E value);
}

