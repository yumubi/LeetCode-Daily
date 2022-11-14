package src.majority_Element_169;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Solution {
    /**
     * ac
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int half = (nums.length-1)/2;
        if(nums[0] != nums[half]) return nums[half];
        return nums[0];
    }


    /**
     * 哈希表法
     * 我们使用哈希映射（HashMap）来存储每个元素以及出现的次数。
     * 对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。
     * 我们用一个循环遍历数组 nums 并将数组中的每个元素加入哈希映射中。
     * 在这之后，我们遍历哈希映射中的所有键值对，返回值最大的键。
     * 我们同样也可以在遍历数组 nums 时候使用打擂台的方法，
     * 维护最大的值，这样省去了最后对哈希映射的遍历。

     * @param nums
     * @return
     */
    private Map<Integer, Integer> countNumber(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for(int num : nums) {
            if(!counts.containsKey(nums)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> counts = countNumber(nums);
        Map.Entry<Integer, Integer> majorityEntry = null;
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if(majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();
    }


    /**
     * 排序法
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    /**
     * 随机化法
     * 由于一个给定的下标对应的数字很有可能是众数，
     * 我们随机挑选一个下标，检查它是否是众数，如果是就返回，否则继续随机挑选。
     * @param rand
     * @param min
     * @param max
     * @return
     */
    private int randRange(Random rand,int min, int max) {
        return rand.nextInt(max - min) + min;
    }
    private int countOccurences(int[] nums, int num) {
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == num) {
                count++;;
            }
        }
        return count;
    }
    public int majorityElement4(int[] nums) {
        Random rand = new Random();
        int majorityCount = nums.length / 2;
        while(true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if(countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }


    /**
     * 分治法
     *
     * 思路
     * 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
     * 我们可以使用反证法来证明这个结论。假设 a 既不是左半部分的众数，也不是右半部分的众数，
     * 那么 a 出现的次数少于 l / 2 + r / 2 次，其中 l 和 r 分别是左半部分和右半部分的长度。由于 l / 2 + r / 2 <= (l + r) / 2，
     * 说明 a 也不是数组 nums 的众数，因此出现了矛盾。所以这个结论是正确的。
     * 这样以来，我们就可以使用分治法解决这个问题：将数组分成左右两部分，
     * 分别求出左半部分的众数 a1 以及右半部分的众数 a2，随后在 a1 和 a2 中选出正确的众数。
     *
     * 算法
     * 我们使用经典的分治算法递归求解，直到所有的子问题都是长度为 1 的数组。
     * 长度为 1 的子数组中唯一的数显然是众数，直接返回即可。如果回溯后某区间的长度大于 1，
     * 我们必须将左右子区间的值合并。如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。
     * 否则，我们需要比较两个众数在整个区间内出现的次数来决定该区间的众数。
     *
     *
     * 时间复杂度：O(nlogn)。函数 majority_element_rec() 会求解 2 个长度为 n/2的子问题，并且做2遍长度为n的线性扫描
     * T(n)= 2T(n/2) + 2n,由主定理，为nlogn
     *
     * 空间复杂度：O(logn)。尽管分治算法没有直接分配额外的数组空间，
     * 但在递归的过程中使用了额外的栈空间。算法每次将数组从中间分成两部分，
     * 所以数组长度变为 1 之前需要进行 O(\log n)O(logn) 次递归，即空间复杂度为O(logn)。
     * @param nums
     * @param num
     * @param lo
     * @param hi
     * @return
     */
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if(nums[i] == num) {
                count++;
            }
        }
        return count;
    }
    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement5(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }


    /**
     * Boyer-Moore 投票算法
     *  时间复杂度：O(n)。Boyer-Moore 算法只对数组进行了一次遍历。
     *  空间复杂度：O(1)。Boyer-Moore 算法只需要常数级别的额外空间。
     * @param nums
     * @return
     */
        public int majorityElement6(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }


    /**
     * 投票算法人话版
     * “同归于尽消杀法” ：
     * 由于多数超过50%, 比如100个数，那么多数至少51个，剩下少数是49个。
     * 1. 第一个到来的士兵，直接插上自己阵营的旗帜占领这块高地，此时领主 winner 就是这个阵营的人，现存兵力 count = 1。
     * 2. 如果新来的士兵和前一个士兵是同一阵营，则集合起来占领高地，领主不变，winner 依然是当前这个士兵所属阵营，现存兵力 count++；
     * 3. 如果新来到的士兵不是同一阵营，则前方阵营派一个士兵和它同归于尽。
     * 此时前方阵营兵力count --。（即使双方都死光，这块高地的旗帜 winner 依然不变，因为已经没有活着的士兵可以去换上自己的新旗帜）
     * 4. 当下一个士兵到来，发现前方阵营已经没有兵力，新士兵就成了领主，winner 变成这个士兵所属阵营的旗帜，现存兵力 count ++。
     * 就这样各路军阀一直以这种以一敌一同归于尽的方式厮杀下去，直到少数阵营都死光，
     * 那么最后剩下的几个必然属于多数阵营，winner 就是多数阵营。（多数阵营 51个，少数阵营只有49个，死剩下的2个就是多数阵营的人）


     * @param nums
     * @return
     */
    public int majorityElement6P(int[] nums) {
        int winner = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (winner == nums[i]) {
                count++;
            } else if (count == 0) {
                winner = nums[i];
                count++;
            } else {
                count--;
            }
        }
        return winner;
    }

}
