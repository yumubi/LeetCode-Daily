package src.reverse_String_344;

public class Solution {
    /**
     * tmd,超时了---原来是指针忘记迭代了orm
     * @param s
     */
    public void reverseString(char[] s){
        int left = 0, right = s.length - 1;
        while (left < right) {
            s[left] = (char)(s[left] + s[right]);
            s[right] = (char)(s[left] - s[right]);
            s[left] = (char)(s[left] - s[right]);
            left++;
            right--;
        }
    }


    public void reverseString2(char[] s) {
        int n = s.length;
        for(int left = 0, right = n - 1; left < right; ++left) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

}
