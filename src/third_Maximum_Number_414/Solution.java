package src.third_Maximum_Number_414;


import java.util.*;
import java.util.stream.IntStream;


public class Solution {
    public int thirdMax(int[] nums) {
        int[] arr = IntStream.of(nums).boxed().distinct().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();
        if(arr.length < 3) return arr[0];
        else return arr[2];
    }


    public int thirdMax1(int[] nums) {
        Arrays.sort(nums);
        reverse(nums);
        for(int i = 1, diff = 1; i < nums.length; ++i) {
            if(nums[i] != nums[i - 1] && ++diff == 3) return nums[i];
        }
        return nums[0];

    }
    public void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }


    /**
     * TreeSet在遍历集合元素时，是有顺序的【从小到大】
     * 我们可以遍历数组，同时用一个有序集合来维护数组中前三大的数。具体做法是每遍历一个数，就将其插入有序集合，若有序集合的大小超过 3，就删除集合中的最小元素。
     * 这样可以保证有序集合的大小至多为 3，且遍历结束后，若有序集合的大小为 3，其最小值就是数组中第三大的数；若有序集合的大小不足 3，那么就返回有序集合中的最大值
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。由于有序集合的大小至多为 3，插入和删除的时间复杂度可以视作是 O(1) 的，因此时间复杂度为 O(n)。
     * @param nums
     * @return
     */
    public int thirdMax2(int[] nums) {
        TreeSet<Integer> s = new TreeSet<Integer>();
        for(int num : nums) {
            s.add(num);
            if(s.size() > 3) s.remove(s.first());
        }
        return s.size() == 3 ? s.first() : s.last();
    }

    /**
     * 我们可以遍历数组，并用三个变量 a、b 和 c 来维护数组中的最大值、次大值和第三大值，以模拟方法二中的插入和删除操作。
     * 为方便编程实现，我们将其均初始化为小于数组最小值的元素，视作「无穷小」，比如 -2^{63}
     * 遍历数组，对于数组中的元素 num：若 num>a，我们将 c 替换为 b，b 替换为 a，a 替换为 num，这模拟了将 num 插入有序集合，并删除有序集合中的最小值的过程；
     * 若 a>num>b，类似地，我们将 c 替换为 b，b 替换为 num，a 保持不变；
     * 若 b>num>c，类似地，我们将 c 替换为 num，a和 b保持不变；
     * 其余情况不做处理。
     * 遍历结束后，若 c 仍然为 -2^{63}则说明数组中不存在三个或三个以上的不同元素，即第三大的数不存在，返回 a，否则返回 c。
     * @param nums
     * @return
     */
    public int thirdMax3(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for(long num : nums) {
            if(num > a) {
                c = b;
                b = a;
                a = num;
            } else if(a > num && num > b) {
                c = b;
                b = num;
            } else if(b > num && num > c) c = num;
        }
        return c == Long.MIN_VALUE ? (int) a : (int) c;
    }

    /**
     * 另一种不依赖元素范围的做法是，将 a、b 和 c 初始化为空指针或空对象，视作「无穷小」，
     * 并在比较大小前先判断是否为空指针或空对象。遍历结束后，若 c 为空，则说明第三大的数不存在，返回 a，否则返回 c。
     * @param nums
     * @return
     */
    public int thirdMax4(int[] nums) {
        Integer a = null, b = null, c = null;
        for(int num : nums) {
            if(a == null || num > a) {
                c = b;
                b = a;
                a = num;
            } else if(a > num && (b == null || num > b)) {
                c = b;
                b = num;
            } else if(b != null && b > num && c == null || num > c) c = num;
        }
        return c == null ? a : c;
    }

    long INF = (long)-1e18;
    public int thirdMax5(int[] nums) {
        long a = INF, b = INF, c = INF;
        for(int x : nums) {
            if(x > a) {
                c = b;
                b = a;
                a = x;
            } else if(x < a && x > b) {
                c = b;
                b = x;
            } else if(x < b && x > c) c = x;
        }
        return c != INF ? (int)c : (int)a;
    }

    public int thirdMax6(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list.size() < 3 ? list.get(list.size() - 1) : list.get(list.size() - 3);
    }

    public int thirdMax7(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        int flag = 1;
        for(int i = len - 1; i > -1; --i) {
            if(!map.containsKey(nums[i])) map.put(nums[i], flag++);
            if(flag > 3) return nums[i];
        }
        return nums[len - 1];
    }






}
