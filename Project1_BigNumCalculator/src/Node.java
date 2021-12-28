/**
 * Class for singly linked Nodes, which will be used in LinkedStack<T> and
 * LinkedList<T> to store and retrieve data
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 * @param <T>
 *            This is the type of object that this class will store
 */
public class Node<T> {

    private Node<T> next; // Link to next Node
    private T data; // Data of the current Node


    /**
     * Default constructor for a Node, sets its next node pointer
     * 
     * @param next
     *            This Node's next Node
     * 
     */
    public Node(Node<T> next) {
        this(null, next);
    }


    /**
     * Constructor that sets Node data and links to next Node
     * 
     * @param data
     *            data to put inside this Node
     * @param next
     *            This Node's next Node
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }


    /**
     * Getter method for the next Node
     * 
     * @return next Node holding a T object
     */
    public Node<T> getNext() {
        return next;
    }


    /**
     * Setter for data in specified Node
     * 
     * @param data
     *            the new Data for the Node
     */
    public void setData(T data) {
        this.data = data;
    }


    /**
     * Getter for data in the specified Node
     * 
     * @return the T object inside of this Node
     */
    public T getData() {
        return data;
    }


    /**
     * Setter for pointer tonext Node
     * 
     * @param newNode
     *            the new next node
     */
    public void setNext(Node<T> newNode) {
        next = newNode;
    }
}
