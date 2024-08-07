package cn.hwyee.algorithms.leecode.daily;

import cn.hwyee.datastructures.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.time.temporal.Temporal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202404
 * @description leetcode 每日
 * @date 2024/4/1
 * @since JDK 1.8
 */
@Slf4j
public class Daily202404 {
    public static void main(String[] args) {
        Solution_1_1 solution11 = new Solution_1_1();
        solution11.finalString("poiinter");
        System.out.println("中文");
        Daily202404.Solution_8_1 solution81 = new Daily202404.Solution_8_1();
        int i81 = solution81.minOperations(new int[]{4, 2, 5, 3});
        System.out.println(i81);
        System.out.println(Integer.toBinaryString(solution81.hashCode()));
        System.out.println(Integer.toBinaryString(solution81.hashCode()));
        int h = 0;
        System.out.println(Integer.toBinaryString((h = solution81.hashCode()) ^ (h >>> 16)));
        Solution_18_1 solution181 = new Solution_18_1();
        int[] ints = {1, 3, 4, 2, 6, 8};
        solution181.findOriginalArray(ints);


    }

    /**
     * 2810. 故障键盘:
     * 你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。
     * 给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。
     * 返回最终笔记本屏幕上输出的字符串。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/1 23:39
     */
    static class Solution_1_1 {
        public String finalString(String s) {
            char[] charArray = s.toCharArray();
            int j = -1;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == 'i') {
                    s = reverse(s, i + j);
                    j--;
                }
            }
            return s;
        }

        public String reverse(String s, int end) {
            char[] charArray = s.toCharArray();
            String substring = s.substring(end + 2);
            for (int i = end; i > end / 2; i--) {
                char temp = charArray[i];
                charArray[i] = charArray[end - i];
                charArray[end - i] = temp;
            }
            String s1 = new String(charArray, 0, end + 1);
            return s1 + substring;
        }
    }

    public String finalStringGF(String s) {
        Deque<Character> q = new ArrayDeque<Character>();
        boolean head = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != 'i') {
                if (head) {
                    q.offerFirst(ch);
                } else {
                    q.offerLast(ch);
                }
            } else {
                head = !head;
            }
        }
        StringBuilder ans = new StringBuilder();
        if (head) {
            while (!q.isEmpty()) {
                ans.append(q.pollLast());
            }
        } else {
            while (!q.isEmpty()) {
                ans.append(q.pollFirst());
            }
        }
        return ans.toString();

    }

    /**
     * 894. 所有可能的真二叉树:
     * 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。
     * 答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。
     * 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/3 23:45
     */
    class Solution2_1 {

        private final List<TreeNode>[] f = new ArrayList[11];

        {
            Arrays.setAll(f, i -> new ArrayList<>());
            f[1].add(new TreeNode());
            for (int i = 2; i < f.length; i++) { // 计算 f[i]
                for (int j = 1; j < i; j++) { // 枚举左子树叶子数
                    for (TreeNode left : f[j]) { // 枚举左子树
                        for (TreeNode right : f[i - j]) { // 枚举右子树
                            f[i].add(new TreeNode(0, left, right));
                        }
                    }
                }
            }
        }

        public List<TreeNode> allPossibleFBT(int n) {
            return f[n % 2 > 0 ? (n + 1) / 2 : 0];
        }

    }

    /**
     * 1379. 找出克隆二叉树中的相同节点:
     * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
     * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
     * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
     * <p>
     * 注意：你 不能 对两棵二叉树，以及 target 节点进行更改。只能 返回对克隆树 cloned 中已有的节点的引用。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/3 23:41
     */
    class Solution_3_1 {
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            if (original == null || original == target) {
                //因为题目要返回cloned节点
                return cloned;
            }
            //左子树
            TreeNode targetCopy = getTargetCopy(original.left, cloned.left, target);
            if (targetCopy != null) {
                //左子树找到了值直接返回
                return targetCopy;
            }
            return getTargetCopy(original.right, cloned.right, target);
        }
    }

    /**
     * 2192. 有向无环图中一个节点的所有祖先:
     * 给你一个正整数 n ，它表示一个 有向无环图 中节点的数目，节点编号为 0 到 n - 1 （包括两者）。
     * 给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi] 表示图中一条从 fromi 到 toi 的单向边。
     * 请你返回一个数组 answer，其中 answer[i]是第 i 个节点的所有 祖先 ，这些祖先节点 升序 排序。
     * 如果 u 通过一系列边，能够到达 v ，那么我们称节点 u 是节点 v 的 祖先 节点。
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/4 23:55
     */
    class Solution_4_4 {
        public List<List<Integer>> getAncestorsLS(int n, int[][] edges) {
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : edges) {
                g[e[1]].add(e[0]); // 反向建图
            }

            List<Integer>[] ans = new ArrayList[n];
            Arrays.setAll(ans, i -> new ArrayList<>());
            boolean[] vis = new boolean[n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(vis, false);
                dfs(i, g, vis); // 从 i 开始 DFS
                vis[i] = false; // ans[i] 不含 i
                for (int j = 0; j < n; j++) {
                    if (vis[j]) {
                        ans[i].add(j);
                    }
                }
            }
            return Arrays.asList(ans);
        }

        private void dfs(int x, List<Integer>[] g, boolean[] vis) {
            vis[x] = true; // 避免重复访问
            for (int y : g[x]) {
                if (!vis[y]) {
                    dfs(y, g, vis); // 只递归没有访问过的点
                }
            }
        }

    }

    /**
     * 1026. 节点与其祖先之间的最大差值:
     * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
     * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/6 20:42
     */
    class Solution_5_1 {
        private int ans;

        public int maxAncestorDiffLS(TreeNode root) {
            dfs(root);
            return ans;
        }

        private int[] dfs(TreeNode node) {
            if (node == null) { // 需要保证空节点不影响 mn 和 mx
                return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
            }
            int[] p = dfs(node.left);
            int[] q = dfs(node.right);
            int mn = Math.min(node.val, Math.min(p[0], q[0]));
            int mx = Math.max(node.val, Math.max(p[1], q[1]));
            ans = Math.max(ans, Math.max(node.val - mn, mx - node.val));
            return new int[]{mn, mx};
        }
    }

    /**
     * 1483. 树节点的第 K 个祖先:
     * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
     * <p>
     * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
     * <p>
     * 实现 TreeAncestor 类：
     * <p>
     * TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
     * getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/6 20:44
     */
    class TreeAncestor {

        private int[][] pa;

        public TreeAncestor(int n, int[] parent) {
            int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
            pa = new int[n][m];
            for (int i = 0; i < n; i++) {
                pa[i][0] = parent[i];
            }
            for (int i = 0; i < m - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int p = pa[x][i];
                    pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
            for (int i = 0; i < m; i++) {
                if (((k >> i) & 1) > 0) { // k 的二进制从低到高第 i 位是 1
                    node = pa[node][i];
                    if (node < 0) {
                        break;
                    }
                }
            }
            return node;
        }

        // 另一种写法，不断去掉 k 的最低位的 1
        public int getKthAncestor2(int node, int k) {
            for (; k > 0 && node != -1; k &= k - 1) {
                node = pa[node][Integer.numberOfTrailingZeros(k)];
            }
            return node;
        }
    }

    /**
     * 1600. 王位继承顺序:
     * 一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。
     * <p>
     * 这个王国有一个明确规定的王位继承顺序，第一继承人总是国王自己。我们定义递归函数 Successor(x, curOrder) ，给定一个人 x 和当前的继承顺序，该函数返回 x 的下一继承人。
     * <p>
     * Successor(x, curOrder):
     * 如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：
     * 如果 x 是国王，那么返回 null
     * 否则，返回 Successor(x 的父亲, curOrder)
     * 否则，返回 x 不在 curOrder 中最年长的孩子
     * 比方说，假设王国由国王，他的孩子 Alice 和 Bob （Alice 比 Bob 年长）和 Alice 的孩子 Jack 组成。
     * <p>
     * 一开始， curOrder 为 ["king"].
     * 调用 Successor(king, curOrder) ，返回 Alice ，所以我们将 Alice 放入 curOrder 中，得到 ["king", "Alice"] 。
     * 调用 Successor(Alice, curOrder) ，返回 Jack ，所以我们将 Jack 放入 curOrder 中，得到 ["king", "Alice", "Jack"] 。
     * 调用 Successor(Jack, curOrder) ，返回 Bob ，所以我们将 Bob 放入 curOrder 中，得到 ["king", "Alice", "Jack", "Bob"] 。
     * 调用 Successor(Bob, curOrder) ，返回 null 。最终得到继承顺序为 ["king", "Alice", "Jack", "Bob"] 。
     * 通过以上的函数，我们总是能得到一个唯一的继承顺序。
     * <p>
     * 请你实现 ThroneInheritance 类：
     * <p>
     * ThroneInheritance(string kingName) 初始化一个 ThroneInheritance 类的对象。国王的名字作为构造函数的参数传入。
     * void birth(string parentName, string childName) 表示 parentName 新拥有了一个名为 childName 的孩子。
     * void death(string name) 表示名为 name 的人死亡。一个人的死亡不会影响 Successor 函数，也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。
     * string[] getInheritanceOrder() 返回 除去 死亡人员的当前继承顺序列表。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/7 21:05
     */
    class ThroneInheritance {
        LinkedList<String> inheritance = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        Map<String, String> relation = new HashMap<>();

        public ThroneInheritance(String kingName) {
            inheritance.add(kingName);
        }

        public void birth(String parentName, String childName) {
            int i = 0;
            while (true) {
                String s = inheritance.get(i++);
                if (s.equals(parentName)) {
                    int sons = map.get(parentName) == null ? 0 : map.get(parentName);
                    i += sons;
                    inheritance.add(i, childName);
                    map.put(parentName, sons + 1);
                    relation.put(childName, parentName);
                    break;
                }
            }
        }

        public void death(String name) {
            String parent = relation.get(name);
            if (parent != null) {
                int sons = map.get(name) == null ? 1 : map.get(name);
                map.put(parent, sons - 1);
            }
            inheritance.remove(name);
        }

        public List<String> getInheritanceOrder() {
            return inheritance;
        }
    }

    class ThroneInheritanceGF {
        Map<String, List<String>> edges;
        Set<String> dead;
        String king;

        public ThroneInheritanceGF(String kingName) {
            edges = new HashMap<String, List<String>>();
            dead = new HashSet<String>();
            king = kingName;
        }

        public void birth(String parentName, String childName) {
            List<String> children = edges.getOrDefault(parentName, new ArrayList<String>());
            children.add(childName);
            edges.put(parentName, children);
        }

        public void death(String name) {
            dead.add(name);
        }

        public List<String> getInheritanceOrder() {
            List<String> ans = new ArrayList<String>();
            preorder(ans, king);
            return ans;
        }

        private void preorder(List<String> ans, String name) {
            if (!dead.contains(name)) {
                ans.add(name);
            }
            List<String> children = edges.getOrDefault(name, new ArrayList<String>());
            for (String childName : children) {
                preorder(ans, childName);
            }
        }
    }

    /**
     * 2009. 使数组连续的最少操作数:
     * 给你一个整数数组 nums 。每一次操作中，你可以将 nums 中 任意 一个元素替换成 任意 整数。
     * <p>
     * 如果 nums 满足以下条件，那么它是 连续的 ：
     * <p>
     * nums 中所有元素都是 互不相同 的。
     * nums 中 最大 元素与 最小 元素的差等于 nums.length - 1 。
     * 比方说，nums = [4, 2, 5, 3] 是 连续的 ，但是 nums = [1, 2, 3, 5, 6] 不是连续的 。
     * <p>
     * 请你返回使 nums 连续 的 最少 操作次数。
     * <p>
     * 这个相当于是灵神的反向操作，但是尽然超出了时间限制，显然题目里面符合规则和是很多的，但是不符合规则的是少数
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/8 23:35
     */
    static class Solution_8_1 {
        public int minOperationsLS(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            int m = 1;
            for (int i = 1; i < n; i++) {
                if (nums[i] != nums[i - 1]) {
                    nums[m++] = nums[i]; // 原地去重
                }
            }

            int ans = 0;
            int left = 0;
            for (int i = 0; i < m; i++) {
                while (nums[left] < nums[i] - n + 1) { // nums[left] 不在窗口内
                    left++;
                }
                ans = Math.max(ans, i - left + 1);
            }
            return n - ans;
        }

        public int minOperations(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            int m = 1;
            for (int i = 1; i < n; i++) {
                if (nums[i] != nums[i - 1]) {
                    nums[m++] = nums[i]; // 原地去重
                }
            }

            int ans = 1;
            for (int i = 0; i < m - 1; i++) {
                int right = 1;
                int left = i + 1;
                while (left < m && nums[left] <= nums[i] + n - 1) { // nums[left]
                    right++;
                    left++;
                }
                ans = Math.max(ans, right);
            }
            return n - ans;
        }

    }

    /**
     * 2529. 正整数和负整数的最大计数:
     * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
     * <p>
     * 换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
     * 注意：0 既不是正整数也不是负整数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/9 23:15
     */
    class Solution_9_1 {

        public int maximumCount(int[] nums) {
            int l = 0;
            int r = nums.length - 1;
            int pos = 0;
            int neg = 0;
            while (l <= r) {
                if (nums[l] > 0) {
                    pos++;
                } else if (nums[l] < 0) {
                    neg++;
                }
                if (l != r) {
                    if (nums[r] > 0) {
                        pos++;
                    } else if (nums[r] < 0) {
                        neg++;
                    }
                }
                l++;
                r--;
            }
            return Math.max(pos, neg);
        }
    }


    /**
     * 1702. 修改后的最大二进制字符串:
     * 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：
     * <p>
     * 操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
     * 比方说， "00010" -> "10010"
     * 操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
     * 比方说， "00010" -> "00001"
     * 请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。
     * 如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/10 21:20
     */
    class Solution_10_1 {
        public String maximumBinaryString(String binary) {
            char[] charArray = binary.toCharArray();
            StringBuilder tail = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            StringBuilder mid = new StringBuilder();
            boolean flag = true;
            for (char c : charArray) {
                if (flag) {
                    if (c == '1') {
                        sb.append(1);
                    } else {
                        mid.append(1);
                        flag = false;
                    }
                } else {
                    if (c == '1') {
                        tail.append(1);
                    } else {
                        mid.append(1);
                    }
                }
            }
            if (mid.length() >= 1) {
                mid.deleteCharAt(mid.length() - 1);
                mid.append(0);
            }
            return sb.append(mid).append(tail).toString();
        }

        /**
         * maximumBinaryStringLS:
         * 灵神，其实思路是一样的，有三个优化点：
         * 1.把我找前面有多少个连续的1,用binary.indexOf('0')优化了。
         * 2.用cnt1 += s[i] - '0';进行统计剩余1的个数，这样就不用进行if判断了
         * 3.String.repeat()方法拷贝1,很快,用的是Stream.arraycopy
         *
         * @param binary
         * @return java.lang.String
         * @author hui
         * @version 1.0
         * @date 2024/4/10 21:47
         */
        public String maximumBinaryStringLS(String binary) {
            int i = binary.indexOf('0');
            if (i < 0) { // binary 全是 '1'
                return binary;
            }
            char[] s = binary.toCharArray();
            int cnt1 = 0;
            for (i++; i < s.length; i++) {
                cnt1 += s[i] - '0'; // 统计 [i, n-1] 中 '1' 的个数
            }
            return "1".repeat(s.length - 1 - cnt1) + '0' + "1".repeat(cnt1);
        }
    }


    /**
     * 1766. 互质树:
     * 给你一个 n 个节点的树（也就是一个无环连通无向图），节点编号从 0 到 n - 1 ，且恰好有 n - 1 条边，每个节点有一个值。树的 根节点 为 0 号点。
     * <p>
     * 给你一个整数数组 nums 和一个二维数组 edges 来表示这棵树。nums[i] 表示第 i 个点的值，edges[j] = [uj, vj] 表示节点 uj 和节点 vj 在树中有一条边。
     * <p>
     * 当 gcd(x, y) == 1 ，我们称两个数 x 和 y 是 互质的 ，其中 gcd(x, y) 是 x 和 y 的 最大公约数 。
     * <p>
     * 从节点 i 到 根 最短路径上的点都是节点 i 的祖先节点。一个节点 不是 它自己的祖先节点。
     * <p>
     * 请你返回一个大小为 n 的数组 ans ，其中 ans[i]是离节点 i 最近的祖先节点且满足 nums[i] 和 nums[ans[i]] 是 互质的 ，如果不存在这样的祖先节点，ans[i] 为 -1 。
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/11 23:30
     */
    class Solution_11_1 {
        public int[] getCoprimes(int[] nums, int[][] edges) {
            return null;
        }

        private final int MX = 51;
        private final int[][] coprime = new int[MX][MX];

        {
            // 预处理
            // coprime[i] 保存 [1, MX) 中与 i 互质的所有元素
            for (int i = 1; i < MX; i++) {
                int k = 0;
                for (int j = 1; j < MX; j++) {
                    if (gcd(i, j) == 1) {
                        coprime[i][k++] = j;
                    }
                }
            }
        }

        public int[] getCoprimesLS(int[] nums, int[][] edges) {
            int n = nums.length;
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : edges) {
                int x = e[0];
                int y = e[1];
                g[x].add(y);
                g[y].add(x);
            }

            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            int[] valDepth = new int[MX];
            int[] valNodeId = new int[MX];
            dfs(0, -1, 1, g, nums, ans, valDepth, valNodeId);
            return ans;
        }

        private void dfs(int x, int fa, int depth, List<Integer>[] g, int[] nums, int[] ans, int[] valDepth, int[] valNodeId) {
            // x 的节点值
            int val = nums[x];

            // 计算与 val 互质的祖先节点值中，节点深度最大的节点编号
            int maxDepth = 0;
            for (int j : coprime[val]) {
                if (j == 0) {
                    break;
                }
                if (valDepth[j] > maxDepth) {
                    maxDepth = valDepth[j];
                    ans[x] = valNodeId[j];
                }
            }

            // tmpDepth 和 tmpNodeId 用于恢复现场
            int tmpDepth = valDepth[val];
            int tmpNodeId = valNodeId[val];

            // 保存 val 对应的节点深度和节点编号
            valDepth[val] = depth;
            valNodeId[val] = x;

            // 向下递归
            for (int y : g[x]) {
                if (y != fa) {
                    dfs(y, x, depth + 1, g, nums, ans, valDepth, valNodeId);
                }
            }

            // 恢复现场
            valDepth[val] = tmpDepth;
            valNodeId[val] = tmpNodeId;
        }

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }


    /**
     * 2923. 找到冠军 I:
     * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。
     * <p>
     * 给你一个下标从 0 开始、大小为 n * n 的二维布尔矩阵 grid 。对于满足 0 <= i, j <= n - 1 且 i != j 的所有 i, j ：
     * 如果 grid[i][j] == 1，那么 i 队比 j 队 强 ；否则，j 队比 i 队 强 。
     * <p>
     * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
     * <p>
     * 返回这场比赛中将会成为冠军的队伍。
     * only one champion.
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/13 23:30
     */
    class Solution_12_1 {
        public int findChampion(int[][] grid) {
            int length = grid.length;
            for (int i = 0; i < length; i++) {
                boolean champion = true;
                for (int j = 0; j < length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (grid[i][j] != 1) {
                        champion = false;
                        break;
                    }
                }
                if (champion) {
                    return i;
                }
            }
            return -1;
        }

        public int findChampionLS(int[][] grid) {
            next:
            for (int i = 0; ; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (j != i && grid[i][j] == 0) {
                        continue next;//继续next循环(最外层)循环
                    }
                }
                return i;
            }
        }
    }


    /**
     * 2924. 找到冠军 II:
     * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。每支队伍也是 有向无环图（DAG） 上的一个节点。
     * <p>
     * 给你一个整数 n 和一个下标从 0 开始、长度为 m 的二维整数数组 edges 表示这个有向无环图，
     * 其中 edges[i] = [ui, vi] 表示图中存在一条从 ui 队到 vi 队的有向边。
     * <p>
     * 从 a 队到 b 队的有向边意味着 a 队比 b 队 强 ，也就是 b 队比 a 队 弱 。
     * <p>
     * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
     * <p>
     * 如果这场比赛存在 唯一 一个冠军，则返回将会成为冠军的队伍。否则，返回 -1 。
     * <p>
     * 注意
     * <p>
     * 环 是形如 a1, a2, ..., an, an+1 的一个序列，且满足：节点 a1 与节点 an+1 是同一个节点；节点 a1, a2, ..., an 互不相同；
     * 对于范围 [1, n] 中的每个 i ，均存在一条从节点 ai 到节点 ai+1 的有向边。
     * 有向无环图 是不存在任何环的有向图。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/14 0:16
     */
    class Solution_13_1 {
        public int findChampion(int n, int[][] edges) {
            int[] ans = new int[n];
            for (int[] edge : edges) {
                ans[edge[1]] = 1;
            }
            int c = 0;
            int champion = -1;
            for (int i = 0; i < ans.length; i++) {
                if (ans[i] != 1) {
                    c++;
                    champion = i;
                }
            }
            return c == 1 ? champion : -1;
        }
    }

    /**
     * 705. 设计哈希集合:
     * 简单
     * 相关标签
     * 相关企业
     * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
     * <p>
     * 实现 MyHashSet 类：
     * <p>
     * void add(key) 向哈希集合中插入值 key 。
     * bool contains(key) 返回哈希集合中是否存在这个值 key 。
     * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
     * <p>
     * 因为这个只有key，没有value，而且key是int类型，int类型的hashcode就是本身。
     * 官方题解用了个盐值取模，和List[]实现的。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/14 18:06
     */
    class MyHashSet {
        int[] table;
        int size = 1000001;

        public MyHashSet() {
            table = new int[size];
        }

        public void add(int key) {
            table[key] = 1;
        }

        public void remove(int key) {
            table[key] = 0;
        }

        public boolean contains(int key) {
            return table[key] == 1;
        }

        public int hash(int key) {
            return key;
        }

        public int hashCode(int key) {
            return key;
        }
    }

    /**
     * 706. 设计哈希映射:
     * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
     * <p>
     * 实现 MyHashMap 类：
     * <p>
     * MyHashMap() 用空映射初始化对象
     * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
     * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
     * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/15 23:25
     */
    class MyHashMap {
        int[] table;
        int size = 1000001;

        public MyHashMap() {
            table = new int[size];
            Arrays.fill(table, -1);
        }

        public void put(int key, int value) {
            table[key] = value;
        }

        public int get(int key) {
            return table[key];
        }

        public void remove(int key) {
            table[key] = -1;
        }
    }

    class MyHashMapGF {
        private class Pair {
            private int key;
            private int value;

            public Pair(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public int getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        private static final int BASE = 769;//质数，避免过多的冲突。
        private LinkedList[] data;

        /**
         * Initialize your data structure here.
         */
        public MyHashMapGF() {
            data = new LinkedList[BASE];
            for (int i = 0; i < BASE; ++i) {
                data[i] = new LinkedList<Pair>();
            }
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            int h = hash(key);
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Pair pair = iterator.next();
                if (pair.getKey() == key) {
                    pair.setValue(value);
                    return;
                }
            }
            data[h].offerLast(new Pair(key, value));
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            int h = hash(key);
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Pair pair = iterator.next();
                if (pair.getKey() == key) {
                    return pair.value;
                }
            }
            return -1;
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            int h = hash(key);
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Pair pair = iterator.next();
                if (pair.key == key) {
                    data[h].remove(pair);
                    return;
                }
            }
        }

        private int hash(int key) {
            return key % BASE;
        }
    }

    /**
     * 924. 尽量减少恶意软件的传播:
     * 给出了一个由 n 个节点组成的网络，用 n × n 个邻接矩阵图 graph 表示。
     * 在节点网络中，当 graph[i][j] = 1 时，表示节点 i 能够直接连接到另一个节点 j。
     * 一些节点 initial 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。
     * 这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
     * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
     * 如果从 initial 中移除某一节点能够最小化 M(initial)， 返回该节点。如果有多个节点满足条件，就返回索引最小的节点。
     * 请注意，如果某个节点已从受感染节点的列表 initial 中删除，它以后仍有可能因恶意软件传播而受到感染。
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/16 22:29
     */
    class Solution {
        public int minMalwareSpread(int[][] graph, int[] initial) {
            return 0;
        }

        public int minMalwareSpreadLS(int[][] graph, int[] initial) {
            int n = graph.length;
            boolean[] vis = new boolean[n];
            boolean[] isInitial = new boolean[n];
            int mn = Integer.MAX_VALUE;
            for (int x : initial) {
                isInitial[x] = true;
                mn = Math.min(mn, x);
            }

            int ans = -1;
            int maxSize = 0;
            for (int x : initial) {
                if (vis[x]) {
                    continue;
                }
                nodeId = -1;
                size = 0;
                dfs(x, graph, vis, isInitial);
                if (nodeId >= 0 && (size > maxSize || size == maxSize && nodeId < ans)) {
                    ans = nodeId;
                    maxSize = size;
                }
            }
            return ans < 0 ? mn : ans;
        }

        private int nodeId, size;

        private void dfs(int x, int[][] graph, boolean[] vis, boolean[] isInitial) {
            vis[x] = true;
            size++;
            // 按照状态机更新 nodeId
            if (nodeId != -2 && isInitial[x]) {
                nodeId = nodeId == -1 ? x : -2;
            }
            for (int y = 0; y < graph[x].length; y++) {
                if (graph[x][y] == 1 && !vis[y]) {
                    dfs(y, graph, vis, isInitial);
                }
            }
        }

    }

    /**
     * 928. 尽量减少恶意软件的传播 II:
     * 给定一个由 n 个节点组成的网络，用 n x n 个邻接矩阵 graph 表示。在节点网络中，只有当 graph[i][j] = 1 时，节点 i 能够直接连接到另一个节点 j。
     * <p>
     * 一些节点 initial 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
     * <p>
     * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
     * <p>
     * 我们可以从 initial 中删除一个节点，并完全移除该节点以及从该节点到任何其他节点的任何连接。
     * <p>
     * 请返回移除后能够使 M(initial) 最小化的节点。如果有多个节点满足条件，返回索引 最小的节点 。
     * <p>
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/17 19:13
     */
    class Solution_17_1 {
        private int nodeId, size;

        public int minMalwareSpreadLS(int[][] graph, int[] initial) {
            int n = graph.length;
            boolean[] vis = new boolean[n];
            boolean[] isInitial = new boolean[n];
            int mn = Integer.MAX_VALUE;
            for (int x : initial) {
                isInitial[x] = true;
                mn = Math.min(mn, x);
            }

            int[] cnt = new int[n];
            for (int i = 0; i < n; i++) {
                if (vis[i] || isInitial[i]) {
                    continue;
                }
                nodeId = -1;
                size = 0;
                dfs(i, graph, vis, isInitial);
                if (nodeId >= 0) { // 只找到一个在 initial 中的节点
                    // 删除节点 nodeId 可以让 size 个点不被感染
                    cnt[nodeId] += size;
                }
            }

            int maxCnt = 0;
            int minNodeId = -1;
            for (int i = 0; i < n; i++) {
                if (cnt[i] > maxCnt) {
                    maxCnt = cnt[i];
                    minNodeId = i;
                }
            }
            return minNodeId < 0 ? mn : minNodeId;
        }

        private void dfs(int x, int[][] graph, boolean[] vis, boolean[] isInitial) {
            vis[x] = true;
            size++;
            for (int y = 0; y < graph.length; y++) {
                if (graph[x][y] == 0) {
                    continue;
                }
                if (isInitial[y]) {
                    // 按照 924 题的状态机更新 nodeId
                    // 注意避免重复统计，例如上图中的 0 有两条不同路径可以遇到 1
                    if (nodeId != -2 && nodeId != y) {
                        nodeId = nodeId == -1 ? y : -2;
                    }
                } else if (!vis[y]) {
                    dfs(y, graph, vis, isInitial);
                }
            }
        }
    }

    /**
     * 2007. 从双倍数组中还原原数组:
     * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。
     * <p>
     * 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。
     * 超时
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/18 23:14
     */
    static class Solution_18_1 {

        /**
         * findOriginalArray:
         * 超时
         *
         * @param changed
         * @return int[]
         * @author hui
         * @version 1.0
         * @date 2024/4/18 23:49
         */
        public int[] findOriginalArray(int[] changed) {
            int length = changed.length;
            if (length % 2 == 1) {
                return new int[0];
            }
            Arrays.sort(changed);
            int[] flagArr = new int[length];
            List<Integer> ans = new ArrayList<Integer>();
            for (int i = 0; i < length; i++) {
                int temp = changed[i];
                if (flagArr[i] == 0) {
                    flagArr[i] = 1;
                    ans.add(temp);
                    for (int j = i + 1; j < length; j++) {
                        int d = changed[j];
                        if (d == temp * 2 && flagArr[j] == 0) {
                            flagArr[j] = 1;
                            break;
                        }
                    }
                }
            }
            if (ans.size() == length / 2) {
                int[] res = new int[length / 2];
                for (int i = 0; i < ans.size(); i++) {
                    res[i] = ans.get(i);
                }
                return res;
            } else {
                return new int[0];
            }
        }

        public int[] findOriginalArrayLS(int[] changed) {
            Arrays.sort(changed);
            int[] ans = new int[changed.length / 2];
            int ansIdx = 0;
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int x : changed) {
                if (!cnt.containsKey(x)) { // x 不是双倍后的元素
                    if (ansIdx == ans.length) {
                        return new int[0];
                    }
                    ans[ansIdx++] = x;
                    cnt.merge(x * 2, 1, Integer::sum); // 标记一个双倍元素
                } else { // x 是双倍后的元素
                    int c = cnt.merge(x, -1, Integer::sum); // 清除一个标记 -1 + 1 = 0
                    if (c == 0) {
                        cnt.remove(x);// 0 就是插入成功的值，说明执行成功了，直接删除。
                    }
                }
            }
            return ans;
        }

    }


    /**
     * 1883. 准时抵达会议现场的最小跳过休息次数:
     * 给你一个整数 hoursBefore ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 n 条道路。
     * 道路的长度用一个长度为 n 的整数数组 dist 表示，其中 dist[i] 表示第 i 条道路的长度（单位：千米）。
     * 另给你一个整数 speed ，表示你在道路上前进的速度（单位：千米每小时）。
     * 当你通过第 i 条路之后，就必须休息并等待，直到 下一个整数小时 才能开始继续通过下一条道路。
     * 注意：你不需要在通过最后一条道路后休息，因为那时你已经抵达会议现场。
     * 例如，如果你通过一条道路用去 1.4 小时，那你必须停下来等待，到 2 小时才可以继续通过下一条道路。
     * 如果通过一条道路恰好用去 2 小时，就无需等待，可以直接继续。
     * 然而，为了能准时到达，你可以选择 跳过 一些路的休息时间，这意味着你不必等待下一个整数小时。
     * 注意，这意味着与不跳过任何休息时间相比，你可能在不同时刻到达接下来的道路。
     * 例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。
     * 跳过第 1 条道路的休息时间意味着你将会在恰好 2 小时完成通过第 2 条道路，且你能够立即开始通过第 3 条道路。
     * 返回准时抵达会议现场所需要的 最小跳过次数 ，如果 无法准时参会 ，返回 -1 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/19 22:11
     */
    class Solution_19_1 {
        public int minSkips(int[] dist, int speed, int hoursBefore) {
            PriorityQueue<Double> queue = new PriorityQueue<Double>();
            double sum = 0;
            for (int i = 0; i < dist.length; i++) {
                int temp = dist[i];
                double time = temp + 0.0 / speed;
                sum += time;
                if (i != dist.length - 1) {
                    queue.add(time);
                }
            }
            if (sum > hoursBefore) {
                return -1;
            }
            int ans = 0;
            while (!queue.isEmpty()) {
                if ((sum += (1 - queue.poll())) > hoursBefore) {
                    break;
                }
                ans++;
            }
            return dist.length - 1 - ans;
        }
    }

    public int minSkipsLS(int[] dist, int speed, int hoursBefore) {
        int sumDist = 0;
        for (int d : dist) {
            sumDist += d;
        }
        if (sumDist > (long) speed * hoursBefore) {
            return -1;
        }

        int n = dist.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        for (int i = 0; ; i++) {
            if (dfs(i, n - 2, memo, dist, speed) + dist[n - 1] <= (long) speed * hoursBefore) {
                return i;
            }
        }
    }

    private int dfs(int i, int j, int[][] memo, int[] dist, int speed) {
        if (j < 0) { // 递归边界
            return 0;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        int res = (dfs(i, j - 1, memo, dist, speed) + dist[j] + speed - 1) / speed * speed;
        if (i > 0) {
            res = Math.min(res, dfs(i - 1, j - 1, memo, dist, speed) + dist[j]);
        }
        return memo[i][j] = res; // 记忆化
    }

    /**
     * 39. 组合总和:
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
     * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * <p>
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * <p>
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * <p>
     * 选或不选
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/20 23:27
     */
    class Solution_20_1 {
        List<List<Integer>> ans = new ArrayList<List<Integer>>(150);

        public List<List<Integer>> combinationSum(int[] candidates, int target) {

            dfs(0, target, candidates, new LinkedList<>());
            return ans;
        }

        public void dfs(int i, int target, int[] candidates, List<Integer> list) {
            if (target == 0) {
                ans.add(new LinkedList<>(list));
                return;
            }
            if (i >= candidates.length || target < 0) {
                return;
            }
            //选
            list.add(candidates[i]);
            dfs(i, target - candidates[i], candidates, list);
            //不选
            list.remove(list.size() - 1);
            dfs(i + 1, target, candidates, list);
        }
    }


    /**
     * 216. 组合总和 III:
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * <p>
     * 只使用数字1到9
     * 每个数字 最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/21 15:01
     */
    class Solution_21_1 {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> combinationSum3(int k, int n) {
            int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            dfs(0, n, k, candidates, new LinkedList<>());
            return ans;
        }

        public void dfs(int i, int target, int k, int[] candidates, List<Integer> list) {
            if (target == 0 && k == 0) {
                ans.add(new LinkedList<>(list));
                return;
            }
            if (i >= candidates.length || target < 0 || k < 0) {
                return;
            }
            //选
            list.add(candidates[i]);
            dfs(i + 1, target - candidates[i], k - 1, candidates, list);
            //不选
            list.remove(list.size() - 1);
            dfs(i + 1, target, k, candidates, list);
        }
    }

    /**
     * 377. 组合总和 Ⅳ:
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     * <p>
     * 题目数据保证答案符合 32 位整数范围。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/23 0:02
     */
    class Solution_23_1 {
        public int combinationSum4LS(int[] nums, int target) {
            int[] memo = new int[target + 1];
            Arrays.fill(memo, -1); // -1 表示没有计算过
            return dfs(target, nums, memo);
        }

        private int dfs(int i, int[] nums, int[] memo) {
            if (i == 0) { // 爬完了
                return 1;
            }
            if (memo[i] != -1) { // 之前计算过
                return memo[i];
            }
            int res = 0;
            for (int x : nums) { // 枚举所有可以爬的台阶数
                if (x <= i) {
                    res += dfs(i - x, nums, memo);
                }
            }
            return memo[i] = res; // 记忆化
        }

        public int combinationSum4LSDT(int[] nums, int target) {
            int[] f = new int[target + 1];
            f[0] = 1;
            for (int i = 1; i <= target; i++) {
                for (int x : nums) {
                    if (x <= i) {
                        f[i] += f[i - x];
                    }
                }
            }
            return f[target];
        }
    }

    /**
     * 1052. 爱生气的书店老板:
     * 有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。
     * 给定一个长度为 n 的整数数组 customers ，其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
     * <p>
     * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
     * <p>
     * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
     * <p>
     * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
     * <p>
     * 请你返回 这一天营业下来，最多有多少客户能够感到满意 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/23 23:31
     */
    class Solution_23_2 {
        public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
            int ans = 0;
            int length = grumpy.length;
            int start = -1;
            int max = 0;
            Queue<List<Integer>> list = new ArrayDeque<>();
            for (int i = 0; i < length; i++) {
                if (grumpy[i] == 0) {
                    ans += customers[i];
                }

            }

            return ans;
        }

        public int maxSatisfiedLS(int[] customers, int[] grumpy, int minutes) {
            int[] s = new int[2];
            int maxS1 = 0;
            for (int i = 0; i < customers.length; i++) {
                s[grumpy[i]] += customers[i];
                if (i < minutes - 1) { // 窗口长度不足 minutes
                    continue;
                }
                maxS1 = Math.max(maxS1, s[1]);
                // 窗口最左边元素离开窗口
                s[1] -= grumpy[i - minutes + 1] > 0 ? customers[i - minutes + 1] : 0;
            }
            return s[0] + maxS1;
        }

    }

    /**
     * 2385. 感染二叉树需要的总时间:
     * 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
     * <p>
     * 每分钟，如果节点满足以下全部条件，就会被感染：
     * <p>
     * 节点此前还没有感染。
     * 节点与一个已感染节点相邻。
     * 返回感染整棵树需要的分钟数。
     * 求最长距离。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/24 0:14
     */
    class Solution_24_1 {
        public int amountOfTime(TreeNode root, int start) {
            return 1;
        }

        public int amountOfTimeGF(TreeNode root, int start) {
            Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
            dfs(graph, root);
            Queue<int[]> queue = new ArrayDeque<int[]>();
            queue.offer(new int[]{start, 0});
            Set<Integer> visited = new HashSet<Integer>();
            visited.add(start);
            int time = 0;
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int nodeVal = arr[0];
                time = arr[1];
                for (int childVal : graph.get(nodeVal)) {
                    if (visited.add(childVal)) {
                        queue.offer(new int[]{childVal, time + 1});
                    }
                }
            }
            return time;
        }

        public void dfs(Map<Integer, List<Integer>> graph, TreeNode node) {
            graph.putIfAbsent(node.val, new ArrayList<Integer>());
            for (TreeNode child : Arrays.asList(node.left, node.right)) {
                if (child != null) {
                    graph.get(node.val).add(child.val);
                    graph.putIfAbsent(child.val, new ArrayList<Integer>());
                    graph.get(child.val).add(node.val);
                    dfs(graph, child);
                }
            }
        }


    }

    /**
     * 2739. 总行驶距离:
     * 卡车有两个油箱。给你两个整数，mainTank 表示主油箱中的燃料（以升为单位），additionalTank 表示副油箱中的燃料（以升为单位）。
     * <p>
     * 该卡车每耗费 1 升燃料都可以行驶 10 km。每当主油箱使用了 5 升燃料时，如果副油箱至少有 1 升燃料，则会将 1 升燃料从副油箱转移到主油箱。
     * <p>
     * 返回卡车可以行驶的最大距离。
     * <p>
     * 注意：从副油箱向主油箱注入燃料不是连续行为。这一事件会在每消耗 5 升燃料时突然且立即发生。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/25 0:50
     */
    class Solution_25_1 {
        public int distanceTraveled(int mainTank, int additionalTank) {
            int ans = 0;
            while (additionalTank > 0 && mainTank >= 5) {
                ans += 50;
                mainTank -= 4;
                additionalTank--;
            }
            return ans + (mainTank * 10);
        }
    }

    /**
     * 1146. 快照数组:
     * 实现支持下列接口的「快照数组」- SnapshotArray：
     * <p>
     * SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
     * void set(index, val) - 会将指定索引 index 处的元素设置为 val。
     * int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
     * int get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/26 0:07
     */
    class SnapshotArray {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<Integer> list = null;
        int snapId = 0;

        public SnapshotArray(int length) {
            list = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                list.add(0);
            }
        }

        public void set(int index, int val) {
            list.set(index, val);
        }

        public int snap() {
            ArrayList<Integer> temp = new ArrayList<>(list);
            map.put(snapId++, temp);
            return snapId - 1;
        }

        public int get(int index, int snap_id) {
            ArrayList<Integer> list1 = map.get(snap_id);
            return list1.get(index);
        }
    }

    class SnapshotArrayGF {
        private int snap_cnt;
        private List<int[]>[] data;

        public SnapshotArrayGF(int length) {
            snap_cnt = 0;
            data = new List[length];
            for (int i = 0; i < length; i++) {
                data[i] = new ArrayList<int[]>();
            }
        }

        public void set(int index, int val) {
            data[index].add(new int[]{snap_cnt, val});
        }

        public int snap() {
            return snap_cnt++;
        }

        public int get(int index, int snap_id) {
            int x = binarySearch(index, snap_id);
            return x == 0 ? 0 : data[index].get(x - 1)[1];
        }

        private int binarySearch(int index, int snap_id) {
            int low = 0, high = data[index].size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                int[] pair = data[index].get(mid);
                if (pair[0] > snap_id + 1 || (pair[0] == snap_id + 1 && pair[1] >= 0)) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }
    }


    /**
     * 2639. 查询网格图中每一列的宽度:
     * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。矩阵中某一列的宽度是这一列数字的最大 字符串长度 。
     * <p>
     * 比方说，如果 grid = [[-10], [3], [12]] ，那么唯一一列的宽度是 3 ，因为 -10 的字符串长度为 3 。
     * 请你返回一个大小为 n 的整数数组 ans ，其中 ans[i] 是第 i 列的宽度。
     * <p>
     * 一个有 len 个数位的整数 x ，如果是非负数，那么 字符串长度 为 len ，否则为 len + 1 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/27 0:19
     */
    class Solution_27_1 {
        public int[] findColumnWidth(int[][] grid) {
            int[] ans = new int[grid[0].length];
            for (int i = 0; i < grid[0].length; i++) {
                int max = 0;
                for (int j = 0; j < grid.length; j++) {
                    int len = String.valueOf(grid[j][i]).length();
                    max = Math.max(max, len);
                }
                ans[i] = max;
            }
            return ans;
        }
    }

    /**
     * 1017. 负二进制转换:
     * 给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。
     * <p>
     * 注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/28 0:32
     */
    class Solution_28_1 {
        public String baseNeg2(int n) {
            int val = 0x55555555 ^ (0x55555555 - n);
            if (val == 0) {
                return "0";
            }
            StringBuilder res = new StringBuilder();
            while (val != 0) {
                res.append(val & 1);
                val >>= 1;
            }
            return res.reverse().toString();
        }
    }


    /**
     * 1329. 将矩阵按对角线排序:
     * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。
     * 例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
     * <p>
     * 给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/29 21:48
     */
    class Solution_29_1 {
        public int[][] diagonalSort(int[][] mat) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int i = 0; i < mat.length - 1; i++) {
                sort(mat, i, 0, priorityQueue);
                int temp = i;
                for (int j = 0; j < mat[0].length && temp < mat.length; j++) {
                    mat[temp++][j] = priorityQueue.poll();
                }
            }
            for (int i = 0; i < mat[0].length - 1; i++) {
                sort(mat, 0, i, priorityQueue);
                int temp = i;
                for (int j = 0; j < mat.length && temp < mat[0].length; j++) {
                    mat[j][temp++] = priorityQueue.poll();
                }
            }
            return mat;
        }

        public void sort(int[][] mat, int i, int j, PriorityQueue<Integer> priorityQueue) {
            if (i >= mat.length || j >= mat[0].length) {
                return;
            }
            priorityQueue.add(mat[i][j]);
            sort(mat, ++i, ++j, priorityQueue);
        }
    }

    /**
     * 2798. 满足目标工作时长的员工数目: 
     * 公司里共有 n 名员工，按从 0 到 n - 1 编号。每个员工 i 已经在公司工作了 hours[i] 小时。
     *
     * 公司要求每位员工工作 至少 target 小时。
     *
     * 给你一个下标从 0 开始、长度为 n 的非负整数数组 hours 和一个非负整数 target 。
     *
     * 请你用整数表示并返回工作至少 target 小时的员工数。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/4/30 20:09
     */
    class Solution_30_1 {
        public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
            int left = 0;
            int right = hours.length - 1;
            int ans = 0;
            while (left <= right) {
                if (left==right) {
                    if (hours[left] >= target){
                        return ++ans;
                    }
                    break;
                }
                if (hours[left] >= target){
                    ans++;
                }
                if (hours[right] >= target){
                    ans++;
                }
                left++;
                right--;
            }
            return ans;
        }
    }

}
