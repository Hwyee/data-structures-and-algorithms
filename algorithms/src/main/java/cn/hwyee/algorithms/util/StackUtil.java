package cn.hwyee.algorithms.util;

import java.util.Stack;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName StackUtil
 * @description 栈工具
 * @date 2023/6/24
 * @since JDK 1.8
 */
public class StackUtil {
    private StackUtil(){}

    /**
     * Queue:
     * 用两个栈实现队列
     * @author hui
     * @version 1.0
     * @date 2023/6/24 23:46
     */
    static class Queue {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();


        public void push(int node) {
            stack1.add(node);
        }

        public int pop() {
            //将第一个栈中内容弹出放入第二个栈中
            while (!stack1.isEmpty()) {
                stack2.add(stack1.pop());
            }
            //第二个栈栈顶就是最先进来的元素，即队首
            int res = stack2.pop();
            //再将第二个栈的元素放回第一个栈
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return res;
        }
    }
}
