package src.counting_Bits_338;

public class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for(int i = 0; i <= n; i++)
            bits[i] = Integer.bitCount(i);
        return bits;
    }


    /**
     * Brian Kernighan 算法
     *
     * 最直观的做法是对从 0 到 n的每个整数直接计算「一比特数」。每个 int 型的数都可以用 32 位二进制数表示，只要遍历其二进制表示的每一位即可得到 1 的数目。
     * 利用 Brian Kernighan 算法，可以在一定程度上进一步提升计算速度。原理是：对于任意整数 x，令 x = x&(x-1)，该运算将 x 的二进制表示的最后一个 1 变成 0。
     * 因此，对 x重复该操作，直到 x 变成 0，则操作次数即为 x 的「一比特数」。
     * 对于给定的 n，计算从 0 到 n 的每个整数的「一比特数」的时间都不会超过 O(logn)，因此总时间复杂度为 O(nlogn)。
     * @param n
     * @return
     */
    public int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        for(int i = 0; i <= n; i++) bits[i] = countOnes(i);
        return bits;
    }
    public int countOnes(int x) {
        int ones = 0;
        while(x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }


    // TODO: 24/11/2022 DP

    /**
     * 不失一般性的，假设当前我要统计的数的 i，i 对应的二进制表示是 00000...0010100101（共 32 位）
     * 如果我们是使用「朴素解法」求解的话，无论是从高位进行统计，还是从低位进行统计，最后一位扫描的都是边缘的数（如果是 1 就计数，不是 1 就不计数）。
     *
     * 从低位到高位，最后一步在扫描最高位之前，统计出 1 的个数应该等同于将 i 左移一位，并在最低位补 0，也就是等于 ans[i << 1]，
     * 这时候就要求我们在计算 i 的时候 i << 1 已经被算出来（从大到小遍历）
     * 从高位到低位，最后一步在扫描最低位之前，统计出 1 的个数应该等同于将 i 右移一位，并在最高位补 0，也就是等于 ans[i >> 1]，
     * 这时候就要求我们在计算 i 的时候 i >> 1 已经被算出来（从小到大遍历）
     * 通过对「朴素做法」的最后一步分析，转移方程就出来了：
     * 当从大到小遍历 ：f(i)=f(i<<1)+((i>>31)&1)
     * 当从小到大遍历 ：f(i)=f(i>>1)+(i&1)

     * @param n
     * @return
     */


    public int[] countBits01(int n) {
        //从大到小遍历
        int[] ans = new int[n + 1];
        for(int i = n; i >= 0; i--) {
//如果计算i所需要的i<<1超过n,则不存在ans内，需要额外计算
            int u = i << 1 <= n ? ans[i << i] : getCnt(i << 1);
            // ans[i] =「i << 1 所包含的 1 的个数」 + 「i 的最高位是否为 1」
            ans[i] = u + ( (i >> 31) & 1);
        }
        return ans;
    }
    int getCnt(int u) {
        int ans = 0;
        for(int i = 0; i < 32; i++)
            ans += (u >> i) & i;
        return ans;
    }


    public int[] countBits02(int n) {
        //从小到大遍历
        int[] ans = new int[n + 1];
        //ans[i] = i >> 1所包含的1的个数  +  i的最低位是否为1
        for(int i = 1; i <= n; i++) ans[i] = ans[i >> 1] + (i & 1);
        return ans;
    }



//
//    a >> b & 1 代表检查 a 的第 b 位是否为 1，有两种可能性 0 或者 1
//
//    a += 1 << b 代表将 a 的第 b 位设置为 1 (当第 b 位为 0 的时候适用)
//
//    如不想写对第 b 位为 0 的前置判断，a += 1 << b 也可以改成 a |= 1 << b
//
//    PS. 1 的二进制就是最低位为 1，其他位为 0 哦


//「一比特数」表示二进制表示中的 1的数目

    /**
     * 动态规划--最高有效位
     * 可以换一个思路，当计算 i 的「一比特数」时，如果存在 0≤j<i，j 的「一比特数」已知，
     * 且 i 和 j 相比，i的二进制表示只多了一个 1，则可以快速得到 i 的「一比特数」。
     * 令bits[i] 表示 i 的「一比特数」，则上述关系可以表示成：bits[i]=bits[j]+1。
     * 对于正整数 x，如果可以知道最大的正整数 y，使得 y≤x 且 y 是  的整数次幂，则 y 的二进制表示中只有最高位是 1，其余都是 0，
     * 此时称 y 为 x 的「最高有效位」。令z=x−y，显然0≤z<x，则 bits[x]=bits[z]+1。
     *
     * 显然，0的「一比特数」为 0。使用 highBit 表示当前的最高有效位，遍历从 1 到 n 的每个正整数 i，进行如下操作。
     * 如果 i&(i-1)=0则令 highBit=i，更新当前的最高有效位。
     * i 比 i−highBit 的「一比特数」多 1，由于是从小到大遍历每个整数，因此遍历到 i 时，i−highBit 的「一比特数」已知，
     * 令 bits[i]=bits[i−highBit]+1。
     *
     * 以8为例，二进制表示是1 0 0 0，那么从8到15的过程中，最高位始终是1，
     * 只有后三位从0 0 0到1 1 1在变化，这就是为什么会有 bits[i] = 1 + bits[i - highBit]
     * 因为bits[highBit] = 1 , 而bits[i] = bits[i - highBit] + bits[highBit]
     *
     * @param n
     * @return
     */
    public int[] countBits3(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for(int i = 1; i <= n; i++) {
            if( (i & i - 1) == 0) highBit = i;
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }


    /**
     * 动态规划--最低有效位
     * 对于正整数 x，将其二进制表示右移一位，等价于将其二进制表示的最低位去掉，
     * 得到的数是 ⌊x/2⌋。如果 bits[⌊x/2⌋] 的值已知，则可以得到 bits[x] 的值：
     * 如果 x 是偶数，则 bits[x]=bits[⌊x/2⌋]；
     * 如果 x 是奇数，则 \bits[x]=bits[⌊x/2⌋]+1。
     * 上述两种情况可以合并成：bits[x] 的值等于 bits[⌊x/2⌋] 的值加上 x 除以 2 的余数。
     * 由于 ⌊x/2⌋ 可以通过 x>>1 得到，x 除以 2 的余数可以通过 x&1得到，因此有：bits[x]=bits[x>>1]+(x&1)。
     * @param n
     * @return
     */
    public int[] countBits4(int n) {
            int[] bits = new int[n + 1];
            for(int i = 1; i <= n; i++) bits[i] = bits[i >> 1] + (i & 1);
            return bits;
    }

    /**
     * 动态规划--最低设置位
     * 定义正整数 x 的「最低设置位」为 x 的二进制表示中的最低的 1 所在位。例如，1010 的二进制表示是 1010(2)，
     * 其最低设置位为 2，对应的二进制表示是 10(2)
     * 遍历从 1到 n 的每个正整数 i，计算 bits 的值。最终得到的数组 bits 即为答案。
     *
     * @param n
     * @return
     */
    public int[] countBits5(int n) {
        int[] bits = new int[n + 1];
        for(int i = 1; i <= n; i++) bits[i] = bits[i & (i - 1)] + 1;
        return bits;
    }








}
