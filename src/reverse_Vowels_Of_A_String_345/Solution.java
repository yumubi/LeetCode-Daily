package src.reverse_Vowels_Of_A_String_345;

import javax.swing.text.html.ListView;
import java.lang.invoke.DirectMethodHandle$Holder;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * 超时
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        int left = 0, right = s.length() - 1;
        char[] ch = s.toCharArray();
        while (left < right) {
            if (!(ch[left] == 'a' || ch[left] == 'e'
                    || ch[left] == 'i' || ch[left] == 'o'
                    || ch[left] == 'u'))
                left++;
            if (!(ch[right] == 'a' || ch[right] == 'e'
                    || ch[right] == 'i' || ch[right] == 'o'
                    || ch[right] == 'u'))
                right++;
            swap(ch, left, right);
        }
        return String.valueOf(ch);
        
    }
        public void swap(char[] chars, int left, int right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }

    /**
     * 双指针
     * @param s
     * @return
     */
    public String reverseVowels2(String s) {
            int n = s.length();
            char[] arr = s.toCharArray();
            int i = 0, j = n -1;
            while(i < j) {
                while (i < n && !isVowel(arr[i])) {
                    ++i;
                }
                while(j > 0 && !isVowel(arr[j])) {
                    --j;
                }
                if(i < j) {
                    swap1(arr, i, j);
                    ++i;
                    --j;
                }
            }
            return new String(arr);
        }

        public boolean isVowel(char ch) {
            return "aeiouAEIOU".indexOf(ch) >= 0;
        }
        public void swap1(char[] arr, int i, int j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }


    /**
     * 由于元音字母相对固定，因此我们可以使用容器将其存储，并使用 static 修饰，确保整个容器的创建和元音字母的填入在所有测试样例中只会发生一次。
     * 我们期望该容器能在 O(1) 的复杂度内判断是否为元音字母，可以使用语言自带的哈希类容器（P2代码）或是使用数组模拟（P1 代码）。
     * 一些细节：由于题目没有说字符串中只包含字母，因此在使用数组模拟哈希表时，我们需要用当前字符减去 ASCII 码的最小值（空字符），而不是 'A'
     * @param s
     * @return
     */
        static boolean[] hash = new boolean[128];
        static char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
        static {
            for(char c : vowels) {
                hash[c - ' '] = hash[Character.toLowerCase(c) - ' '] = true;
            }
        }
        public String reverseVowelsP1(String s) {
            char[] cs = s.toCharArray();
            int n = s.length();
            int l = 0, r = n - 1;
            while(l < r) {
                if(hash[cs[l] - ' '] && hash[cs[r] - ' ']) {
                    swap(cs, l++, r--);
                } else {
                    if(!hash[cs[l] - ' ']) l++;
                    if(!hash[cs[r] - ' ']) r++;
                }
            }
            return String.valueOf(cs);
        }


    static char[] vowels2 = new char[]{'a', 'e', 'i', 'o', 'u'};
    static Set<Character> set = new HashSet<>();
    static {
        for(char c : vowels2) {
            set.add(c);
            set.add(Character.toLowerCase(c));
        }
    }
        public String reverseVowelsP2(String s) {
           char[] cs = s.toCharArray();
           int n = s.length();
           int l = 0, r = n - 1;
           while(l < r) {
               if(set.contains(cs[l]) && set.contains(cs[r])) swap(cs, l++, r--);
               else {
                   if(!set.contains(cs[l])) l++;
                   if(!set.contains(cs[r])) r--;
               }
           }
           return String.valueOf(cs);
        }




}
