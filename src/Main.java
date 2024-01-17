import java.util.*;

// Class validates card number using the Luhn Check
// Check for number length 13-16
// Check for prefixes 4,5,37, or 6
// 1. Double every second digit from right to left. If doubling of a digit results in a
// two-digit number, add up the two digits to get a single-digit number.
//
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
        if (creditCardAsString.length() >= 13 && creditCardAsString.length() <= 16) {
            // Check for valid prefix
            if (prefixMatched(number, 1) || prefixMatched(number, 2)) {
                // Check sums for divisible by 10
                /** TEST ONLY
                int division = ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10);
                System.out.print("DIV CHECK" + division);
                 */
                if ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0) {
                    return true;
                }
            }
        }
        return false;

    }

    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        number /= 10;
        while (number > 0) {
            sum += getDigit((int)(number % 10)*2);
            number /= 100;
        }
        /** TEST ONLY
        System.out.println("Sum even: " + sum);
         */
        return sum;

    }

    /** Return this number if it is a single digit, otherwise,
     * return the sum of the two digits */
    public static int getDigit(int number) {
        if (getSize(number) > 1) {
            int d1 = number % 10;
            int d2 = number / 10;
            return d1 + d2;
        }
        return number;
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number) {
        int sum = 0;

        while (number > 0) {
            sum += getDigit((int)(number % 10));
            number /= 100;
        }
        /** TEST ONLY
        System.out.println("Sum odd: " + sum);
         */
        return sum;
    }

    /** Return true if the number d is a prefix for number */
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