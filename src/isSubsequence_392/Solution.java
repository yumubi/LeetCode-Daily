package src.isSubsequence_392;

import org.junit.Test;

public class Solution {
    public boolean isSubsequence(String s, String t) {
      int sptr = 0;
      int tptr = 0;
      char[] sub = s.toCharArray();
      char[] origin = t.toCharArray();
      while(sptr < sub.length && tptr < origin.length) {
          if(origin[tptr] == sub[sptr]) {
              tptr++;
              sptr++;
          }
          else tptr++;
      }
      return sptr == sub.length;
    }


    /**
     * 双指针
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence1(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while(i < n && j <m) {
            if(s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return  i == n;
    }

    /**
     * dp
     * 考虑前面的双指针的做法，我们注意到我们有大量的时间用于在 t 中找到下一个匹配字符。
     * 这样我们可以预处理出对于 t 的每一个位置，从该位置开始往后每一个字符第一次出现的位置。
     * 我们可以使用动态规划的方法实现预处理，令f[i][j] 表示字符串t中从位置 i 开始往后字符 j 第一次出现的位置。
     * 在进行状态转移时，如果 t 中位置 i 的字符就是 j，那么 f[i][j]=i，否则 j 出现在位置 i+1 开始往后，
     * 即 f[i][j]=f[i+1][j]，因此我们要倒过来进行动态规划，从后往前枚举 i。
     *
     *
     * 假定下标从0 开始，那么f[i][j]中有 0≤i≤m−1 ，对于边界状态 f[m−1][..]，我们置 f[m][..] 为 m，让 f[m−1][..] 正常进行转移。
     * 这样如果 f[i][j]=m，则表示从位置 i 开始往后不存在字符 j。
     * 这样，我们可以利用 f 数组，每次 O(1) 地跳转到下一个位置，直到位置变为 m 或 s 中的每一个字符都匹配成功。
     * 该解法中对 t 的处理与 s 无关，且预处理完成后，可以利用预处理数组的信息，线性地算出任意一个字符串 s 是否为 t 的子串。这样我们就可以解决「后续挑战」。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] f = new int[m + 1][26];
        for(int i = 0; i < 26; i++) {
            f[m][i] = m;
        }
        for(int i = m - 1; i >= 0; i--) {
            for(int j = 0; j < 26; j++) {
                if(t.charAt(i) == j + 'a') f[i][j] = i;
                else f[i][j] = f[i + 1][j];
            }
        }

        int add = 0;
        for(int i = 0; i < n; i++) {
            if(f[add][s.charAt(i) - 'a'] == m) return false;
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

    @Test
    public void test() {

//        String s  = "axc";
        String t = "ahbgdcx";
        String s = "abc";
        System.out.println(isSubsequence(s, t));
    }
}
