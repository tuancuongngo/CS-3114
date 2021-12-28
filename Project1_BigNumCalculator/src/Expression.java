/**
 * This class will take in a String representing an arithmetic expression.
 * It will evaluate all arithmetic operations the expression and create
 * an output with the result of the expression
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 */
public class Expression {

    private String outputLine; // The final line to print out
    private String[] expressionA; // The array containing operands and operators
    private LinkedStack<BigNum> numStack; // The stack containing BigNum objects


    /**
     * Default constructor that takes in a String[] representation of the
     * expression and attempt to evaluate it by calling appropriate helper
     * methods
     * 
     * @param expressionA
     *            the array containing all operands and operators for that
     *            expression
     */
    public Expression(String[] expressionA) {

        // Initializes common variables
        numStack = new LinkedStack<BigNum>();
        this.expressionA = expressionA;
        outputLine = "";

        // Attempt to evaluate the expression
        evaluateExpression();
    }


    /**
     * Method to evaluate the current expression by using a loop to extract and
     * add all operands to a Stack, then performing calculations on them
     */
    public void evaluateExpression() {

        // Variable to keep track whether expression is valid or invalid
        boolean isWrong = false;

        for (int i = 0; i < expressionA.length; i++) {

            // temp variables to do addition, multiplication, and
            // exponentiation
            // for expression
            BigNum firstOp = null;
            BigNum secOp = null;
            BigNum result = null;

            // Use a switch statement to determine if operand or operators
            switch (expressionA[i]) {
                case "+": // Addition
                    // Add + sign to the output line
                    outputLine += expressionA[i] + " ";

                    // Need at least 2 operands in stack to perform
                    // arithmetic
                    // operation
                    if (numStack.size() < 2) {
                        isWrong = true;
                        break;
                    }

                    // Pop 2 top elements, add them together, then
                    // push onto stack
                    result = numStack.pop().add(numStack.pop());
                    numStack.push(result);
                    break;

                case "*": // Multiplication

                    // Add * sign to the output line
                    outputLine += expressionA[i] + " ";

                    // Need at least 2 operands in stack to perform
                    // arithmetic
                    // operation
                    if (numStack.size() < 2) {
                        isWrong = true;
                        break;
                    }

                    // Pop 2 top elements, multiply them, then
                    // push onto stack
                    result = numStack.pop().multiply(numStack.pop());
                    numStack.push(result);
                    break;

                case "^": // Exponentiation
                    // Add ^ sign to the output line
                    outputLine += expressionA[i] + " ";

                    // Need at least 2 operands in stack to perform
                    // arithmetic
                    // operation
                    if (numStack.size() < 2) {
                        isWrong = true;
                        break;
                    }

                    // Pop 2 top elements, exponentiate them, then
                    // push onto stack
                    firstOp = numStack.pop();
                    secOp = numStack.pop();
                    result = secOp.exponent(Integer.parseInt(firstOp
                        .getStringRep()));
                    numStack.push(result);

                    break;

                default: // Operand otherwise

                    // Call helper method to create the BigNum for the operand
                    // and push it onto the stack
                    createNum(expressionA[i]);

                    break;
            } // Switch
        } // For loop

        // Add equal sign to end of input line
        outputLine += "= ";

        // After for loop, if it's a valid expression, the variable to track
        // whether invalid expression should be false, and size of the stack
        // should be 1 containing the final result
        if (!isWrong && numStack.size() == 1) {
            outputLine += numStack.pop().getStringRep();
        }

        // Call method to print out the full expression
        printOut();
    }


    /**
     * Prints out the evaluated expression to console
     */
    public void printOut() {
        System.out.println(outputLine);
    }


    /**
     * Return the evaluated expression as a String
     * 
     * @return the expression as a String
     */
    public String toString() {
        return outputLine;
    }


    /**
     * Create a bigNum object, adds it to existing stack of BigNums, and update
     * the output line
     * 
     * @param numStr
     *            the String Integer representation to create BigNum on
     */
    private void createNum(String numStr) {

        String input = numStr;

        // Remove all leading 0's from String containing Integer. However, if
        // input is only 0, don't remove it
        input = replaceZero(input);

        // Add it to the output line
        outputLine += input + " ";

        // Create a new bigNum object and push it onto the stack
        BigNum currNum = new BigNum(input);
        numStack.push(currNum);
    }


    /**
     * Method to trim leading 0's
     * 
     * @param line
     *            the String integer representation
     * @return 0 if the line is 0 or any numbers of 0 combined (e.g. 00000000)
     *         or number without leading 0's
     */
    private String replaceZero(String line) {

        boolean isZero = true;

        // Determine if the String integer represents 0
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != '0') {
                isZero = false;
            }
        }

        // If it is a 0, return 0
        if (isZero) {
            return "0";
        }

        // Otherwise, trim leading 0's
        return line.replaceFirst("^0*", "");
    }
}
