package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202406
 * @description
 * @date 2024/6/1
 * @since JDK 1.8
 */
@Slf4j
public class Daily202406 {

    public static void main(String[] args) {
        Solution_6_1 solution61 = new Solution_6_1();
        solution61.minimumSteps("0111");
        Solution_8_1 solution81 = new Solution_8_1();
        solution81.maxOperations(new int[]{3, 2, 1, 2, 3, 4});
        Solution_14_1 solution141 = new Solution_14_1();
        solution141.maxScore(new int[]{2, 3, 6, 1, 9, 2}, 5);

        Solution_18_1 solution181 = new Solution_18_1();
        System.out.println(solution181.discountPrices("$81 $9606986 brwa $3 $1351 $96 89m h7lbe $4", 38));

        Solution_19_1 solution191 = new Solution_19_1();
        System.out.println(solution191.maxIncreasingCells(new int[][]{{1, 2}, {3, 4}}));
        System.out.println((int) '2');
        System.out.println('2' - '0');//int
    }

    /**
     * 2928. 给小朋友们分糖果 I:
     * 给你两个正整数 n 和 limit 。
     * <p>
     * 请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的 总方案数 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/2 13:20
     */
    class Solution_1_1 {
        public int distributeCandies(int n, int limit) {

            if (n <= limit) {
                return 3 * limit + 1;
            } else {
                int mod = n % 3;

            }
            return 0;
        }

        public int distributeCandiesLS(int n, int limit) {
            return c2(n + 2) - 3 * c2(n - limit + 1) + 3 * c2(n - 2 * limit) - c2(n - 3 * limit - 1);
        }

        private int c2(int n) {
            return n > 1 ? n * (n - 1) / 2 : 0;
        }
    }


    /**
     * 575. 分糖果:
     * Alice 有 n 枚糖，其中第 i 枚糖的类型为 candyType[i] 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。
     * <p>
     * 医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。
     * <p>
     * 给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/2 13:22
     */
    class Solution_2_1 {
        public int distributeCandies(int[] candyType) {
            Arrays.sort(candyType);
            int ans = 1;
            for (int i = 0; i < candyType.length - 1; i++) {
                if (ans == candyType.length / 2) {
                    return ans;
                }
                if (candyType[i] != candyType[i + 1]) {
                    ans++;
                }

            }
            return ans;
        }
    }

    /**
     * 1103. 分糖果 II:
     * 排排坐，分糖果。
     * <p>
     * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
     * <p>
     * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
     * <p>
     * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
     * <p>
     * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
     * <p>
     * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/3 21:26
     */
    class Solution_3_1 {
        public int[] distributeCandies(int candies, int num_people) {
            int i = 1;
            int sum = 1;
            int[] ans = new int[num_people];
            int j = 0;
            for (; sum < candies; i++, sum += i, j++) {
                ans[j % num_people] = ans[j % num_people] + i;
            }
            if (sum > candies) {
                i = i - (sum - candies);
            }
            ans[j % num_people] = ans[j % num_people] + i;
            return ans;
        }
    }

    /**
     * 3067. 在带权树网络中统计可连接服务器对数目:
     * 给你一棵无根带权树，树中总共有 n 个节点，分别表示 n 个服务器，服务器从 0 到 n - 1 编号。同时给你一个数组 edges ，
     * 其中 edges[i] = [ai, bi, weighti] 表示节点 ai 和 bi 之间有一条双向边，边的权值为 weighti 。再给你一个整数 signalSpeed 。
     * <p>
     * 如果两个服务器 a ，b 和 c 满足以下条件，那么我们称服务器 a 和 b 是通过服务器 c 可连接的 ：
     * <p>
     * a < b ，a != c 且 b != c 。
     * 从 c 到 a 的距离是可以被 signalSpeed 整除的。
     * 从 c 到 b 的距离是可以被 signalSpeed 整除的。
     * 从 c 到 b 的路径与从 c 到 a 的路径没有任何公共边。
     * 请你返回一个长度为 n 的整数数组 count ，其中 count[i] 表示通过服务器 i 可连接 的服务器对的 数目 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/5 9:25
     */
    class Solution_4_1 {
        public int[] countPairsOfConnectableServersGF(int[][] edges, int signalSpeed) {
            int n = edges.length + 1;
            List<int[]>[] graph = new ArrayList[n];
            Arrays.setAll(graph, i -> new ArrayList<>());

            for (int[] e : edges) {
                int u = e[0];
                int v = e[1];
                int w = e[2];
                graph[u].add(new int[]{v, w});
                graph[v].add(new int[]{u, w});
            }

            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int pre = 0;
                for (int[] e : graph[i]) {
                    int cnt = dfs(e[0], i, e[1] % signalSpeed, signalSpeed, graph);
                    res[i] += pre * cnt;
                    pre += cnt;
                }
            }
            return res;
        }

