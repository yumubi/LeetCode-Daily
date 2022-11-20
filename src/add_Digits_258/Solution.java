package src.add_Digits_258;

public class Solution {
    /**
     * my answer
     * @param num
     * @return
     */
    public int addDigits(int num) {
        while(num >= 10) {
             num = split(num);
            if(num < 10) return num;
        }
        return num;
    }

    public int split(int num) {
        int sum = 0;
        while(num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }


    /**
     * 模拟
     * @param num
     * @return
     */
    public int addDigits2(int num) {
        while(num >= 10) {
            int sum = 0;
            while(num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }


    /**
     * 对于任意一个正整数而言，我们最终是要求得 a_0 + a_1 + a_2 + ... + a_{n - 1}对 9 取模的数。
     * 而利用「同余式相加」性质，等价于每个数分别对 9 取模之和，再集合任意的 10^n对 9 取模恒为 1，
     * 可得最终答案为原数对 9 取模，剩下只需要对相加结果为 9 的边界情况进行处理即可。
     * @param num
     * @return
     */
    public int addDigits3(int num) {
        return (num - 1) % 9 + 1;
    }








}
