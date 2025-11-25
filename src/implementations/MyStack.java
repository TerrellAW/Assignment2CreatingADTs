package implementations;

import exceptions.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Linked-list implementation of the StackADT interface.
 *
 * Stores elements in LIFO order. Each push places a new node at the top.
 *
 * @author
 *      Youssif Al-Halawche
 */
public class MyStack<E> implements StackADT<E> {

    /**
     * Internal node class for stack storage.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        /**
         * Constructs a new node.
         *
         * Precondition: None.
         * Postcondition: A node with the given data and next reference is created.
         */
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    /** Pointer to the top of the stack. */
    private Node<E> top;

    /** Number of elements stored. */
    private int size;

    /**
     * Creates an empty stack.
     *
     * Precondition: None.
     * Postcondition: top = null, size = 0.
     */
    public MyStack() {
        top = null;
        size = 0;
    }

    /**
     * Pushes an element onto the stack.
     *
     * Precondition: item != null.
     * Postcondition: item is added at the top. Size increases.
     *
     * @param item the value to push
     * @throws NullPointerException if item is null
     */
    @Override
    public void push(E item) throws NullPointerException {
        if (item == null)
            throw new NullPointerException("Cannot push null.");

        top = new Node<>(item, top);
        size++;
    }

    /**
     * Removes and returns the top element.
     *
     * Precondition: stack is not empty.
     * Postcondition: top node is removed. Size decreases.
     *
     * @return element removed
     * @throws EmptyStackException if empty
     */
    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Pop from empty stack.");

        E value = top.data;
        top = top.next;
        size--;
        return value;
    }

    /**
     * Returns (but does not remove) the top element.
     *
     * Precondition: stack is not empty.
     * Postcondition: stack unchanged.
     *
     * @return top element
     * @throws EmptyStackException if empty
     */
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Peek from empty stack.");
        return top.data;
    }

    /**
     * Removes all elements.
     *
     * Precondition: None.
     * Postcondition: stack becomes empty.
     */
    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    /**
     * Checks if stack is empty.
     *
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Converts stack to an Object[].
     *
     * Precondition: None.
     * Postcondition: stack unchanged.
     *
     * @return Object[] of elements from top to bottom
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];

        Node<E> curr = top;
        int i = 0;

        while (curr != null) {
            arr[i++] = curr.data;
            curr = curr.next;
        }

        return arr;
    }

    /**
     * Converts stack to an E[].
     *
     * Precondition: holder != null.
     * Postcondition: stack unchanged.
     *
     * @param holder destination array
     * @return holder or new array if holder too small
     * @throws NullPointerException if holder is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null)
            throw new NullPointerException("Holder cannot be null.");

        if (holder.length < size)
            holder = (E[]) java.lang.reflect.Array.newInstance(
                    holder.getClass().getComponentType(), size);

        Node<E> curr = top;
        int i = 0;

        while (curr != null) {
            holder[i++] = curr.data;
            curr = curr.next;
        }

        if (holder.length > size)
            holder[size] = null;

        return holder;
    }

    /**
     * Checks if the stack contains a value.
     *
     * Precondition: toFind != null.
     * Postcondition: stack unchanged.
     *
     * @param toFind value to check
     * @return true if found
     * @throws NullPointerException if toFind is null
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null)
            throw new NullPointerException("Cannot search for null.");

        Node<E> curr = top;
        while (curr != null) {
            if (curr.data.equals(toFind))
                return true;
            curr = curr.next;
        }
        return false;
    }

    /**
     * Searches for a value (1-based position from the top).
     *
     * Precondition: None.
     * Postcondition: stack unchanged.
     *
     * @param toFind value to find
     * @return 1-based index or -1 if not found
     */
    @Override
    public int search(E toFind) {
        if (toFind == null)
            return -1;

        Node<E> curr = top;
        int pos = 1;

        while (curr != null) {
            if (curr.data.equals(toFind))
                return pos;

            curr = curr.next;
            pos++;
        }

        return -1;
    }

    /**
     * Returns an iterator for the stack.
     *
     * Precondition: None.
     * Postcondition: stack unchanged.
     *
     * @return an iterator from top → bottom
     */
    @Override
    public utilities.Iterator<E> iterator() {
        return new StackIterator();
    }

    /**
     * Custom iterator implementation using SAIT's utilities.Iterator.
     */
    private class StackIterator implements utilities.Iterator<E> {

        private Node<E> cursor = top;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (cursor == null)
                throw new NoSuchElementException("No more elements.");

            E value = cursor.data;
            cursor = cursor.next;
            return value;
        }
    }

    /**
     * Compares two stacks for equality.
     *
     * Precondition: that != null.
     * Postcondition: stacks unchanged.
     *
     * @param that another stack
     * @return true if same size and same elements in same order
     */
    @Override
    public boolean equals(StackADT<E> that) {
        if (that == null)
            return false;

        if (this.size != that.size())
            return false;

        utilities.Iterator<E> it1 = this.iterator();
        utilities.Iterator<E> it2 = that.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next()))
                return false;
        }

        return true;
    }

    /**
     * Returns size of stack.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns false—stack is unbounded.
     */
    @Override
    public boolean stackOverflow() {
        return false;
    }
}
