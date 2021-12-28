/**
 * This class is an implementation for DoublyLinkedList.
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 * @param <T>
 *            The type of object the class will store
 */
public class DoublyLinkedList<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;
    

    /**
     * Creates a new constructor.
     */
    public DoublyLinkedList() {
        clear();     
    }
    

    /**
     * Check if the list is empty
     * 
     * @return Returns true if the list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    

    /**
     * Gets the number of elements in the list.
     * 
     * @return the size of list
     */
    public int getSize() {
        return size;
    }
    

    /**
     * REmoves all of the elements from the list.
     */
    public void clear() {
        head = new DoublyLinkedList.Node<T>(null);
        tail = new DoublyLinkedList.Node<T>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;  
    }


    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    public int lastIndexOf(T obj) {
        /*
         * We should go from the end of the list as then we an stop once we find
         * the first one
         */
        Node<T> current = tail.previous();
        for (int i = getSize() - 1; i >= 0; i--) {
            if (current.getData().equals(obj)) {
                return i;
            }
            current = current.previous();
        }
        return -1; // if we do not find it
    }
    
    
    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
    public boolean contains(T obj) {
        return lastIndexOf(obj) != -1;
    }
    

    /**
     * gets the node at that index
     * 
     * @param index
     * @return node at index
     */
    private Node<T> getNodeAtIndex(int index) {
        if (index < 0 || getSize() <= index) {
            throw new IndexOutOfBoundsException("No element exists at "
                + index);
        }
        Node<T> current = head.next(); // as we have a sentinel node
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }
    

    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if there no node at the given index
     */
    public T getData(int index) {
        return getNodeAtIndex(index).getData();
    }
    

    /**
     * Adds the object to the position in the list
     *
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    public void add(int index, T obj) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (obj == null) {
            throw new IllegalArgumentException("Cannot add null "
                + "objects to a list");
        }

        Node<T> nodeAfter;
        if (index == size) {
            nodeAfter = tail;
        }
        else {
            nodeAfter = getNodeAtIndex(index);
        }

        Node<T> addition = new Node<T>(obj);
        addition.setPrevious(nodeAfter.previous());
        addition.setNext(nodeAfter);
        nodeAfter.previous().setNext(addition);
        nodeAfter.setPrevious(addition);
        size++;
    }
    

    /**
     * Adds a element to the end of the list
     *
     * @param newEntry
     *            the element to add to the end
     */
    public void add(T newEntry) {
        add(getSize(), newEntry);
    }
    

    /**
     * Removes the element at the specified index from the list
     *
     * @param index
     *            where the object is located
     * @throws IndexOutOfBoundsException
     *             if there is not an element at the index
     * @return true if successful
     */
    public boolean remove(int index) {
        Node<T> nodeToBeRemoved = getNodeAtIndex(index);
        nodeToBeRemoved.previous().setNext(nodeToBeRemoved.next());
        nodeToBeRemoved.next().setPrevious(nodeToBeRemoved.previous());
        size--;
        return true;
    }


    /**
     * Removes the first object in the list that .equals(obj)
     *
     * @param obj
     *            the object to remove
     * @return true if the object was found and removed
     */

    public boolean remove(T obj) {
        Node<T> current = head.next();
        while (!current.equals(tail)) {
            if (current.getData().equals(obj)) {
                current.previous().setNext(current.next());
                current.next().setPrevious(current.previous());
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }
    

    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            Node<T> currNode = head.next();
            while (currNode != tail) {
                T element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next != tail) {
                    builder.append(", ");
                }
                currNode = currNode.next();
            }
        }

        builder.append("}");
        return builder.toString();
    }



    /**
     * This represents a node in a doubly linked list. This node stores data, a
     * pointer to the node before it in the list, and a pointer to the node
     * after it in the list
     *
     * @param <E>
     *            This is the type of object that this class will store
     * @author Mark Wiggans (mmw125)
     * @version 4/14/2015
     */
    private static class Node<T> {
        private Node<T> next;
        private Node<T> previous;
        private T data;
        /**
         * Creates a new node with the given data
         *
         * @param data
         *            the data to put inside the node
         */
        public Node(T data) {
            this.data = data;
        }


        /**
         * Sets the node after this node
         *
         * @param n
         *            the node after this one
         */
        public void setNext(Node<T> n) {
            next = n;
        }


        /**
         * Sets the node before this one
         *
         * @param n
         *            the node before this one
         */
        public void setPrevious(Node<T> n) {
            previous = n;
        }


        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<T> next() {
            return next;
        }


        /**
         * Gets the node before this one
         *
         * @return the node before this one
         */
        public Node<T> previous() {
            return previous;
        }


        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public T getData() {
            return data;
        }
    }

}