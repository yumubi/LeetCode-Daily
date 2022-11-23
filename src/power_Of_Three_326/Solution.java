package src.power_Of_Three_326;

public class Solution {
    public boolean isPowerOfThree(int n) {
        if(n == 3 || n == 1) return false;
        if(n < 3) return false;
        else
            if(n % 3 == 0) return isPowerOfThree(n / 3);
            else return false;
    }
}
