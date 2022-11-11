package src.pascal_TrianglePlue_119;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 过了，但是时间和内存都超级垃圾
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        if(rowIndex == 0) return new ArrayList<>(){{add(1);}};
        List<Integer> list = new ArrayList<>(){{add(1); add(1);}};
        if(rowIndex == 1) return list;
        for(int i = 2; i <= rowIndex; i++) {
            List<Integer> preList = List.copyOf(list);
            for (int j = 1; j < i / 2; j++) {
                list.set(j, preList.get(j) + preList.get(j - 1));
            }
            list.add(1);
        }
        return list;
    }


    /**
     * 递推
     * @param rowIndex
     * @return
     */

    public List<Integer> getRow2(int rowIndex) {
        List<List<Integer>> C  = new ArrayList<List<Integer>>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(C.get(i-1).get(j-1) + C.get(i-1).get(j));
                }
            }
            C.add(row);
        }
        return C.get(rowIndex);
    }


    /**
     * 注意到第i+1行的计算只用到了第i行的数据，因此可以使用滚动数组的思想优化空间复杂度
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j == 0 || j == i) cur.add(1);
                else cur.add(pre.get(j-1) + pre.get(j));
            }
            pre = cur;
        }
        return pre;
    }


    /**
     *   0 1 2 1
     *   1 2 1 0
     * 0 1 3 3 1
     * 1 3 3 1 0
     * 1 4 6 4 1
     *
     * 当前行第i项的计算只与上一行第i-1项及第i项有关，因此可以倒着计算按当前行，
     * 这样当计算到第i项时，第i-1项仍然是上一行的值
     *
     *
     * 时间复杂度：O({rowIndex}^2)
     * 空间复杂度：O(1)。不考虑返回值的空间占用。
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow4(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for(int i = 1; i <= rowIndex; i++) {
            row.add(0);
            for(int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    /**
     * 线性递推
     * 时间复杂度：O(rowIndex)。
     * 空间复杂度：O(1)。不考虑返回值的空间占用。
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow5(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 0; i <= rowIndex; i++) {
            row.add( (int) ( (long)row.get(i-1)*(rowIndex-i+1)/i ));
        }
        return row;
    }




    @Test
    public void test() {
        System.out.println(getRow(5));
    }
}
