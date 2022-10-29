package src.Palindrome_Number_09;

import java.util.Arrays;

public class Main {

}

/**
 * 双指针
 * log n
 */
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0 ) return false;

        String s =  String.valueOf(x);

        int lo = 0;
        int hi = s.length() - 1;
        while( lo <= hi) {
            if(s.charAt(lo) != s.charAt(hi)) return false;
            //把lo++和hi--写在while判断条件里面报错
            lo++;
            hi--;
        }
        return true;
    }
}


class answer1 {
    public boolean isPalindrome(int x) {
        String reverseStr = (new StringBuilder(x + "")).reverse().toString();
        return reverseStr.equals(x + "");
    }
}


/**
 * 反转一半数字
 * 时间:O(log n)
 * 空间: O(1)
 *
 */

class answer2 {
    public boolean isPalindrome(int x) {
        if(x < 0 || x %10 == 0 && x != 0) return false;
        int revertedNum = 0;
        while(x > revertedNum) {
            revertedNum = revertedNum * 10 + x % 10;
            x /= 10;
        }

        //位数为奇数需要除去中位数
        return x == revertedNum || x == revertedNum / 10;
    }
}
