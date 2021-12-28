import java.util.NoSuchElementException;

/**
 * This is a basic implementation of a linked list
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * 
 * @version 07/16/2020
 * @param <T>
 *            This is the type of object that this class will store
 */
public class LinkedList<T> {

    private Node<T> head; // Empty head Node
    private Node<T> tail; // Empty tail Node
    private Node<T> curr; // Current Node
    private int size; // Size of linked list


    /**
     * Default constructor that initializes a List
     */
    public LinkedList() {
        tail = new Node<T>(null); // Initializes tail Node
        curr = tail; // Initializes curr Node to point to tail
        head = new Node<T>(tail);
        size = 0;
    }


    /**
     * Add element to the end of the list
     * 
     * @param element
     *            the element to be added to list
     * @return True if successfully added to list
     */
    public boolean add(T element) {

        tail.setNext(new Node<T>(null)); // New null Tail node
        tail.setData(element); // old tail node is now storing data
        tail = tail.getNext(); // Move to new tail node
        size++; // Increase size
        return true;

    }


    /**
     * Remove element at current position
     * 
     * @return the removed element to caller
     * @throws NoSuchElementException
     *             if no element left to be removed
     */
    public T remove() throws NoSuchElementException {
        // Nothing to remove
        if (curr == tail) {
            throw new NoSuchElementException("No element left to be removed");
        }

        T removed = curr.getData(); // Get the element to be removed
        curr.setData(curr.getNext().getData()); // Update curr. curr is now the
                                                // next element

        // If the removed curr is the last element in list, set tail to point at
        // curr.
        if (curr.getNext() == tail) {
            tail = curr;
        }

        curr.setNext(curr.getNext().getNext()); // Skip over the duplicated Node
        size--; // Decrease size
        return removed; // Return removed element to caller
    }


    /**
     * Move curr to the first element in the list
     */
    public void moveToStart() {
        curr = head.getNext();
    }


    /**
     * Move curr pointer one element ahead to the right
     */
    public void next() throws NoSuchElementException {
        if (curr == tail) {
            throw new NoSuchElementException("Reached tail of List.");
        }
        curr = curr.getNext();
    }


    /**
     * Get data in Node at curr position in List
     * 
     * @return data at curr position in List, or throw exception if there's no
     *         data to extract
     */
    public T getCurrData() {
        // Nothing to remove
        if (curr == tail) {
            throw new NoSuchElementException("No data to extract");
        }
        return curr.getData();
    }


    /**
     * Getter for number of elements in list
     * 
     * @return number of elements in list
     */
    public int getSize() {
        return size;
    }


    /**
     * Clears all elements in linked list
     */
    public void clear() {
        tail = new Node<T>(null); // Initializes tail Node
        curr = tail; // Initializes curr Node
        head = new Node<T>(tail);
        size = 0;
    }


    /**
     * Returns a string representation of the list. If a list contains A, B, and
     * C, the following should be returned "ABC" (Without the quotations)
     *
     * @return a string representing the list, or an empty string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // Iterate through the list, getting each element
        if (size != 0) {
            Node<T> currNode = head.getNext();
            while (currNode != tail) {
                T element = currNode.getData();
                builder.append(element.toString());

                currNode = currNode.getNext();
            }
        }
        return builder.toString();
    }
}
