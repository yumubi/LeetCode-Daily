package src.word_Pattern_290;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    public boolean wordPattern(String pattern, String s) {
       String[] strings = s.split("\\s+");
       if(strings.length != pattern.length()) return false;
        HashMap<String,String > mapX = new HashMap<>();
        HashMap<String, String> mapY = new HashMap<>();
        for(int i = 0; i < pattern.length(); i++) {
            String patternSymbol = pattern.substring(i, i+1);
            if( (mapX.containsKey(strings[i]) && !mapX.get(strings[i]).equals(patternSymbol))
                    || (mapY.containsKey(patternSymbol) && !mapY.get(patternSymbol).equals(strings[i])) )
                return false;

                mapX.put(strings[i], patternSymbol);
                mapY.put(patternSymbol, strings[i]);

        }
        return true;
    }

    public boolean wordPattern2(String pattern, String str) {
        Map<String,Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for(int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if( i >= m) return false;
            int j = i;
            while(j < m && str.charAt(j) != ' ') j++;
            String tmp = str.substring(i, j);
            if(str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) return false;
            if(ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) return false;
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }



        public boolean wordPattern3(String pattern, String str) {
            String[] words = str.split(" ");
            //字符和单词是互相映射，数量必须相等
            if (words.length != pattern.length()) {
                return false;
            }
            Map<Object, Integer> map = new HashMap<>();
            for (Integer i = 0; i < words.length; i++) {
            /*
                如果key不存在，插入成功，返回null；如果key存在，返回之前对应的value。

                以pattern = "abba", str = "dog cat cat dog"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("dog",3)返回0，两者相等，
                结果为 true。

                以pattern = "abba", str = "dog cat cat fish"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("fish",3)返回null，两者不相等，
                结果为 false。
            */
                if (map.put(pattern.charAt(i), i) != map.put(words[i], i)) {
                    return false;
                }
            }
            return true;
        }








    @Test
    public void test() {
        String pattern = "abba";
        String s = "dog cat cat dog";

    }
}
