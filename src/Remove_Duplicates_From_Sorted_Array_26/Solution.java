package src.Remove_Duplicates_From_Sorted_Array_26;

import org.junit.Test;

public class Solution {
    /**快慢指针
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int count = nums.length;
        if (count == 0) return 1;
        int slow = 0;
        int fast = 1;
        while (slow < fast && fast < nums.length) {
            if (nums[fast] == nums[slow]) {
                count--;
                fast++;
            }
            else {
                nums[++slow] = nums[fast++];
            }

        }
        return count;
    }

    /**
     * 双指针解法
     * 一个指针i进行遍历，另一个指针j指向有效数组的最后一个位置
     * 只有当i所指的值和j不一致时，才将i的值添加到j的下一个位置
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {

        int n = nums.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] != nums[j]) nums[++j] = nums[i];
        }
        return j + 1;
    }

    /**
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     *
     * 对于此类问题，我们应该进行如下考虑：
     *
     * 由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
     * 对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
     * 举个🌰，我们令 k=1，假设有样例：[3,3,3,3,4,4,4,5,5,5]
     *
     * 1. 设定变量 idx，指向待插入位置。idx 初始值为 0，目标数组为 []
     *
     * 2. 首先我们先让第 1 位直接保留（性质 1）。idx 变为 1，目标数组为 [3]
     *
     * 3. 继续往后遍历，能够保留的前提是与 idx 的前面 1 位元素不同（性质 2），
     *
     * ​	因此我们会跳过剩余的 3，将第一个 4 追加进去。idx 变为 2，目标数组为 [3,4]
     *
     * 4. 继续这个过程，跳过剩余的 4，将第一个 5 追加进去。idx 变为 3，目标数组为 [3,4,5]
     *
     * 5. 当整个数组被扫描完，最终我们得到了目标数组 [3,4,5] 和 答案 idx 为 3。
     * @param nums
     * @return
     */
    public int removeDuplicatesPlus(int[] nums) {
//此处设k=1
        return process(nums, 1);
    }
    int process(int[] nums, int k) {
        int idx = 0;
        for(int x : nums) {
            if(idx < k || nums[idx - k] != x) nums[idx++] = x;
        }
        return idx;
    }


//基于上述解法我们还能做一点小剪枝：利用目标数组的最后一个元素必然与原数组的最后一个元素相同进行剪枝，
// 从而确保当数组有超过 k 个最大值时，数组不会被完整扫描。
//但需要注意这种「剪枝」同时会让我们单次循环的常数变大，所以仅作为简单拓展

    /**
     * 暂时没看懂
     * @param nums
     * @return
     */
    public int removeDuplicatesUltra(int[] nums) {
    int n = nums.length;
    if (n <= 1) return n;
    return process(nums, 1, nums[n - 1]);
}
    int process(int[] nums, int k, int max) {
        int idx = 0;
        for (int x : nums) {
            if (idx < k || nums[idx - k] != x) nums[idx++] = x;
            if (idx - k >= 0 && nums[idx - k] == max) break;
        }
        return idx;
    }

@Test
public void test() {

}
}

