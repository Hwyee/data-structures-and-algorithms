package cn.hwyee.algorithms.leecode.daily;

import cn.hwyee.datastructures.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
            for (int i = 0; i < n; i++)
                pa[i][0] = parent[i];
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
                    if (node < 0) break;
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
     *
     * 如果 nums 满足以下条件，那么它是 连续的 ：
     *
     * nums 中所有元素都是 互不相同 的。
     * nums 中 最大 元素与 最小 元素的差等于 nums.length - 1 。
     * 比方说，nums = [4, 2, 5, 3] 是 连续的 ，但是 nums = [1, 2, 3, 5, 6] 不是连续的 。
     *
     * 请你返回使 nums 连续 的 最少 操作次数。
     *
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
            for (int i = 0; i < m-1; i++) {
                int right = 1;
                int left = i+1;
                while (left<m && nums[left] <= nums[i] + n - 1 ) { // nums[left]
                    right++;
                    left++;
                }
                ans = Math.max(ans, right);
            }
            return n - ans ;
        }

    }

    /**
     * 2529. 正整数和负整数的最大计数:
     * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
     *
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
            int r = nums.length-1;
            int pos = 0;
            int neg = 0;
            while (l<=r){
                if (nums[l]>0 ){
                    pos++;
                } else if (nums[l]<0){
                    neg++;
                }
                if (l!=r){
                    if (nums[r]>0 ){
                        pos++;
                    } else if (nums[r]<0){
                        neg++;
                    }
                }
                l++;
                r--;
            }
            return Math.max(pos, neg);
        }
    }

}
