import java.util.Scanner;
import student.TestCase;

/**
 * Test the functionality of FileProcessor class
 * 
 * @author Cuong Ngo (ngoct)
 * @version 07/28/2020
 */
public class FileProcessorTest extends TestCase {

    /**
     * Sets up common conditions for FileProcessor
     */
    public void setUp() {
        // Method intentionally left blank
    }


    /**
     * Test processing an empty line in fileProcessor
     */
    public void testProcessEmpty() {
        String input = "               "; // Empty line to process
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // The root of the Tree associated with FileProcessor is empty
        BinNode root = fp.getTree().getRoot();
        assertEquals("E", root.getType());
    }


    /**
     * Test a valid insert command line
     */
    public void testInsert() {
        String input = "insert 37 80 Blacksburg";
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // Checks to see if the correct input line was printed
        String output = systemOut().getHistory();
        assertEquals(">insert 37 80 Blacksburg\n", output);

        // Checks to see if FileProcessor put the city in its BinTree
        BinTree fpTree = fp.getTree();
        assertEquals("Blacksburg 37 80", fpTree.find("37", "80"));
    }


    /**
     * Test inserting out of bounds for a bunch of coordinates to pass
     * complicated if statement
     */
    public void testInvalidInsert() {
        String input = "insert 37000 80 one\n" + "insert 30303 99999 both\n"
            + "insert -10 100 neg\n" + "insert -10 -1222 both\n"
            + "insert 1024 -1024 br\n" + "insert -10 10000 br\n";
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        String actual = systemOut().getHistory();
        String expected = ">insert 37000 80 one\r\n"
            + "Error. Out of bounds. Not inserted\r\n"
            + ">insert 30303 99999 both\r\n"
            + "Error. Out of bounds. Not inserted\r\n"
            + ">insert -10 100 neg\r\n"
            + "Error. Out of bounds. Not inserted\r\n"
            + ">insert -10 -1222 both\r\n"
            + "Error. Out of bounds. Not inserted\r\n"
            + ">insert 1024 -1024 br\r\n"
            + "Error. Out of bounds. Not inserted\r\n"
            + ">insert -10 10000 br\r\n"
            + "Error. Out of bounds. Not inserted\n";

        assertEquals(expected, actual);

        BinNode root = fp.getTree().getRoot();
        assertEquals("E", root.getType());

    }


    /**
     * Test valid find command lines
     */
    public void testFind() {
        String input = "insert 38 122 San_Francisco\n" + "find 38 122\n"
            + "find 100 100\n";
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // Checks to see if the correct input line was printed
        String output = systemOut().getHistory();
        String expected = ">insert 38 122 San_Francisco\n" + ">find 38 122\n"
            + "San_Francisco 38 122\n" + ">find 100 100\n"
            + "Record could not be printed. " + "It does not exist.\n";
        assertEquals(expected, output);

        // Checks to see if FileProcessor put the city in its BinTree
        BinTree fpTree = fp.getTree();
        assertEquals("San_Francisco 38 122", fpTree.find("38", "122"));
    }


    /**
     * Test a invalid command
     */
    public void testInvalid() {
        String input = "I am the very model of a modern Major-General";
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // Checks to see if the correct input line was printed
        String output = systemOut().getHistory();
        String expected = ">I am the very model of a modern Major-General\n"
            + "ERROR! Unrecognized command: " + input + "\n";
        assertEquals(expected, output);

        // Checks to see if FileProcessor tree is empty as expected
        BinTree fpTree = fp.getTree();
        assertEquals("E", fpTree.getRoot().getType());
    }


    /**
     * Test a print() command
     */
    public void testPrint() {
        String input = "print";
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // Checks to see if the correct input line was printed
        String output = systemOut().getHistory();
        String expected = ">print\n" + "E 0 0 1024 1024\n";
        assertEquals(expected, output);

        // Checks to see if FileProcessor is empty as expected
        BinTree fpTree = fp.getTree();
        assertEquals("E", fpTree.getRoot().getType());
    }


