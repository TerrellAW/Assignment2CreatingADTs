package utilities;

import java.util.Iterator;
import java.util.NoSuchElementException;

import utilities.StackADT;

/** 
 * Implementation of The Stack abstract data type 
 * 
 * Stack stores elements in a last-in, first-out (LIFO) structure using dynamically 
 * allocated nodes. the top of hte stack is 
 * referenced by a pointer, and each push or pop operation occurs in constant time
 * 
 * @author Youssif Al-Halawche
 * 
 * **/
public class MyStack<E> implements StackADT<E> {


    /** 
     * Node class, used to build the linked structure of the stack
     * 
     * 
     * @author Youssif Al-Halawhce
     * **/
    private static class Node<E> {

        /** Constructs a new Node with data and reference to the next node
         * 
         * 
         * Precondition: None
         * 
         * Postcondition: A node is created containing the provided data and reference
         * 
         * @param data the element to store
         * @param next Reference to the next node
         * **/
        E data;
        Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }


    /** Pointer to the top element (last inserted) of the stack**/
    private Node<E> top;
    /** Number of elements currently stored int eh stack **/
    private int size;

    /**
     * Constructor
     *
     * Precondition: None
     *
     * Postcondition: An empty stack is created with zero size
     */
    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * adds an element to the top of the stack
     *
     * Precondition: The element is added to the top of the stack
     * Postcondition: Stack size increases by one
     *
     * @param item element to be inserted
     * @return True if insertion was a success, False otherwise
     */
    @Override
    public Boolean push(E item) {
        if (item == null) return false;

        top = new Node<>(item, top);
        size++;
        return true;
    }

    /**
     * Removes and returns the top element of the stack
     *
     * Precondition: Stack is not empty
     * Postcondition: The top element is removed
     * Postcondition: The stack size is reduced by one
     * @return
     */
    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");

        E value = top.data;
        top = top.next;
        size--;
        return value;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * Precondition: Stack is not empty.
     *
     * Postcondition: Stack remains unchanged.
     *
     * @return The element at the top of the stack.
     * @throws NoSuchElementException if the stack is empty.
     */
    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        return top.data;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * Precondition: None.
     *
     * Postcondition: Stack remains unchanged.
     *
     * @return The size of the stack.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the stack.
     *
     * Precondition: None.
     *
     * Postcondition: Stack becomes empty and size becomes zero.
     */
    @Override
    public void clear() {
        top = null;
        size = 0;
    }
    /**
     * Checks whether the stack contains a given value.
     *
     * Precondition: {@code value} is not null.
     *
     * Postcondition: Stack remains unchanged.
     *
     * @param value The value to search for.
     * @return True if found, otherwise false.
     */
    @Override
    public Boolean contains(E value) {
        if (value == null) return false;

        Node<E> current = top;
        while (current != null) {
            if (current.data.equals(value)) return true;
            current = current.next;
        }
        return false;
    }
    /**
     * Checks whether the stack is empty.
     *
     * Precondition: None.
     *
     * Postcondition: Stack remains unchanged.
     *
     * @return True if stack is empty, otherwise false.
     */
    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    /**
     * Compares this stack to another stack for equality.
     * Two stacks are equal if they contain the same elements in the same order.
     *
     * Precondition: {@code that} is not null.
     *
     * Postcondition: Both stacks remain unchanged.
     *
     * @param that The stack to compare.
     * @return True if both stacks match element-wise, otherwise false.
     */
    @Override
    public Boolean equals(StackADT<E> that) {
        if (that == null) return false;
        if (this.size() != that.size()) return false;

        // Iterate both top → bottom
        Iterator<E> it1 = this.iterator()[0];
        Iterator<E> it2 = that.iterator()[0];

        while (it1.hasNext() && it2.hasNext()) {
            E a = it1.next();
            E b = it2.next();
            if (!a.equals(b)) return false;
        }

        return true;
    }


    /**
     * Converts this stack into an array containing all elements from top to bottom.
     *
     * Precondition: None.
     *
     * Postcondition: Stack remains unchanged.
     *
     * @return An array containing all stack elements.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] arr = (E[]) new Object[size];
        Node<E> current = top;
        int i = 0;

        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }

        return arr;
    }
    /**
     * Returns an array containing a single iterator for this stack.
     * The iterator traverses from top to bottom.
     *
     * Precondition: None.
     *
     * Postcondition: Stack remains unchanged.
     *
     * @return An array holding one iterator.
     */
    @Override
    public Iterator[] iterator() {
        return new Iterator[] { new StackIterator() };
    }

    /**
     * Iterator class for traversing the stack from top to bottom.
     *
     * @author Youssif Al-Halawche
     * @version 21-10-2025
     */
    private class StackIterator implements Iterator<E> {
        /** cursor pointing to the current node */
        private Node<E> cursor = top;

        /**
         * Checks whether there is another element after the cursor
         *
         * Precondition: None
         *
         * Postcondition: Stack remains unchanged
         *
         * @return True if another element exists, otherwise False
         */
        @Override
        public boolean hasNext() {
            return cursor != null;
        }


        /**
         * Returns the next element in the stack and moves the cursor.
         *
         * Precondition: There must be a next element.
         *
         * Postcondition: Cursor advances to the next node.
         *
         * @return The next element in the iteration.
         * @throws NoSuchElementException if no elements remain.
         */
        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            E value = cursor.data;
            cursor = cursor.next;
            return value;
        }
    }
    /**
     * Searches for a specific item in the stack.
     *
     * Precondition: {@code item} is not null.
     *
     * Postcondition: Stack remains unchanged.
     *
     * @param item The element to search for.
     * @return True if found, otherwise false.
     */
    @Override
    public Boolean search(E item) {
        return contains(item);
    }
}
