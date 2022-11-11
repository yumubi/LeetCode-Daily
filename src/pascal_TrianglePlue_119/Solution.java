package src.pascal_TrianglePlue_119;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        if(rowIndex == 0) return new ArrayList<>(){{add(1);}};
        List<Integer> list = new ArrayList<>(){{add(1); add(1);}};
        if(rowIndex == 1) return list;
        for(int i = 2; i <= rowIndex; i++) {
            List<Integer> preList = List.copyOf(list);
            for (int j = 1; j < i; j++) {
                list.set(j, preList.get(j) + preList.get(j - 1));
            }
            list.add(1);
        }
        return list;
    }


    @Test
    public void test() {
        System.out.println(getRow(5));
    }
}
