import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class takes in a input file and calls other classes to create a BinTree
 * based on input
 * 
 * @author Cuong Ngo (ngoct)
 * @version 07/28/2020
 *
 */
public class Binprog {

    /**
     * This method takes in input file name from user
     * 
     * @param args
     *            the array containing the name of the input file from user
     * @throws FileNotFoundException
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws FileNotFoundException {
        // Argument should only contain name of the input file
        if (args.length != 1) {
            throw new IllegalArgumentException(
                "Argument should contains only 1 element, which is file name");
        }

        // Create file for input file
        File input = new File(args[0]);

        // Scanner to read input file
        Scanner scan;

        // Attempt to read input file using newly created Scanner and create a
        // new FileProcessor to process input
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
