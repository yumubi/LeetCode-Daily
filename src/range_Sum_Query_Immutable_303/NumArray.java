package src.range_Sum_Query_Immutable_303;

public class NumArray {

//    public int[] nums;
//    public NumArray(int[] nums) {
//        this.nums = nums;
//    }
//    public int sumRange(int left, int right) {
//        int len = nums.length;
//        int sum = 0;
//        if(left <0 || right >= len || left > right) return Integer.MIN_VALUE;
//        for(int i = left; i <= right; i++) sum += nums[i];
//        return sum;
//    }


    /**
     * 由于会进行多次检索，即多次调用 sumRange，因此为了降低检索的总时间，应该降低 sumRange 的时间复杂度，
     * 最理想的情况是时间复杂度 O(1)。为了将检索的时间复杂度降到 O(1)，需要在初始化的时候进行预处理。
     * 假设数组 nums 的长度为 n，创建长度为 n+1的前缀和数组 sums，对于0≤i<n 都有sums[i+1]=sums[i]+nums[i]
     * ，则当0<i≤n 时，sums[i] 表示数组 nums 从下标 0 到下标i−1 的前缀和。
     *
     *
     *
     * 前缀和的作用就是为了帮助我们快速求某一段的和，是「差分」的逆运算。
     * 前缀和数组 sum 的每一位记录的是当前位置距离起点位置，这连续一段的和区间和。
     * 因此当我们要求特定的一段 [i,j] 的区域和的时候，可以直接利用前缀和数组快速求解：ans = sum[j] - sum[i - 1]。
     */



        int[] sum;
        public NumArray(int[] nums) {
            int n = nums.length;
            // 前缀和数组下标从 1 开始，因此设定长度为 n + 1（模板部分）
            sum = new int[n + 1];
            // 预处理除前缀和数组（模板部分）
            for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        }
        public int sumRange(int i, int j) {
            // 求某一段区域和 [i, j] 的模板是 sum[j] - sum[i - 1]（模板部分）
            // 但由于我们源数组下标从 0 开始，因此要在模板的基础上进行 + 1
            i++; j++;
            return sum[j] - sum[i - 1];
        }




        //blockSize分块算法

//    class NumArray {
//        int blockSize = 100;  // blockSize取法：blockSize * bockSize >= nums.length
//        int[] nums;
//        int[] block;          // block[i] 表示[i, i + blockSize - 1]之间的区间和； 可以定义成 ArrayList
//
//        public NumArray(int[] nums) {
//            this.nums = nums;
//            block = new int[nums.length / blockSize + 1];
//            int idx = 0;
//            for (int i = 0; i + blockSize <= nums.length; i += blockSize) {
//                int curSum = 0;
//                for (int j = 0; j < blockSize; j++)  // 当前块的元素和
//                    curSum += nums[i + j];
//                block[idx++] = curSum;
//            }
//        }
//
//        public int sumRange(int i, int j) {
//            int k = i, res = 0;
//            while (k <= j) {
//                // k 右边有个完整块
//                if (k % blockSize == 0 && k + blockSize - 1 <= j) {
//                    res += block[k / blockSize];
//                    k += blockSize;
//                    // k 右边不够一个块
//                } else {
//                    res += nums[k];
//                    k++;
//                }
//            }
//            return res;
//        }
//
//        // 把 i 位置的元素改成 data
//        public void update(int i, int data) {
//            int blockIdx = i / blockSize;
//            block[blockIdx] -= nums[i];
//            block[blockIdx] += data;
//
//            nums[i] = data;
//        }
//    }










}
