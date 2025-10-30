package utilities;

/**
 * Interface for defining methods for manipulating stack datastructures.
 * 
 * TODO: Consider exceptions, more pre-conditions and post-conditions.
 *
 * @author TerrellAW
 * @author Youssif Al-Halawche
 * @version 21-10-2025
 */
public interface StackADT<E>
{
	/**
	 * Adds an item to the top of the stack.
	 *
	 * @author TerrellAW
     * @author Youssif Al-Halawche
     *
     * @param item The element to push onto the stack.
     * <p><b>Preconditions:</b></p>
     * <ul>
     *   <li>The item is not {@code null}.</li>
     *   <li>The stack is not full.</li>
     * </ul>
     *
     * <p><b>Postconditions:</b></p>
     * <ul>
     *   <li>The stack’s size increases by one.</li>
     *   <li>The top of the stack becomes the pushed item.</li>
     * </ul>
	 * @return True for a successful push, false for a failed push.
	 */
	public Boolean push(E item);

	/**
	 * Removes and returns the item at the top of the stack.
	 *
     * <p><b>Preconditions:</b></p>
     * <ul>
     *   <li>The stack is not{@code empty}.</li>
     * </ul>
     *
     * <p><b>Postconditions:</b></p>
     * <ul>
     *   <li>The top element is eliminated</li>
     *   <li>The stack's size decreases by one/li>
     *   <li>The returned value is the element that was previously at the top</li>
     * </ul>
     * @author TerrellAW
     * @author Youssif Al-Halawche
	 * @return The item removed from the top of the stack.
	 */
	public E pop();

	/**
	 * Returns the item at the top of the stack without removing it.
     * <p><b>Preconditions:</b></p>
     * <ul>
     *   <li>The stack is not {@code empty}.</li>
     * </ul>
     *
     * <p><b>Postconditions:</b></p>
     * <ul>
     *   <li>The stack remains unchanged</li>
     *   <li>The returned value equals the current top element</li>
     * </ul>
	 * @author TerrellAW
     * @author Youssif Al-Halawche
	 * @return The item at the top of the stack.
	 */
	public E peek();

	/**
	 * Count the amount of items in the stack and return the number.
     * <p><b>Preconditions:</b></p>
     * <ul>
     *   <li>None, it can always be called without preassumptions</li>
     * </ul>
     *
     * <p><b>Postconditions:</b></p>
     * <ul>
     *   <li>Stack remains unchanged</li>
     * </ul>
	 * @author TerrellAW
     * @author Youssif Al-Halawche
	 * @return Integer containing total amount of items in the stack.
	 */
	public int size();

	/**
	 * Clears the entire stack.
     * <p><b>Preconditions:</b></p>
     * <ul>
     *   <li>None</li>
     * </ul>
     *
     * <p><b>Postconditions:</b></p>
     * <ul>
     *   <li>Stack becomes empty</li>
     *   <li>size() returns 0</li>
     * </ul>
	 * @author TerrellAW
     * @author Youssif Al-Halawche
	 */
	public void clear();

	/**
	 * Check if the stack contains the given value.
	 * Return true if the value exists in the stack, false if it does not.
     * <p><b>Preconditions:</b></p>
     * <ul>
     *   <li>{@code value} is not {@code null}</li>
     * </ul>
     *
     * <p><b>Postconditions:</b></p>
     * <ul>
     *   <li>Returns {@code true} if the value exists in the stack otherwise {@code false}</li>
     *   <li>stack remains unchanged</li>
     * </ul>
	 * @author TerrellAW
     * @author Youssif Al-Halawche
	 * @param value is the search term the method will try to find.
	 * @return <code>True</code> if the <code>value</code> exist in the stack, <code>False</code> if it does not.
	 */
	public Boolean contains(E value);
}

