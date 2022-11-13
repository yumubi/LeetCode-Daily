package src.excelSheetColumn_Title_168;

import org.junit.Test;

import java.util.HashMap;

public class Solution {
    /**
     * sb解法
     * @param columnNumber
     * @return
     */
    public String convertToTitle(int columnNumber) {
        HashMap<Integer,String> map = new HashMap<>();

        map.put(1,"A");
        map.put(2,"B");
        map.put(3,"C");
        map.put(4,"D");
        map.put(5,"E");
        map.put(6,"F");
        map.put(7,"G");
        map.put(8,"H");
        map.put(9,"I");
        map.put(10,"J");
        map.put(11,"K");
        map.put(12,"L");
        map.put(13,"M");
        map.put(14,"N");
        map.put(15,"O");
        map.put(16,"P");
        map.put(17,"Q");
        map.put(18,"R");
        map.put(19,"S");
        map.put(20,"T");
        map.put(21,"U");
        map.put(22,"V");
        map.put(23,"W");
        map.put(24,"X");
        map.put(25,"Y");
        map.put(26,"Z");


        StringBuilder sb = new StringBuilder();
        while(columnNumber > 0) {
            int mod = columnNumber % 26;
            if (mod == 0) {
                sb.append("Z");
                columnNumber = columnNumber / 26 - 1;
            } else {
                sb.append(map.get(mod));
                columnNumber /= 26;
            }
        }
        return sb.reverse().toString();
    }

    /**
     * 这是一道从 1 开始的的 26 进制转换题。
     * 对于一般性的进制转换题目，只需要不断地对 columnNumber 进行 % 运算取得最后一位，
     * 然后对 columnNumber 进行 / 运算，将已经取得的位数去掉，直到 columnNumber 为 00 即可。
     * 一般性的进制转换题目无须进行额外操作，是因为我们是在「每一位数值范围在 [0,x)的前提下进行「逢 x 进一」。
     * 但本题需要我们将从 1开始，因此在执行「进制转换」操作前，我们需要先对 columnNumber 执行减一操作，从而实现整体偏移
     * @param cn
     * @return
     */

    public String convertToTitle2(int cn) {
        StringBuilder sb = new StringBuilder();
        while (cn > 0) {
            //因为x%26=[0,25] 但是我们取从[1-26]对应[A-Z]
            // 直接取模 26%26=0 不在我们范围内 所以我们采用-1 [0-25]对应[A-Z] (26-1)%26 =25 25+'A'='Z
            cn--;
            sb.append((char)(cn % 26 + 'A'));
            cn /= 26;
        }
        sb.reverse();
        return sb.toString();
    }



    @Test
    public void test() {
        int i = 260;
        System.out.println(convertToTitle(i));
        System.out.println(26/26);

    }
}
