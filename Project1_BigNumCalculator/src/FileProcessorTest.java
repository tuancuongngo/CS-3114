import java.util.Scanner;
import student.TestCase;

/**
 * Test the functionality of FileProcessor class
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 *
 */
public class FileProcessorTest extends TestCase {

    /**
     * Set up common conditions.
     * Method intentionally left blank
     */
    public void setUp() {
        // Method intentionally left blank
    }


    /**
     * Test readLine() on valid line
     */
    public void testReadLine() {
        String input =
            "000000056669777     99999911111 + " 
                + "352324012 + 03 ^          555557778 *";
        Scanner scan = new Scanner(input);
        FileProcessor read = new FileProcessor(scan);
        assertEquals(1, read.getNumProcessed());
    }


    /**
     * Test readLine() on empty line
     */
    public void testReadLineEmpty() {
        String input = "               ";
        Scanner scan = new Scanner(input);
        FileProcessor read = new FileProcessor(scan);
        assertEquals(0, read.getNumProcessed());
    }

}
