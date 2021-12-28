import java.util.Scanner;
import student.TestCase;

/**
 * Test the functionality of the Expression class
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 */
public class ExpressionTest extends TestCase {

    /**
     * Sets up the initial conditions for all test cases
     */
    public void setUp() {
        // Intentionally left blank
    }


    /**
     * Test the evaluateExpression method on a valid expression
     */
    public void testValidEvaluateExpression() {
        
        String input = "000000056669777     99999911111 + "
            + "352324012 + 03 ^          555557778 *";
        Scanner scan = new Scanner(input);
        FileProcessor pro = new FileProcessor(scan);
        Expression ex = new Expression(pro.getCurrArray());

        // Should print out everything and result
        String expected = "56669777 99999911111 + 352324012 + 3 "
            + "^ 555557778 * = 562400792227677956625810678708149922000000";

        assertEquals(expected, ex.toString());
    }


    /**
     * Test the evaluateExpression method on a invalid expression
     */
    public void testInvalidEvaluateExpression() {

        // Passing in an invalid input expression
        String input = "03432 03333 09999 + * ^ * * 00006666 +";

        Scanner scan = new Scanner(input);
        FileProcessor pro = new FileProcessor(scan);
        Expression ex = new Expression(pro.getCurrArray());

        // Should print out everything and an equal sign at the end
        String expected = "3432 3333 9999 + * ^ * * 6666 + = ";

        assertEquals(expected, ex.toString());
    }


    /**
     * Test the evaluateExpression method on a invalid expression multiplying by
     * 0
     */
    public void testEvaluateExpression0() {
        // Passing in an input expression
        String input = "999999999  0 *";

        Scanner scan = new Scanner(input);
        FileProcessor pro = new FileProcessor(scan);
        Expression ex = new Expression(pro.getCurrArray());

        // Should print out everything with results
        String expected = "999999999 0 * = 0";

        assertEquals(expected, ex.toString());
    }


    /**
     * Test simple invalid add expression
     */
    public void testEvaluateExpressionAdd() {
        String input = "002 +";

        Scanner scan = new Scanner(input);
        FileProcessor pro = new FileProcessor(scan);
        Expression ex = new Expression(pro.getCurrArray());

        // Should print out everything and an equal sign at the end
        String expected = "2 + = ";

        assertEquals(expected, ex.toString());
    }
    
    
    /**
     * Test complicated invalid expression
     */
    public void testEvaluateExpressionComp() {
        
        String input = " 5555555 333333 5454353 999999 666666 01 ^ * * +";

        Scanner scan = new Scanner(input);
        FileProcessor pro = new FileProcessor(scan);
        Expression ex = new Expression(pro.getCurrArray());

        // Should print out everything and an equal sign at the end
        String expected = "5555555 333333 5454353 999999 666666 1 ^ * * + = ";

        assertEquals(expected, ex.toString());
    }
    
}
