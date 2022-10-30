package src.Roman_to_Integer13;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
}

/**
 * wrong answer
 */
class Solution {
    public int romanToInt(String s) {
        int result = 0;
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("III", 3);
        map.put("II", 2);
        map.put("I", 1);
        while (s != "") {
            for (String roman : map.keySet()) {
                if (s.startsWith(roman)) {
                    result += map.get(roman);
                    s.replace(roman, "");
                    break;
                }
            }
        }
        return result;
    }
}

/**
 * HashMap
 * 从左往右贪婪查看s的两个字符，和匹配则取值，否则按1个字符匹配
 */

class answer1 {
    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ans = 0;
        for(int i = 0; i < s.length();) {
            if(i + 1 < s.length()  && map.containsKey(s.substring(i, i+2))) {
                ans += map.get(s.substring(i, i+2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i+1));
                i++;
            }
        }
        return ans;
    }
}


/**
 * 模拟
 * 通常情况下，罗马数字中小的数字在大的数字右边，出现
 * 这种情况时，将该字符的对应的值的符号取反
 */
class answer2 {
    Map<Character, Integer> symbolValue = new HashMap<>(){ {
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    } };

    public int romanToInt(String s) {
            int ans = 0;
            int n = s.length();
        for (int i = 0; i < n; i++) {
                int value = symbolValue.get(s.charAt(i));
                if(i < n-1 && value < symbolValue.get(s.charAt(i+1))) {
                    ans -= value;
                } else {
                    ans += value;
                }
        }
        return ans;
    }
}
