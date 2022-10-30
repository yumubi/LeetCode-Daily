package src.Roman_to_Integer13;
import java.util.LinkedHashMap;
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