    /**
     * Test a remove() command on a non existent element
     * 
     */
    public void testRemoveEmp() {
        String input = "remove 100 100";
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // Checks to see if the correct input line was printed
        String output = systemOut().getHistory();
        String expected = ">remove 100 100\n"
            + "Record could not be removed. It does not exist.\n";
        assertEquals(expected, output);

        // Checks to see if FileProcessor tree is empty as expected
        BinTree fpTree = fp.getTree();
        assertEquals("E", fpTree.getRoot().getType());
    }


    /**
     * Test a remove command on a valid and non existent element
     */
    public void testRemoveValid() {

        String input = "insert 37 80 Blacksburg\r\n"
            + "insert 38 122 San_Francisco\r\n" + "insert 41 96 Omaha\r\n"
            + "remove 38 122\r\n" + "find 38 122\r\n" + "remove 38 122";
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // Checks to see if the correct input line was printed
        String output = systemOut().getHistory();
        String expected = ">insert 37 80 Blacksburg\r\n"
            + ">insert 38 122 San_Francisco\r\n" + ">insert 41 96 Omaha\r\n"
            + ">remove 38 122\r\n" + ">find 38 122\r\n"
            + "Record could not be printed. It does not exist.\r\n"
            + ">remove 38 122\r\n"
            + "Record could not be removed. It does not exist.\n";

        assertEquals(expected, output);

        // Checks to see if FileProcessor tree is as expected
        BinTree fpTree = fp.getTree();
        assertEquals("I", fpTree.getRoot().getType());

    }


    /**
     * Test the regionSearch method based on sample input file provided
     */
    public void testSearchSample() {
        String input = "insert 37 80 Blacksburg\r\n"
            + "insert 38 122 San_Francisco\r\n" + "insert 41 96 Omaha\r\n"
            + "remove 38 122\r\n" + "find 38 122\r\n" + "remove 38 122\r\n"
            + "find 41 96\r\n" + "regionsearch 30 70 20 50";

        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // Checks to see if the correct input line was printed
        String output = systemOut().getHistory();
        String expected = ">insert 37 80 Blacksburg\r\n"
            + ">insert 38 122 San_Francisco\r\n" + ">insert 41 96 Omaha\r\n"
            + ">remove 38 122\r\n" + ">find 38 122\r\n"
            + "Record could not be printed. It does not exist.\r\n"
            + ">remove 38 122\r\n"
            + "Record could not be removed. It does not exist.\r\n"
            + ">find 41 96\r\n" + "Omaha 41 96\r\n"
            + ">regionsearch 30 70 20 50\r\n" + "Blacksburg 37 80\r\n"
            + "Omaha 41 96\r\n" + "13 nodes visited\n";

        assertEquals(expected, output);

        // The root of the Tree associated with FileProcessor should be
        // InternalNode
        BinNode root = fp.getTree().getRoot();
        assertEquals("I", root.getType());
    }


    /**
     * Test searching a region out of the world
     */
    public void testSearchOut() {
        String input = "regionsearch 20000 70 20 50\n"
            + "regionsearch 20000 70000 20 50\n"
            + "regionsearch 20 70000 20 50\n";
        Scanner scan = new Scanner(input);
        FileProcessor fp = new FileProcessor(scan);

        // Checks to see if the correct input line was printed
        String output = systemOut().getHistory();
        String expected = ">regionsearch 20000 70 20 50\r\n"
            + "The specified region is outside the known world.\r\n"
            + ">regionsearch 20000 70000 20 50\r\n"
            + "The specified region is outside the known world.\n"
            + ">regionsearch 20 70000 20 50\r\n" + 
             "The specified region is outside the known world.\n";
        assertEquals(expected, output);

        // The root of the Tree associated with FileProcessor is empty
        BinNode root = fp.getTree().getRoot();
        assertEquals("E", root.getType());
    }

}
