import java.io.FileNotFoundException;
import student.TestCase;

/**
 * Test the functionality of the BinProg class, passing in valid and invalid
 * file names
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 *
 */
public class BinprogTest extends TestCase {

    /**
     * Sets up common test Conditions, intentionally left blank
     * 
     * @throws FileNotFoundException
     */
    public void setUp() throws FileNotFoundException {
        // Blank intentionally
    }


    /**
     * Test the main method, passing in more than 1 argument, which will throw
     * an exception
     */
    public void testMainInvalidSize() {
        Binprog bp = new Binprog();
        String[] argument = { "", "invalid" };

        // Passing in invalid argument to main
        Exception thrown = null;
        try {
            bp.main(argument);
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
     * Test main method passing in an Empty file
     * 
     * @throws FileNotFoundException
     */
    public void testMainEmp() throws FileNotFoundException {

        String[] argument = { "empty.txt" };

        Binprog.main(argument);

        // Empty file so nothing is processed and printed out
        String expected = "";
        String output = systemOut().getHistory();

        assertEquals(expected, output);
        // System.out.println(systemOut().getHistory());
    }


    /**
     * Test the main method on a input file with 3 lines
     * 
     * @throws FileNotFoundException
     */
    public void testMainSmall() throws FileNotFoundException {
        String[] argument = { "smallInput.txt" };

        Binprog.main(argument);

        String expected = ">insert 1000 80 Blacksburg\n"
            + ">insert 38 122 San_Francisco\n" + ">find 38 122\n"
            + "San_Francisco 38 122\n";
        String output = systemOut().getHistory();

        assertEquals(expected, output);
    }
}
