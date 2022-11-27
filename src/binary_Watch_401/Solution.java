package src.binary_Watch_401;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * 由题意可知，小时由 4个比特表示，分钟由 6 个比特表示，比特位值为 0 表示灯灭，为 1 表示灯亮。
     * 我们可以枚举小时的所有可能值 [0,11]，以及分钟的所有可能值 [0,59]，并计算二者的二进制中 1 的个数之和，若为 turnedOn，则将其加入到答案中。
     * @param turnedOn
     * @return
     */
    public List<String> readBinaryWatch(int turnedOn) {
            List<String> ans = new ArrayList<String>();
            for(int h = 0; h < 12; ++h) {
                for(int m = 0; m < 60; ++m) {
                    if(Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
            return ans;
    }

    /**
     * 另一种枚举方法是枚举所有 2^{10}=102种灯的开闭组合，即用一个二进制数表示灯的开闭，其高 44 位为小时，低 66 位为分钟。
     * 若小时和分钟的值均在合法范围内，且二进制中 1 的个数为 turnedOn，则将其加入到答案中。
     * @param turnedOn
     * @return
     */
    public List<String> readBinaryWatch1(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        for(int i = 0; i < 1024; ++i) {
            int h = i >> 6, m = i & 63;//用位运算取出高四位和低六位
            if(h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) ans.add(h + ":" + (m < 10 ? "0" : "") + m);
        }
        return ans;
    }

















}
