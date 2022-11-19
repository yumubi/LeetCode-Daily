package src.implements_Queue_Using_Stack_232;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Stack;
public class MyQueue {


    /**
     * 只过了15个，空指针异常待解决
     */


//        Stack<Integer> inStack;
//        Stack<Integer> outStack;
//
//        public MyQueue() {
//            inStack = new Stack<Integer>();
//            outStack = new Stack<Integer>();
//        }
//
//        public void push(int x) {
//
//            inStack.push(x);
//
//            while(!outStack.isEmpty()) inStack.push(outStack.pop());
//            Stack<Integer> tmp;
//            tmp = outStack;
//            outStack = inStack;
//            inStack = tmp;
//        }
//
//        public int pop() {
//            return outStack.pop();
//        }
//
//        public int peek() {
//            return outStack.peek();
//        }
//
//        public boolean empty() {
//            return outStack.isEmpty();
//        }


    /**
     * 将一个栈当作输入栈，用于压入 push 传入的数据；另一个栈当作输出栈，
     * 用于 pop 和 peek 操作。
     * 每次 pop 或 peek 时，若输出栈为空则将输入栈的全部数据依次弹出并压入输出栈，
     * 这样输出栈从栈顶往栈底的顺序就是队列从队首往队尾的顺序。
     *
     * 时间复杂度：push 和 empty 为 O(1) pop 和 peek 为均摊 O(1)
     * 对于每个元素，至多入栈和出栈各两次，故均摊复杂度为 O(1)。
     * 空间复杂度：O(n)。其中 n是操作总数。对于有 n次 push 操作的情况，队列中会有n个元素，故空间复杂度为 O(n)。
     */
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public MyQueue() {
        inStack = new ArrayDeque<Integer>();
        outStack = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if(outStack.isEmpty()) in2out();
        return outStack.pop();
    }

    public int peek() {
        if(outStack.isEmpty()) in2out();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void in2out() {
        while(!inStack.isEmpty()) outStack.push(inStack.pop());
    }


    /**
     * O(n)解法
     * 创建两个栈，分别为 out 和 in：
     * in 用作处理输入操作 push()，使用 in 时需确保 out 为空
     * out 用作处理输出操作 pop() 和 peek()，使用 out 时需确保 in 为空
     */
//
//    class MyQueue {
//        Deque<Integer> out, in;
//        public MyQueue() {
//            in = new ArrayDeque<>();
//            out = new ArrayDeque<>();
//        }
//
//        public void push(int x) {
//            while (!out.isEmpty()) in.addLast(out.pollLast());
//            in.addLast(x);
//        }
//
//        public int pop() {
//            while (!in.isEmpty()) out.addLast(in.pollLast());
//            return out.pollLast();
//        }
//
//        public int peek() {
//            while (!in.isEmpty()) out.addLast(in.pollLast());
//            return out.peekLast();
//        }
//
//        public boolean empty() {
//            return out.isEmpty() && in.isEmpty();
//        }
//    }


/**
 * 均摊 O(1) 解法
 * 事实上，我们不需要在每次的「入栈」和「出栈」操作中都进行「倒腾」。
 * 只需要保证，输入的元素总是跟在前面的输入元素的后面，而输出元素总是最早输入的那个元素即可。
 * 可以通过调整「倒腾」的时机来确保满足上述要求，但又不需要发生在每一次操作中：
 * 只有在「输出栈」为空的时候，才发生一次性的「倒腾」
 */
//class MyQueue {
//    Deque<Integer> out, in;
//    public MyQueue() {
//        in = new ArrayDeque<>();
//        out = new ArrayDeque<>();
//    }
//
//    public void push(int x) {
//        in.addLast(x);
//    }
//
//    public int pop() {
//        if (out.isEmpty()) {
//            while (!in.isEmpty()) out.addLast(in.pollLast());
//        }
//        return out.pollLast();
//    }
//
//    public int peek() {
//        if (out.isEmpty()) {
//            while (!in.isEmpty()) out.addLast(in.pollLast());
//        }
//        return out.peekLast();
//    }
//
//    public boolean empty() {
//        return out.isEmpty() && in.isEmpty();
//    }
//}










}
