package cn.hwyee.algorithms.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
    private StackUtil() {
    }

    /**
     * Queue:
     * 用两个栈实现队列
     *
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

    /**
     * 包含min函数的栈:
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的 min 函数，输入操作时保证 pop、top 和 min 函数操作时，栈中一定有元素。
     * <p>
     * 此栈包含的方法有：
     * push(value):将value压入栈中
     * pop():弹出栈顶元素
     * top():获取栈顶元素
     * min():获取栈中最小元素
     * <p>
     * 栈是一种仅支持在表尾进行插入和删除操作的线性表，这一端被称为栈顶，另一端被称为栈底。
     * 元素入栈指的是把新元素放到栈顶元素的上面，使之成为新的栈顶元素；元素出栈指的是从一个栈删除
     * 元素又称作出栈或退栈，它是把栈顶元素删除掉，使其相邻的元素成为新的栈顶元素。
     *
     * @author hui
     * @version 1.0
     * @date 2023/6/25 23:26
     */
    static class MinStack {
        //用于栈的push 与 pop
        Stack<Integer> s1 = new Stack<Integer>();
        //用于存储最小min
        Stack<Integer> s2 = new Stack<Integer>();

        public void push(int node) {
            s1.push(node);
            //空或者新元素较小，则入栈
            if (s2.isEmpty() || s2.peek() > node) {
                s2.push(node);
            } else
            //重复加入栈顶
            {
                s2.push(s2.peek());
            }
        }

        public void pop() {
            s1.pop();
            s2.pop();
        }

        public int top() {
            return s1.peek();
        }

        public int min() {
            return s2.peek();
        }
    }


    /**
     * isValid:
     * 有效括号序列
     * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
     * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
     * 括号的匹配规则应该符合先进后出原理：最外层的括号即最早出现的左括号，也对应最晚出现的右括号，即先进后出，因此可以使用同样先进后出的栈：
     * 遇到左括号就将相应匹配的右括号加入栈中，后续如果是合法的，右括号来的顺序就是栈中弹出的顺序。
     *
     * @param s
     * @return boolean
     * @author hui
     * @version 1.0
     * @date 2023/6/25 23:32
     */
    public boolean isValid(String s) {
        //辅助栈
        Stack<Character> st = new Stack<Character>();
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            //遇到左小括号
            if (s.charAt(i) == '(')
            //期待遇到右小括号
            {
                st.push(')');
            }
            //遇到左中括号
            else if (s.charAt(i) == '[')
            //期待遇到右中括号
            {
                st.push(']');
            }
            //遇到左打括号
            else if (s.charAt(i) == '{')
            //期待遇到右打括号
            {
                st.push('}');
            }
            //必须有左括号的情况下才能遇到右括号
            else if (st.isEmpty() || st.pop() != s.charAt(i)) {
                return false;
            }
        }
        //栈中是否还有元素
        return st.isEmpty();
    }

    /**
     * maxInWindows:
     * 滑动窗口的最大值
     * 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。
     *
     * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
     * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
     * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     *
     * 窗口大于数组长度或窗口长度为0的时候，返回空。
     * 我们都知道，若是一个数字A进入窗口后，若是比窗口内其他数字都大，那么这个数字之前的数字都没用了，因为它们必定会比A早离开窗口，
     * 在A离开之前都争不过A，所以A在进入时依次从尾部排除掉之前的小值再进入，而每次窗口移动要弹出窗口最前面值，因此队首也需要弹出，
     * 所以我们选择双向队列。
     * @author hui
     * @version 1.0
     * @param num 
    * @param size  
     * @return java.util.ArrayList<java.lang.Integer>
     * @date 2023/6/25 23:39
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        //窗口大于数组长度的时候，返回空
        if (size <= num.length && size != 0) {
            //双向队列
            ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
            //先遍历一个窗口
            for (int i = 0; i < size; i++) {
                //去掉比自己先进队列的小于自己的值
                while (!dq.isEmpty() && num[dq.peekLast()] < num[i]) {
                    dq.pollLast();
                }
                dq.add(i);
            }
            //遍历后续数组元素
            for (int i = size; i < num.length; i++) {
                //取窗口内的最大值
                res.add(num[dq.peekFirst()]);
                while (!dq.isEmpty() && dq.peekFirst() < (i - size + 1))
                //弹出窗口移走后的值
                {
                    dq.pollFirst();
                }
                //加入新的值前，去掉比自己先进队列的小于自己的值
                while (!dq.isEmpty() && num[dq.peekLast()] < num[i]) {
                    dq.pollLast();
                }
                dq.add(i);
            }
            res.add(num[dq.pollFirst()]);
        }
        return res;
    }

}
