import student.TestCase;

/**
 * Test the functions of BigNumArithmetic class
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 */
public class BigNumArithmeticTest extends TestCase {

    /**
     * Test the main method, passing in more than 1 argument, which will throw
     * an exception
     */
    public void testMainInvalidSize() {
        String[] argument = { "", "invalid" };

        // Passing in invalid argument to main
        Exception thrown = null;
        try {
            BigNumArithmetic.main(argument);
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /**
     * Test the creation of a BigNumArithmetic
     */
    public void testCreation() {
        String[] argument = { "C:\\Users\\Tuan Cuong\\CS3114\\"
                + "Project_1\\src\\BignumInput.txt" };
        new BigNumArithmetic().main(argument);

        assertNotNull(argument);
    }

}
