/**
 * Class the represents the InternalNode of the BinTree
 * An InternalNode cannot store any data.
 * It can only store pointers to its left and right children
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public class InternalNode implements BinNode {

    // Mainly used for testing to make sure right kind of Node is returned
    private String type = "I"; // Stores the Type of the Node
                               // "I" for Internal node
                               // "E" for Empty node
                               // "" (Empty String) for Leaf Node
                               

    // Left and Right children
    private BinNode left;
    private BinNode right;


    /**
     * Default constructor that initializes the children to flyweight EmptyNode
     */
    public InternalNode() {
        left = LeafNode.EMP;
        right = LeafNode.EMP;
    }


    /**
     * Constructor that initializes Type to InternalNode based on by calling
     * parent BinNode class, then sets pointer to left and
     * right children
     * 
     * 
     * @param leftC
     *            pointer to left child Node
     * @param rightC
     *            pointer to right child Node
     */
    public InternalNode(BinNode leftC, BinNode rightC) {
        left = leftC;
        right = rightC;
    }


    /**
     * Getter for Type of Node, which should always return "I"
     */
    @Override
    public String getType() {
        return type;
    }


    /**
     * Setter for the left child of the Internal Node
     * 
     * @param n
     *            the Node to be the leftChild
     */
    public void setLeft(BinNode n) {
        left = n;
    }


    /**
     * Setter for the right child of the Internal Node
     * 
     * @param n
     *            the Node to be the rightChild
     */
    public void setRight(BinNode n) {
        right = n;
    }


    /**
     * Getter for the left child of the Internal Node
     * 
     * @return the left Child
     */
    public BinNode getLeft() {
        return left;
    }


    /**
     * Getter for the right child of the Internal Node
     * 
     * @return the right child
     */
    public BinNode getRight() {
        return right;
    }


    /**
     * Method that does insertion of new Nodes into BinTree by connecting Nodes
     * with its children
     * 
     * @param city
     *            the city trying to be inserted into BinTree
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
        City city,
        int level,
        int xUp,
        int yUp,
        int xBound,
        int yBound) {

        // If the currRoot is an Internal Node and level is EVEN, then splitting
        // by x coordinate
        if (level % 2 == 0) {
            if (city.getX() < xUp + xBound / 2) {
                this.setLeft(this.getLeft().insert(city, ++level, xUp, yUp,
                    xBound / 2, yBound));
                return this;
            }

            else {
                this.setRight(this.getRight().insert(city, ++level, xUp + xBound
                    / 2, yUp, xBound / 2, yBound));
                return this;
            }
        }

        // If the currRoot is an Internal Node and level is ODD, then splitting
        // by y coordinate
        else {
            // Put data in left or right child based on y
            if (city.getY() < yUp + yBound / 2) {

                this.setLeft(this.getLeft().insert(city, ++level, xUp, yUp,
                    xBound, yBound / 2));
                return this;
            }

            else {
                this.setRight(this.getRight().insert(city, ++level, xUp, yUp
                    + yBound / 2, xBound, yBound / 2));
                return this;
            }
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

        if (level % 2 == 0) {
            // If x of City we're looking for is less than to boundary line
            if (x < xUp + xBound / 2) {
                return this.getLeft().find(x, y, ++level, xUp, yUp, xBound / 2,
                    yBound);
            }

            // If x of City we're looking for is more than, or equal to boundary
            // line
            else {
                return this.getRight().find(x, y, ++level, xUp + xBound / 2,
                    yUp, xBound / 2, yBound);
            }
        }

        else {
            // If y of City we're looking for is less than to boundary line
            if (y < yUp + yBound / 2) {
                return this.getLeft().find(x, y, ++level, xUp, yUp, xBound,
                    yBound / 2);
            }

            // If y of City we're looking for is more than, or equal to boundary
            // line
            else {
                return this.getRight().find(x, y, ++level, xUp, yUp + yBound
                    / 2, xBound, yBound / 2);
            }
        }
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

        // Add spaces based on level
        String toPrint = "";

        // Spaces based on level
        String spaces = "";
        for (int i = 0; i < level; i++) {
            spaces = spaces + "  ";
        }

        // Prints out root Internal Node data
        toPrint = toPrint + spaces + this.getType() + " " + x + " " + y + " "
            + xBound + " " + yBound;
        System.out.println(toPrint);

        // Even level, splitting by x coordinate, calls print() on left child,
        // then on right child
        if (level % 2 == 0) {
            xBound = xBound / 2;

            this.getLeft().print(x, y, level + 1, xBound, yBound);
            this.getRight().print(x + xBound, y, level + 1, xBound, yBound);
        }

        // Odd level, splitting by y coordinate, calls print() on left child,
        // then on right child
        else {
            yBound = yBound / 2;

            this.getLeft().print(x, y, level + 1, xBound, yBound);
            this.getRight().print(x, y + yBound, level + 1, xBound, yBound);
        }

    }


    /**
     * Traverses the BinTree to remove the City at the specified coordinates
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
        
        // If even level, then compare x values to find whether to remove() in
        // left or right child
        if (level % 2 == 0) {

            // If the City is in the left subtree, look in the subtree
            if (x < xUp + xBound / 2) {
                this.setLeft(this.getLeft().remove(x, y, ++level, xUp, yUp,
                    xBound / 2, yBound));
            }

            // If the City is in the right subtree, look in the subtree
            else {
                this.setRight(this.getRight().remove(x, y, ++level, xUp + xBound
                    / 2, yUp, xBound / 2, yBound));
            }
        }

        // If odd level, then compare y values to find whether to remove() in
        // left or right child
        else {
            // If the City is in the left subtree, look in the subtree
            if (y < yUp + yBound / 2) {
                this.setLeft(this.getLeft().remove(x, y, ++level, xUp, yUp,
                    xBound, yBound / 2));
            }

            // If the City is in the right subtree, look in the subtree
            else {
                this.setRight(this.getRight().remove(x, y, ++level, xUp, yUp
                    + yBound / 2, xBound, yBound / 2));
            }
        }

        // If left child is empty node, and right child is leaf,
        // move right child to be root to preserve full tree property
        if (this.getLeft().getType().equals("E") && this.getRight().getType()
            .equals("")) {
            return this.getRight();
        }

        // If right child is empty node, and left child is leaf,
        // move left child to be root to preserve full tree property
        else if (this.getRight().getType().equals("E") && this.getLeft()
            .getType().equals("")) {
            return this.getLeft();
        }
        return this;
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

        // Variable to store the total number of Nodes visited
        int totalVisits = 0;


        // If even level
        if (level % 2 == 0) {
            
            // Split by xBound into a Region on the left and another on the
            // right
            Region leftWorld = new Region(xUp, yUp, xBound / 2, yBound);
            Region rightWorld = new Region(xUp + xBound / 2, yUp, xBound / 2,
                yBound);

            // Check if left child intersects the search region
            if (searchReg.intersect(leftWorld)) {
                totalVisits += this.getLeft().regionsearch(searchReg, level + 1,
                    xUp, yUp, xBound / 2, yBound);
            }

            // Check if right child intersects the search region
            if (searchReg.intersect(rightWorld)) {
                totalVisits += this.getRight().regionsearch(searchReg, level
                    + 1, xUp + xBound / 2, yUp, xBound / 2, yBound);
            }
        }

        // If odd level
        else {

            // Split by yBound into a Region that's upper and another that's
            // lower
            Region upWorld = new Region(xUp, yUp, xBound, yBound / 2);
            Region downWorld = new Region(xUp, yUp + yBound / 2, xBound, yBound
                / 2);

            // Check if left child intersects the search region
            if (upWorld.intersect(searchReg)) {
                totalVisits += this.getLeft().regionsearch(searchReg, level + 1,
                    xUp, yUp, xBound, yBound / 2);
            }

            // Check if right child intersects the search region
            if (downWorld.intersect(searchReg)) {
                totalVisits += this.getRight().regionsearch(searchReg, level
                    + 1, xUp, yUp + yBound / 2, xBound, yBound / 2);
            }
        }

        // Return the total number of Nodes visited, 1 for this Node, and
        // totalVists for all nodes visisted in children
        return 1 + totalVisits; 
    }
}
