package utilities;

/**
 * Interface for defining methods for manipulating stack datastructures.
 * * TODO: Consider exceptions, more pre-conditions and post-conditions.
 *
 * @author TerrellAW
 * @author Youssif Al-Halawche
 * @version 21-10-2025
 * @param <E> The type of elements held in this stack.
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
     *
     * Precondition: The item is not {@code null}.
     * Precondition: The stack is not full.
     * Postcondition: The stack’s size increases by one.
     * Postcondition: The top of the stack becomes the pushed item.
     *
     * @return True for a successful push, false for a failed push.
     */
    public Boolean push(E item);

    /**
     * Removes and returns the item at the top of the stack.
     *
     * @author TerrellAW
     * @author Youssif Al-Halawche
     *
     * Precondition: The stack is not {@code empty}.
     *
     * Postcondition: The top element is eliminated.
     * Postcondition: The stack's size decreases by one.
     * Postcondition: The returned value is the element that was previously at the top.
     *
     * @return The item removed from the top of the stack.
     */
    public E pop();

    /**
     * Returns the item at the top of the stack without removing it.
     *
     * @author TerrellAW
     * @author Youssif Al-Halawche
     * Precondition: The stack is not {@code empty}.
     *
     * Postcondition: The stack remains unchanged.
     * Postcondition: The returned value equals the current top element.
     *
     * @return The item at the top of the stack.
     */
    public E peek();

    /**
     * Counts the amount of items in the stack and returns the number.
     *
     * @author TerrellAW
     * @author Youssif Al-Halawche
     *
     * Precondition: None, it can always be called without preassumptions.
     *
     * Postcondition: Stack remains unchanged.
     * @return Integer containing total amount of items in the stack.
     */
    public int size();

    /**
     * Clears the entire stack.
     *
     * @author TerrellAW
     * @author Youssif Al-Halawche
     * Precondition: None.
     *
     * Postcondition: Stack becomes empty.
     * Postcondition: size() returns 0.
     */
    public void clear();

    /**
     * Check if the stack contains the given value.
     *
     * @author TerrellAW
     * @author Youssif Al-Halawche
     *
     * @param value The search term the method will try to find.
     *
     * Precondition: {@code value} is not {@code null}.
     *
     * Postcondition: Returns {@code true} if the value exists in the stack otherwise {@code false}.
     * Postcondition: Stack remains unchanged.
     * @return {@code True} if the {@code value} exists in the stack, {@code False} if it does not.
     */
    public Boolean contains(E value);

    /**
     * Checks if stack is empty
     *
     * @author Youssif Al-Halawche
     *
     * precondition: no precondition
     *
     * postcondition: the stack is not changed and left as it
     *
     *
     * @return true if empty, false is not empty
     *
     */
    public Boolean isEmpty();

    /**
     * Compares this stack to another object to check if it has the same
     * values in the same order
     *
     * @author Youssif Al-Halawche
     *
     * precondition: a stack object must be provided for comparison
     *
     * postcondition: the stack remains unchanged
     *
     * @return {@code true} if {@code obj} has the same elements in the same order
     * as StackADT, otherwise returns {@code false}
     */
     public Boolean equals(StackADT<E> that);

    /**
     * generated and returns an array of all the elements in the StackADT in Ascending
     * order
     * @author Youssif Al-Halawche
     *
     * precondition: No precondition
     *
     * postcondition: stack remains unchanged
     *
     * @return array containing all elements of this stack
     */
     public E[] toArray();

    /**
     * returns an iterator object over all items in this Stack
     *
     * @author Youssif Al-Halawche
     *
     * precondition: No precondition
     *
     * postcondition: Stack remains unchanged
     *
     * @return an {@code Iterator<E>} that allows for iterating over elements of the stack from top to bottom
     */
    public Iterator[] iterator();

    /**
     * Searches for a specific element in the stack
     *
     * @author Youssif Al-Halawche
     * 
     * precondition: {@code item} is not {@code null}
     *
     * postcondition: stack remains unchanged
     *
     * @return {@code true} if the item is found,otherwise {@code false}
     */
    public Boolean search(E item);
}