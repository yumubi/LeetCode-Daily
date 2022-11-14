package src.excelSheetColumn_Number_171;

import org.junit.Test;

import javax.print.DocFlavor;
import java.util.HashMap;

public class Solution {
    /**
     * ac
     * @param columnTitle
     * @return
     */
    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int sum = 0;
        for(int i = 0; i < chars.length; i++) {
            int num = (int)chars[i] - 64;
            sum *= 26;
            sum += num;
        }
        return sum;
    }


    /**
     * 日常膜拜三叶大佬
     * @param s
     * @return
     */
    public int titleToNumber2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = ans * 26 + (cs[i] - 'A' + 1);
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(titleToNumber("ZY"));
    }

}
