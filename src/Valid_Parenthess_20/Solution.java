package src.Valid_Parenthess_20;

import org.junit.Test;

import java.util.*;

public class Solution {


    /**
     * todo
     * java.lang.NullPointerException: Cannot invoke "java.lang.Character.charValue()"
     * because the return value of "java.util.ArrayDeque.peekFirst()" is null
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        ArrayDeque<Character> p1 = new ArrayDeque<>();
        char[] pList = s.toCharArray();
        for(char p : pList) {
            if(p == '(') p1.addFirst('(');
            if(p == '[') p1.addFirst('[');
            if(p == '{') p1.addFirst('{');
            if(p == ')' && p1.peekFirst() == '(') p1.pollFirst();
            if(p == ']' && p1.peekFirst() == '[') p1.pollFirst();
            if(p == '}' && p1.peekFirst() == '{') p1.pollFirst();
        }
        if(p1.isEmpty()) return true;
        return false;
    }


    /**
     * replaace及其变种
     * @param s
     * @return
     */
    public boolean isValid1(String s) {
        while(true) {
            int l = s.length();
            s.replace("()", "")
                .replace("[]", "")
                    .replace("{}", "");
            if(s.length() == l) return  l == 0;
        }
    }

    public boolean isValid2(String s) {
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='('||c=='['||c=='{'){stack.push(c);}
            else if(stack.isEmpty()||c==')'&&stack.pop()!='('||c==']'&&stack.pop()!='['||c=='}'&&stack.pop()!='{'){return false;}
        }
        return stack.isEmpty();
    }


    /**
     * map
     * 建立哈希表 dic 构建左右括号对应关系：keykey 左括号，valuevalue 右括号；
     * 这样查询 22 个括号是否对应只需 O(1)O(1) 时间复杂度；建立栈 stack，遍历字符串 s 并按照算法流程一一判断。
     如果 c 是左括号，则入栈 pushpush；
     否则通过哈希表判断括号对应关系，若 stack 栈顶出栈括号 stack.pop() 与当前遍历括号 c 不对应，则提前返回 falsefalse。
     提前false返回优点： 在迭代过程中，提前发现不符合的括号并且返回，提升算法效率。

     注意边界问题
     https://leetcode.cn/problems/valid-parentheses/solution/valid-parentheses-fu-zhu-zhan-fa-by-jin407891080/
     *
     *
     */
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid3(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }


    /**
     * leetCode
     */
    public boolean isValid4(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }




    @Test
    public void test() {

    }

}

