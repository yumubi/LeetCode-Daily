package src.find_The_Difference_389;

import java.util.Arrays;

public class Solution {
    public char findTheDifference(String s, String t) {
        char[] orgin = s.toCharArray();
        char[] inserted = t.toCharArray();
        Arrays.sort(orgin);
        Arrays.sort(inserted);
        int idx1 = 0, idx2 = 0;
        while (idx1 < orgin.length) {
            if(orgin[idx1] != inserted[idx2]) return inserted[idx2];
            idx1++;
            idx2++;
        }
        return inserted[t.length() - 1];
    }


    /**
     * 计数
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference1(String s, String t) {
        int[] cnt = new int[26];
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            cnt[ch - 'a']++;
        }
        for(int i = 0; i < t.length(); ++i) {
            char ch = t.charAt(i);
            cnt[ch - 'a']--;
            if(cnt[ch - 'a'] < 0) return ch;
        }
        return ' ';
    }

    /**
     * 求和
     * 将字符串 s 中每个字符的 ASCII 码的值求和，对字符串 t 同样的方法，求出和的差值
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference2(String s, String t) {
        int as = 0, at = 0;
        for(int i = 0; i < s.length(); ++i) {
            as += s.charAt(i);
        }
        for(int i = 0; i < t.length(); ++i) at += t.charAt(i);
        return (char) (at - as);
    }

    /**
     * 如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符。类似于「136. 只出现一次的数字」，我们使用位运算的技巧解决本题。
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference3(String s, String t) {
        int ret = 0;
        for(int i = 0; i < s.length(); ++i) ret ^= s.charAt(i);
        for(int i = 0; i < t.length(); ++i) ret ^= t.charAt(i);
        return (char) ret;
    }














}
