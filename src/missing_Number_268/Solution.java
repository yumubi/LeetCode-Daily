package src.missing_Number_268;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int missingNumber(int[] nums){

        Arrays.sort(nums);
        //if(nums[0] != 0) return 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i) return i;
        }
        return nums.length;
    }

    /**
     * 排序
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(nums[i] != i) return i;
        }
        return n;
    }


    public int missingNumber3(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int n = nums.length;
        for(int i = 0; i < n; i++) set.add(nums[i]);
        int missing = -1;
        for(int i = 0; i <= n; i++) {
            if(!set.contains(i)) {
                missing = i;
                break;
            }
        }
        return missing;
    }

    /**
     * 位运算
     * 数组 nums 中有 n个数，在这 n个数的后面添加从 0 到 n的每个整数，则添加了 n+1个整数，共有 2n+1 个整数。
     * 在 2n+1个整数中，丢失的数字只在后面 n+1个整数中出现一次，其余的数字在前面 n个整数中（即数组中）和后面 n+1 个整数中各出现一次，即其余的数字都出现了两次。
     * 根据出现的次数的奇偶性，可以使用按位异或运算得到丢失的数字。按位异或运算 满足交换律和结合律，且对任意整数 x 都满足 x⊕x=0 和 x⊕0=x。
     * 由于上述 2n+1个整数中，丢失的数字出现了一次，其余的数字都出现了两次，因此对上述 2n+1 个整数进行按位异或运算，结果即为丢失的数字。
     * @param nums
     * @return
     */
    public int missingNumber4(int[] nums) {
        int xor = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        return xor;
    }


    public int missingNumber5(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int arrSum = 0;
        for(int i = 0; i < n; i++) arrSum += nums[i];
        return total - arrSum;
    }


    /**
     * 数组哈希
     * @param nums
     * @return
     */
    public int missingNumber6(int[] nums) {
        int n = nums.length;
        boolean[] hash = new boolean[n + 1];
        for(int i = 0; i < n; i++) hash[nums[i]] = true;
        for(int i = 0; i < n; i++) if(!hash[i]) return i;
        return n;
    }

    /**
     * 原地哈希
     * 事实上，我们可以将 nums本身作为哈希表进行使用，将 nums[i] 放到其应该出现的位置（下标）nums[i] 上（ nums[i]<n ），
     * 然后对nums 进行检查，找到满足 nums[i]！= i 的位置即是答案，如果不存在nums[i]！=i 的位置，则 n为答案
     *
     * 时间复杂度：每个元素会被一次性移动到对应位置，因此每个元素的访问次数为常数次。复杂度为 O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public int missingNumber7(int[] nums){
        int n = nums.length;
        for(int i = 0; i < n; i++) if(nums[i] != i && nums[i] < n) swap(nums, nums[i], i--);
        for(int i = 0; i < n; i++) if(nums[i] != i) return i;
        return n;
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    @Test
    public void test() {
        int[] nums = {9,6,4,2,3,5,7,0,1};
        System.out.println(missingNumber7(nums));
    }

}
