package src.valid_Anagram_242;

import org.junit.Test;

import java.util.*;

public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();
        toList(s, list1);
        toList(t, list2);
        Collections.sort(list1);
        Collections.sort(list2);
        return list1.equals(list2);
    }

    public void toList(String s, List<Character> res) {
        for(Character ch : s.toCharArray()) res.add(ch);
    }


    /**
     * 排序
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        if(s.length() != t.length()) return false;
        char[] str1 = s.toCharArray();
        char[] str2 =t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }


    /**
     * hashtable
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram3(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] table = new int[26];
        for(int i = 0; i < s.length(); i++) table[s.charAt(i) - 'a']++;
        for(int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if(table[t.charAt(i) - 'a'] < 0) return false;
        }
        return false;


                //装逼写法
//        int[] counts = new int[26];
//        t.chars().forEach(tc -> counts[tc - 'a']++);
//        s.chars().forEach(cs -> counts[cs - 'a']--);
//        return Arrays.stream(counts).allMatch(c -> c == 0);
    }

    ///////////////////////////////////////////////////////////////////

    /**
     * Unicode 一个字符可能对应多个字节的问题，
     * 字符是离散未知的，
     * 因此我们用哈希表维护对应字符的频次即可。
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram4(String s, String t) {
        if(s.length() != t.length()) return false;
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if(table.get(ch) < 0) return false;
        }
        return true;
    }







    @Test
    public void test() {
        String s = "aacc";
        String t = "caac";
        System.out.println(isAnagram(s, t));
    }
}
