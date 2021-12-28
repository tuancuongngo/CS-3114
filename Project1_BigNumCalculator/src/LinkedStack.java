import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in a chain of nodes.
 * This class will store objects of type <T>
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 * @param <T>
 *            This is the type of object that this class will store
 */
public class LinkedStack<T> {

    private Node<T> topNode; // The top element in the stack
    private int size; // Size of the stack


    /**
     * Default constructor that sets up the original size of the LinkedStack,
     * and initializes a node
     */
    public LinkedStack() {
        clear();
    }


    /**
     * This method shows what’s on the top of the stack, without modifying the
     * stack in any way.
     * 
     * @return topNode’s data (the element at the top of the stack)
     * @throws EmptyStackException
     *             if the stack is empty
     */
    public T peek() {

        // Throws exception if there are no elements in stack
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topNode.getData();
    }


    /**
     * Adds the specified element to the top of the LinkedStack
     * and increase size by 1
     * 
     * @param anEntry
     *            to be added
     * @precondition the entry is a valid entry
     **/
    public void push(T anEntry) {
        Node<T> newNode = new Node<T>(anEntry, topNode);
        topNode = newNode;
        size++;
    }


    /**
     * This method takes away the Node from the top of the stack, and return its
     * data.
     * 
     * @return the data that is retrieved from topNode
     * @throws EmptyStackException
     *             if the stack is empty
     */
    public T pop() {

        // If the stack empty
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        // Variable to store the object that will be removed
        T toReturn = topNode.getData();

        // Remove the object and decrements size
        topNode = topNode.getNext();
        size--;

        return toReturn;
    }


    /**
     * Getter method for the number of objects
     * currently stored in the LinkedStack
     * 
     * @return the number of objects
     *         currently stored in the LinkedStack
     */
    public int size() {
        return size;
    }


    /**
     * Determines if the LinkedStack contains no elements.
     *
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Clear all data in the LinkedStack by setting topNode to null and size to
     * 0
     */
    public void clear() {
        topNode = null;
        size = 0;
    }

}
