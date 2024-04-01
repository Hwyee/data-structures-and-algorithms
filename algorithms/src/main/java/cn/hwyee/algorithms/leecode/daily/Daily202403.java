package cn.hwyee.algorithms.leecode.daily;

import ch.qos.logback.core.joran.conditional.IfAction;
import cn.hwyee.datastructures.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202403
 * @description
 * @date 2024/3/1
 * @since JDK 1.8
 */
@Slf4j
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
//        Solution_7_1 solution71 = new Solution_7_1();
//        solution71.divisibilityArray("5292821435",4);
        //minimumPossibleSum
//        Solution_8_1 solution81 = new Solution_8_1();
//        solution81.minimumPossibleSum(63623, 82276);
        //kSum
//        Solution_9_1 solution91 = new Solution_9_1();
//        long kSumF = solution91.kSumEF(new int[]{8, 1, 2, 3}, 5);
//        log.info("kSum: " + kSumF);
        //FindElements
//        TreeNode treeNode = new TreeNode(-1, null, new TreeNode(-1));
//        FindElements findElements = new FindElements(treeNode);
//        findElements.find(1);
//        findElements.find(2);
        //maximumOddBinaryNumber
        Solution_13_1 solution131 = new Solution_13_1();
        solution131.maximumOddBinaryNumber("010");
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
     *
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

    /**
     * 2834. 找出美丽数组的最小和:
     * 给你两个正整数：n 和 target 。
     * 如果数组 nums 满足下述条件，则称其为 美丽数组 。
     * nums.length == n.
     * nums 由两两互不相同的正整数组成。
     * 在范围 [0, n-1] 内，不存在 两个 不同 下标 i 和 j ，使得 nums[i] + nums[j] == target 。
     * 返回符合条件的美丽数组所可能具备的 最小 和，并对结果进行取模 109 + 7。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/8 23:04
     */
    static class Solution_8_1 {

        //超出时间限制 574 / 575 个通过的测试用例
        public int minimumPossibleSum(int n, int target) {
            int index = 1;
            int res = 0;
            int a = target / 2;
            for (int i = 0; i < n; i++) {
                if (index == a) {
                    res += index;
                    index += (target - a - 1);
                } else {
                    res += index;
                }
                index++;
                if (res >= 1000000007) {
                    res %= 1000000007;
                }
            }
            return (res % (1000000007));

        }

        //数字是连续的，可以使用等差数列求解
        public int minimumPossibleSumGF(int n, int target) {
            final int MOD = (int) 1e9 + 7;
            int m = target / 2;
            if (n <= m) {
                return (int) ((long) (1 + n) * n / 2 % MOD);
            }
            //第二阶段是计算 target 到 (target +  (n - m - 1))的总数，总共有(n - m) 个数
            return (int) (((long) (1 + m) * m / 2 +
                    ((long) target + target + (n - m) - 1) * (n - m) / 2) % MOD);

        }
    }


    /**
     * 2386. 找出数组的第 K 大和:
     * 给你一个整数数组 nums 和一个 正 整数 k 。你可以选择数组的任一 子序列 并且对其全部元素求和。
     * 数组的 第 k 大和 定义为：可以获得的第 k 个 最大 子序列和（子序列和允许出现重复）
     * 返回数组的 第 k 大和 。
     * 子序列是一个可以由其他数组删除某些或不删除元素排生而来的数组，且派生过程不改变剩余元素的顺序。
     * 注意：空子序列的和视作 0 。
     * <p>
     * 求所有子序列，然后倒序。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/9 16:23
     */
    static class Solution_9_1 {
        public long kSum(int[] nums, int k) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(0);
            for (int i = 1; i < nums.length; i++) {

            }
            return 1L;
        }

        public long kSumF(int[] nums, int k) {
            int n = nums.length;
            long total = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] >= 0) {
                    total += nums[i];
                } else {
                    nums[i] = -nums[i];
                }
            }
            Arrays.sort(nums);

            long ret = 0;
            PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a, b) -> Long.compare(a[0], b[0]));
            pq.offer(new long[]{nums[0], 0});
            //如果k=1，那total即为所求，所有正数加起来就是第一大
            for (int j = 2; j <= k; j++) {
                long[] arr = pq.poll();
                long t = arr[0];
                int i = (int) arr[1];
                ret = t;
                if (i == n - 1) {
                    continue;
                }
                //这个相当于迭代，每个元素都有两种情况，在子序列中（相加），不在子序列中（减去）。
                //相当于是一颗树，一分为二，二分为四，但是少了空数组的情况
                //题目中子序列的空数组和为0，但是在这个算法中，空数组的和为total。可以好好体会下。
                pq.offer(new long[]{t + nums[i + 1], i + 1});
                pq.offer(new long[]{t - nums[i] + nums[i + 1], i + 1});
            }
            return total - ret;
        }

        int cnt;

        /**
         * 官方二分:
         *
         * @return
         * @author hui
         * @version 1.0
         * @date 2024/3/9 21:28
         */
        public long kSumEF(int[] nums, int k) {
            int n = nums.length;
            long total = 0, total2 = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] >= 0) {
                    total += nums[i];
                } else {
                    nums[i] = -nums[i];
                }
                total2 += Math.abs(nums[i]);
            }
            Arrays.sort(nums);
            //和优先队列差不多，也是把问题转化成递增的正整数序列第k个子序列的和
            //使用二分法不断逼近结果
            long left = 0, right = total2;
            while (left <= right) {
                long mid = (left + right) / 2;
                cnt = 0;
                //这个深度优先搜索相当于是二分法所要寻找的值
                dfs(nums, k, n, 0, 0, mid);
                if (cnt >= k - 1) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return total - left;
        }

        /**
         * dfs:
         *
         * @param nums
         * @param k     第k个最小子序列
         * @param n     长度
         * @param i     当前搜索的元素
         * @param t     前i个序列的和
         * @param limit
         * @return void
         * @author hui
         * @version 1.0
         * @date 2024/3/9 23:37
         */
        public void dfs(int[] nums, int k, int n, int i, long t, long limit) {
            //一般来讲，t+nums[i] > limit会先符合条件，除非k值很小，这时cnt会大于k，
            if (i == n || cnt >= k - 1 || t + nums[i] > limit) {
                return;
            }
            cnt++;
            dfs(nums, k, n, i + 1, t + nums[i], limit);
            dfs(nums, k, n, i + 1, t, limit);
        }
    }


    /**
     * 299. 猜数字游戏:
     * 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
     * 写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：
     * 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls"，公牛），
     * 有多少位属于数字猜对了但是位置不对（称为 "Cows"，奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
     * 给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。
     * 提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。
     * 请注意秘密数字和朋友猜测的数字都可能含有重复数字。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/10 23:39
     */
    class Solution_10_1 {
        public String getHint(String secret, String guess) {
            return "1";
        }

        public String getHintGF(String secret, String guess) {
            int bulls = 0;
            int[] cntS = new int[10];
            int[] cntG = new int[10];
            for (int i = 0; i < secret.length(); ++i) {
                if (secret.charAt(i) == guess.charAt(i)) {
                    ++bulls;
                } else {
                    ++cntS[secret.charAt(i) - '0'];
                    ++cntG[guess.charAt(i) - '0'];
                }
            }
            int cows = 0;
            for (int i = 0; i < 10; ++i) {
                cows += Math.min(cntS[i], cntG[i]);
            }
            return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
        }
    }

    /**
     * 2129. 将标题首字母大写:
     * 给你一个字符串 title ，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。请你按以下规则将每个单词的首字母 大写 ：
     * 如果单词的长度为 1 或者 2 ，所有字母变成小写。
     * 否则，将单词首字母大写，剩余字母变成小写。
     * 请你返回 大写后 的 title 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/11 22:24
     */
    class Solution_11_1 {
        public String capitalizeTitle(String title) {
            String[] split = title.split(" ");
            StringBuilder sb = new StringBuilder();
            //97-65
            int up = 'a' - 'A';
            for (String s : split) {
                if (s.length() == 1 || s.length() == 2) {
                    sb.append(s.toLowerCase()).append(" ");
                } else {
                    sb.append(s.charAt(0) > 96 ? (char) (s.charAt(0) - up) :
                            s.charAt(0)).append(s.substring(1)
                            .toLowerCase()).append(" ");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }

    /**
     * 1261. 在受污染的二叉树中查找元素:
     * 给出一个满足下述规则的二叉树：
     * <p>
     * root.val == 0
     * 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
     * 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
     * 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
     * 请你先还原二叉树，然后实现 FindElements 类：
     * FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。
     * bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/12 22:52
     */
    static class FindElements {
        TreeNode treeNode = null;

        public FindElements(TreeNode root) {
            root.val = 0;
            bfs(root);
            treeNode = root;
        }

        public void bfs(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                root.left.val = root.val * 2 + 1;
                bfs(root.left);
            }
            if (root.right != null) {
                root.right.val = root.val * 2 + 2;
                bfs(root.right);
            }
        }


        public boolean find(int target) {
            //层序遍历
            Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
            deque.offer(treeNode);
            boolean res = false;
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = deque.pop();
                    if (pop.val == target) {
                        res = true;
                        deque = new ArrayDeque<>();
                        break;
                    }
                    if (pop.val > target) {
                        deque = new ArrayDeque<>();
                        break;
                    }
                    if (pop.left != null) {
                        deque.offer(pop.left);
                    }
                    if (pop.right != null) {
                        deque.offer(pop.right);
                    }
                }
            }
            return res;
        }

        public boolean findGF(int target) {
            target++;
            int k = 30 - Integer.numberOfLeadingZeros(target);
            TreeNode node = treeNode;
            while (k >= 0 && node != null) {
                if ((target & (1 << k)) == 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
                k--;
            }
            return node != null;
        }


    }

    class FindElementsGF {
        private Set<Integer> valSet;

        public FindElementsGF(TreeNode root) {
            this.valSet = new HashSet<>();
            dfs(root, 0);
        }

        public boolean find(int target) {
            return valSet.contains(target);
        }


        private void dfs(TreeNode node, int val) {
            if (node == null) {
                return;
            }
            node.val = val;
            valSet.add(val);
            dfs(node.left, val * 2 + 1);
            dfs(node.right, val * 2 + 2);
        }
    }


    /**
     * 2864. 最大二进制奇数:
     * 给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。
     * 你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。
     * 以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。
     * 注意 返回的结果字符串 可以 含前导零。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/13 23:26
     */
    static class Solution_13_1 {
        public String maximumOddBinaryNumber(String s) {
            StringBuilder s0 = new StringBuilder();
            StringBuilder s1 = new StringBuilder();
            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                if (c == '1') {
                    s1.append(1);
                } else {
                    s0.append(0);
                }
            }
            return s1.deleteCharAt(s1.length() - 1).append(s0).append(1).toString();
        }
    }

    /**
     * 2789. 合并后数组中的最大元素:
     * 给你一个下标从 0 开始、由正整数组成的数组 nums 。
     * 你可以在数组上执行下述操作 任意 次：
     * 选中一个同时满足 0 <= i < nums.length - 1 和 nums[i] <= nums[i + 1] 的整数 i 。将元素 nums[i + 1] 替换为 nums[i] + nums[i + 1] ，并从数组中删除元素 nums[i] 。
     * 返回你可以从最终数组中获得的 最大 元素的值。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/14 23:14
     */
    class Solution_14_1 {
        public long maxArrayValue(int[] nums) {
            long sum = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 0; i--) {
                sum = nums[i] <= sum ? nums[i] + sum : nums[i];
            }
            return sum;
        }
    }

    /**
     * 2312. 卖木头块:
     * 给你两个整数 m 和 n ，分别表示一块矩形木块的高和宽。同时给你一个二维整数数组 prices ，其中 prices[i] = [hi, wi, pricei]
     * 表示你可以以 pricei 元的价格卖一块高为 hi 宽为 wi 的矩形木块。
     * 每一次操作中，你必须按下述方式之一执行切割操作，以得到两块更小的矩形木块：
     * 沿垂直方向按高度 完全 切割木块，或
     * 沿水平方向按宽度 完全 切割木块
     * 在将一块木块切成若干小木块后，你可以根据 prices 卖木块。你可以卖多块同样尺寸的木块。你不需要将所有小木块都卖出去。你 不能 旋转切好后木块的高和宽。
     * 请你返回切割一块大小为 m x n 的木块后，能得到的 最多 钱数。
     * 注意你可以切割木块任意次。
     * 输入：m = 3, n = 5, prices = [[1,4,2],[2,2,7],[2,1,3]]
     * <p>
     * 状态方程：f(m,n) = f(hi,wi) + f(m-hi,n-wi)
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/15 19:49
     */
    class Solution_15_1 {
        public long sellingWood(int m, int n, int[][] prices) {
            int length = prices.length;
            int[][] priceArr = new int[m + 1][n + 1];
            for (int[] price : prices) {
                priceArr[price[0]][price[1]] = price[2];
            }
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    //因为对于一块木头，从第三分之一处切割和三分之二处切割是一样的。
                    for (int k = 1; k <= j / 2; k++) {
                        priceArr[i][j] = Math.max(priceArr[i][j], priceArr[i][k] + priceArr[i][j - k]); // 垂直切割
                    }

                    for (int k = 1; k <= i / 2; k++) {
                        priceArr[i][j] = Math.max(priceArr[i][j], priceArr[k][j] + priceArr[i - k][j]); // 水平切割
                    }

                }
            }
            return priceArr[m][n];
        }
    }

    /**
     * 2684. 矩阵中移动的最大次数:
     * 给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。
     * 你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：
     * 从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1)
     * 三个单元格中任一满足值 严格 大于当前单元格的单元格。
     * 返回你在矩阵中能够 移动 的 最大 次数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/16 22:19
     */
    class Solution_16_1 {
        int res;
        PriorityQueue<Integer> s = new PriorityQueue<Integer>();

        public int maxMoves(int[][] grid) {
            //row
            int m = grid.length;
            //col
            int n = grid[0].length;
            //取反标记已经走过的格子，这样就不用额外空间存储已经走过的格子了
            for (int[] row : grid) {
                row[0] *= -1; // 入队标记
            }
            for (int j = 0; j < n - 1; j++) {
                boolean ok = false;
                for (int i = 0; i < m; i++) {
                    if (grid[i][j] > 0) { // 不在队列中
                        continue;
                    }
                    for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, m); k++) {
                        if (grid[k][j + 1] > -grid[i][j]) {
                            grid[k][j + 1] *= -1; // 入队标记
                            ok = true;
                        }
                    }
                }
                if (!ok) { // 无法再往右走了
                    return j;
                }
            }
            return n - 1;
        }

    }

    /**
     * 310. 最小高度树:
     * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
     * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），
     * 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
     * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。
     * 在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
     * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
     * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
     * //TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/17 15:45
     */
    class Solution_17_1 {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            ArrayList<Integer>[] minHeightTrees = new ArrayList[n];
            ArrayList<Integer>[] nodeEdge = new ArrayList[n];
            Arrays.fill(nodeEdge, new ArrayList<Integer>());
            for (int[] edge : edges) {
                nodeEdge[edge[0]].add(edge[1]);
                nodeEdge[edge[1]].add(edge[0]);
            }
            int[] cache = new int[n];
            Arrays.fill(cache, -1);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int hi = 0;
                hi = dfs(nodeEdge, cache, new ArrayList<>(), hi, i);
                min = Math.min(hi, min);
                minHeightTrees[hi] = minHeightTrees[hi] == null ? new ArrayList<Integer>() : minHeightTrees[hi];
                minHeightTrees[hi].add(i);
            }
            return minHeightTrees[min];
        }

        public int dfs(ArrayList<Integer>[] nodeEdge, int[] cache, List<Integer> already, int hi, int i) {
            if (nodeEdge[i].isEmpty() || already.contains(i)) {
                return 0;
            }
            already.add(i);
            hi++;
            int max = 0;
            if (cache[i] != -1) {
                return cache[i];
            }
            for (Integer integer : nodeEdge[i]) {
                if (already.contains(integer)) {
                    continue;
                }
                max = Math.max(max, dfs(nodeEdge, cache, already, 0, integer));
            }
            max += hi;
            cache[i] = max;
            return max;
        }

        public List<Integer> findMinHeightTreesGF(int n, int[][] edges) {
            List<Integer> ans = new ArrayList<Integer>();
            if (n == 1) {
                ans.add(0);
                return ans;
            }
            int[] degree = new int[n];
            List<Integer>[] adj = new List[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<Integer>();
            }
            //统计每个节点的度
            for (int[] edge : edges) {
                adj[edge[0]].add(edge[1]);
                adj[edge[1]].add(edge[0]);
                degree[edge[0]]++;
                degree[edge[1]]++;
            }
            Queue<Integer> queue = new ArrayDeque<Integer>();
            //找出度为1的节点，度为1的节点说明是树的根节点或叶子节点，这样可以顺藤摸瓜理出一根树。
            for (int i = 0; i < n; i++) {
                if (degree[i] == 1) {
                    queue.offer(i);
                }
            }
            int remainNodes = n;
            //节点=2时说明只有一条边，最小高度。
            while (remainNodes > 2) {
                int sz = queue.size();
                remainNodes -= sz;
                for (int i = 0; i < sz; i++) {
                    int curr = queue.poll();
                    //这个节点相连的所有节点
                    for (int v : adj[curr]) {
                        degree[v]--;
                        //相连节点度为2时，则继续加入队列遍历，叶子节点都去除，剩余节点就是最小高度树的节点？
                        if (degree[v] == 1) {
                            queue.offer(v);
                        }
                    }
                }
            }
            while (!queue.isEmpty()) {
                ans.add(queue.poll());
            }
            return ans;
        }
    }


    /**
     * 303. 区域和检索 - 数组不可变:
     * 给定一个整数数组  nums，处理以下类型的多个查询:
     * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
     * 实现 NumArray 类：
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，
     * 包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/18 23:25
     */
    class NumArray {
        int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public int sumRange(int left, int right) {
            int res = 0;
            while (left <= right) {
                if (left == right) {
                    res += nums[left++];
                } else {
                    res += nums[left++];
                    res += nums[right--];
                }
            }
            return res;
        }

        /**
         * 官方：前缀和:
         *
         * @author hui
         * @version 1.0
         * @return
         * @date 2024/3/18 23:33
         */
        class NumArrayGF {
            int[] sums;

            public NumArrayGF(int[] nums) {
                int n = nums.length;
                sums = new int[n + 1];
                for (int i = 0; i < n; i++) {
                    sums[i + 1] = sums[i] + nums[i];
                }
            }

            public int sumRange(int i, int j) {
                return sums[j + 1] - sums[i];
            }
        }

    }

    /**
     * 1793. 好子数组的最大分数:
     * 给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
     * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。
     * 一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
     * 请你返回 好 子数组的最大可能 分数 。
     * k是最小值，i和j的范围越大越好
     * k不是最小值
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/19 21:45
     */
    class Solution_19_1 {
        public int maximumScore(int[] nums, int k) {
            int n = nums.length;
            int ans = nums[k], minH = nums[k];
            int i = k, j = k;
            for (int t = 0; t < n - 1; t++) { // 循环 n-1 次
                //那边大移动哪边
                if (j == n - 1 || i > 0 && nums[i - 1] > nums[j + 1]) {
                    minH = Math.min(minH, nums[--i]);
                } else {
                    minH = Math.min(minH, nums[++j]);
                }
                ans = Math.max(ans, minH * (j - i + 1));
            }
            return ans;
        }


    }

    /**
     * 1969. 数组元素的最小非零乘积:
     * 给你一个正整数 p 。你有一个下标从 1 开始的数组 nums ，这个数组包含范围 [1, 2的p次方 - 1] 内所有整数的二进制形式（两端都 包含）。你可以进行以下操作 任意 次：
     * 从 nums 中选择两个元素 x 和 y  。
     * 选择 x 中的一位与 y 对应位置的位交换。对应位置指的是两个整数 相同位置 的二进制位。
     * 比方说，如果 x = 1101 且 y = 0011 ，交换右边数起第 2 位后，我们得到 x = 1111 和 y = 0001 。
     * 请你算出进行以上操作 任意次 以后，nums 能得到的 最小非零 乘积。将乘积对 10的9次方 + 7 取余 后返回。
     * 注意：答案应为取余 之前 的最小值。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/20 23:35
     */
    class Solution_20_1 {
        /**
         * java7新特性,等效于1000000007
         */
        private static final int MOD = 1_000_000_007;

        private long pow(long x, int p) {
            x %= MOD;
            long res = 1;
            while (p-- > 0) {
                res = res * x % MOD;
                x = x * x % MOD;
            }
            return res;
        }

        /**
         * minNonZeroProductLS:
         * 贪心，快速幂
         * 两数之和不变，两数越接近，则乘积越大，需要将两数的距离尽可能拉远。
         * 公式
         * (2的p次方−1)⋅(2的p次方−2)的(2的p−1次方−1)次方
         *
         * @param p
         * @return int
         * @author 灵神
         * @version 1.0
         * @date 2024/3/20 23:42
         */
        public int minNonZeroProductLS(int p) {
            long k = (1L << p) - 1;
            return (int) (k % MOD * pow(k - 1, p - 1) % MOD);
        }
    }

    /**
     * 2671. 频率跟踪器:
     * 请你设计并实现一个能够对其中的值进行跟踪的数据结构，并支持对频率相关查询进行应答。
     * 实现 FrequencyTracker 类：
     * FrequencyTracker()：使用一个空数组初始化 FrequencyTracker 对象。
     * void add(int number)：添加一个 number 到数据结构中。
     * void deleteOne(int number)：从数据结构中删除一个 number 。数据结构 可能不包含 number ，在这种情况下不删除任何内容。
     * bool hasFrequency(int frequency): 如果数据结构中存在出现 frequency 次的数字，则返回 true，否则返回 false。
     * 应该是超时了
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/21 23:30
     */
    class FrequencyTracker {
        private int[] nums;
        private HashMap<Integer, Integer> map;
        int size;

        public FrequencyTracker() {
            this.nums = new int[16];
            size = 0;
            map = new HashMap<Integer, Integer>();
        }

        public void add(int number) {
            if (size >= nums.length) {
                int[] temp = new int[nums.length << 1];
                for (int i = 0; i < size; i++) {
                    temp[i] = nums[i];
                }
                nums = temp;
                nums[size] = number;
            } else {
                nums[size++] = number;
            }
            int i = map.get(number) == null ? 1 : map.get(number) + 1;
            map.put(number, i);
        }

        public void deleteOne(int number) {
            int left = 0;
            int right = size - 1;
            while (left <= right) {
                if (nums[left] == number) {
                    arrayDelete(nums, left, size);
                    size--;
                    map.put(number, map.get(number) - 1);
                    return;
                } else if (nums[right] == number) {
                    arrayDelete(nums, right, size);
                    size--;
                    map.put(number, map.get(number) - 1);
                    return;
                }
                left++;
                right--;
            }
        }

        public boolean hasFrequency(int frequency) {
            for (Integer value : map.values()) {
                if (frequency == value) {
                    return true;
                }
            }
            return false;
        }


        public void arrayDelete(int[] arr, int index, int size) {
            if (size == 1) {
                arr[0] = 0;
            } else {
                int len = size - index;
                while (len > 1) {
                    arr[index] = arr[++index];
                    len--;
                }
                arr[size - 1] = 0;
            }
        }
    }

    class FrequencyTrackerLS {
        private final Map<Integer, Integer> cnt = new HashMap<>(); // number 的出现次数
        private final Map<Integer, Integer> freq = new HashMap<>(); // number 的出现次数的出现次数

        public FrequencyTrackerLS() {
        }

        public void update(int number, int delta) {
            //c = number的频率(个数)
            int c = cnt.merge(number, delta, Integer::sum);
            //如果是原来的频率新增，则老的频率需要-1，新的频率+1，反之亦然。
            freq.merge(c - delta, -1, Integer::sum); // 去掉一个旧的 cnt[number]
            freq.merge(c, 1, Integer::sum); // 添加一个新的 cnt[number]
        }

        public void add(int number) {
            update(number, 1);
        }

        public void deleteOne(int number) {
            if (cnt.getOrDefault(number, 0) > 0) {
                update(number, -1);
            }
        }

        public boolean hasFrequency(int frequency) {
            return freq.getOrDefault(frequency, 0) > 0; // 至少有一个 number 的出现次数恰好为 frequency
        }
    }

    /**
     * 2617. 网格图中最少访问的格子数:
     * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0).
     * 当你在格子 (i, j) 的时候，你可以移动到以下格子之一：
     * 满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者
     * 满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。
     * 请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
     * //TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/22 23:08
     */
    class Solution_22_1 {
        public int minimumVisitedCells(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int kx = 0;
            int ky = 0;
            for (int i = 0; i < n; i++) {
                if (i > kx && (i < grid[kx][ky] + kx)) {

                }
            }
            return 1;
        }

        /**
         * minimumVisitedCellsLS1:
         * ；灵神单调栈
         *
         * @param grid
         * @return int
         * @author hui
         * @version 1.0
         * @date 2024/3/22 23:28
         */
        public int minimumVisitedCellsLS1(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int mn = 0;
            List<int[]>[] colStacks = new ArrayList[n]; // 每列的单调栈，为了能二分用 ArrayList
            Arrays.setAll(colStacks, i -> new ArrayList<int[]>());
            List<int[]> rowSt = new ArrayList<>(); // 行单调栈
            for (int i = m - 1; i >= 0; i--) {
                rowSt.clear();
                for (int j = n - 1; j >= 0; j--) {
                    int g = grid[i][j];
                    List<int[]> colSt = colStacks[j];
                    mn = i < m - 1 || j < n - 1 ? Integer.MAX_VALUE : 1;
                    if (g > 0) { // 可以向右/向下跳
                        // 在单调栈上二分查找最优转移来源
                        int k = search(rowSt, j + g);
                        if (k < rowSt.size()) {
                            mn = rowSt.get(k)[0] + 1;
                        }
                        k = search(colSt, i + g);
                        if (k < colSt.size()) {
                            mn = Math.min(mn, colSt.get(k)[0] + 1);
                        }
                    }
                    if (mn < Integer.MAX_VALUE) {
                        // 插入单调栈
                        while (!rowSt.isEmpty() && mn <= rowSt.get(rowSt.size() - 1)[0]) {
                            rowSt.remove(rowSt.size() - 1);
                        }
                        rowSt.add(new int[]{mn, j});
                        while (!colSt.isEmpty() && mn <= colSt.get(colSt.size() - 1)[0]) {
                            colSt.remove(colSt.size() - 1);
                        }
                        colSt.add(new int[]{mn, i});
                    }
                }
            }
            return mn < Integer.MAX_VALUE ? mn : -1; // 最后一个算出的 mn 就是 f[0][0]
        }

        // 开区间二分，见 https://www.bilibili.com/video/BV1AP41137w7/
        private int search(List<int[]> st, int target) {
            int left = -1, right = st.size(); // 开区间 (left, right)
            while (left + 1 < right) { // 区间不为空
                int mid = left + (right - left) / 2;
                if (st.get(mid)[1] <= target) {
                    right = mid; // 范围缩小到 (left, mid)
                } else {
                    left = mid; // 范围缩小到 (mid, right)
                }
            }
            return right;
        }
    }

    /**
     * 2549. 统计桌面上的不同数字:
     * 给你一个正整数 n ，开始时，它放在桌面上。在 10的9次方 天内，每天都要执行下述步骤：
     * 对于出现在桌面上的每个数字 x ，找出符合 1 <= i <= n 且满足 x % i == 1 的所有数字 i 。
     * 然后，将这些数字放在桌面上。
     * 返回在 109 天之后，出现在桌面上的 不同 整数的数目。
     * 一旦数字放在桌面上，则会一直保留直到结束。
     * % 表示取余运算。例如，14 % 3 等于 2 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/23 23:24
     */
    class Solution_23_1 {
        public int distinctIntegers(int n) {
            if (n == 1) {
                return 1;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            Set<Integer> res = new HashSet<>();
            res.add(n);
            queue.add(n);
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                for (int i = 1; i <= poll; i++) {
                    if (poll % i == 1) {
                        if (!res.contains(i)) {
                            res.add(i);
                            queue.add(i);

                        }
                    }
                }
            }
            return res.size();
        }

        public int distinctIntegers_0(int n) {
            return Math.max(n - 1, 1);
        }
    }

    /**
     * 322. 零钱兑换:
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * dp[i] = 凑成i个金币，需要的最少硬币数
     * dp[i] = min(dp[i-coins[0]), dp[i-coins[1],...,dp[i-coins[j]] + 1
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/24 17:00
     */
    class Solution_24_1 {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            //dp初始化
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i - coin >= 0) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }
            return dp[amount] == amount + 1 ? -1 : dp[amount];
        }

    }

    /**
     * 518. 零钱兑换 II:
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * 假设每一种面额的硬币有无限个。
     * 题目数据保证结果符合 32 位带符号整数。
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/25 21:54
     */
    class Solution_25_1 {
        public int change(int amount, int[] coins) {
            int[] f = new int[amount + 1];
            f[0] = 1;
            for (int x : coins) {
                for (int c = x; c <= amount; c++) {
                    f[c] += f[c - x];
                }
            }
            return f[amount];
        }
    }


    /**
     * 2642. 设计可以求最短路径的图类
     * Your Graph object will be instantiated and called as such:
     * Graph obj = new Graph(n, edges);
     * obj.addEdge(edge);
     * int param_2 = obj.shortestPath(node1,node2);
     * 给你一个有 n 个节点的 有向带权 图，节点编号为 0 到 n - 1 。图中的初始边用数组 edges 表示，
     * 其中 edges[i] = [fromi, toi, edgeCosti] 表示从 fromi 到 toi 有一条代价为 edgeCosti 的边。
     * <p>
     * 请你实现一个 Graph 类：
     * <p>
     * Graph(int n, int[][] edges) 初始化图有 n 个节点，并输入初始边。
     * addEdge(int[] edge) 向边集中添加一条边，其中 edge = [from, to, edgeCost] 。
     * 数据保证添加这条边之前对应的两个节点之间没有有向边。
     * int shortestPath(int node1, int node2) 返回从节点 node1 到 node2 的路径 最小 代价。如果路径不存在，返回 -1 。
     * 一条路径的代价是路径中所有边代价之和。
     * <p>
     * TODO
     */
    static class Graph {
        int n;
        int[][] edges;

        public Graph(int n, int[][] edges) {
            this.n = n;
            this.edges = edges;
        }

        public void addEdge(int[] edge) {

        }

        public int shortestPath(int node1, int node2) {
            return 1;
        }
    }

    /**
     * <h1>2580. 统计将重叠区间合并成组的方案数:</h1>
     * 给你一个二维整数数组 ranges ，其中 ranges[i] = [starti, endi] 表示 starti 到 endi 之间（包括二者）的所有整数都包含在第 i 个区间中。
     * 你需要将 ranges 分成 两个 组（可以为空），满足：
     * 每个区间只属于一个组。
     * 两个有 交集 的区间必须在 同一个 组内。
     * 如果两个区间有至少 一个 公共整数，那么这两个区间是 有交集 的。
     * 比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
     * 请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 109 + 7 取余 后返回。
     * <p>
     * 左端点排序就行了，我做的是狗屎啊，通过率50%
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/27 22:28
     */
    class Solution_27_1 {
        int mod = 100_000_000_7;

        public int countWays(int[][] ranges) {
            int length = ranges.length;
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            int[][] group = new int[length][2];
            for (int[] range : ranges) {
                boolean flag = true;
                int start = range[0];
                int end = range[range.length - 1];
                int remove = -1;
                for (int i = 0; i < list.size(); i++) {
                    List<Integer> integers = list.get(i);
                    int fstart = integers.get(0);
                    int fend = integers.get(1);
                    if ((fstart <= start && start <= fend) || (fstart <= end && end <= fend) || (start < fstart && end > fend)) {
                        flag = false;
                        start = Math.min(start, fstart);
                        end = Math.max(end, fend);
                        integers.set(0, start);
                        integers.set(1, end);
                        if (remove != -1) {
                            remove = -1;
                            list.remove(remove);
                        }
                        remove = i;
                    }
                }
                if (flag) {
                    list.add(Stream.of(start, end).collect(Collectors.toList()));
                }
            }
            //快速幂
            int pow = list.size();
            int ans = 1;
            int a = 2;
            while (pow > 0) {
                if ((pow & 1) == 1) {
                    ans *= a;
                }
                a *= a;
                pow = pow >> 1;
            }
            return ans;
        }

        public int countWaysLS(int[][] ranges) {
            Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
            int ans = 1;
            int maxR = -1;
            for (int[] p : ranges) {
                if (p[0] > maxR) { // 无法合并
                    ans = ans * 2 % 1_000_000_007; // 新区间
                }
                maxR = Math.max(maxR, p[1]); // 合并
            }
            return ans;
        }

    }

    /**
     * 1997. 访问完所有房间的第一天:
     * 你需要访问 n 个房间，房间从 0 到 n - 1 编号。同时，每一天都有一个日期编号，从 0 开始，依天数递增。你每天都会访问一个房间。
     * 最开始的第 0 天，你访问 0 号房间。给你一个长度为 n 且 下标从 0 开始 的数组 nextVisit 。
     * 在接下来的几天中，你访问房间的 次序 将根据下面的 规则 决定：
     * 假设某一天，你访问 i 号房间。
     * 如果算上本次访问，访问 i 号房间的次数为 奇数 ，那么 第二天 需要访问 nextVisit[i] 所指定的房间，其中 0 <= nextVisit[i] <= i 。
     * 如果算上本次访问，访问 i 号房间的次数为 偶数 ，那么 第二天 需要访问 (i + 1) mod n 号房间。
     * 请返回你访问完所有房间的第一天的日期编号。题目数据保证总是存在这样的一天。由于答案可能很大，返回对 109 + 7 取余后的结果。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/28 22:39
     */
    class Solution_28_1 {
        int mod = 1_000_000_007;
        int ans = 0;
        int cur = 0;

        /**
         * firstDayBeenInAllRooms:
         * 超时，通过率10%
         *
         * @param nextVisit
         * @return int
         * @author hui
         * @version 1.0
         * @date 2024/3/28 23:18
         */
        public int firstDayBeenInAllRooms(int[] nextVisit) {
            int length = nextVisit.length;
            int n = nextVisit.length - 1;
            int[][] arr = new int[length][1];
            arr[0][0] = 1;
            while (n > 0) {
                ans++;
                if (arr[cur][0] % 2 == 0) {
                    cur = (cur + 1) % length;
                } else {
                    cur = nextVisit[cur];
                }
                if (arr[cur][0] == 0) {
                    n--;
                    arr[cur][0] = 1;
                } else {
                    arr[cur][0] += 1;
                }
            }
            return ans % mod;
        }

        public int firstDayBeenInAllRoomsLS(int[] nextVisit) {
            final long MOD = 1_000_000_007;
            int n = nextVisit.length;
            long[] s = new long[n];
            for (int i = 0; i < n - 1; i++) {
                int j = nextVisit[i];
                // s[i+1] = s[i] + （s[j] 到 s[i]) 的天数再+2 = s[i] * 2 - s[j] + 2
                s[i + 1] = (s[i] * 2 - s[j] + 2 + MOD) % MOD; // + MOD 避免算出负数
            }
            return (int) s[n - 1];
        }

    }

    /**
     * 2908. 元素和最小的山形三元组 I:
     * 给你一个下标从 0 开始的整数数组 nums 。
     * 如果下标三元组 (i, j, k) 满足下述全部条件，则认为它是一个 山形三元组 ：
     * i < j < k
     * nums[i] < nums[j] 且 nums[k] < nums[j]
     * 请你找出 nums 中 元素和最小 的山形三元组，并返回其 元素和 。如果不存在满足条件的三元组，返回 -1 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/29 23:46
     */
    class Solution_29_1 {
        public int minimumSum(int[] nums) {
            int n = nums.length;
            int[] suf = new int[n]; // 后缀最小值
            suf[n - 1] = nums[n - 1];
            for (int i = n - 2; i > 1; i--) {
                suf[i] = Math.min(suf[i + 1], nums[i]);
            }

            int ans = Integer.MAX_VALUE;
            int pre = nums[0]; // 前缀最小值
            for (int j = 1; j < n - 1; j++) {
                if (pre < nums[j] && nums[j] > suf[j + 1]) { // 山形
                    ans = Math.min(ans, pre + nums[j] + suf[j + 1]); // 更新答案
                }
                pre = Math.min(pre, nums[j]);
            }
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }
    }

    /**
     * 2952. 需要添加的硬币的最小数量:
     * 给你一个下标从 0 开始的整数数组 coins，表示可用的硬币的面值，以及一个整数 target 。
     * 如果存在某个 coins 的子序列总和为 x，那么整数 x 就是一个 可取得的金额 。
     * 返回需要添加到数组中的 任意面值 硬币的 最小数量 ，使范围 [1, target] 内的每个整数都属于 可取得的金额 。
     * 数组的 子序列 是通过删除原始数组的一些（可能不删除）元素而形成的新的 非空 数组，删除过程不会改变剩余元素的相对位置。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/30 23:28
     */
    class Solution_30_1 {
        public int minimumAddedCoins(int[] coins, int target) {
            Arrays.sort(coins);
            int ans = 0, s = 1, i = 0;
            while (s <= target) {
                if (i < coins.length && coins[i] <= s) {
                    s += coins[i++];
                } else {
                    //走到这里，说明凑不到s金额，如果想添加最少得硬币数量，直接添加s即可
                    //添加完s后，由于可以认为0 - (s-1)的金额是可以取得的金额，那么加上s后可得 s - 2s - 1的金额也可以取得
                    //就可以将s变为2s判断能不能取2s的金额就行了
                    s *= 2;
                    ans++;
                }
            }
            return ans;
        }
    }

    /**
     * 331. 验证二叉树的前序序列化:
     * 序列化二叉树的一种方法是使用 前序遍历 。当我们遇到一个非空节点时，
     * 我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
     * <p>
     * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
     * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
     * 保证 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
     * 你可以认为输入格式总是有效的
     * 例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
     * 注意：不允许重建树。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/31 12:33
     */
    class Solution_31_1 {
        public boolean isValidSerialization(String preorder) {
            int n = preorder.length();
            int i = 0;
            int slots = 1;
            while (i < n) {
                if (slots == 0) {
                    return false;
                }
                if (preorder.charAt(i) == ',') {
                    i++;
                } else if (preorder.charAt(i) == '#') {
                    slots--;
                    i++;
                } else {
                    // 读一个数字
                    while (i < n && preorder.charAt(i) != ',') {
                        i++;
                    }
                    slots++; // slots = slots - 1 + 2
                }
            }
            return slots == 0;

        }
    }

}

