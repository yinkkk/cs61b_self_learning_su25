package Arithmetic;

/** Simple Arithmetic Class.
 * @author Josh Hug
 * */
public class Arithmetic {

    /** Computes product of two ints.
     * @param a Value 1
     * @param b Value 2
     * @return Product of a and b
     * */
    public static int product(int a, int b) {
        int result =a*b;
        System.out.println("sum called with " + a + ", " + b + " result: " + result );
        return result;
    }

    /** Computes sum of two ints (incorrectly).
     * @param a Value 1
     * @param b Value 2
     * @return Sum of a and b
     * */
    public static int sum(int a, int b) {
        int result = a+b;
        System.out.println("sum called with " + a + ", " + b + " result: " + result );
        return result;
    }

}
