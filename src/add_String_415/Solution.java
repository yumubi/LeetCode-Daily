package src.add_String_415;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Stack;

public class Solution {
    /**
     * 这又臭又长的code
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
            int len1 = num1.length();
            int len2 = num2.length();
            if(len1 > len2) return addStrings(num2, num1);
            char[] n1 = num1.toCharArray();
            char[] n2 = num2.toCharArray();
            int [] res = new int[len2 + 1];
            int idx1 = len1 - 1;
            int idx2 = len2 - 1;
            int carry = 0;
            for(int i = 0; i < len2; i++) {
                if(idx1 >= 0 && idx2 >= 0) {
                    int result = (n1[idx1] - '0') + (n2[idx2] - '0') + carry;
                    res[i] = result % 10;
                    carry = result / 10;
                    idx1--;
                    idx2--;
                }
                else if(idx2 >= 0) {
                    int result = (n2[idx2] - '0') + carry;
                    res[i] = result % 10;
                    carry = result / 10;
                    idx2--;
                }
            }
            res[len2] = carry;
        StringBuilder sb = new StringBuilder();
        if(res[len2] != 0) sb.append(res[len2]);
        for(int i = len2 - 1; i >= 0; i--) {
            sb.append(res[i]);
        }
        return sb.toString();
    }


    public String addString1(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while(i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    /**
     * 使用栈结构
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings2(String num1, String num2) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while(i >= 0 || j >= 0 || carry  != 0) {
            carry += i >= 0 ? num1.charAt(i--) - '0' : 0;
            carry += j >= 0 ? num2.charAt(j--) - '0' : 0;
            stack.push(carry % 10);
            carry = carry / 10;
        }
        while(!stack.isEmpty()) sb.append(stack.pop());
        return sb.toString();
    }


    /**
     * 递归
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings3(String num1, String num2) {
        return addBinaryHelper(num1, num1.length() - 1, num2, num2.length() - 1, 0);
    }
    public String addBinaryHelper(String num1, int indexA, String num2, int indexB, int carry) {
        if(indexA < 0 && indexB < 0 && carry == 0) return "";
        carry += indexA < 0 ? 0 : num1.charAt(indexA--) - '0';
        carry += indexB < 0 ? 0 : num2.charAt(indexB--) - '0';
        int digit = carry % 10;
        String res = addBinaryHelper(num1, indexA, num2, indexB, carry);
        return res + digit;
    }

    /**
     * 作弊写法
     * @param num1
     * @param num2
     * @return
     */
    public String addBinaryHelper(String num1, String num2) {
        return new BigInteger(num1).add(new BigInteger(num2)).toString();
    }


    @Test
    public void test() {
        String s1 = "19";
        String s2 = "89";
        System.out.println(addStrings(s1, s2));
    }
}
