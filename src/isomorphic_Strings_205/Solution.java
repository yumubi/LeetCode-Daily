package src.isomorphic_Strings_205;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            if(!map.containsKey(s.charAt(i))) {
                if(map.containsValue(t.charAt(i))) return false;
                map.put(s.charAt(i), t.charAt(i));
            }
            else {
                if(map.get(s.charAt(i)) != t.charAt(i)) return false;
            }
        }
        return true;
    }

    /**
     * 哈希表
     * 判断 s 和 t 每个位置上的字符是否都一一对应，即两者是否是双射的关系
     * 我们维护两张哈希表，第一张哈希表 s2t 以 ss 中字符为键，映射至 t 的字符为值，第二张哈希表 t2s 以 t 中字符为键，映射至 s 的字符为值。
     * 从左至右遍历两个字符串的字符，不断更新两张哈希表，如果出现冲突（即当前下标 index 对应的字符 s[index] 已经存在映射且不为 t[index]
     * 或当前下标 index 对应的字符t[index] 已经存在映射且不为 s[index]）时说明两个字符串无法构成同构，返回 false。
     * 如果遍历结束没有出现冲突，则表明两个字符串是同构的，返回 true 即可。
     * 时间复杂度：O(n)
     * 空间复杂度：O(∣Σ∣)，其中 Σ 是字符串的字符集。哈希表存储字符的空间取决于字符串的字符集大小，最坏情况下每个字符均不相同，需要O(∣Σ∣) 的空间。
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for(int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if( (s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x) ) return false;
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }



    /**
     * 最前索引比较
     */
    public boolean isIsomorphic3(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            //取当前位置字符第一次出现的索引对比
            //情况1(一个字符映射多个): foo 与 bar, 处理第二个o时发现索引不同
            //情况2(多个字符映射同个): abc 与 ggl, 处理第二个g时发现索引不同
            if (s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))){
                return false;
            }
        }
        return true;
    }


    /**
     * 下标映射法
     * egg -> 122, add -> 122
     * 都变成了 122，所以两个字符串异构。
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic4(String s, String t) {
        return isIsomorphicHelper(s).equals(isIsomorphicHelper(t));
    }

    private String isIsomorphicHelper(String s) {
        int[] map = new int[128];
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            //当前字母第一次出现,赋值
            if (map[c] == 0) {
                map[c] = i + 1;
            }
            sb.append(map[c]);
        }
        return sb.toString();
    }


}
