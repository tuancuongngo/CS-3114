/**
 * Class the represents the LeafNode of the BinTree
 * Stores data for a City object
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public class LeafNode implements BinNode {

    // Mainly used for testing to make sure right kind of Node is returned
    private String type; // Stores the Type of the Node
                         // "I" for Internal node
                         // "E" for Empty node
                         // "" (Empty String) for Leaf Node

    private City city; // City object the Node stores

    /**
     * The flyweight empty node that every empty node pointer will point to
     */
    public static final LeafNode EMP = new EmptyNode();


    /**
     * Default constructor, doesn't store a city
     */
    public LeafNode() {
        this(null);
    }


    /**
     * Constructor that takes in a city object that the node will store
     * 
     * @param city
     *            the City Object of the Node
     */
    public LeafNode(City city) {
        this.city = city;
        type = "";
    }


    /**
     * Constructor that creates a LeafNode and initializes the city it will
     * contain
     * 
     * @param xCo
     *            The x coordinate of the city
     * @param yCo
     *            The y coordinate of the city
     * @param name
     *            The name of the city
     */
    public LeafNode(int xCo, int yCo, String name) {
        city = new City(xCo, yCo, name);
        type = "";
    }


    /**
     * Getter for Type of BinNode, which will always return ""
     * 
     * @return "" because type is LeafNode
     */
    @Override
    public String getType() {
        return type;
    }


    /**
     * Getter method for the City object
     * 
     * @return the CIty associated with LeafNode
     */
    public City getCity() {
        return city;
    }


    /**
     * Getter for city name
     * 
     * @return name of the city
     */
    public String getName() {
        return city.getCity();
    }


    /**
     * Getter for x coordinate
     * 
     * @return the x coordinate of the city
     */
    public int getX() {
        return city.getX();
    }


    /**
     * Getter for y coordinate
     * 
     * @return the y coordinate of the city
     */
    public int getY() {
        return city.getY();
    }


    /**
     * Method that does insertion of new Nodes into BinTree by connecting Nodes
     * with its children
     * 
     * @param name
     *            the name of city trying to be inserted into BinTree
     * @param level
     *            the current level
     * @param xUp
     *            the upper left x coordinate of the region we are in
     * 
     * @param yUp
     *            the upper left y coordinate of the region we are in
     * 
     * @param xBound
     *            the boundary line for width
     * @param yBound
     *            the boundary line for height
     * @return the root Node after all insertion are completed
     */
    @Override
    public BinNode insert(
        City name,
        int level,
        int xUp,
        int yUp,
        int xBound,
        int yBound) {

        // Base case, check to see if Node is empty
        if (this.getType().equals("E")) {
            // If it is, then turn in into a Leaf Node storing the city data
            LeafNode leaf = new LeafNode(name);
            return leaf;
        }

        // Otherwise, need to split into subtree so make root a internal Node
        // and split based on x or y coordinate
        else {

            // Store current root into a variable
            BinNode currRoot = this;

            // Convert to LeafNode to extract City data
            LeafNode tempRoot = (LeafNode)currRoot;
            
            // Temp variable to store City of RootNode before turning it to a
            // Internal Node
            City temp = tempRoot.getCity();

            // Make root an Internal Node
            currRoot = new InternalNode();

            // Call recursion passing down the InternalNode root, and the data
            // of when root used to be a LeafNode, and split the x world in half
            currRoot = currRoot.insert(temp, level, xUp, yUp, xBound, yBound);

            // After creating new LeafNode for old root, insert new LeafNode for
            // new city
            currRoot = currRoot.insert(name, level, xUp, yUp, xBound, yBound);

            return currRoot;
        }

    }


    /**
     * Traverses the BinTree to search for a (single) city with coordinates x
     * and y
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param level
     *            the current level in the tree
     * @param xUp
     *            the upper left x coordinate of the region we are in
     * 
     * @param yUp
     *            the upper left y coordinate of the region we are in
     * 
     * @param xBound
     *            the boundary line for width
     * @param yBound
     *            the boundary line for height
     * @return a BinNode containing the City, or null
     */
    @Override
    public BinNode find(
        int x,
        int y,
        int level,
        int xUp,
        int yUp,
        int xBound,
        int yBound) {

        // Base case, check to see if it's a leaf node and if it's empty
        if (this.getType().equals("E")) {
            return null;
        }

        // Get LeafNode representation to extract City data
        LeafNode leafRep = (LeafNode)this;
        City currCity = leafRep.getCity();

        // Check if coordinates are not equal, return null
        if (currCity.getX() != x || currCity.getY() != y) {
            return null;
        }

        // If it gets here, means x,y are equal and return the Node
        return this;
    }


    /**
     * Print a listing of the Bintree nodes in preorder traversal order, one
     * node per line using System.out.print()
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param level
     *            the current level in the tree
     * @param xBound
     *            the boundary line for width
     * @param yBound
     *            the boundary line for height
     */
    @Override
    public void print(int x, int y, int level, int xBound, int yBound) {

        String toPrint = "";

        // Spaces based on level
        String spaces = "";
        for (int i = 0; i < level; i++) {
            spaces = spaces + "  ";
        }

        // Prints empty node
        if (this.getType().equals("E")) {
            toPrint = toPrint + spaces + this.getType() + " " + x + " " + y
                + " " + xBound + " " + yBound;
        }

        // Prints leaf node
        else {
            LeafNode leaf = (LeafNode)this;
            toPrint = toPrint + spaces + leaf.getName() + " " + leaf.getX()
                + " " + leaf.getY();
        }

        System.out.println(toPrint);
    }


    /**
     * Traverses the BinTree to remove the City at the specified coordinates
     * If this method is called, it means the City in this LeafNode is removed
     * and this Node is made into the flyweight empty node
     * 
     * @param x
     *            the x coordinate of the city
     * @param y
     *            the y coordinate of the city
     * @param level
     *            the current level in the tree
     * @param xUp
     *            the upper left x coordinate of the region we are in
     * 
     * @param yUp
     *            the upper left y coordinate of the region we are in
     * 
     * @param xBound
     *            the boundary line for width
     * @param yBound
     *            the boundary line for height
     * @return the root of the new BinTree with the desired element removed
     */
    public BinNode remove(
        int x,
        int y,
        int level,
        int xUp,
        int yUp,
        int xBound,
        int yBound) {

        return LeafNode.EMP;
    }


    /**
     * Searches for Cities in the tree that lies within a specified region based
     * on xy coordinates
     * 
     * @param searchReg
     *            the region to search for
     * @param level
     *            the current level in the tree
     * @param xUp
     *            the upper left x coordinate of the region we are in
     * 
     * @param yUp
     *            the upper left y coordinate of the region we are in
     * 
     * @param xBound
     *            the boundary line for width
     * @param yBound
     *            the boundary line for height
     * @return the number of Cities/Nodes visited
     */
    public int regionsearch(
        Region searchReg,
        int level,
        int xUp,
        int yUp,
        int xBound,
        int yBound) {

        // If it is a NON empty leaf node and its city is in the region, prints
        // City to console
        if (this.getType().equals("") && searchReg.cityIntersect(this
            .getCity())) {
            System.out.println(this.getName() + " " + this.getX() + " " + this
                .getY());
        }

        // Always return 1 because we visited this node
        return 1;
    }

}
