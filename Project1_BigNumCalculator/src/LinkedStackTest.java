import java.util.EmptyStackException;
import student.TestCase;

/**
 * Test the functionality of LinkedStack
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 */
public class LinkedStackTest extends TestCase {

    private LinkedStack<Integer> stack;
    private int a;
    private int b;


    /**
     * Sets up common conditions used in every test cases
     */
    public void setUp() {
        stack = new LinkedStack<Integer>();
        a = 1;
        b = 2;
    }


    /**
     * Test the peek() method on empty stack
     */
    public void testPeekEmpty() {
        assertTrue(stack.isEmpty());

        // Peeking from empty stack
        Exception thrown = null;
        try {
            stack.peek();
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof EmptyStackException);
    }


    /**
     * Test the peek() method
     */
    public void testPeek() {
        stack.push(b);
        assertEquals(b, (int)stack.peek());
        assertEquals(1, stack.size());

        stack.push(a);
        assertEquals(a, (int)stack.peek());
        assertEquals(2, stack.size());
    }


    /**
     * Test the pop() method on empty stack
     */
    public void testPopEmpty() {
        assertTrue(stack.isEmpty());

        // Popping from empty stack
        Exception thrown = null;
        try {
            stack.pop();
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof EmptyStackException);
    }


    /**
     * Test the pop() method
     */
    public void testPop() {
        stack.push(b);
        stack.push(a);

        assertEquals(a, (int)stack.pop());
        assertEquals(b, (int)stack.pop());
        assertTrue(stack.isEmpty());
    }

}
