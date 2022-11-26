package src.first_Unique_Character_In_A_String_387;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int firstUniqChar(String s) {
        int ans = -1;
        char[] chars = new char[26];

        for(int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < chars.length; i++) {
            if(chars[i] > 1)
                 s  = s.replace( (char)(i + 97), 'A');
        }

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != 'A') {
                ans = i;
                return ans;
            }
        }
        return ans;
    }

    /**
     * 使用哈希表存储频数
     * 我们可以对字符串进行两次遍历。
     * 在第一次遍历时，我们使用哈希映射统计出字符串中每个字符出现的次数。
     * 在第二次遍历时，我们只要遍历到了一个只出现一次的字符，那么就返回它的索引，否则在遍历结束后返回 -1。
     * @param s
     * @return
     */
    public int firstUniqChar1(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < s.length(); ++i) {
            if(frequency.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }



    @Test
    public void test() {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
        System.out.println((char)97);
    }
}
