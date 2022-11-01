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
     *startWitch
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
     *扫描
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
     * 分治法
     */

    public String longestCommonPrefix3(String[] strs) {

        return longestCommonPrefix3(strs, 0, strs.length - 1);

    }
    public  String longestCommonPrefix3(String[] strs, int lo, int hi) {

        if(lo >= hi) return strs[lo];
        int mid = lo + (hi - lo) / 2;
        String lcpLeft = longestCommonPrefix3(strs,lo, mid);
        String lcpRight = longestCommonPrefix3(strs,mid+1, hi);
         return commonPrefix(lcpLeft,lcpRight);
    }
    public String commonPrefix(String lcpLeft, String lcpRight) {
            int minLen = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLen; i++) {
            if(lcpLeft.charAt(i) != lcpRight.charAt(i)) return lcpLeft.substring(0,i);
        }
            return lcpLeft.substring(0,minLen);
    }


    /**
     * todo
     *二分法
     */

    public String longestCommonPrefix4(String[] strs) {
        int minlength = Integer.MAX_VALUE;
        for(String str : strs) {
            minlength = Math.min(str.length(),minlength);
        }
        int low = 0;
        int high = minlength;
        while( low < high) {
            int mid = (high - low + 1) / 2 + low;
            if(isCommonPrefix(strs,mid)) {
                low = mid;
            }  else {
                high = mid -1;
            }
        }
        return strs[0].substring(0,low);
    }

    public boolean isCommonPrefix(String[] strs,int length) {
        String str0 = strs[0].substring(0,length);
        int count = strs.length;
        for (int i = 0; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if(str0.charAt(i) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }






    @Test
    public void test() {

    }
}
