package src.convert_A_Number_To_Hexadecimal_405;

import org.junit.Test;

import javax.management.StringValueExp;
import java.util.HashMap;

public class Solution {
    /**
     * 负数不会写
     * @param num
     * @return
     */
    public String toHex(int num) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(10, "a");
        map.put(11, "b");
        map.put(12, "c");
        map.put(13, "d");
        map.put(14, "e");
        map.put(15, "f");
        StringBuilder sb = new StringBuilder();
        if(num < 0) num = Integer.MAX_VALUE + num + 1;
        while(num > 0) {
            if(num % 16 < 10) sb.append(num % 16);
            else sb.append(map.get(num % 16));
            num /= 16;
        }
        return sb.reverse().toString();
    }


    /**
     * 在补码运算中，最高位表示符号位，符号位是 0 表示正整数和零，符号位是 1 表示负整数。32 位有符号整数的二进制数有 32 位，
     * 由于一位十六进制数对应四位二进制数，因此 32 位有符号整数的十六进制数有 8 位。将 num 的二进制数按照四位一组分成 8 组，
     * 依次将每一组转换为对应的十六进制数，即可得到 num 的十六进制数。
     * 假设二进制数的 8 组从低位到高位依次是第 0 组到第 7 组，则对于第 i 组，可以通过 (nums>>(4×i)) & 0xf 得到该组的值，其取值范围是 0 到 15（即十六进制的 \f）。
     * 将每一组的值转换为十六进制数的做法如下：
     * 对于 0 到 9，数字本身就是十六进制数；
     * 对于 10到 15，将其转换为 a 到 f 中的对应字母。
     * 对于负整数，由于最高位一定不是 0，因此不会出现前导零。对于零和正整数，可能出现前导零。避免前导零的做法如下：
     * 如果 num=0，则直接返回 0；
     * 如果 num>0，则在遍历每一组的值时，从第一个不是 0 的值开始拼接成十六进制数。

     * @param num
     * @return
     */
    public String toHex1(int num) {
        if(num == 0) return "0";
        StringBuffer sb = new StringBuffer();
        for(int i = 7; i >= 0; i--) {
            int val = (num >> (4 * i)) & 0xf;
            if(sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
                sb.append(digit);
            }
        }
        return sb.toString();
    }


    /**
     *  首先，我们可以利用通用的进制转换思路来做，不断循环 num % k 和 num / k 的操作来构造出 k 进制每一位。
     * 但需要处理「补码」问题：对于负数的 num，我们需要先在 num基础上加上 2^{32}的偏移量，再进行进制转换。
     * @param _num
     * @return
     */
    public String toHex2(int _num) {
        if(_num == 0) return "0";
        long num = _num;
        StringBuilder sb = new StringBuilder();
        if(num < 0) num = (long)(Math.pow(2, 32) + num);
        while(num != 0) {
            long u = num % 16;
            char c = (char)(u + '0');
            if(u >= 10) c = (char) (u - 10 + 'a');
            sb.append(c);
            num /= 16;
        }
        return sb.reverse().toString();
    }

    /**
     * 将长度为 32 的二进制转换为 16 进制数，本质是对长度为 32 的二进制数进行分组，
     * 每 4个一组（二进制 (1111)2表示 15，则使用长度为 4 的二进制可以表示 0-15。
     * 同时，由于我们是直接对长度为 32 的二进制进行分组转算（4 个为一组，共 8 组），
     * 而长度为 32 的二进制本身就是使用补码规则来表示的，因此我们无须额外处理「补码」问题。
     * 具体的，我们将 num 与 15 = (1111)_2进行 & 运算，然后对 num进行无符号右移 4 位来实现每 4 位处理。
     * @param num
     * @return
     */
    public String toHex3(int num) {
        if(num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while(num != 0) {
            int u = num & 15;
            char c = (char)(u + '0');
            if(u >= 10) c = (char)(u - 10 + 'a');
            sb.append(c);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }


    public String toHex4(int num) {
        if(num == 0) return "0";
        char[] map = {'0', '1', '2', '3', '4', '5', '6',
        '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder builder = new StringBuilder();
        while(num != 0) {
            builder.insert(0, map[num & 0xf]);
        }
        return builder.toString();
    }




    @Test
    public void test() {
        System.out.println();
        int n = -1;
        System.out.println(toHex(n));
    }


}
