package src.valid_Palindrome_125;

import org.junit.Test;

import java.util.List;

public class Solution {
    /**
     * 错误
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s == "") return true;
        char[] chars = s.toLowerCase().trim().toCharArray();
        int lo = 0, hi = chars.length-1;
        while(lo <=hi) {
            while (chars[lo] < 97 || chars[lo] > 122) {
                lo++;
            }
            while (chars[hi] < 97 || chars[hi] > 122) {
                hi--;
            }
            if (chars[lo] != chars[hi]) return false;
            else {
                lo++;
                hi--;
            }
        }
        System.out.println(chars);
        return true;
    }


    /**
     * java api
     * @param s
     * @return
     */
    public boolean isPalindrome2(String s) {
        StringBuilder sgood = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        StringBuilder sgood_rev = new StringBuilder(sgood).reverse();
        return sgood.toString().equals(sgood_rev.toString());
    }



    public boolean isPalindrome3(String s) {
        StringBuilder sgood = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        int n = sgood.length();
        int left = 0, right = n-1;
        while(left < right) {
            if(Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) return false;
            ++left;
            --right;
        }
        return true;
    }


    public boolean isPalindrome4(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) ++left;
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) --right;
            if(left < right) {
                if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
            }
            ++left;
            --right;
        }
        return true;
    }



    @Test
    public void test() {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));

    }
}
