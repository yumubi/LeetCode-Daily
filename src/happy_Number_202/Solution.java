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
     * Freud循环查找算法
     * 通过反复调用 getNext(n) 得到的链是一个隐式的链表。
     * 我们不是只跟踪链表中的一个值，而是跟踪两个值，称为快跑者和慢跑者。在算法的每一步中，
     * 慢速在链表中前进 1 个节点，快跑者前进 2 个节点（对 getNext(n) 函数的嵌套调用）。
     * 如果 n 是一个快乐数，即没有循环，那么快跑者最终会比慢跑者先到达数字 1。
     * 如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇
     *
     * 时间复杂度：O(logn)
     * 空间复杂度O(1)
     * @param n
     * @return
     */
    public boolean isHappy3(int n) {
        //双指针有两个注意点，快指针每次眺两个不用担心跳过1的点，
        // 因为1的点的下一个点还是1。快指针在慢指针后一个位置出发
        // ，这是因为只要求可追击，不要求环形列表的起始点

        //环形链表龟兔是同一起点即可，但是为什么快乐数同一起点不可以
        //因为如果是同一起点出发的话，while循环就进不去
        int slowRunner = n;
        int fastRunner = getNext(n);
        while(fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }


    /**
     * 下一个值可能比自己大的最大数字是什么？根据我们之前的分析，我们知道它必须低于 243。
     * 因此，我们知道任何循环都必须包含小于 243 的数字，用这么小的数字，编写一个能找到所有周期的强力程序并不困难。
     * 如果这样做，您会发现只有一个循环： 4→16→37→58→89→145→42→20→4。
     * 所有其他数字都在进入这个循环的链上，或者在进入 1 的链上。
     * 因此，我们可以硬编码一个包含这些数字的散列集，如果我们达到其中一个数字，那么我们就知道在循环中。
     * @param n
     * @return
     */


    private static Set<Integer> cycleMembers =
            new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

    public boolean isHappy4(int n) {

        while (n != 1 && !cycleMembers.contains(n)) {
            n = getNext(n);
        }
        return n == 1;
    }













    @Test
    public void test() {
        System.out.println(isHappy(19));
    }

}
