import java.util.Scanner;

/**
 * Class that takes in input file and processes it line by line by making a
 * BinTree and using the function of its BinNodes
 * 
 * @author Cuong Ngo (ngoct)
 * @version 07/28/2020
 */
public class FileProcessor {

    private String currLine; // The current line being processed
    private BinTree tree; // The tree associated with the FileProcessor


    /**
     * This constructor will invoke the methods to read the input and
     * return or print out an output
     * 
     * @param scan
     *            the Scanner containing inputFile
     */
    public FileProcessor(Scanner scan) {
        Scanner sc = scan;

        // Initializes the BinTree and currrent line variable
        tree = new BinTree();
        currLine = null;

        // Process input file line by line by calling helper method
        while (scan.hasNextLine()) {
            // Get the current line and trim whitespace
            currLine = sc.nextLine().trim();

            // Process line if it is not empty by calling helper
            if (currLine.length() != 0) {
                processLine(currLine);
            }
        }
    }


    /**
     * Private method that takes in a command line from input file and calls
     * appropriate BinTree command.
     * 
     * It then prints out to console the processed command
     * 
     * @param line
     *            the line to process
     */
    private void processLine(String line) {
        // Remove all unnecessary spaces in the line
        currLine = line.replaceAll("\\s+", " ");

        // Split line into Array that contains all tokens
        String[] expressionA = currLine.split(" ");

        // String to store output that will be printed out to console
        String output = "";

        String command = expressionA[0];
        if (command.equals("insert")) {
            // Storing xy coordinates into variable
            int xCo = Integer.parseInt(expressionA[1]);
            int yCo = Integer.parseInt(expressionA[2]);

            // Forms output and prints it out
            output = ">" + currLine;
            System.out.println(output);

            // Checks and prints error if not in bounds
            if (yCo >= tree.yWorld || xCo < 0 || yCo < 0
                || xCo >= tree.xWorld) {
                System.out.println("Error. Out of bounds. Not inserted");
            }

            // Otherwise, insert into world
            else {
                tree.insert(expressionA[1], expressionA[2], expressionA[3]);
            }

        }

        else if (command.equals("find")) {
            // Prints out find command
            output = ">" + currLine;
            System.out.println(output);

            // Calls find and prints out whether or not a City was found
            output = tree.find(expressionA[1], expressionA[2]);
            System.out.println(output);
        }

        else if (command.equals("remove")) {
            // Prints out remove command
            output = ">" + currLine;
            System.out.println(output);

            // Tries to remove city, if city doesn't exists, prints
            // "Record was not removed. It does not exist."
            output = tree.remove(expressionA[1], expressionA[2]);

            // BinTree.remove() returns "" if a city was removed
            // If it doesn't, then it means nothing was removed
            // so prints "Record was not removed. It does not exist."
            if (!output.equals("")) {
                System.out.println(output);
            }

        }

        else if (command.equals("print")) {
            System.out.println(">" + currLine);
            tree.print();
        }

        else if (command.equals("regionsearch")) {
            // Storing xy coordinates into variable
            int xCo = Integer.parseInt(expressionA[1]);
            int yCo = Integer.parseInt(expressionA[2]);

            // Prints out regionsearch command
            output = ">" + currLine;
            System.out.println(output);

            // Checks and prints error if not in bounds
            if (xCo >= tree.yWorld || yCo >= tree.xWorld) {
                System.out.println(
                    "The specified region is outside the known world.");
            }

            else {
                tree.regionsearch(expressionA[1], expressionA[2],
                    expressionA[3], expressionA[4]);
            }

        }
        // Invalid commands
        else {
            System.out.println(">" + currLine);
            System.out.println("ERROR! Unrecognized command: " + currLine);
        }

    }


    /**
     * Getter method that gets the tree associated with FileProcessor that
     * contains City data
     * Mainly used for testing purposes
     * 
     * @return the Tree of File processor
     */
    public BinTree getTree() {
        return tree;
    }

}
