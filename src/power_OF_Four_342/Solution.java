package src.power_OF_Four_342;

public class Solution {
    public boolean isPowerOfFour(int n) {
        if(n == 4 || n == 1) return true;
        if(n < 4) return false;
        else {
            if(n % 4 == 0) return isPowerOfFour(n / 4);
            else return false;
        }
    }
}
