/**
 * Class that represents a BinTree used to store Cities
 * A Bintree is a natural extension of the BST to multiple dimensions.
 * For more information, please see OpenDSA 15.5
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public class BinTree {

    private BinNode root; // The root Node

    /**
     * The width of the world, can be accessed by FileProcessor to check if
     * inserting or region searching out of bounds
     */
    protected int xWorld = 1024;

    /**
     * The height of the world, can be accessed by FileProcessor to check if
     * inserting or region searching out of bounds
     */
    protected int yWorld = 1024;


    /**
     * Default constructor the sets root Node to flyweight empty Node
     */
    public BinTree() {
        // default constructor
        root = LeafNode.EMP; // Root of tree will always be a empty leaf node at
                             // first
    }


    /**
     * Traverses the BinTree to search for a (single) city with coordinates x
     * and y
     * 
     * @param x
     *            the x coordinate to look for
     * @param y
     *            the y coordinate to look for
     * @return a String of city in this format: name x y. Otherwise return
     *         "Record could not be printed. It does not exist."
     * 
     */
    public String find(String x, String y) {

        LeafNode found = (LeafNode)root.find(Integer.parseInt(x), Integer
            .parseInt(y), 0, 0, 0, xWorld, yWorld);

        String result = "Record could not be printed. It does not exist.";

        // If nothing was found
        if (found == null) {
            return result;
        }

        result = found.getName() + " " + found.getX() + " " + found.getY();
        return result;
    }


    /**
     * Insert a new City into the BinTree by calling insert() from Node classes
     * 
     * @param x
     *            the x coordinate of the city
     * @param y
     *            the y coordinate of the city
     * @param city
     *            the name of the city
     */
    public void insert(String x, String y, String city) {
        root = root.insert(new City(Integer.parseInt(x), Integer.parseInt(y),
            city), 0, 0, 0, xWorld, yWorld);
    }


    /**
     * Remove the city with coordinate (x, y) from Tree if it exists
     * If it does not, prints "Record was not removed. It does not exist."
     * 
     * @param x
     *            the x coordinate of the City to remove
     * @param y
     *            the y coordinate of the City to remove
     * 
     * @return the removed City, if it was one was found
     */
    public String remove(String x, String y) {

        // Convert String coordinates to int
        int xCo = Integer.parseInt(x);
        int yCo = Integer.parseInt(y);

        // Checks to see if a city with such coordinates exists
        BinNode exist = root.find(xCo, yCo, 0, 0, 0, xWorld, yWorld);

        // If it does not exist
        if (exist == null) {
            return "Record could not be removed. It does not exist.";
        }

        // Remove it if it exists
        root = root.remove(xCo, yCo, 0, 0, 0, xWorld, yWorld);

        // Otherwise, do nothing because record is already removed
        return "";
    }


    /**
     * Finds and prints to console all Cities in the BinTree the intersect the
     * query rectangle specified by the regionsearch parameters.
     * Prints out the number of Nodes visited after
     * 
     * If the regionsearch parameters are out of World, will print "The
     * specified region is outside the known world"
     * 
     * @param x
     *            the upper left x coordinate of rectangle region
     * @param y
     *            the upper left y coordinate of rectangle region
     * @param w
     *            the width of the rectangle
     * @param h
     *            the height of the rectangle
     */
    public void regionsearch(String x, String y, String w, String h) {

        // Converting coordinates to integers to make a new Region
        int xCo = Integer.parseInt(x);
        int yCo = Integer.parseInt(y);
        int width = Integer.parseInt(w);
        int height = Integer.parseInt(h);

        Region searchReg = new Region(xCo, yCo, width, height);

        // Calls regionsearch, which will return number of Nodes visited, and
        // prints it out
        int totalVisits = root.regionsearch(searchReg, 0, 0, 0, xWorld, yWorld);
        System.out.println(totalVisits + " nodes visited");
    }


    /**
     * Print a listing of the Bintree nodes in preorder traversal order, one
     * node per line using System.out.print()
     */
    public void print() {
        root.print(0, 0, 0, xWorld, yWorld);
    }


    /**
     * Getter for the root of tree/subtree
     * 
     * @return the root of the BinTree
     */
    public BinNode getRoot() {
        return root;
    }


    /**
     * Setter method for width
     * Used to make a smaller world during Testing
     * 
     * @param newW
     *            the new Width
     */
    public void setW(int newW) {
        xWorld = newW;
    }


    /**
     * Setter method for height
     * Used to make a smaller world during Testing
     * 
     * @param newH
     *            the new Height
     */
    public void setH(int newH) {
        yWorld = newH;
    }
}
