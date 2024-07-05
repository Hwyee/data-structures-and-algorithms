package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202407
 * @description
 * @date 2024/7/1
 * @since JDK 1.8
 */
@Slf4j
public class Daily202407 {
    public static void main(String[] args) {
        
    }
    
    /**
     * 2065. 最大化一张图中的路径价值:
     * 给你一张 无向 图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。同时给你一个下标从 0 开始的整数数组 values ，其中 values[i] 是第 i 个节点的 价值 。同时给你一个下标从 0 开始的二维整数数组 edges ，其中 edges[j] = [uj, vj, timej] 表示节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。最后，给你一个整数 maxTime 。
     *
     * 合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。你可以访问一个节点任意次。一条合法路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。
     *
     * 请你返回一条合法路径的 最大 价值。
     *
     * 注意：每个节点 至多 有 四条 边与之相连。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/7/1 16:01
     */
    class Solution_1_1 {

        public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
            int n = values.length;
            List<int[]>[] g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : edges) {
                //无向图 x -> y的时间
                int x = e[0];
                int y = e[1];
                int t = e[2];
                g[x].add(new int[]{y, t});
                g[y].add(new int[]{x, t});
            }

            boolean[] vis = new boolean[n];
            vis[0] = true;
            return dfs(0, 0, values[0], vis, g, values, maxTime);
        }

        private int dfs(int x, int sumTime, int sumValue, boolean[] vis, List<int[]>[] g, int[] values, int maxTime) {
            int res = x == 0 ? sumValue : 0;
            for (int[] e : g[x]) {
                int y = e[0];
                int t = e[1];
                if (sumTime + t > maxTime) {
                    continue;
                }
                if (vis[y]) {
                    res = Math.max(res, dfs(y, sumTime + t, sumValue, vis, g, values, maxTime));
                } else {
                    vis[y] = true;
                    // 每个节点的价值至多算入价值总和中一次
                    res = Math.max(res, dfs(y, sumTime + t, sumValue + values[y], vis, g, values, maxTime));
                    vis[y] = false; // 恢复现场
                }
            }
            return res;
        }


    }

    /**
     * 3115. 质数的最大距离:
     * 给你一个整数数组 nums。
     *
     * 返回两个（不一定不同的）质数在 nums 中 下标 的 最大距离。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/7/2 11:12
     */
    class Solutiond2q1 {
        public int maximumPrimeDifference(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                if (isPrime(nums[left]) && isPrime(nums[right])) {
                    return right - left;
                }
                if (!isPrime(nums[left])) {
                    left++;
                }
                if (!isPrime(nums[right])) {
                    right--;
                }
            }
            return 0;
        }
        private boolean isPrime(int num) {
            if (num < 2) {
                return false;
            }
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 3099. 哈沙德数:
     * 如果一个整数能够被其各个数位上的数字之和整除，则称之为 哈沙德数（Harshad number）。给你一个整数 x 。如果 x 是 哈沙德数 ，则返回 x 各个数位上的数字之和，否则，返回 -1 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/7/3 12:22
     */
    class Solutiond3q1 {
        public int sumOfTheDigitsOfHarshadNumber(int x) {
            int tmp = x;
            int ans = 0;
            while(x/10>0){
                ans+=(x%10);
                x/=10;
            }
            ans+=x;
            return tmp%ans==0?ans:-1;
        }

        public int sumOfTheDigitsOfHarshadNumberGF(int x) {
            int s = 0;
            for (int y = x; y != 0; y /= 10) {
                s += y % 10;
            }
            return x % s != 0 ? -1 : s;
        }


    }


    /**
     * 3086. 拾起 K 个 1 需要的最少行动次数:
     * 给你一个下标从 0 开始的二进制数组 nums，其长度为 n ；另给你一个 正整数 k 以及一个 非负整数 maxChanges 。
     *
     * Alice 在玩一个游戏，游戏的目标是让 Alice 使用 最少 数量的 行动 次数从 nums 中拾起 k 个 1 。游戏开始时，Alice 可以选择数组 [0, n - 1] 范围内的任何索引 aliceIndex 站立。如果 nums[aliceIndex] == 1 ，Alice 会拾起一个 1 ，并且 nums[aliceIndex] 变成0（这 不算 作一次行动）。之后，Alice 可以执行 任意数量 的 行动（包括零次），在每次行动中 Alice 必须 恰好 执行以下动作之一：
     *
     * 选择任意一个下标 j != aliceIndex 且满足 nums[j] == 0 ，然后将 nums[j] 设置为 1 。这个动作最多可以执行 maxChanges 次。
     * 选择任意两个相邻的下标 x 和 y（|x - y| == 1）且满足 nums[x] == 1, nums[y] == 0 ，然后交换它们的值（将 nums[y] = 1 和 nums[x] = 0）。如果 y == aliceIndex，在这次行动后 Alice 拾起一个 1 ，并且 nums[y] 变成 0 。
     * 返回 Alice 拾起 恰好 k 个 1 所需的 最少 行动次数。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/7/5 23:32
     */
    class Solutiond4q1 {
        public long minimumMoves(int[] nums, int k, int maxChanges) {
            List<Integer> pos = new ArrayList<>();
            int c = 0; // nums 中连续的 1 长度
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) continue;
                pos.add(i); // 记录 1 的位置
                c = Math.max(c, 1);
                if (i > 0 && nums[i - 1] == 1) {
                    if (i > 1 && nums[i - 2] == 1) {
                        c = 3; // 有 3 个连续的 1
                    } else {
                        c = Math.max(c, 2); // 有 2 个连续的 1
                    }
                }
            }

            c = Math.min(c, k);
            if (maxChanges >= k - c) {
                // 其余 k-c 个 1 可以全部用两次操作得到
                return Math.max(c - 1, 0) + (k - c) * 2;
            }

            int n = pos.size();
            long[] sum = new long[n + 1];
            for (int i = 0; i < n; i++) {
                sum[i + 1] = sum[i] + pos.get(i);
            }

            long ans = Long.MAX_VALUE;
            // 除了 maxChanges 个数可以用两次操作得到，其余的 1 只能一步步移动到 pos[i]
            int size = k - maxChanges;
            for (int right = size; right <= n; right++) {
                // s1+s2 是 j 在 [left, right) 中的所有 pos[j] 到 index=pos[(left+right)/2] 的距离之和
                int left = right - size;
                int i = left + size / 2;
                long index = pos.get(i);
                long s1 = index * (i - left) - (sum[i] - sum[left]);
                long s2 = sum[right] - sum[i] - index * (right - i);
                ans = Math.min(ans, s1 + s2);
            }
            return ans + maxChanges * 2;
        }


    }

    /**
     * 3033. 修改矩阵:
     * 给你一个下标从 0 开始、大小为 m x n 的整数矩阵 matrix ，新建一个下标从 0 开始、名为 answer 的矩阵。使 answer 与 matrix 相等，接着将其中每个值为 -1 的元素替换为所在列的 最大 元素。
     *
     * 返回矩阵 answer 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/7/5 23:34
     */
    class Solution {
        public int[][] modifiedMatrix(int[][] matrix) {
            int[][] ans = new int [matrix.length][matrix[0].length];

            for (int i = 0; i < matrix[0].length; i++) {
                int max = -1;
                for (int j = 0; j < matrix.length; j++) {
                    max = Math.max(max, matrix[j][i]);
                }
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[j][i] == -1) {
                        ans[j][i] = max;
                    }else{
                        ans[j][i] = matrix[j][i];
                    }
                }
            }
            return ans;
        }
    }
}
