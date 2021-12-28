import student.TestCase;

/**
 * Test all the functionality, especially adding, multiplying, and exponent of
 * BigNum class
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 *
 */
public class BigNumTest extends TestCase {

    /**
     * Sets up common methods, left blank
     */
    public void setUp() {
        // Intentionally left blank because each BigNum is different for all
        // test cases
    }


    /**
     * Test the creation of BigNum integer object
     */
    public void testCreation() {

        BigNum test = new BigNum("");
        assertEquals("", test.toString());

        test = new BigNum("1234");
        assertEquals("4321", test.toString());
    }


    /**
     * Test add method on 4 digit number with no carry over
     */
    public void testAdd0() {

        BigNum test1 = new BigNum("1234");
        BigNum test2 = new BigNum("4000");

        // System.out.println("Expected: " + test1.add(test2).toString());
        assertEquals("4325", test1.add(test2).toString());

    }


    /**
     * Test add method adding bigNum to itself
     */
    public void testAddItself() {
        BigNum test1 = new BigNum("1234");
        assertEquals("8642", test1.add(test1).toString());
    }


    /**
     * Test the add method on 4 digit number with one carry over
     */
    public void testAdd1() {
        BigNum test1 = new BigNum("1009");
        BigNum test2 = new BigNum("2");

        // Result should be 1011, which is 1101 in linked list
        // System.out.println("Expected: " + test1.add(test2).toString());
        assertEquals("1101", test1.add(test2).toString());
    }


    /**
     * Test the add method on 4 digit number with two carry over
     */
    public void testAdd2() {
        BigNum test1 = new BigNum("1099");
        BigNum test2 = new BigNum("22");

        // Result should be 1121, which is 1211 in linked list
        // System.out.println("Expected: " + test1.add(test2).toString());
        assertEquals("1211", test1.add(test2).toString());
    }


    /**
     * Test the add method with all carry over
     */
    public void testAddAll() {
        BigNum test1 = new BigNum("9999");
        BigNum test2 = new BigNum("1");

        // Result should be 10000, which is 00001 in linked list
        // System.out.println("Expected: " + test1.add(test2).toString());
        assertEquals("00001", test1.add(test2).toString());
    }


    /**
     * Test the add method with a 62 digit number and all carry over
     */
    public void testAddBig() {
        // This has 62 digits
        BigNum test1 = new BigNum(
            "99999999999999999999999999999999999999999999999999999999999999");
        BigNum test2 = new BigNum("1");

        // Result should be
        // 100000000000000000000000000000000000000000000000000000000000000,
        // which is
        // 000000000000000000000000000000000000000000000000000000000000001 in
        // linked list (63 digit long)
        // System.out.println("Expected: " + test1.add(test2).getStringRep());
        assertEquals(
            "000000000000000000000000000000000000000000000000000000000000001",
            test1.add(test2).toString());

        assertEquals(
            "100000000000000000000000000000000000000000000000000000000000000",
            test1.add(test2).getStringRep());
    }


    /**
     * Test multiplying by 0;
     */
    public void testMultiply0() {
        BigNum test1 = new BigNum("999999999999999999999999999");
        BigNum test2 = new BigNum("0");
        BigNum test3 = new BigNum("0");

        assertEquals("0", test1.multiply(test2).toString());
        assertEquals("0", test2.multiply(test3).toString());
    }


    /**
     * Test multiplying 2 simple 1 digit numbers
     */
    public void testMultiplySimple() {
        BigNum test1 = new BigNum("2");
        BigNum test2 = new BigNum("5");

        // System.out.println("Expected: " + test1.multiply(test2).toString());
        assertEquals("01", test1.multiply(test2).toString());

    }


    /**
     * Test multiplying 2 integers with varying digits
     */
    public void testMultiplyDiff() {
        BigNum test1 = new BigNum("29");
        BigNum test2 = new BigNum("5");

        assertEquals("541", test1.multiply(test2).toString());
    }


    /**
     * Test multiplying medium sized integers
     */
    public void testMultiplyMedium() {
        BigNum test1 = new BigNum("22222222222222222");
        BigNum test2 = new BigNum("10");

        assertEquals("022222222222222222", test1.multiply(test2).toString());

        BigNum test3 = new BigNum("99");
        BigNum test4 = new BigNum("999");
        assertEquals("10989", test3.multiply(test4).toString());

    }


    /**
     * Test multiplying integers bigger than allowed range of a standard Java
     * int
     */
    public void testMultiplyBig() {
        BigNum test1 = new BigNum("999999999999999999999999999999999");
        BigNum test2 = new BigNum("999999999999999999999999999999999");

        assertEquals("10000000000000000000000000000000089"
            + "9999999999999999999999999999999",

            test1.multiply(test2).toString());

        assertEquals("9999999999999999999999999999"
            + "99998000000000000000000000000000000001", test1.multiply(test2)
                .getStringRep());
    }


    /**
     * Test multiplying by itself
     */
    public void testMultiplyItself() {
        BigNum test1 = new BigNum("10");
        assertEquals("001", test1.multiply(test1).toString());
    }


    /**
     * Test exponentiation by base case of BigNum^1 and BigNum^0
     */
    public void testExponentBase() {
        BigNum test1 = new BigNum("21");

        assertEquals("12", test1.exponent(1).toString());
        assertEquals("21", test1.exponent(1).getStringRep());

        assertEquals("1", test1.exponent(0).toString());
    }


    /**
     * Test exponentiation on small numbers
     */
    public void testExponentSmall() {
        BigNum test1 = new BigNum("2");

        // 2^3 = 8
        assertEquals("8", test1.exponent(3).toString());
        assertEquals("8", test1.exponent(3).getStringRep());

        // 2^4 = 16
        assertEquals("61", test1.exponent(4).toString());
        assertEquals("16", test1.exponent(4).getStringRep());

        // 2^7 = 128
        assertEquals("821", test1.exponent(7).toString());
        assertEquals("128", test1.exponent(7).getStringRep());

        // 2^10 = 1024
        assertEquals("4201", test1.exponent(10).toString());
        assertEquals("1024", test1.exponent(10).getStringRep());

    }


    /**
     * Test exponentiation on invalid exponent (exp less than 0)
     */
    public void testExponentX() {
        BigNum test1 = new BigNum("2");
        assertNull(test1.exponent(-10));
    }


    /**
     * Test exponentiation on integers bigger than allowed range of a standard
     * Java int
     */
    public void testExponentBig() {
        BigNum test1 = new BigNum("123456789123456789");

        // 123456789123456789 ^ 2 = 15241578780673678515622620750190521
        assertEquals("12509105702622651587637608787514251", test1.exponent(2)
            .toString());
        assertEquals("15241578780673678515622620750190521", test1.exponent(2)
            .getStringRep());

        // 123456789123456789 ^ 10 =
        // 8225262673723652079894686990319143324765690034454891
        // 536195189893250839080839221336397044201660459053469601170
        // 46949453426283086050487204639652635846010822673782217799736601

        assertEquals("8225262673723652079894686990319143324765690034454891"
            + "536195189893250839080839221336397044201660459053469601170469"
            + "49453426283086050487204639652635846010822673782217799736601",
            test1.exponent(10).getStringRep());
    }
}
