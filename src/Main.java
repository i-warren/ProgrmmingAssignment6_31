import java.util.*;

// Class validates card number using the Luhn Check
public class Main {
    public static void main(String[] args) {
        //Prompt user to enter credit card number
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a credit card number: ");
        String creditCardNum = input.next();

        if (isValid(Long.parseLong(creditCardNum))) {
            System.out.println(creditCardNum + " is valid.");
        } else {
            System.out.println(creditCardNum + " is invalid.");
        }
    }

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        String creditCardAsString = String.valueOf(number);
        // Check length of number for appropriate range
        if (creditCardAsString.length() < 13 || creditCardAsString.length() > 16) {
            return false;
        // Check for valid prefix
        } else if (prefixMatched(number, 1) || prefixMatched(number, 2)) {
            // TODO Proceed through steps
            return true;
        }
        return false;

    }

//    /** Get the result from Step 2 */
//    public static int sumOfDoubleEvenPlace(long number) {
//
//
//    }

//    /** Return this number if it is a single digit, otherwise,
//     * return the sum of the two digits */
//    public static int getDigit(int number) {
//
//    }
//
//    /** Return sum of odd-place digits in number */
//    public static int sumOfOddPlace(long number) {
//
//    }

    /** Return true if the number d is a prefix for number
//     * converts number to string and uses .startsWith() to check against d
     **/
    public static boolean prefixMatched(long number, int d) {
        // get prefix and cast to int
        int prefix = (int)getPrefix(number, d);

        //check prefix against valid
        return switch (prefix) {
            case 4, 37, 5, 6 -> true;
            default -> false;
        };
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        String creditCardAsString = String.valueOf(d);
        return creditCardAsString.length();
    }

    /** Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number. */
    public static long getPrefix(long number, int k) {
        if (getSize(number) < k) {
            return number;
        }
        //convert to string
        String creditCardAsString = String.valueOf(number);
        // take substring of 0,k and cast to long
        return Long.parseLong(creditCardAsString.substring(0, k));
    }


}
