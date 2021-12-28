
/**
 * Class that will represent integers in a linked list
 * Some integers will go out of bounds because of their size,
 * but integers in this class will never go out of bounds
 * because all digits are stored in a LinkedList
 * 
 * @author Tuan Cuong
 * @author Yongjae Lim
 * @version 07/16/2020
 */
public class BigNum {

    private LinkedList<Integer> numList; // The linked list to store the String
                                         // as Integer
    private String numStr; // The String representing the number


    /**
     * Default constructor that calls helper methods to create a BigNum object
     * 
     * @param numStr
     *            the String representation of the integer
     */
    public BigNum(String numStr) {
        numList = new LinkedList<Integer>();
        this.numStr = numStr;

        // Cal helper method to create LinkedList
        processNumber();
    }


    /**
     * Private method to create BigNum object for the String Integer
     */
    private void processNumber() {
        // Creating the integer by getting each digit in the String from right
        // to left and adding it to the LinkedList from right to left
        for (int i = (numStr.length() - 1); i >= 0; i--) {
            int currInt = Character.getNumericValue(numStr.charAt(i));
            numList.add(currInt);
        }

    }


    /**
     * Returns a string representation of the integer
     * If a integer is CBA, it will contain A, B, and C
     * and the following should be returned: "ABC" (Without the quotations)
     *
     * @return a string representing the list
     */
    public String toString() {
        return numList.toString();
    }


    /**
     * Get the Integer in the form of a String
     * E.g. if BigNum() linked list is 5=>2=>1
     * It will return 125
     *
     * @return the Integer as a String
     */
    public String getStringRep() {
        return numStr;
    }


    /**
     * Add current BigNum to the BigNum in parameter
     * 
     * @param a
     *            the BigNum to add to
     * @return the resulting BigNum
     */
    public BigNum add(BigNum a) {

        // If adding to itself, just multiply by 2
        if (this.equals(a)) {
            return this.multiply(new BigNum("2"));
        }

        // BigNum that will store final result
        BigNum finalResult = new BigNum("");

        // Integer that will store the sum
        LinkedList<Integer> sum = new LinkedList<Integer>();

        // String that will store the result of the sum
        String sumStr = "";

        // Store the number of digits of each BigNum
        int thisSize = this.numList.getSize();
        int aSize = a.numList.getSize();

        // Find which Integer linked list is bigger/has more digits
        int numDigit = Math.max(thisSize, aSize);

        // Variables to do single digit addition
        int operand1 = 0; // for the digit in this BigNum
        int operand2 = 0; // for the digit in BigNum a

        int currSum = 0; // Variable to store the sum of each single digit
                         // addition

        // Variable to store remainder when adding
        int remain = 0;

        // Add each digit together
        for (int i = 0; i < numDigit; i++) {

            // Get the current digit in each Integer list by calling helper
            // method, if no digit left, 0 is returned
            operand1 = checkDigit(i, thisSize, this.numList);
            operand2 = checkDigit(i, aSize, a.numList);

            // Add the 2 digits together and remainder, if there is any
            currSum = remain + operand1 + operand2;

            // Reset the remainder;
            remain = 0;

            // If the sum is double digit, add to remainder
            if (currSum > 9) {
                currSum = currSum % 10;
                remain = 1;
            }

            // Add the digit to Integer list containing resulting int
            sum.add(currSum);
            // Update the String representing the Integer in BigNum
            sumStr = currSum + sumStr;

            // If it's at the last digit of addition but there is still a
            // remainder
            if (i + 1 == numDigit && remain != 0) {
                sum.add(remain);
                // Update the String representing the Integer in BigNum
                sumStr = remain + sumStr;
            }
        }
        // Assign the resulting Integer to result BigNum and return to user
        finalResult.numList = sum;
        finalResult.numStr = sumStr;
        return finalResult;
    }


