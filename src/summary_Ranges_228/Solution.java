package src.summary_Ranges_228;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * laji
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if(nums.length == 0) return list;
        int start = nums[0];
        int end = nums[0];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i+1] !=  nums[i]+1)  {
                end = nums[i];
                if(start == end) sb.append(start);
                else sb.append(start + "->" + end);
                list.add(sb.toString());
                sb.setLength(0);
                start = nums[i+1];
            }
        }
        end = nums[nums.length - 1];
        if(start == end) sb.append(start);
        else sb.append(start + "->" + end);
        list.add(sb.toString());
        return list;
    }


    /**
     * 一次遍历
     *
     * 我们从数组的位置 0 出发，向右遍历。每次遇到相邻元素之间的差值大于 1 时，
     * 我们就找到了一个区间。遍历完数组之后，就能得到一系列的区间的列表。
     * 在遍历过程中，维护下标 low 和high 分别记录区间的起点和终点，对于任何区间都有 low≤high。
     * 当得到一个区间时，根据 low 和 high 的值生成区间的字符串表示。
     * 当low<high 时，区间的字符串表示为 ‘low→high"；
     * 当 low=high 时，区间的字符串表示为 ‘‘low"。
     * @param nums
     * @return
     */
    public List<String> summaryRanges2(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;
        while(i < n) {
            int low = i;
            i++;
            while(i < n && nums[i] == nums[i - 1] + 1) i++;
            int high = i - 1;
            StringBuffer temp = new StringBuffer(Integer.toString(nums[low]));
            if(low < high) {
                temp.append("->");
                temp.append(Integer.toString(nums[high]));
            }
            ret.add(temp.toString());
        }
        return ret;
    }



    public List<String> summaryRanges3(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;//i初始指向第一个区间的起始位置
        for(int j = 0; j < nums.length; j++) {
            //j向后遍历，直到不满足连续递增
            //或者j到达数组边界，则当前连续递增区间[i,j]遍历完毕
            if(j + 1 == nums.length || nums[j] + 1 != nums[j + 1]) {
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                if(i != j) sb.append("->").append(nums[j]);
                res.add(sb.toString());
                //将i指向更新为就j+1，作为下一个区间的起始位置
                i = j + 1;
            }
        }
        return res;
    }



    public List<String> summaryRanges4(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        if(n == 0) return res;
        //两个整数记录窗口大小
        int pre = 0, later = 0;
        for(int i = 1; i < n; i++) {
            ///当连续时，窗口右边界往后移动
            if(nums[i] - nums[i - 1] == 1) later++;
            else {//当不连续时将当前窗口加入结果集，并开始新窗口
                if(pre == later) res.add(nums[pre] + "");
                else res.add(nums[pre] + "->" + nums[later]);
                pre = i;
                later = i;
            }
        }
        //处理最后边界
        if(pre == n - 1) res.add(nums[pre] + "");
        if(pre < n - 1) res.add(nums[pre] + "->" + nums[later]);
        return res;
    }


















    @Test
    public void test(){

    }
}
