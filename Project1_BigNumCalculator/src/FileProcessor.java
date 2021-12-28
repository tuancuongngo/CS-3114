import java.util.Scanner;

/**
 * Class that takes in input file and for every line it reads,
 * it will create an Expression for each line to perform arithmetic operations
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 */
public class FileProcessor {

    private String currLine; // Current line that is being processed
    private int lineProcessed; // Counter to keep track of number of valid
                               // processed lines
                               // which is every line except blank lines
    private String[] expressionA; // String Array that contains all operators
                                  // and operands for each line


    /**
     * Default constructor that reads input file, process it line by line by
     * creating an Expression for each line
     * 
     * @param scanner
     *            the Scanner containing the input file
     */
    public FileProcessor(Scanner scanner) {
        // The scanner used to process input file
        Scanner scan = scanner;

        // Initializing variables for the current line
        currLine = null;
        lineProcessed = 0;
        expressionA = null;

        // Process input file line by line
        while (scan.hasNextLine()) {
            // Get the current line
            currLine = scan.nextLine().trim();

            // Process line if it is not empty
            if (currLine.length() != 0) {
                createExpression(currLine);
            }
        }
    }


    /**
     * Process the line by creating an Expression object for it
     * 
     * @param line
     *            the line containing the operands and operators
     */
    private void createExpression(String line) {

        // Remove all unnecessary spaces in the line
        currLine = line.replaceAll("\\s+", " ");
        // Split line into Array that contains all operators and operands
        expressionA = currLine.split(" ");

        // Create expression for line, and increase number of lines processed
        new Expression(expressionA);
        lineProcessed++;

    }


    /**
     * Getter method for number of valid processed lines
     * 
     * @return number of valid processed lines, which is every line except blank
     *         lines
     */
    public int getNumProcessed() {
        return lineProcessed;
    }


    /**
     * Getter method to get the array containing all operators and operands
     * 
     * @return array containing all operators and operands
     */
    public String[] getCurrArray() {
        return expressionA;
    }

}
