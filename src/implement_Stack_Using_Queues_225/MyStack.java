package src.implement_Stack_Using_Queues_225;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    //不行，完全没思路
//    Queue<Integer> q1 = new LinkedList<Integer>();
//    Queue<Integer> q2 = new LinkedList<Integer>();
//    public MyStack() {
//
//    }
//
//    public void push(int x) {
//        q1.offer(x);
//    }
//
//    public int pop() {
//        if(q1.isEmpty()) return -1;
//        while(q1.size() != 1) q2.offer(q1.poll());
//        return q1.poll();
//    }
//
//    public int top() {
//        return q2.peek();
//    }
//
//    public boolean empty() {
//        return q2.isEmpty();
//    }



    Queue<Integer> queue1;
    Queue<Integer> queue2;

    /**
     * 时间复杂度：入栈操作 O(n)，其余操作都是 O(1)，其中n是栈内的元素个数。
     * 入栈操作需要将 queue1中的 n个元素出队，并入队 n+1个元素到 queue2
     * 共有 2n+1次操作，每次出队和入队操作的时间复杂度都是 O(1)，因此入栈操作的时间复杂度是 O(n)。
     * 出栈操作对应将 queue1前端元素出队，时间复杂度是 O(1)。
     * 获得栈顶元素操作对应获得queue1的前端元素，时间复杂度是 O(1)。
     * 判断栈是否为空操作只需要判断queue1是否为空，时间复杂度是 O(1)。
     * 空间复杂度：O(n)，其中 n是栈内的元素个数。需要使用两个队列存储栈内的元素。
     */
    public MyStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    public void push(int x) {
        queue2.offer(x);
        while(!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue1.poll();
    }
    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //时间复杂度：入栈操作 O(n)，其余操作都是 O(1)，其中 n 是栈内的元素个数。
    //入栈操作需要将队列中的 n个元素出队，并入队 n+1个元素到队列，共有 2n+1次操作，每次出队和入队操作的时间复杂度都是 O(1)，因此入栈操作的时间复杂度是 O(n)。
    //出栈操作对应将队列的前端元素出队，时间复杂度是 O(1)。
    //获得栈顶元素操作对应获得队列的前端元素，时间复杂度是 O(1)。
    //判断栈是否为空操作只需要判断队列是否为空，时间复杂度是 O(1)。
    //空间复杂度：O(n)O，其中 n是栈内的元素个数。需要使用一个队列存储栈内的元素。


//    Queue<Integer> queue;
//
//    public MyStack(){
//        queue = new LinkedList<Integer>();
//    }
//
//    public void push(int x) {
//        int n = queue.size();
//        queue.offer(x);
//        for(int i = 0; i < n; i++) {
//            queue.offer(queue.poll());
//        }
//    }
//
//    public int pop() {
//        return queue.poll();
//    }
//
//    public int top() {
//        return queue.peek();
//    }
//
//    public boolean empty() {
//        return queue.isEmpty();
//    }



}
