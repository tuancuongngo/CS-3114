import java.util.NoSuchElementException;
import student.TestCase;

/**
 * Test the functionality of a Integer LinkedList
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 */
public class LinkedListTest extends TestCase {

    private LinkedList<Integer> list;
    private int a;
    private int b;


    /**
     * Sets up common conditions used in every test cases
     */
    public void setUp() {
        list = new LinkedList<Integer>();
        a = 1;
        b = 2;
    }


    /**
     * Test the add() method
     */
    public void testAdd() {
        assertEquals(0, list.getSize());
        assertEquals("", list.toString());
        
        list.add(a);
        assertEquals(1, list.getSize());
        assertEquals(a, (int)list.getCurrData());

        list.add(b);
        assertEquals(2, list.getSize());
        assertEquals("12", list.toString());
    }


    /**
     * Test the remove() method from an empty list
     */
    public void testRemoveEmpty() {
        // Removing from empty list
        Exception thrown = null;
        try {
            list.remove();
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof NoSuchElementException);
    }


    /**
     * Test the remove() method
     */
    public void testRemove() {
        list.add(a);
        list.add(b);
        assertEquals(2, list.getSize());

        // curr is at a
        assertEquals(a, (int)list.remove());
        assertEquals(1, list.getSize());

        // curr is at b, the only element left in list
        assertEquals(b, (int)list.remove());
        assertEquals(0, list.getSize());
    }


    /**
     * Test the moveToStart() method
     */
    public void testMoveToStart() {
        list.add(a);
        list.add(b);

        // Move curr to b
        list.next();
        assertEquals(b, (int)list.getCurrData());

        // Move curr to start, which is at a
        list.moveToStart();
        assertEquals(a, (int)list.getCurrData());
    }


    /**
     * Test the getCurrData() method on an empty list
     */
    public void testGetCurrData() {
        // Getting data from empty list
        Exception thrown = null;
        try {
            list.getCurrData();
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof NoSuchElementException);
    }


    /**
     * Test the next() method on an empty list
     */
    public void testNext() {
        // Calling next() on empty list
        Exception thrown = null;
        try {
            list.next();
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof NoSuchElementException);
    }

    
    /**
     * Test the clear() method
     */
    public void testClear() {
        list.add(a);
        list.add(b);
        list.clear();
        
        assertEquals(0, list.getSize());
    }
}
