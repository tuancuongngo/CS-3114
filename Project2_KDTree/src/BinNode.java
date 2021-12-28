/**
 * This interface is the blueprint for creating BinNodes in a BinTree
 * It contains method outlines for InternalNode, and LeafNode
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public interface BinNode {

    /**
     * Getter for the Type of the BinNode
     * Mainly used for testing to make sure right kind of Node is returned
     * 
     * @return the Type, whether it be a InternalNode, LeafNode, or EmptyNode
     */
    public String getType();


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
    public BinNode insert(
        City city,
        int level,
        int xUp,
        int yUp,
        int xBound,
        int yBound);


    /**
     * Traverses the BinTree to search for a (single) city with coordinates x
     * and y
     * 
     * @param x
     *            the x coordinate to look for
     * @param y
     *            the y coordinate to look for
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
    public BinNode find(
        int x,
        int y,
        int level,
        int xUp,
        int yUp,
        int xBound,
        int yBound);


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
    public void print(int x, int y, int level, int xBound, int yBound);


    /**
     * Remove a city from BinTree at the specified coordinates if it exists
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
        int yBound);


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
        int yBound);

}
