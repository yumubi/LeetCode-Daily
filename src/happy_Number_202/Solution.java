package src.happy_Number_202;

import org.junit.Test;
import src.Two_Sum_01.Main;

import java.util.*;

public class Solution {
    /**
     * 我快乐了
     * @param n
     * @return
     */
    public  boolean isHappy(int n) {
        ArrayList<Integer> dictionary = new ArrayList<>();
        dictionary.add(n);
        while(true) {
            n = Intermediates(n);
            if(n == 1) return true;
            if (dictionary.contains(n)) return false;
            dictionary.add(n);
        }
    }

    public int Intermediates(int n) {
        int square = 0;
        while( n > 0) {
            square += Math.pow(n % 10, 2);
            n /= 10;
        }
        return square;
    }


    /**
     * 用哈希集合检测循环
     *
     * 我们使用哈希集合而不是向量、列表或数组的原因是因为我们反复检查其中是否存在某数字。
     * 检查数字是否在哈希集合中需要 O(1)的时间，而对于其他数据结构，则需要 O(n) 的时间
     * 。选择正确的数据结构是解决这些问题的关键部分。
     *
     * O(243⋅3+logn+loglogn+logloglogn)... = O(logn)。
     * 空间复杂度：O(logn)。


     * @param n
     * @return
     */
    public int getNext(int n) {
        int totalSum = 0;
        while(n > 0) {
            int d = n % 10;
            n /= 10;
            totalSum += d * d;
        }
        return totalSum;
    }
    public boolean isHappy2(int n) {
        Set<Integer> seen = new HashSet<>();
        while( n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }


    /**
     * Freud
     * @param n
     * @return
     */
    public boolean isHappy3(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while(fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }











    @Test
    public void test() {
        System.out.println(isHappy(19));
    }

}
