import student.TestCase;

/**
 * Test the functions of EmptyNode class
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public class EmptyNodeTest extends TestCase {

    /**
     * Sets up the common conditions.
     * Blank intentionally
     */
    public void setUp() {
        // This method is intentionally left blank
    }


    /**
     * Test the type of the EmptyNode, which should return "E"
     */
    public void testGetType() {
        EmptyNode emp = new EmptyNode();
        assertEquals("E", emp.getType());
    }

}
