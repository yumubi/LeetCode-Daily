package src.ransom_Note_383;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 哈希解决
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canContruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()) return false;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < magazine.length(); i++) {
            char key = magazine.charAt(i);
            int count = map.getOrDefault(key, 0) + 1;
            map.put(key, count);
        }
        for(int i = 0; i < ransomNote.length(); i++) {
            char targetKey = ransomNote.charAt(i);
            int count = map.getOrDefault(targetKey, 0);
            if(count == 0) return false;
            else if(count > 0) map.put(targetKey, --count);
        }
        return true;
    }


    /**
     * 字符统计
     * 要求使用字符串 magazine 中的字符来构建新的字符串 ransomNote，且ransomNote 中的每个字符只能使用一次，
     * 只需要满足字符串 magazine 中的每个英文字母 (’a’-’z’) 的统计次数都大于等于 ransomNote 中相同字母的统计次数即可。
     * 如果字符串 magazine 的长度小于字符串 ransomNote 的长度，则我们可以肯定magazine 无法构成 ransomNote，此时直接返回 false。
     * 首先统计 magazine 中每个英文字母 a 的次数 cnt[a]，再遍历统计 ransomNote 中每个英文字母的次数，
     * 如果发现ransomNote 中存在某个英文字母 c 的统计次数大于 magazine 中该字母统计次数 cnt[c]，则此时我们直接返回false。
     * 时间复杂度：O(m + n)其中 m 是字符串 ransomNote 的长度，n 是字符串 magazine 的长度，我们只需要遍历两个字符一次即可。
     * 空间复杂度：O(∣S∣)，S 是字符集，这道题中 S 为全部小写英语字母，因此 ∣S∣=26。
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canContruct1(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()) return false;
        int[] cnt = new int[26];
        for(char c : magazine.toCharArray()) cnt[c - 'a']++;
        for(char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) return false;
        }
        return true;
    }

    public boolean canContruct2(String a, String b) {
        int[] cnt = new int[26];
        for(char c : b.toCharArray()) cnt[c - 'a']++;
        for(char c : a.toCharArray()) if(--cnt[c - 'a'] < 0) return false;
        return true;
    }

}
