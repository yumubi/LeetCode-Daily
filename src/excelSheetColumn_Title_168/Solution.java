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

    @Test
    public void test() {
        int i = 260;
        System.out.println(convertToTitle(i));
        System.out.println(26/26);

    }
}