    /**
     * Calculate the product of multiplying 2 BigNums
     * 
     * @param a
     *            the BigNum to multiply with
     * @return the resulting BigNum
     */
    public BigNum multiply(BigNum a) {
        // If multiplying by 0, return 0
        if (this.numList.toString().equals("0") || a.numList.toString().equals(
            "0")) {
            return new BigNum("0");
        }

        // If multiplying by itself, square it
        if (this.equals(a)) {
            return this.exponent(2);
        }

        // Integer that will store the product of each multiplication row
        LinkedList<Integer> pro = new LinkedList<Integer>();

        // BigNum that will store final product
        BigNum finalResult = new BigNum("");

        // Store the number of digits of each BigNum
        int thisSize = this.numList.getSize();
        int aSize = a.numList.getSize();

        // Variables to do single digit multiplication
        int operand1 = 0; // for the digit in this BigNum
        int operand2 = 0; // for the digit in BigNum a

        int currPro = 0; // Variable to store the product of each single digit
                         // multiplication

        // Variable to store carry-over / remainder when multiplying
        int remain = 0;

        // This for loop accesses every digit in the BigNum passed in as
        // parameter
        for (int i = 0; i < aSize; i++) {
            // Clear the current product linked list for next multiplication
            pro.clear();

            // Add appropriate 0's to start of product linked list based on
            // position being multiplied
            for (int offset = 0; offset < i; offset++) {
                pro.add(0);
            }

            operand2 = checkDigit(i, aSize, a.numList);

            // This for loop accesses every digit in the BigNum that called this
            // method
            for (int j = 0; j < thisSize; j++) {
                operand1 = checkDigit(j, thisSize, this.numList);

                // Perform the single digit multiplication, adding remainder
                // from previous multiplication, if there's any, and add it to
                // list
                int tempPro = operand2 * operand1 + remain;

                // Reset remainder
                remain = 0;

                // Update the current product to tempProduct, if tempProduct is
                // 1 digit, otherwise, enter if below
                currPro = tempPro;

                // If tempPro is 2 digits, get the next remainder if there is
                // one, which turns product into 1 digit
                if (tempPro > 9) {
                    currPro = tempPro % 10;
                    remain = tempPro / 10;
                }

                // Add the updated product to resulting Integer Linked list
                pro.add(currPro);

                // If it's at the last digit of multiplication but there is
                // still a remainder. Then setting it to 0
                if (j + 1 == thisSize && remain != 0) {
                    pro.add(remain);
                    remain = 0;
                }
            } // for

            // temp BigNum to store the result of the multiplcation row
            BigNum temp = new BigNum("");
            temp.numList = pro;

            // Add result of multiplication row to BigNum that stores final
            // product
            finalResult = finalResult.add(temp);
        } // for
        return finalResult;
    }


    /**
     * Find the result of BigNum^ex using recursion
     * 
     * @param exp
     *            the exponent
     * @return BigNum containing result of BigNum^ex
     *         null if invalid exponentiation
     */
    public BigNum exponent(int exp) {

        // x^0 = 1 for any x
        if (exp == 0) {
            return new BigNum("1");
        }

        // Base case recursion
        // x^1 = x for any x
        if (exp == 1) {
            return this;
        }

        // If the exponent is even, multiply BigNum by itself, then call
        // exponent(), passing in the resulting BigNum and (exp/2) as new
        // exponent
        if (exp % 2 == 0) {

            // Squaring/Multiplying BigNum by itself
            BigNum squared = this.multiply(new BigNum(this.getStringRep()));
            return (squared.exponent(exp / 2));
        }

        // If the exponent is odd, square BigNum, then multiply current BigNum
        // with the squared BigNum raised to (exp-1)/2
        // BigNum * (BigNum^2)^(exp-1)/2
        if (exp % 2 > 0) {
            BigNum squared = this.multiply(new BigNum(this.getStringRep()));
            return this.multiply(squared.exponent((exp - 1) / 2));
        }

        return null; // If invalid exponent, return null
    }


    /**
     * Helper method to determine whether there are still digits in the linked
     * list
     * If there is, it will return that digit
     * If there isn't it will return 0
     * 
     * @param currDigit
     *            the current digit in that integer in the linked list
     * @param totalDigit
     *            the total number of digits in that integer in the linked list
     * @return the appropriate digit, or 0
     */
    private int checkDigit(
        int currDigit,
        int totalDigit,
        LinkedList<Integer> list) {

        // If there are still digits in the list, return to user
        if (currDigit < totalDigit) {
            int digit = list.getCurrData();

            // If it is at the last element in list, do not move to next() node
            // or will throw exception
            // Resets currNode for the list
            if (currDigit + 1 == totalDigit) {
                list.moveToStart();
                return digit;
            }

            // Otherwise, move curr to next digit in list
            list.next();

            return digit;
        }
        // Otherwise, replace it with 0 since the other list still has digits to
        // add
        return 0;
    }

}
