package cn.hwyee.algorithms.leecode.daily;

import org.springframework.cglib.proxy.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202403
 * @description
 * @date 2024/3/1
 * @since JDK 1.8
 */
public class Daily202403 {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.pop();
        System.out.println(myQueue.empty());
    }

    /**
     * 2369. 检查数组是否存在有效划分:
     * 给你一个下标从 0 开始的整数数组 nums ，你必须将数组划分为一个或多个 连续 子数组。
     * <p>
     * 如果获得的这些子数组中每个都能满足下述条件 之一 ，则可以称其为数组的一种 有效 划分：
     * <p>
     * 子数组 恰 由 2 个相等元素组成，例如，子数组 [2,2] 。
     * 子数组 恰 由 3 个相等元素组成，例如，子数组 [4,4,4] 。
     * 子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1 。例如，子数组 [3,4,5] ，但是子数组 [1,3,5] 不符合要求。
     * 如果数组 至少 存在一种有效划分，返回 true ，否则，返回 false 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/1 23:21
     */
    class Solution_01_01 {
        public boolean validPartition(int[] nums) {
            //传过来的数组应该是有序的
            int n = nums.length;
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 2; i <= n; i++) {
                if (i >= 2) {
                    dp[i] = dp[i - 2] && validTwo(nums[i - 1], nums[i - 2]);
                }
                if (i >= 3) {
                    dp[i] = dp[i] || (dp[i - 3] && validThree(nums[i - 3], nums[i - 2], nums[i - 1]));
                }

            }
            return dp[n];
        }

        public boolean validTwo(int i, int j) {
            return i == j;
        }

        public boolean validThree(int i, int j, int k) {
            return (i == j && i == k) || (j == i + 1 && k == j + 1);
        }
    }

    /**
     * 2368. 受限条件下可到达节点的数目:
     * 现有一棵由 n 个节点组成的无向树，节点编号从 0 到 n - 1 ，共有 n - 1 条边。
     * 给你一个二维整数数组 edges ，长度为 n - 1 ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。另给你一个整数数组 restricted 表示 受限 节点。
     * 在不访问受限节点的前提下，返回你可以从节点 0 到达的 最多 节点数目。
     * 注意，节点 0 不 会标记为受限节点。也就是至少有一个。
     * <p>
     * edges 表示一棵有效的树
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/2 20:36
     */
    class Solution {
        int res = 0;
        boolean[] isRestricted;

        public int reachableNodes(int n, int[][] edges, int[] restricted) {
            isRestricted = new boolean[n];
            for (int i : restricted) {
                isRestricted[i] = true;
            }
            ArrayList<Integer>[] list = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                list[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                list[edge[0]].add(edge[1]);
                list[edge[1]].add(edge[0]);
            }
            dfs(0, -1, list);
            return res;
        }

        public void dfs(int i, int j, ArrayList<Integer>[] edges) {
            res++;
            for (Integer o : edges[i]) {
                if (o != j && !isRestricted[o]) {
                    dfs(o, i, edges);
                }
            }
        }
    }

    /**
     * 225. 用队列实现栈:
     * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
     * 实现 MyStack 类：
     * void push(int x) 将元素 x 压入栈顶。
     * int pop() 移除并返回栈顶元素。
     * int top() 返回栈顶元素。
     * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/3 17:17
     */
    class MyStack {
        /**
         * Your MyStack object will be instantiated and called as such:
         * MyStack obj = new MyStack();
         * obj.push(x);
         * int param_2 = obj.pop();
         * int param_3 = obj.top();
         * boolean param_4 = obj.empty();
         */
        List<Integer> list;

        public MyStack() {
            list = new ArrayList<Integer>();
        }

        public void push(int x) {
            list.add(x);
        }

        public int pop() {
            Integer integer = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return integer;
        }

        public int top() {
            Integer integer = list.get(list.size() - 1);
            return integer;
        }

        public boolean empty() {
            return list.isEmpty();
        }
    }

    /**
     * 232. 用栈实现队列:
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
     * <p>
     * 实现 MyQueue 类：
     * <p>
     * void push(int x) 将元素 x 推到队列的末尾
     * int pop() 从队列的开头移除并返回元素
     * int peek() 返回队列开头的元素
     * boolean empty() 如果队列为空，返回 true ；否则，返回 false
     * 说明：
     * <p>
     * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/4 21:44
     */
    static class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        /**
         * MyQueue:
         * <p>
         * * Your MyQueue object will be instantiated and called as such:
         * * MyQueue obj = new MyQueue();
         * * obj.push(x);
         * * int param_2 = obj.pop();
         * * int param_3 = obj.peek();
         * * boolean param_4 = obj.empty();
         *
         * @return
         * @author hui
         * @version 1.0
         * @date 2024/3/4 21:44
         */
        public MyQueue() {
            stack1 = new Stack();
            stack2 = new Stack();
        }

        public void push(int x) {
            if (stack1.isEmpty() && stack2.isEmpty()) {
                stack1.push(x);
            } else if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                stack2.push(x);
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
            } else {
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
                stack1.push(x);
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }

        }

        public int pop() {
            int x = 0;
            if (stack2.isEmpty()) {
                x = stack1.pop();
            } else {
                x = stack2.pop();
            }
            return x;
        }

        public int peek() {
            int x = 0;
            if (stack2.isEmpty()) {
                x = stack1.peek();
            } else {
                x = stack2.peek();
            }
            return x;
        }

        public boolean empty() {
            return (stack1.isEmpty() && stack2.isEmpty());
        }
    }


}