        private int dfs(int p, int root, int curr, int signalSpeed, List<int[]>[] graph) {
            int res = 0;
            if (curr == 0) {
                res++;
            }
            for (int[] e : graph[p]) {
                int v = e[0];
                int cost = e[1];
                if (v != root) {
                    res += dfs(v, p, (curr + cost) % signalSpeed, signalSpeed, graph);
                }
            }
            return res;
        }
    }


    class BinaryIndexedTree {
        private int[] tree;

        public BinaryIndexedTree(int n) {
            tree = new int[n + 1];
        }

        public void add(int i) {
            while (i < tree.length) {
                tree[i]++;
                i += i & -i;
            }
        }

        public int get(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }


    /**
     * 3072. 将元素分配到两个数组中 II:
     * 给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
     * <p>
     * 现定义函数 greaterCount ，使得 greaterCount(arr, val) 返回数组 arr 中 严格大于 val 的元素数量。
     * <p>
     * 你需要使用 n 次操作，将 nums 的所有元素分配到两个数组 arr1 和 arr2 中。在第一次操作中，将 nums[1] 追加到 arr1 。在第二次操作中，将 nums[2] 追加到 arr2 。之后，在第 i 次操作中：
     * <p>
     * 如果 greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr1 。
     * 如果 greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr2 。
     * 如果 greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]) ，将 nums[i] 追加到元素数量较少的数组中。
     * 如果仍然相等，那么将 nums[i] 追加到 arr1 。
     * 连接数组 arr1 和 arr2 形成数组 result 。例如，如果 arr1 == [1,2,3] 且 arr2 == [4,5,6] ，那么 result = [1,2,3,4,5,6] 。
     * <p>
     * 返回整数数组 result 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/5 23:42
     */
    class Solution_5_1 {
        public int[] resultArrayGF(int[] nums) {
            int n = nums.length;
            int[] sortedNums = Arrays.copyOf(nums, n);
            Arrays.sort(sortedNums);

            Map<Integer, Integer> index = new HashMap<>();
            for (int i = 0; i < n; i++) {
                index.put(sortedNums[i], i + 1);
            }

            List<Integer> arr1 = new ArrayList<>(List.of(nums[0]));
            List<Integer> arr2 = new ArrayList<>(List.of(nums[1]));
            BinaryIndexedTree tree1 = new BinaryIndexedTree(n);
            BinaryIndexedTree tree2 = new BinaryIndexedTree(n);
            tree1.add(index.get(nums[0]));
            tree2.add(index.get(nums[1]));

            for (int i = 2; i < n; i++) {
                int count1 = arr1.size() - tree1.get(index.get(nums[i]));
                int count2 = arr2.size() - tree2.get(index.get(nums[i]));
                if (count1 > count2 || (count1 == count2 && arr1.size() <= arr2.size())) {
                    arr1.add(nums[i]);
                    tree1.add(index.get(nums[i]));
                } else {
                    arr2.add(nums[i]);
                    tree2.add(index.get(nums[i]));
                }
            }

            int i = 0;
            for (int a : arr1) {
                nums[i++] = a;
            }
            for (int a : arr2) {
                nums[i++] = a;
            }
            return nums;
        }
    }

    /**
     * 2938. 区分黑球与白球:
     * 桌子上有 n 个球，每个球的颜色不是黑色，就是白色。
     * <p>
     * 给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。
     * <p>
     * 在每一步中，你可以选择两个相邻的球并交换它们。
     * <p>
     * 返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/6 20:03
     */
    static class Solution_6_1 {
        public long minimumSteps(String s) {
            char[] charArray = s.toCharArray();

            long blackCount = 0;
            long blackIndex = 0;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == '1') {
                    blackCount++;
                    blackIndex += i;
                }

            }
            long realBlackIndex = (s.length() * 2L - blackCount - 1) * blackCount / 2;
            return realBlackIndex - blackIndex;
//            while (flag){
//                int temp = ans;
//                firstBB = false;
//                for (int i = begin; i < charArray.length-1; i++) {
//                    if (charArray[i] == '1' && charArray[i+1] == '0'){
//                        charArray[i] = '0';
//                        charArray[i+1] = '1';
//                        ans++;
//                    }else if ( !firstBB && charArray[i] == '1' && charArray[i+1] == '1' ){
//                        begin=i;
//                        firstBB = true;
//                    }else if (firstBB && charArray[i] == '0' && charArray[i+1] == '0' ){
//                        i++;
//                    }
//                }
//                if (temp == ans){
//                    flag = false;
//                }
//            }
//            return ans;
        }

        public long minimumStepsGF(String s) {
            int n = s.length();
            long count = 0;
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '0') {
                    count += (i - index);
                    index++;
                }
            }
            return count;
        }
    }

    /**
     * 3038. 相同分数的最大操作数目 I:
     * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作：
     * <p>
     * 选择 nums 中的前两个元素并将它们删除。
     * 一次操作的 分数 是被删除元素的和。
     * <p>
     * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
     * <p>
     * 请你返回按照上述要求 最多 可以进行的操作次数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/7 23:47
     */
    class Solution_7_1 {
        public int maxOperations(int[] nums) {
            int pre = 0;
            int cur = 0;
            int ans = 1;
            pre = nums[0] + nums[1];
            for (int i = 2; i < nums.length - 1; i++) {
                cur = nums[i] + nums[i + 1];
                if (cur == pre) {
                    i++;
                    ans++;
                } else {
                    break;
                }
            }
            return ans;
        }
    }

    /**
     * 3040. 相同分数的最大操作数目 II:
     * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
     * <p>
     * 选择 nums 中最前面两个元素并且删除它们。
     * 选择 nums 中最后两个元素并且删除它们。
     * 选择 nums 中第一个和最后一个元素并且删除它们。
     * 一次操作的 分数 是被删除元素的和。
     * <p>
     * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
     * <p>
     * 请你返回按照上述要求 最多 可以进行的操作次数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/8 13:12
     */
    static class Solution_8_1 {


        int[][] mem;

        public int maxOperations(int[] nums) {
            mem = new int[nums.length][nums.length];
            int a = dfs(nums, nums[0] + nums[nums.length - 1], nums[0] + nums[nums.length - 1], 1, nums.length - 2, 0);
            mem = new int[nums.length][nums.length];
            int b = dfs(nums, nums[nums.length - 2] + nums[nums.length - 1], nums[nums.length - 2] + nums[nums.length - 1], 0, nums.length - 3, 0);
            mem = new int[nums.length][nums.length];
            int c = dfs(nums, nums[0] + nums[1], nums[0] + nums[1], 2, nums.length - 1, 0);
            return Math.max(a, Math.max(b, c));
        }

        public int dfs(int[] nums, int pre, int cur, int left, int right, int ans) {
            if (pre == cur) {
                ans++;
            } else {
                return ans;
            }

            if (left >= right) {
                return ans;
            }
            if (mem[left][right] != 0) {
                return mem[left][right];
            }


            int a = nums[left] + nums[right];
            int b = nums[left] + nums[left + 1];
            int c = nums[right] + nums[right - 1];
            return mem[left][right] = Math.max(dfs(nums, pre, a, left + 1, right - 1, ans), Math.max(dfs(nums, pre, b, left + 2, right, ans), dfs(nums, pre, c, left, right - 2, ans)));
        }


        private int[] nums;
        private int[][] memo;
        private boolean done;

        public int maxOperationsLS(int[] nums) {
            this.nums = nums;
            int n = nums.length;
            memo = new int[n][n];
            int res1 = helper(2, n - 1, nums[0] + nums[1]); // 删除前两个数
            int res2 = helper(0, n - 3, nums[n - 2] + nums[n - 1]); // 删除后两个数
            int res3 = helper(1, n - 2, nums[0] + nums[n - 1]); // 删除第一个和最后一个数
            return Math.max(Math.max(res1, res2), res3) + 1; // 加上第一次操作
        }

        private int helper(int i, int j, int target) {
            if (done) { // 说明之前已经算出了 res = n / 2
                return 0; // 返回任意 <= n/2 的数均可
            }
            for (int[] row : memo) {
                Arrays.fill(row, -1); // -1 表示没有计算过
            }
            return dfs(i, j, target);
        }

        private int dfs(int i, int j, int target) {
            if (done) {
                return 0;
            }
            if (i >= j) {
                done = true;
                return 0;
            }
            if (memo[i][j] != -1) { // 之前计算过
                return memo[i][j];
            }
            int res = 0;
            if (nums[i] + nums[i + 1] == target) { // 删除前两个数
                res = Math.max(res, dfs(i + 2, j, target) + 1);
            }
            if (nums[j - 1] + nums[j] == target) { // 删除后两个数
                res = Math.max(res, dfs(i, j - 2, target) + 1);
            }
            if (nums[i] + nums[j] == target) { // 删除第一个和最后一个数
                res = Math.max(res, dfs(i + 1, j - 1, target) + 1);
            }
            return memo[i][j] = res; // 记忆化
        }


    }

    /**
     * 312. 戳气球:
     * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     * <p>
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
     * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     * <p>
     * 求所能获得硬币的最大数量。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/9 20:37
     */
    class Solution_9_1 {
        public int maxCoinsGF(int[] nums) {
            int n = nums.length;
            int[][] rec = new int[n + 2][n + 2];
            int[] val = new int[n + 2];
            val[0] = val[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                val[i] = nums[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 2; j <= n + 1; j++) {
                    for (int k = i + 1; k < j; k++) {
                        int sum = val[i] * val[k] * val[j];
                        sum += rec[i][k] + rec[k][j];
                        rec[i][j] = Math.max(rec[i][j], sum);
                    }
                }
            }
            return rec[0][n + 1];


        }
    }

    /**
     * 881. 救生艇:
     * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
     * <p>
     * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
     * <p>
     * 返回 承载所有人所需的最小船数 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/10 22:22
     */
    class Solution_10_1 {
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int left = 0;
            int right = people.length - 1;
            int ans = 0;
            while (left < right) {
                if (people[right] + people[left] > limit) {
                    right--;

                } else {
                    left++;
                    right--;
                }
                ans++;
            }
            return left == right ? ans + 1 : ans;

        }
    }


    /**
     * 419. 甲板上的战舰:
     * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
     * <p>
     * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/11 15:46
     */
    class Solution_11_1 {
        public int countBattleships(char[][] board) {
            int m = board.length;
            int n = board[0].length;
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'X') {
                        if (i == 0 && j == 0) {
                            ans++;
                        } else if (i == 0 && board[i][j - 1] == '.') {
                            ans++;
                        } else if (j == 0 && board[i - 1][j] == '.') {
                            ans++;
                        } else if (i > 0 && j > 0 && board[i - 1][j] == '.' && board[i][j - 1] == '.') {
                            ans++;
                        }
                    }
                }
            }
            return ans;
        }
    }

    /**
     * 2806. 取整购买后的账户余额:
     * 一开始，你的银行账户里有 100 块钱。
     * <p>
     * 给你一个整数purchaseAmount ，它表示你在一次购买中愿意支出的金额。
     * <p>
     * 在一个商店里，你进行一次购买，实际支出的金额会向 最近 的 10 的 倍数 取整。换句话说，你实际会支付一个 非负 金额 roundedAmount ，满足 roundedAmount 是 10 的倍数且 abs(roundedAmount - purchaseAmount) 的值 最小 。
     * <p>
     * 如果存在多于一个最接近的 10 的倍数，较大的倍数 是你的实际支出金额。
     * <p>
     * 请你返回一个整数，表示你在愿意支出金额为 purchaseAmount 块钱的前提下，购买之后剩下的余额。
     * <p>
     * 注意： 0 也是 10 的倍数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/12 20:47
     */
    class Solution_12_1 {
        public int accountBalanceAfterPurchase(int purchaseAmount) {
            int i = purchaseAmount % 10;
            return i < 5 ? 100 - purchaseAmount + i : 90 - purchaseAmount + i;
        }
    }

    /**
     * 2813. 子序列最大优雅度
     * 给你一个长度为 n 的二维整数数组 items 和一个整数 k 。
     * <p>
     * items[i] = [profiti, categoryi]，其中 profiti 和 categoryi 分别表示第 i 个项目的利润和类别。
     * <p>
     * 现定义 items 的 子序列 的 优雅度 可以用 total_profit + distinct_categories2 计算，其中 total_profit 是子序列中所有项目的利润总和，distinct_categories 是所选子序列所含的所有类别中不同类别的数量。
     * <p>
     * 你的任务是从 items 所有长度为 k 的子序列中，找出 最大优雅度 。
     * <p>
     * 用整数形式表示并返回 items 中所有长度恰好为 k 的子序列的最大优雅度。
     * <p>
     * 注意：数组的子序列是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。:
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/13 11:02
     */
    class Solution_13_1 {
        public long findMaximumElegance(int[][] items, int k) {
            // 把利润从大到小排序
            Arrays.sort(items, (a, b) -> b[0] - a[0]);
            long ans = 0;
            long totalProfit = 0;
            Set<Integer> vis = new HashSet<>();
            Deque<Integer> duplicate = new ArrayDeque<>(); // 重复类别的利润
            for (int i = 0; i < items.length; i++) {
                int profit = items[i][0];
                int category = items[i][1];
                if (i < k) {
                    totalProfit += profit; // 累加前 k 个项目的利润
                    if (!vis.add(category)) { // 重复类别
                        duplicate.push(profit);
                    }
                } else if (!duplicate.isEmpty() && vis.add(category)) { // 之前没有的类别
                    totalProfit += profit - duplicate.pop(); // 选一个重复类别中的最小利润替换
                } // else：比前面的利润小，而且类别还重复了，选它只会让 totalProfit 变小，vis.size() 不变，优雅度不会变大
                ans = Math.max(ans, totalProfit + (long) vis.size() * vis.size()); // 注意 1e5*1e5 会溢出
            }
            return ans;
        }
    }


    static class Solution_14_1 {
        long[][] a;

        public long maxScore(int[] nums, int x) {
            a = new long[nums.length][2];
//            return dfs(nums, x, 1, nums[0], nums[0] % 2);
            return dfs1(nums, x, 1, nums[0] % 2) + nums[0];
        }

        public long dfs(int[] nums, int x, int i, int ans, int flag) {
            if (i == nums.length) {
                return ans;
            }
            if (a[i][flag] != 0) {
                return a[i][flag];
            }
            if (nums[i] % 2 == flag) {
                return a[i][flag] = dfs(nums, x, i + 1, ans + nums[i], flag);

            } else {
                long r2 = dfs(nums, x, i + 1, ans, flag);
                long r1 = dfs(nums, x, i + 1, ans - x + nums[i], nums[i] % 2);
                return a[i][flag] = Math.max(r1, r2);

            }
        }

        public long dfs1(int[] nums, int x, int i, int flag) {
            if (i == nums.length) {
                return 0;
            }
            if (a[i][flag] != 0) {
                return a[i][flag];
            }
            if (nums[i] % 2 == flag) {
                return a[i][flag] = dfs1(nums, x, i + 1, flag) + nums[i];

            } else {
                long r2 = dfs1(nums, x, i + 1, flag);
                long r1 = dfs1(nums, x, i + 1, nums[i] % 2);
                return a[i][flag] = Math.max(r1 - x + nums[i], r2);

            }
        }
    }

    public long maxScoreLS(int[] nums, int x) {
        long[] f = new long[2];
        for (int i = nums.length - 1; i >= 0; i--) {
            int v = nums[i];
            int r = v & 1; // 比 % 2 快一点
            f[r] = Math.max(f[r], f[r ^ 1] - x) + v;
        }
        return f[nums[0] & 1];
    }

    /**
     * 2779. 数组的最大美丽值:
     * 给你一个下标从 0 开始的整数数组 nums 和一个 非负 整数 k 。
     * <p>
     * 在一步操作中，你可以执行下述指令：
     * <p>
     * 在范围 [0, nums.length - 1] 中选择一个 此前没有选过 的下标 i 。
     * 将 nums[i] 替换为范围 [nums[i] - k, nums[i] + k] 内的任一整数。
     * 数组的 美丽值 定义为数组中由相等元素组成的最长子序列的长度。
     * <p>
     * 对数组 nums 执行上述操作任意次后，返回数组可能取得的 最大 美丽值。
     * <p>
     * 注意：你 只 能对每个下标执行 一次 此操作。
     * <p>
     * 数组的 子序列 定义是：经由原数组删除一些元素（也可能不删除）得到的一个新数组，且在此过程中剩余元素的顺序不发生改变。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/16 12:53
     */
    class Solution_15_1 {
        public int maximumBeauty(int[] nums, int k) {
            int res = 0, n = nums.length;
            Arrays.sort(nums);
            for (int i = 0, j = 0; i < n; i++) {
                while (nums[i] - 2 * k > nums[j]) {
                    j++;
                }
                res = Math.max(res, i - j + 1);
            }
            return res;
        }
    }

    /**
     * 521. 最长特殊序列 Ⅰ:
     * 给你两个字符串 a 和 b，请返回 这两个字符串中 最长的特殊序列  的长度。如果不存在，则返回 -1 。
     * <p>
     * 「最长特殊序列」 定义如下：该序列为 某字符串独有的最长
     * 子序列
     * （即不能是其他字符串的子序列） 。
     * <p>
     * 字符串 s 的子序列是在从 s 中删除任意数量的字符后可以获得的字符串。
     * <p>
     * 例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。
     * "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/16 12:54
     */
    class Solution_16_1 {
        public int findLUSlength(String a, String b) {
            return !a.equals(b) ? Math.max(a.length(), b.length()) : -1;

        }
    }

    /**
     * 522. 最长特殊序列 II:
     * 给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
     * <p>
     * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
     * <p>
     * s 的 子序列可以通过删去字符串 s 中的某些字符实现。
     * <p>
     * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/17 9:53
     */
    class Solution_17_1 {
        public int findLUSlength(String[] strs) {
            int ans = -1;
            next:
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() <= ans) { // 不会让 ans 变大
                    continue;
                }
                for (int j = 0; j < strs.length; j++) {
                    if (j != i && isSubseq(strs[i], strs[j])) {
                        continue next;
                    }
                }
                ans = strs[i].length();
            }
            return ans;
        }

        // 判断 s 是否为 t 的子序列
        private boolean isSubseq(String s, String t) {
            int i = 0;
            for (char c : t.toCharArray()) {
                if (s.charAt(i) == c && ++i == s.length()) { // 所有字符匹配完毕
                    return true; // s 是 t 的子序列
                }
            }
            return false;
        }
    }

    /**
     * 2288. 价格减免:
     * 句子 是由若干个单词组成的字符串，单词之间用单个空格分隔，其中每个单词可以包含数字、小写字母、和美元符号 '$' 。如果单词的形式为美元符号后跟着一个非负实数，那么这个单词就表示一个 价格 。
     * <p>
     * 例如 "$100"、"$23" 和 "$6" 表示价格，而 "100"、"$" 和 "$1e5 不是。
     * 给你一个字符串 sentence 表示一个句子和一个整数 discount 。对于每个表示价格的单词，都在价格的基础上减免 discount% ，并 更新 该单词到句子中。所有更新后的价格应该表示为一个 恰好保留小数点后两位 的数字。
     * <p>
     * 返回表示修改后句子的字符串。
     * <p>
     * 注意：所有价格 最多 为  10 位数字。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/18 10:13
     */
    static class Solution_18_1 {
        public String discountPrices(String sentence, int discount) {
            String[] words = sentence.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].charAt(0) == '$') {
                    try {
                        boolean flag = true;
                        for (int i1 = 1; i1 < words[i].toCharArray().length; i1++) {
                            if (words[i].charAt(i1) > 'A') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {

                            words[i] = "$" + BigDecimal.valueOf((Double.parseDouble(words[i].substring(1)) * ((100 - discount) / 100.0))).setScale(2, 4).toPlainString();
                        }

                    } catch (Exception e) {
                    }
                }
            }
            return String.join(" ", words);
        }
    }

    /**
     * 2713. 矩阵中严格递增的单元格数:
     * 给你一个下标从 1 开始、大小为 m x n 的整数矩阵 mat，你可以选择任一单元格作为 起始单元格 。
     * <p>
     * 从起始单元格出发，你可以移动到 同一行或同一列 中的任何其他单元格，但前提是目标单元格的值 严格大于 当前单元格的值。
     * <p>
     * 你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。
     * <p>
     * 请你找出从某个单元开始访问矩阵所能访问的 单元格的最大数量 。
     * <p>
     * 返回一个表示可访问单元格最大数量的整数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/19 18:09
     */
    static class Solution_19_1 {
        public int maxIncreasingCells(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            TreeMap<Integer, List<int[]>> g = new TreeMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 相同元素放在同一组，统计位置
                    g.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }

            int ans = 0;
            int[] rowMax = new int[m];
            int[] colMax = new int[n];
            for (List<int[]> pos : g.values()) {
                // 先把所有 f 值都算出来，再更新 rowMax 和 colMax
                int[] fs = new int[pos.size()];
                for (int k = 0; k < pos.size(); k++) {
                    int[] p = pos.get(k);
                    int i = p[0];
                    int j = p[1];
                    fs[k] = Math.max(rowMax[i], colMax[j]) + 1;
                    ans = Math.max(ans, fs[k]);
                }
                for (int k = 0; k < pos.size(); k++) {
                    int[] p = pos.get(k);
                    int i = p[0];
                    int j = p[1];
                    rowMax[i] = Math.max(rowMax[i], fs[k]); // 更新第 i 行的最大 f 值
                    colMax[j] = Math.max(colMax[j], fs[k]); // 更新第 j 列的最大 f 值
                }
            }
            return ans;


        }
    }

    /**
     * 2748. 美丽下标对的数目:
     * 给你一个下标从 0 开始的整数数组 nums 。如果下标对 i、j 满足 0 ≤ i < j < nums.length ，
     * 如果 nums[i] 的 第一个数字 和 nums[j] 的 最后一个数字 互质 ，则认为 nums[i] 和 nums[j] 是一组 美丽下标对 。
     * <p>
     * 返回 nums 中 美丽下标对 的总数目。
     * <p>
     * 对于两个整数 x 和 y ，如果不存在大于 1 的整数可以整除它们，则认为 x 和 y 互质 。换而言之，
     * 如果 gcd(x, y) == 1 ，则认为 x 和 y 互质，其中 gcd(x, y) 是 x 和 y 的 最大公因数 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/20 8:37
     */
    class Solution_20_1 {
        public int countBeautifulPairs(int[] nums) {
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                int x = String.valueOf(nums[i]).charAt(0) - '0';
                for (int j = i + 1; j < nums.length; j++) {
                    String ys = String.valueOf(nums[j]);
                    if (gcd(x, ys.charAt(ys.length() - 1) - '0')) {
                        ans++;
                    }
                }
            }
            return ans;
        }

        public boolean gcd(int x, int y) {
            int m = x > y ? y : x;
            for (int i = 2; i <= m; i++) {
                if (x % i == 0 && y % i == 0) {
                    return false;
                }
            }
            return true;
        }

        public int countBeautifulPairsLS(int[] nums) {
            int ans = 0;
            int[] cnt = new int[10];
            for (int x : nums) {
                for (int y = 1; y < 10; y++) {
                    if (cnt[y] > 0 && gcd1(y, x % 10) == 1) {
                        ans += cnt[y];
                    }
                }
                while (x >= 10) {
                    x /= 10;
                }
                cnt[x]++; // 统计最高位的出现次数
            }
            return ans;
        }

        private int gcd1(int a, int b) {
            return b == 0 ? a : gcd1(b, a % b);
        }
    }

    /**
     * LCP 61. 气温变化趋势:
     * 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 i ~ (i+1) 天的气温变化趋势，将根据以下规则判断：
     * <p>
     * 若第 i+1 天的气温 高于 第 i 天，为 上升 趋势
     * 若第 i+1 天的气温 等于 第 i 天，为 平稳 趋势
     * 若第 i+1 天的气温 低于 第 i 天，为 下降 趋势
     * 已知 temperatureA[i] 和 temperatureB[i] 分别表示第 i 天两地区的气温。 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。请分析并返回两地气温变化趋势相同的最大连续天数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/21 12:35
     */
    class Solution_21_1 {
        public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
            int ans = 0;
            int max = 0;
            for (int i = 0; i < temperatureA.length - 1; i++) {
                if (judge(temperatureA[i + 1], temperatureA[i]) == judge(temperatureB[i + 1], temperatureB[i])) {
                    ans++;
                    max = Math.max(ans, max);
                } else {
                    ans = 0;
                }
            }
            return max;
        }

        public int judge(int x, int y) {
            if (x > y) {
                return -1;
            } else if (x == y) {
                return 0;
            } else {
                return 1;
            }
        }
    }


    /**
     * 2663. 字典序最小的美丽字符串:
     * 如果一个字符串满足以下条件，则称其为 美丽字符串 ：
     * <p>
     * 它由英语小写字母表的前 k 个字母组成。
     * 它不包含任何长度为 2 或更长的回文子字符串。
     * 给你一个长度为 n 的美丽字符串 s 和一个正整数 k 。
     * <p>
     * 请你找出并返回一个长度为 n 的美丽字符串，该字符串还满足：在字典序大于 s 的所有美丽字符串中字典序最小。如果不存在这样的字符串，则返回一个空字符串。
     * <p>
     * 对于长度相同的两个字符串 a 和 b ，如果字符串 a 在与字符串 b 不同的第一个位置上的字符字典序更大，则字符串 a 的字典序大于字符串 b 。
     * <p>
     * 例如，"abcd" 的字典序比 "abcc" 更大，因为在不同的第一个位置（第四个字符）上 d 的字典序大于 c 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/22 10:09
     */
    class Solution_22_1 {
        public String smallestBeautifulString(String S, int k) {
            k += 'a';
            char[] s = S.toCharArray();
            int n = s.length;
            int i = n - 1; // 从最后一个字母开始
            s[i]++; // 先加一
            while (i < n) {
                if (s[i] == k) { // 需要进位
                    if (i == 0) { // 无法进位
                        return "";
                    }
                    // 进位
                    s[i] = 'a';
                    s[--i]++;
                } else if (i > 0 && s[i] == s[i - 1] || i > 1 && s[i] == s[i - 2]) {
                    s[i]++; // 如果 s[i] 和左侧的字符形成回文串，就继续增加 s[i]
                } else {
                    i++; // 反过来检查后面是否有回文串
                }
            }
            return new String(s);
        }


    }

    /**
     * 520. 检测大写字母:
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     * <p>
     * 全部字母都是大写，比如 "USA" 。
     * 单词中所有字母都不是大写，比如 "leetcode" 。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
     * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/23 11:05
     */
    class Solution_23_1 {
        public boolean detectCapitalUse(String word) {
            if (word.length() == 1) {
                return true;
            }
            int ans = 0;
            if (word.charAt(0) - 'a' >= 0) {
                if (word.charAt(1) - 'a' < 0) {
                    return false;
                }
                ans = 2;
            } else if (word.charAt(1) - 'a' < 0) {
                ans = 1;
            } else {
                ans = 3;
            }
            for (int i = 2; i < word.length(); i++) {
                if ((ans == 1 && word.charAt(i) - 'a' >= 0) || (ans > 1 && word.charAt(i) - 'a' < 0)) {
                    return false;
                }
            }
            return true;
        }

        public boolean detectCapitalUseLS(String word) {
            int cnt = 0;
            for (char c : word.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    cnt++;
                }
            }
            return cnt == 0 || cnt == word.length() || cnt == 1 && Character.isUpperCase(word.charAt(0));
        }


    }

    /**
     * 503. 下一个更大元素 II:
     * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
     *
     * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/24 13:48
     */
    class Solution_24_1 {
        public int[] nextGreaterElements(int[] nums) {
            int[] ans = new int[nums.length];
            for(int i = 0;i<nums.length;i++){
                int temp = nums[i];
                boolean b = true;
                for(int j = 1;j<nums.length;j++){
                    int find = nums[(j+i)%nums.length];
                    if(find>temp){
                        ans[i] = find;
                        b = false;
                        break;
                    }
                }
                if(b){
                    ans[i] = -1;
                }
            }
            return ans;
        }

        public int[] nextGreaterElementsStack(int[] nums) {
            int len = nums.length;
            int[] ans = new int[len];
            Arrays.fill(ans,-1);
            Stack<Integer> stack = new Stack<Integer>();
            for(int i = 0;i<2*len-1;i++){
                int temp = nums[i%len];
                while(!stack.isEmpty() && nums[stack.peek()]<temp ){
                    ans[stack.pop()] = temp;
                }
                if(i<len){
                    stack.push(i);
                }

            }
            return ans;
        }

    }

    /**
     * 2732. 找到矩阵中的好子集:
     * 给你一个下标从 0 开始大小为 m x n 的二进制矩阵 grid 。
     *
     * 从原矩阵中选出若干行构成一个行的 非空 子集，如果子集中任何一列的和至多为子集大小的一半，那么我们称这个子集是 好子集。
     *
     * 更正式的，如果选出来的行子集大小（即行的数量）为 k，那么每一列的和至多为 floor(k / 2) 。
     *
     * 请你返回一个整数数组，它包含好子集的行下标，请你将子集中的元素 升序 返回。
     *
     * 如果有多个好子集，你可以返回任意一个。如果没有好子集，请你返回一个空数组。
     *
     * 一个矩阵 grid 的行 子集 ，是删除 grid 中某些（也可能不删除）行后，剩余行构成的元素集合。
     *
     *
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/25 10:56
     */
    class Solution_25_1 {
        public List<Integer> goodSubsetofBinaryMatrixGF(int[][] grid) {
            List<Integer> ans = new ArrayList<Integer>();
            Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
            int m = grid.length;
            int n = grid[0].length;

            for (int j = 0; j < m; j++) {
                int st = 0;
                for (int i = 0; i < n; i++) {
                    st |= (grid[j][i] << i);
                }
                mp.put(st, j);
            }

            if (mp.containsKey(0)) {
                ans.add(mp.get(0));
                return ans;
            }

            for (Map.Entry<Integer, Integer> entry1 : mp.entrySet()) {
                int x = entry1.getKey(), i = entry1.getValue();
                for (Map.Entry<Integer, Integer> entry2 : mp.entrySet()) {
                    int y = entry2.getKey(), j = entry2.getValue();
                    if ((x & y) == 0) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(Math.min(i, j));
                        list.add(Math.max(i, j));
                        return list;
                    }
                }
            }

            return ans;

        }
    }

    /**
     * 46. 全排列:
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/26 12:14
     */
    class Solution_26_1 {
        public List<List<Integer>> permuteError(int[] nums) {
            List<Integer> list = new ArrayList<Integer>(nums.length);
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            ans.add(list);
            for(int i : nums){
                list.add(i);
            }
            for(int i = 0;i<nums.length-1;i++){
                for(int j  = i+1;j<nums.length;j++){
                    swap(list,i,j);
                    ans.add(new ArrayList<Integer>(list));
                    swap(list,i,j);
                }
            }
            return ans;
        }
        public void swap(List<Integer> list,int x,int y){
            int temp = list.get(x);
            list.set(x, list.get(y));
            list.set(y, temp);
        }

        private int[] nums;
        private List<Integer> path;
        private boolean[] onPath;
        private final List<List<Integer>> ans = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            this.nums = nums;
            path = Arrays.asList(new Integer[nums.length]);
            onPath = new boolean[nums.length];
            dfs(0);
            return ans;
        }

        private void dfs(int i) {
            if (i == nums.length) {
                ans.add(new ArrayList<>(path));
                return;
            }
            for (int j = 0; j < nums.length; ++j) {
                if (!onPath[j]) {
                    path.set(i, nums[j]);
                    onPath[j] = true;
                    dfs(i + 1);
                    onPath[j] = false; // 恢复现场
                }
            }
        }


    }

    /**
     * 2741. 特别的排列:
     * 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：
     *
     * 对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
     * 请你返回特别排列的总数目，由于答案可能很大，请将它对 109 + 7 取余 后返回。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/27 22:06
     */
    class Solution_26_2 {
        int mod = 1_000_000_007;
        public int specialPerm(int[] nums) {
            int n = nums.length;
            int u = (1 << n) - 1;
            long[][] f = new long[u][n];
            Arrays.fill(f[0], 1L);
            for (int s = 1; s < u; s++) {
                for (int i = 0; i < n; i++) {
                    if ((s >> i & 1) != 0) {
                        continue;
                    }
                    for (int j = 0; j < n; j++) {
                        if ((s >> j & 1) != 0 && (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0)) {
                            f[s][i] += f[s ^ (1 << j)][j];
                        }
                    }
                }
            }
            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += f[u ^ (1 << i)][i];
            }
            return (int) (ans % 1_000_000_007);

        }
    }

    /**
     * 2734. 执行子串操作后的字典序最小字符串:
     * 给你一个仅由小写英文字母组成的字符串 s 。在一步操作中，你可以完成以下行为：
     *
     * 选择 s 的任一非空子字符串，可能是整个字符串，接着将字符串中的每一个字符替换为英文字母表中的前一个字符。例如，'b' 用 'a' 替换，'a' 用 'z' 替换。
     * 返回执行上述操作 恰好一次 后可以获得的 字典序最小 的字符串。
     *
     * 子字符串 是字符串中的一个连续字符序列。
     *
     * 现有长度相同的两个字符串 x 和 字符串 y ，在满足 x[i] != y[i] 的第一个位置 i 上，如果  x[i] 在字母表中先于 y[i] 出现，则认为字符串 x 比字符串 y 字典序更小 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/27 22:07
     */
    class Solution_27_1 {
        public String smallestString(String S) {
            char[] s = S.toCharArray();
            int n = s.length;
            for (int i = 0; i < n; i++) {
                if (s[i] > 'a') {
                    // 继续向后遍历
                    for (; i < n && s[i] > 'a'; i++) {
                        s[i]--;
                    }
                    return new String(s);
                }
            }
            // 所有字母均为 a
            s[n - 1] = 'z';
            return new String(s);
        }
    }
    
    /**
     * 2742. 给墙壁刷油漆:
     * 给你两个长度为 n 下标从 0 开始的整数数组 cost 和 time ，分别表示给 n 堵不同的墙刷油漆需要的开销和时间。你有两名油漆匠：
     *
     * 一位需要 付费 的油漆匠，刷第 i 堵墙需要花费 time[i] 单位的时间，开销为 cost[i] 单位的钱。
     * 一位 免费 的油漆匠，刷 任意 一堵墙的时间为 1 单位，开销为 0 。但是必须在付费油漆匠 工作 时，免费油漆匠才会工作。
     * 请你返回刷完 n 堵墙最少开销为多少。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/28 11:16
     */
    class Solution {
        public int paintWalls(int[] cost, int[] time) {
            int n = cost.length;
            int[] f = new int[n + 2];
            Arrays.fill(f, Integer.MAX_VALUE / 2);
            f[0] = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = n + 1; j >= 0; --j) {
                    f[Math.min(j + time[i], n) + 1] = Math.min(f[Math.min(j + time[i], n) + 1], f[j] + cost[i]);
                }
            }
            return Math.min(f[n], f[n + 1]);

        }
    }

    /**
     * 2710. 移除字符串中的尾随零:
     * 给你一个用字符串表示的正整数 num ，请你以字符串形式返回不含尾随零的整数 num 。
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/6/29 16:05
     */
    class Solution_29_1 {
        public String removeTrailingZeros(String num) {
            int len = num.length();
            while(num.charAt(len-1)=='0'){
                len--;
            }
            return num.substring(0,len);
        }
    }
    
}
