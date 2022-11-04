package src.Length_Of_LastWord_58;

import org.junit.Test;

public class Solution {
    /**
     * 重拳出击,但是错了
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if(s == null) return 0;
        int count = 0;
        char[] chars = s.trim().toCharArray();
        for(int i = chars.length-1; i >= 0; i--) {
            if(chars[i] == ' ') return count;
            count++;
        }
        return count;
    }

    /**
     * 反向遍历
     */
    public int lengthOfLastWord1(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int wordLength = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLength++;
            index--;
        }
        return wordLength;
    }


    @Test
    public void test(){
        String s = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWord(s));
    }
}
