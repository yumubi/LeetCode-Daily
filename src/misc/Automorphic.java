package src.misc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Automorphic {
    /**
     * 在数字上进行循环比较
     * @param number
     * @return
     */
    public boolean isAutomorphicUsingLoop(int number) {
        int square = number * number;
        while(number > 0) {
            if(number % 10 != square % 10)  return false;
            number /= 10;
            square /= 10;
        }
        return true;
    }

    public boolean isAutomorphicusingMath(int number) {
        int square = number * number;
        int numberOfDigits = (int) Math.floor(Math.log10(number) + 1);
        int lastDigits = (int) (square % (Math.pow(10, numberOfDigits)));

        return number == lastDigits;
    }

    @Test
    public void test() {
       assertTrue(isAutomorphicUsingLoop(76));
       assertTrue(isAutomorphicusingMath(43));
    }
}
