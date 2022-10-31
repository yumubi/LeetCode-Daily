package src.Longest_Commom_Prefix;

import org.junit.Test;

public class Solution {


    /**
     * todo
     * 答案错误，待更新
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {

        StringBuilder sb = new StringBuilder(strs[0].substring(0,1));
        int length = strs[0].length();
        for( String s : strs) if(s.length() < length) length = s.length();
        int idx = 1;
        while (idx < length) {
            for (String s : strs)
                if (sb.toString() != s.substring(0, idx))
                {
                    if(idx == 1)
                        return "";
                    else
                        return sb.toString();
                }
            sb.append(strs[0].charAt(idx));
            idx++;
        }
        return " ";
    }

    /**
     *
     */
    public String longestCommonPrefix1(String[] strs) {
        if(strs.length==0)return "";
        //公共前缀比所有字符串都短，随便选一个先
        String s=strs[0];
        for (String string : strs) {
            while(!string.startsWith(s)){
                if(s.length()==0)return "";
                //公共前缀不匹配就让它变短！
                s=s.substring(0,s.length()-1);
            }
        }
        return s;
    }

    /**
     *
     */
    public String longestCommonPrefix2  (String[] strs) {
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            result = maxStr(result, strs[i]);
        }
        return result;
    }
    public static String maxStr(String str1, String str2) {
        int min = Math.min(str1.length(), str2.length());
        int i = 0;
        for (; i < min; i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        if(i == min) return str1.substring(0, i);
        return "";
    }

    /**
     * todo
     */









    @Test
    public void test() {

    }
}
