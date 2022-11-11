package src.pascal_Triangle_118;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution {





    /**
     * 杨辉三角
     * 我是菜逼，numsRows=15莫名奇妙错误
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        helper(numRows-1, list);
        return list;
    }
    public void helper(int length, List<List<Integer>> res) {
        for (int i = 0; i <= length; i++) {
             List<Integer> level = new ArrayList<>();
             for(int j = 0; j <= i; j++) {
                 level.add(rankNumber(i, j));
             }
             res.add(level);
        }
    }

    public Integer factorial(int rank) {
        Integer result = 1;
        if(rank == 0) return 1;
        for(int i = 1; i <= rank; i++) {
            result *= i;
        }
        return result;
    }

    public Integer rankNumber(int rank, int k) {
        return factorial(rank) / ( factorial(k) * factorial(rank-k));
    }


    /**
     *
     * @param numRows
     * @return
     */




    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if(j == 0 || j == i) row.add(1);
                else row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
            }
            ret.add(row);
        }
        return ret;
    }



    @Test
    public void test() {
        System.out.println(generate(15));
    }




}
