package src.longest_Palindrome_409;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    public int longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int[] cnt = new int[52];
        for(int i = 0; i < chars.length; i++) {

            if(chars[i] >= 'a')
            cnt[chars[i] - 'a' + 26]++;
            else cnt[chars[i] - 'A']++;
        }
        int sum = 0;
        boolean flag = false;
        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] > 0 && cnt[i] % 2 == 0) sum += cnt[i];
            else if(cnt[i] % 2 == 1) {
                flag = true;
                sum += --cnt[i];
            }
        }


        return sum += flag == true ? 1 : 0;
    }

    /**
     * 贪心
     * 对于每个字符 ch，假设它出现了 v 次，我们可以使用该字符 v / 2 * 2 次，在回文串的左侧和右侧分别放置 v / 2 个字符 ch，
     * 其中 / 为整数除法。例如若 "a" 出现了 5 次，那么我们可以使用 "a" 的次数为 4，回文串的左右两侧分别放置 2 个 "a"。
     * 如果有任何一个字符 ch 的出现次数 v 为奇数（即 v % 2 == 1），那么可以将这个字符作为回文中心，注意只能最多有一个字符作为回文中心。
     * 在代码中，我们用 ans 存储回文串的长度，由于在遍历字符时，ans 每次会增加 v / 2 * 2，因此 ans 一直为偶数。
     * 但在发现了第一个出现次数为奇数的字符后，我们将 ans 增加 1，
     * 这样 ans 变为奇数，在后面发现其它出现奇数次的字符时，我们就不改变 ans 的值了。
     * @param s
     * @return
     */
    public int longestPalindrome1(String s) {
        int[] count = new int[128];
        int length = s.length();
        for(int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            count[c]++;
        }
        int ans = 0;
        for(int v : count) {
            ans += v / 2 * 2;
            if(v % 2  == 1 && ans % 2 == 0) ans++;
        }
        return ans;
    }


    public int longestPalindrome2(String s) {
        HashMap<Character, Integer> counter = new HashMap<>();
        for(int i = 0; i < s.length(); i++) counter.merge(s.charAt(i), 1, (a, b) -> a + b);
        int res = 0, odd = 0;
        for(Map.Entry<Character, Integer> kv : counter.entrySet()) {
            int count = kv.getValue();
            int rem = count % 2;
            res += count - rem;
            if(rem == 1) odd = 1;
        }
        return res + odd;
    }

    /**
     * 相互抵消，这个妙啊
     * @param s
     * @return
     */
    public int longestPalinedrome3(String s) {
        HashSet<Character> set = new HashSet<>();
        for(int i = 0; i < s.length(); ++i) {
            if(set.contains(s.charAt(i)))
            set.remove(s.charAt(i));
            else set.add(s.charAt(i));
        }
        int res = s.length() - set.size();
        return set.size() == 0 ? res : res + 1;
    }

    public int longestPalindrome4(String s) {
        int[] cnt = new int[58];
        for(char c : s.toCharArray()) cnt[c - 'A'] += 1;
        int ans = 0;
        for(int x : cnt) ans += x - (x & 1);
        //如果最终长度小于原来字符串的长度，说明里面某个字符出现了奇数次
        //该字符可以放在会文串的中间
        return ans < s.length() ? ans + 1 : ans;
    }

    public int longestPalinedrome(String s) {
        Map<Integer, Integer> count = s.chars().boxed()
                .collect(Collectors.toMap(k -> k, v -> 1, Integer :: sum));
        int ans = count.values().stream().mapToInt(i -> i - (i & 1)).sum();
        return ans < s.length() ? ans + 1 : ans;
    }
















    @Test
    public void test() {
        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        System.out.println(longestPalindrome(s));
    }
}
