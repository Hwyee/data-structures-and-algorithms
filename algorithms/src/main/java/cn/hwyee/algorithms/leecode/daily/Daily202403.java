package cn.hwyee.algorithms.leecode.daily;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

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
//        MyQueue myQueue = new MyQueue();
//        myQueue.push(1);
//        myQueue.pop();
//        System.out.println(myQueue.empty());
        //findKOr test
//        int[] find = new int[]{8};
//        Solution_6_1 solution61 = new Solution_6_1();
//        int kOr = solution61.findKOr(find, 1);
        //divisibilityArray
        Solution_7_1 solution71 = new Solution_7_1();
        solution71.divisibilityArray("5292821435",4);
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
            //second solution
            //if (!stack2.isEmpty()) {stack1.forEach(stack2.push(stack1.pop()));} return stack2.pop();
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

    /**
     * 方法一：优先队列实现的 Dijkstra 算法:
     * //TODO 图论
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/7 0:44
     */
    class Solution_5_1 {
        public int countPaths(int n, int[][] roads) {
            return 1;
        }
    }


    /**
     * 2917. 找出数组中的 K-or 值:
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
     * nums 中的 K-or 是一个满足以下条件的非负整数：
     * 只有在 nums 中，至少存在 k 个元素的第 i 位值为 1 ，那么 K-or 中的第 i 位的值才是 1 。
     * 返回 nums 的 K-or 值。
     * 注意 ：对于整数 x ，如果 (2i AND x) == 2i (这个公式里的2i是2的i次方) ，则 x 中的第 i 位值为 1 ，其中 AND 为按位与运算符。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/6 23:07
     */
    static class Solution_6_1 {
        public int findKOr(int[] nums, int k) {
            //int 最大值 2的32次方
            int len = 0;
            for (int num : nums) {
                if (num >= len) {
                    len = num;
                }
            }

            len = (int) Math.sqrt(len) + 1;
            int res = 0;
            for (int i = 0; i <= len; i++) {
                int count = 0;
                for (int num : nums) {
                    if ((num >> i & 1) == 1) {
                        count++;
                    }
                    if (count == k) {
                        break;
                    }
                }
                if (count == k) {
                    res |= 1 << i;
                }
            }

            return res;
        }
    }

    /**
     * 2575. 找出字符串的可整除数组:
     * 给你一个下标从 0 开始的字符串 word ，长度为 n ，由从 0 到 9 的数字组成。另给你一个正整数 m 。
     * word 的 可整除数组 div  是一个长度为 n 的整数数组，并满足：
     * 如果 word[0,...,i] 所表示的 数值 能被 m 整除，div[i] = 1
     * 否则，div[i] = 0
     * 返回 word 的可整除数组。
     * (a×10+b)modm=(amodm×10+b)modm
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/7 21:30
     */
    static class Solution_7_1 {
        public int[] divisibilityArray(String word, int m) {
            int[] res = new int[word.length()];
            //m的值最大10亿，int最大20亿，如果被除数为30亿就炸了所以用long
            long cur = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                //为什么 * 10，这样可以手动加法找到比除数大的
                cur = (cur * 10 + (c - '0')) % m;
                res[i] = (cur == 0) ? 1 : 0;
            }
            return res;
        }

    }




}
