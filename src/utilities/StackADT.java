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
}

