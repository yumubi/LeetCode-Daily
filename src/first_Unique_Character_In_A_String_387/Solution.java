package src.first_Unique_Character_In_A_String_387;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
    public int firstUniqChar(String s) {
        int ans = -1;
        char[] chars = new char[26];

        for(int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < chars.length; i++) {
            if(chars[i] > 1)
                 s  = s.replace( (char)(i + 97), 'A');
        }

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != 'A') {
                ans = i;
                return ans;
            }
        }
        return ans;
    }

    /**
     * 使用哈希表存储频数
     * 我们可以对字符串进行两次遍历。
     * 在第一次遍历时，我们使用哈希映射统计出字符串中每个字符出现的次数。
     * 在第二次遍历时，我们只要遍历到了一个只出现一次的字符，那么就返回它的索引，否则在遍历结束后返回 -1。
     * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。我们需要进行两次遍历。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。我们需要 OO(∣Σ∣) 的空间存储哈希映射。
     * @param s
     * @return
     */
    public int firstUniqChar1(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        //遍历的是字符串，所以没有顺序问题
        for(int i = 0; i < s.length(); ++i) {
            if(frequency.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }


    /**
     * 使用哈希表存储索引
     * 使得第二次遍历的对象从字符串变为哈希映射。
     * 具体地，对于哈希映射中的每一个键值对，键表示一个字符，值表示它的首次出现的索引（如果该字符只出现一次）或者 −1（如果该字符出现多次）。
     * 当我们第一次遍历字符串时，设当前遍历到的字符为 c，如果 c 不在哈希映射中，我们就将 c 与它的索引作为一个键值对加入哈希映射中
     * ，否则我们将 c在哈希映射中对应的值修改为 −1。
     * 在第一次遍历结束后，我们只需要再遍历一次哈希映射中的所有值，找出其中不为 −1 的最小值，即为第一个不重复字符的索引。如
     * 果哈希映射中的所有值均为 -1，我们就返回 -1。
     * @param s
     * @return
     */

    // java的哈希不是无序的，所以这个保存字符第一次出现的索引就很妙
    public int firstUniqChar2(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        int n = s.length();
        for(int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if(position.containsKey(ch)) position.put(ch, -1);
            else position.put(ch, i);
        }
        int first = n;
        for(Map.Entry<Character, Integer> entry : position.entrySet()) {
            int pos = entry.getValue();
            //寻找第一次出现的索引的最小值
            if (pos != -1 && pos < first) first = pos;
        }
        if(first == n) first = -1;
            return first;
    }


    // TODO: 26/11/2022 感觉很妙，但没搞懂
    /**
     * 队列
     * 思路与算法
     * 我们也可以借助队列找到第一个不重复的字符。队列具有「先进先出」的性质，因此很适合用来找出第一个满足某个条件的元素。
     * 具体地，我们使用与方法二相同的哈希映射，并且使用一个额外的队列，按照顺序存储每一个字符以及它们第一次出现的位置。
     * 当我们对字符串进行遍历时，设当前遍历到的字符为 c，如果 c 不在哈希映射中，我们就将 c与它的索引作为一个二元组放入队尾，
     * 否则我们就需要检查队列中的元素是否都满足「只出现一次」的要求，即我们不断地根据哈希映射中存储的值（是否为 −1）选择弹出队首的元素
     * 直到队首元素「真的」只出现了一次或者队列为空。
     * 在遍历完成后，如果队列为空，说明没有不重复的字符，返回 −1，否则队首的元素即为第一个不重复的字符以及其索引的二元组。
     * 小贴士
     * 在维护队列时，我们使用了「延迟删除」这一技巧。也就是说，即使队列中有一些字符出现了超过一次，但它只要不位于队首，
     * 那么就不会对答案造成影响，我们也就可以不用去删除它。只有当它前面的所有字符被移出队列，它成为队首时，我们才需要将它移除。
     * @param s
     * @return
     */
    public int firstUniqChar3(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        Queue<Pair> queue = new LinkedList<Pair>();
        int n = s.length();
        for(int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if(!position.containsKey(ch)) {
                position.put(ch, i);
                queue.offer(new Pair(ch, i));
            } else {
                position.put(ch, -1);
                while(!queue.isEmpty() && position.get(queue.peek().ch) == -1) queue.poll();
            }
        }
        return queue.isEmpty() ? -1 : queue.poll().pos;
    }

    class Pair {
        char ch;
        int pos;
         Pair(char ch, int pos) {
             this.ch = ch;
             this.pos = pos;
        }
    }


    /**
     todo 我感觉有点问题,first如何保证呢
     * @param s
     * @return
     */
    public int firstUniqChar4(String s) {
        int[] arr = new int[26];
        int n = s.length();
        for(int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < n; i++) {
            if(arr[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }


    //dp
    public int firstUniqChar5(String s) {
        int ans = s.length();
        int temp;
        for(char i = 'a'; i <= 'z'; i++) {
            temp = s.indexOf(i);
            if(temp == -1) continue;
            if(temp == s.lastIndexOf(i)) ans = Math.min(ans, temp);
        }
        return ans == s.length() ? -1 : ans;
    }







//    奇技淫巧：遍历字符串如果当前字符的第一个索引和最后一个索引不是同一个则继续遍历，是同一个返回即可。
//        public int firstUniqChar(String s) {
//            for (int i = 0; i < s.length(); i++) {
//                if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
//                    return i;
//                }
//            }
//            return -1;
//        }



    @Test
    public void test() {
        String s = "loveleetcode";
        System.out.println(firstUniqChar4(s));
    }
}
