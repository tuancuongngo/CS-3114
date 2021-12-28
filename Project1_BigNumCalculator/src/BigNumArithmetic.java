import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class takes in and initializes a input file of Expressions, passes it to
 * appropriate methods and classes to do calculations and output all the
 * Expressions and their results
 * 
 * @author Tuan Cuong (ngoct@vt.edu)
 * @author Yongjae Lim (yongjae@vt.edu)
 * @version 07/16/2020
 */
public class BigNumArithmetic {

    /**
     * This method takes in input file name from user, then it creates a File
     * and Scanner. Finally it makes a new FileProcessor, passing in the Scanner
     * to process the file and create outputs to the console
     * 
     * @param args
     *            the array containing the name of the input file from user
     */
    public static void main(String[] args) {

        // Argument should only contain name of the input file
        if (args.length != 1) {
            throw new IllegalArgumentException(
                "Argument should contains only 1 element, which is file name");
        }

        // Create file for input file
        File input = new File(args[0]);

        // Scanner to read input file
        Scanner scan;

        // Attempt to read input file using newly created Scanner
        try {
            scan = new Scanner(input);
            new FileProcessor(scan);
        }
        // Catch exception if input file is invalid, or not found
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
