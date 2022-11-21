package src.misc;

import org.junit.Test;

public class Solution_KMP {
    //KMP算法
    //ss：原串(string)， pp:匹配串(pattern)
    public int strStr(String ss, String pp) {
        if(pp.isEmpty()) return 0;

        //分别读取原串和匹配串长度
        int n = ss.length(), m = pp.length();
        //原串和匹配串前面都加空格,使其小标从1开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        //构建next数组，数组长度为匹配串的长度（next数组是和匹配串相关的)
        int[] next = new int[m+1];
        //构造过程i = 2, j = 0;开始，i 小于匹配串长度(构造i从2开始)
        for(int i = 2, j = 0; i <= m; i++) {
            //匹配不成功的话，j = next(j)
            while(j > 0 && p[i] != p[j + 1]) j = next[j];
            //匹配成功的话，先让j++
            if(p[i] == p[j + 1]) j++;
            //更新next[i]结束本次循环,i++
            next[i] = j;
        }
        //匹配过程，i=1， j=0开始，i小于等于原串长度，（匹配i从1开始）
        for(int i = 1, j = 0; i <= n; i++) {
            //匹配不成功， j = next(j)
            while(j > 0 && s[i] != p[j + 1]) j = next[j];
            //匹配成功的话,先让j++,结束本次循环后i++
            if(s[i] == p[j+1]) j++;
            //整一段匹配成功,直接返回下标
            if(j == m) return i - m;
        }
        return -1;
    }



    @Test
    public void test() {
        String ss = "aaabbacbaaabbab";
        String pp = "aaabbab";
        System.out.println(strStr(ss, pp));
    }
}
