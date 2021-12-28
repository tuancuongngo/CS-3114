/**
 * Class that represents a Empty Node that contains no data
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public class EmptyNode extends LeafNode {

    // Mainly used for testing to make sure right kind of Node is returned
    private String type = "E"; // Stores the Type of the Node
                               // "I" for Internal node
                               // "E" for Empty node
                               // "" (Empty String) for Leaf Node


    /**
     * Default constructor that does nothing because this Node is not supposed
     * to contain any data except for its type
     */
    public EmptyNode() {
        // Intentionally left blank
    }


    /**
     * Getter for Type of Node, which is always going to return "E" for Empty
     * 
     * @return "E" for Empty Node
     */
    public String getType() {
        return type;
    }

}
