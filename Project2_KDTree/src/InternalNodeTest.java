import student.TestCase;

/**
 * 
 */

/**
 * Tests the functionality of InternalNode class
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public class InternalNodeTest extends TestCase {

    private InternalNode noChild; // InternalNode with no children
    private InternalNode preg; // InternalNode with children
    private EmptyNode emp; // EmptyNode to be a child
    private LeafNode leaf; // LeafNode to be the other child


    /**
     * Sets up the common conditions for all test cases
     */
    public void setUp() {
        emp = new EmptyNode();
        leaf = new LeafNode(1, 2, "leaf");

        noChild = new InternalNode();
        preg = new InternalNode(leaf, emp);
    }


    /**
     * Test getType() method
     */
    public void testGetType() {
        assertEquals("I", noChild.getType());
        assertEquals("I", preg.getType());
    }


    /**
     * Test getRight() on empty and full InternalNodes
     */
    public void testGetRight() {
        assertEquals(emp, preg.getRight());
        assertEquals("E", noChild.getRight().getType());
    }


    /**
     * Test getLeft() on empty and full InternalNodes
     */
    public void testGetLeft() {
        assertEquals(leaf, preg.getLeft());
        assertEquals("E", noChild.getLeft().getType());
    }


    /**
     * Test setRight() on empty InternalNode
     */
    public void testSetRight() {
        noChild.setRight(leaf);
        assertEquals(leaf, noChild.getRight());
    }


    /**
     * Test setLeft() on empty InternalNode
     */
    public void testSetLeft() {
        noChild.setLeft(leaf);
        assertEquals(leaf, noChild.getLeft());
    }
    
    /**
     * Test the regionsearch method on a ODD level split
     */
    public void testRegSearchOdd() {
        Region searchReg = new Region(0, 0, 1024, 512);
        
        int visits = preg.regionsearch(searchReg , 1, 0, 0, 1024, 1024);
        assertEquals(2, visits);
        
    }

}
