import student.TestCase;

/**
 * This class tests every operation in DoublyLinkedList.
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 */
public class DoublyLinkedListTest extends TestCase {
    private DoublyLinkedList<String> list;
    

    /**
     * Run before every test case
     */
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }
    

    /**
     * Tests the add method. Ensures that it adds the object is added at the end
     * and the size is increased
     */
    public void testAdd() {
        assertEquals(0, list.getSize());
        list.add("A");
        assertEquals(1, list.getSize());
        list.add("B");
        assertEquals(2, list.getSize());
        assertEquals("B", list.getData(1));

    }

    /**
     * Tests that objects can be added at the beginning and end and that they
     * are placed correctly
     */
    public void testAddIndex() {
        list.add("B");
        list.add(0, "A");
        assertEquals("A", list.getData(0));
        assertEquals(2, list.getSize());
        list.add(2, "D");
        assertEquals("D", list.getData(2));
        list.add(2, "C");
        assertEquals("C", list.getData(2));
    }
    

    /**
     * This tests that the add method throws a null pointer exception when
     * adding null data to the list
     */
    public void testAddNullException() {
        Exception e = null;
        try {
            list.add(null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }
    

    /**
     * This tests that the add method throws a Invalid argument when adding null
     * data to the list
     */
    public void testAddIndexNullException() {
        Exception e = null;
        try {
            list.add(0, null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    /**
     * This tests when the add method is called and the index is greater than
     * size or less than zero
     */
    public void testAddException() {
        list.add("A");
        Exception e = null;
        try {
            list.add(2, "B");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.add(-1, "B");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue( e instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests that an IndexOutOfBounds exception is thrown when the index is
     * greater than or equal to size and less than zero
     */
    public void testRemoveException() {
        list.add("A");
        Exception e = null;
        try {
            list.remove(2);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.remove(-1);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests that objects can be removed at the beginning and end and that the
     * size is changed
     */
    public void testRemoveIndex() {
        list.add("A");
        list.add("B");
        assertTrue(list.remove(1));
        assertEquals(1, list.getSize());
        list.add("B");
        assertTrue(list.remove(0));
        assertEquals(1, list.getSize());
    }

    /**
     * Tests removing a object changes the size appropiately and that you can
     * remove the first and last elements
     */
    public void testRemoveObj() {
        assertFalse(list.remove(null));
        list.add("A");
        list.add("B");
        assertTrue( list.remove("A"));
        assertEquals( "B", list.getData(0));
        assertEquals( 1, list.getSize());
        list.add("C");
        assertTrue(list.remove("C"));
        assertEquals("B", list.getData(0));
    }
    

    /**
     * Tests removing from an empty list
     */
    public void testRemoveFromEmpty() {
        list.add("dance");
        list.add(0, "safety");
        list.clear();
        assertFalse(list.remove("safety"));
        Exception exception;
        exception = null;
        try {
            list.remove(0);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(  exception instanceof IndexOutOfBoundsException);

        DoublyLinkedList<String> emptyList = new DoublyLinkedList<String>();
        exception = null;
        try {
            emptyList.remove(0);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue( exception instanceof IndexOutOfBoundsException);
    }
    



    /**
     * Tests get when the index is greater than or equal to size and when the
     * index is less than zero
     */
    public void testGetDataException() {
        Exception exception = null;
        try {
            list.getData(-1);
        } 
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
        exception = null;
        list.add("A");
        try {
            list.getData(1);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }
    

    /**
     * Test contains when it does and does not contain the object
     */
    public void testContains() {
        assertFalse( list.contains("A"));
        list.add("A");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("B"));
        list.add("B");
        assertTrue(list.contains("B"));
    }
    

    /**
     * Test lastIndexOf when the list is empty, when the object is not in the
     * list, and when it is at the beginning or end
     */
    public void testLastIndexOf() {
        assertEquals( -1, list.lastIndexOf("A"));
        list.add("A");
        assertEquals(0, list.lastIndexOf("A"));
        list.add("A");
        assertEquals(1, list.lastIndexOf("A"));
        list.add("B");
        assertEquals(1, list.lastIndexOf("A"));
        assertEquals(2, list.lastIndexOf("B"));
        list.add("A");
        assertEquals(3, list.lastIndexOf("A"));
    }

    /**
     * Ensures that all of the objects are cleared and the size is changed
     */
    public void testClear() {
        list.add("A");
        list.clear();
        assertEquals(0, list.getSize());
        assertFalse(
                list.contains("A"));
    }


    /**
     * Tests isEmpty method.
     */
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("10");
        assertFalse(list.isEmpty());
    }

    /**
     * Tests the toString when there are 0, 1, and 2 objects in the list
     */
    public void testToString() {
        assertEquals("{}",
                list.toString());
        list.add("A");
        assertEquals("{A}",
                list.toString());
        list.add("B");
        assertEquals("{A, B}",
                list.toString());
    }
}
