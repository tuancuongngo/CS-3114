import student.TestCase;

/**
 * Test the functionality of LeafNode class
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public class LeafNodeTest extends TestCase {

    private City cc; // the City object LeafNode will store
    private LeafNode leaf; // the LeafNode


    /**
     * Sets up the common conditions in all test cases
     */
    public void setUp() {
        cc = new City(3, 5, "cc");
        leaf = new LeafNode(cc);
    }


    /**
     * Test the getType() method on a LeafNode
     */
    public void testGetType() {
        assertEquals("", leaf.getType());
    }


    /**
     * Test the getCity() method
     */
    public void testGetCity() {
        assertEquals(cc, leaf.getCity());
    }


    /**
     * Test the getName() method, which should return name of city
     */
    public void testGetName() {
        assertEquals("cc", leaf.getName());
    }


    /**
     * Test the getX() method, which should return x of city
     */
    public void testGetX() {
        assertEquals(3, leaf.getX());
    }


    /**
     * Test the getY() method, which should return y of city
     */
    public void testGetY() {
        assertEquals(5, leaf.getY());
    }


    /**
     * Test the Constructor
     */
    public void testConstructor() {
        leaf = new LeafNode(1, 1, "con");
        assertEquals("con", leaf.getName());
    }

}
