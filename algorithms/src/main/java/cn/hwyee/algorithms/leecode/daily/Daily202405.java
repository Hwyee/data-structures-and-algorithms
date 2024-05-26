package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;


/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202405
 * @description
 * @date 2024/5/1
 * @since JDK 1.8
 */
@Slf4j
public class Daily202405 {
    public static void main(String[] args) {
        Solution_1_1 solution11 = new Solution_1_1();
        solution11.totalCost(new int[]{31, 25, 72, 79, 74, 65, 84, 91, 18, 59, 27, 9, 81, 33, 17, 58}, 11, 2);
        Solution_12_1 solution121 = new Solution_12_1();
        System.out.println(solution121.minDays(429));
    }

    /**
     * 2462. 雇佣 K 位工人的总代价:
     * 给你一个下标从 0 开始的整数数组 costs ，其中 costs[i] 是雇佣第 i 位工人的代价。
     * <p>
     * 同时给你两个整数 k 和 candidates 。我们想根据以下规则恰好雇佣 k 位工人：
     * <p>
     * 总共进行 k 轮雇佣，且每一轮恰好雇佣一位工人。
     * 在每一轮雇佣中，从最前面 candidates 和最后面 candidates 人中选出代价最小的一位工人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
     * 比方说，costs = [3,2,7,7,1,2] 且 candidates = 2 ，第一轮雇佣中，我们选择第 4 位工人，因为他的代价最小 [3,2,7,7,1,2] 。
     * 第二轮雇佣，我们选择第 1 位工人，因为他们的代价与第 4 位工人一样都是最小代价，而且下标更小，[3,2,7,7,2] 。注意每一轮雇佣后，剩余工人的下标可能会发生变化。
     * 如果剩余员工数目不足 candidates 人，那么下一轮雇佣他们中代价最小的一人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
     * 一位工人只能被选择一次。
     * 返回雇佣恰好 k 位工人的总代价。
     * <p>
     * 超时
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/1 0:17
     */
    static class Solution_1_1 {
        int size = 0;

        public long totalCost(int[] costs, int k, int candidates) {
            size = costs.length;
            int ans = 0;
            while (k > 0) {
                int min = Integer.MAX_VALUE;
                int i = 0;
                if (size > candidates) {
                    int a = 1;
                    int b = candidates;
                    while (a <= candidates || b > 0) {
                        if (a <= candidates) {
                            if (costs[size - a] <= min) {
                                min = costs[size - a];
                                i = size - a;
                            }
                            a++;
                        } else {
                            if (costs[b - 1] <= min) {
                                min = costs[b - 1];
                                i = b - 1;
                            }
                            b--;
                        }
                    }
                } else {
                    for (int j = 0; j < size; j++) {
                        int ele = costs[j];
                        if (ele < min) {
                            min = ele;
                            i = j;
                        }
                    }
                }
                remove(costs, i);
                ans += min;
                k--;
            }
            return ans;
        }

        public boolean remove(int[] es, int i) {
            int newSize = 0;
            if ((newSize = size - 1) > i)
                System.arraycopy(es, i + 1, es, i, newSize - i);
            size--;


            return true;
        }


        public long totalCostLS(int[] costs, int k, int candidates) {
            int n = costs.length;
            long ans = 0;
            if (candidates * 2 + k > n) {
                Arrays.sort(costs);
                for (int i = 0; i < k; i++) {
                    ans += costs[i];
                }
                return ans;
            }

            PriorityQueue<Integer> pre = new PriorityQueue<>();
            PriorityQueue<Integer> suf = new PriorityQueue<>();
            for (int i = 0; i < candidates; i++) {
                pre.offer(costs[i]);
                suf.offer(costs[n - 1 - i]);
            }
            int i = candidates, j = n - 1 - candidates;
            while (k-- > 0) {
                if (pre.peek() <= suf.peek()) {
                    ans += pre.poll();
                    if (i <= j) {
                        pre.offer(costs[i++]);
                    }
                } else {
                    ans += suf.poll();
                    if (i <= j) {
                        suf.offer(costs[j--]);
                    }
                }
            }
            return ans;
        }


    }

    /**
     * 857. 雇佣 K 名工人的最低成本:
     * 有 n 名工人。 给定两个数组 quality 和 wage ，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i] 。
     * <p>
     * 现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：
     * <p>
     * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
     * 工资组中的每名工人至少应当得到他们的最低期望工资。
     * 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额 。在实际答案的 10-5 以内的答案将被接受。。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/2 1:05
     */
    class Solution {
        public double mincostToHireWorkersLS(int[] quality, int[] wage, int k) {
            int n = quality.length, sumQ = 0;
            var id = IntStream.range(0, n).boxed().toArray(Integer[]::new);
            Arrays.sort(id, (i, j) -> wage[i] * quality[j] - wage[j] * quality[i]); // 按照 r 值排序
            var pq = new PriorityQueue<Integer>((a, b) -> b - a); // 最大堆
            for (var i = 0; i < k; ++i) {
                pq.offer(quality[id[i]]);
                sumQ += quality[id[i]];
            }
            var ans = sumQ * ((double) wage[id[k - 1]] / quality[id[k - 1]]); // 选 r 值最小的 k 名工人组成当前的最优解
            for (var i = k; i < n; ++i) {
                var q = quality[id[i]];
                if (q < pq.peek()) { // sumQ 可以变小，从而可能得到更优的答案
                    sumQ -= pq.poll() - q;
                    pq.offer(q);
                    ans = Math.min(ans, sumQ * ((double) wage[id[i]] / q));
                }
            }
            return ans;
        }

        public double mincostToHireWorkersGF(int[] quality, int[] wage, int k) {
            int n = quality.length;
            Integer[] h = new Integer[n];
            for (int i = 0; i < n; i++) {
                h[i] = i;
            }
            Arrays.sort(h, (a, b) -> {
                return quality[b] * wage[a] - quality[a] * wage[b];
            });
            double res = 1e9;
            double totalq = 0.0;
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
            for (int i = 0; i < k - 1; i++) {
                totalq += quality[h[i]];
                pq.offer(quality[h[i]]);
            }
            for (int i = k - 1; i < n; i++) {
                int idx = h[i];
                totalq += quality[idx];
                pq.offer(quality[idx]);
                double totalc = ((double) wage[idx] / quality[idx]) * totalq;
                res = Math.min(res, totalc);
                totalq -= pq.poll();
            }
            return res;
        }

    }

    /**
     * 1491. 去掉最低工资和最高工资后的工资平均值:
     * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
     * <p>
     * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/4 0:05
     */
    class Solution_3_1 {
        public double average(int[] salary) {
            int min = 10000000;
            int max = 0;
            int sum = 0;
            for (int a : salary) {
                min = Math.min(min, a);
                max = Math.max(max, a);
                sum += a;
            }
            return (sum - min - max + 0.0) / (salary.length - 2);
        }
    }


    /**
     * 1235. 规划兼职工作:
     * 你打算利用空闲时间来做兼职工作赚些零花钱。
     * <p>
     * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
     * <p>
     * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
     * <p>
     * 注意，时间上出现重叠的 2 份工作不能同时进行。
     * <p>
     * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/4 0:03
     */
    class Solution_4_1 {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            return 1;
        }

        public int jobSchedulingLS(int[] startTime, int[] endTime, int[] profit) {
            int n = startTime.length;
            int[][] jobs = new int[n][];
            for (int i = 0; i < n; ++i)
                jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
            Arrays.sort(jobs, (a, b) -> a[1] - b[1]); // 按照结束时间排序

            int[] f = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                int j = search(jobs, i, jobs[i][0]);
                f[i + 1] = Math.max(f[i], f[j + 1] + jobs[i][2]);
            }
            return f[n];
        }

        // 返回 endTime <= upper 的最大下标
        private int search(int[][] jobs, int right, int upper) {
            int left = -1;
            while (left + 1 < right) {
                int mid = (left + right) >>> 1;
                if (jobs[mid][1] <= upper) left = mid;
                else right = mid;
            }
            return left;
        }


    }


    /**
     * 1652. 拆炸弹:
     * 你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
     * <p>
     * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。
     * <p>
     * 如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
     * 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
     * 如果 k == 0 ，将第 i 个数字用 0 替换。
     * 由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
     * <p>
     * 给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/4 23:59
     */
    class Solution_5_1 {
        public int[] decrypt(int[] code, int k) {
            int[] ans = new int[code.length];
            if (k > 0) {
                int sum = 0;
                for (int i = 1; i <= k; i++) {
                    sum += code[i];
                }
                for (int i = 0; i < code.length; i++) {
                    ans[i] = sum;
                    sum -= code[(i + 1) % code.length];
                    sum += code[(i + k + 1) % code.length];
                }
            } else if (k < 0) {
                int sum = 0;
                for (int i = code.length - 1; i >= code.length + k; i--) {
                    sum += code[i];
                }
                for (int i = 0; i < code.length; i++) {
                    ans[i] = sum;
                    sum += code[i];
                    sum -= code[(i + k + code.length) % code.length];
                }
            }
            return ans;
        }

        /**
         * decryptGF:
         * 滑动窗口
         * 搞了个原数组的两倍容量，使用滑动窗口
         *
         * @param code
         * @param k
         * @return int[]
         * @author hui
         * @version 1.0
         * @date 2024/5/5 0:39
         */
        public int[] decryptGF(int[] code, int k) {
            int n = code.length;
            if (k == 0) {
                return new int[n];
            }
            int[] res = new int[n];
            int[] newCode = new int[n * 2];
            System.arraycopy(code, 0, newCode, 0, n);
            System.arraycopy(code, 0, newCode, n, n);
            code = newCode;
            int l = k > 0 ? 1 : n + k;
            int r = k > 0 ? k : n - 1;
            int w = 0;
            for (int i = l; i <= r; i++) {
                w += code[i];
            }
            for (int i = 0; i < n; i++) {
                res[i] = w;
                w -= code[l];
                w += code[r + 1];
                l++;
                r++;
            }
            return res;
        }
    }

    /**
     * 741. 摘樱桃:
     * 给你一个 n x n 的网格 grid ，代表一块樱桃地，每个格子由以下三种数字的一种来表示：
     * <p>
     * 0 表示这个格子是空的，所以你可以穿过它。
     * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
     * -1 表示这个格子里有荆棘，挡着你的路。
     * 请你统计并返回：在遵守下列规则的情况下，能摘到的最多樱桃数：
     * <p>
     * 从位置 (0, 0) 出发，最后到达 (n - 1, n - 1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为 0 或者 1 的格子）；
     * 当到达 (n - 1, n - 1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
     * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为 0 ）；
     * 如果在 (0, 0) 和 (n - 1, n - 1) 之间不存在一条可经过的路径，则无法摘到任何一个樱桃。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/6 1:05
     */
    class Solution_6_1 {
        public int cherryPickupGF(int[][] grid) {
            int n = grid.length;
            int[][] f = new int[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(f[i], Integer.MIN_VALUE);
            }
            f[0][0] = grid[0][0];
            for (int k = 1; k < n * 2 - 1; ++k) {
                for (int x1 = Math.min(k, n - 1); x1 >= Math.max(k - n + 1, 0); --x1) {
                    for (int x2 = Math.min(k, n - 1); x2 >= x1; --x2) {
                        int y1 = k - x1, y2 = k - x2;
                        if (grid[x1][y1] == -1 || grid[x2][y2] == -1) {
                            f[x1][x2] = Integer.MIN_VALUE;
                            continue;
                        }
                        int res = f[x1][x2]; // 都往右
                        if (x1 > 0) {
                            res = Math.max(res, f[x1 - 1][x2]); // 往下，往右
                        }
                        if (x2 > 0) {
                            res = Math.max(res, f[x1][x2 - 1]); // 往右，往下
                        }
                        if (x1 > 0 && x2 > 0) {
                            res = Math.max(res, f[x1 - 1][x2 - 1]); // 都往下
                        }
                        res += grid[x1][y1];
                        if (x2 != x1) { // 避免重复摘同一个樱桃
                            res += grid[x2][y2];
                        }
                        f[x1][x2] = res;
                    }
                }
            }
            return Math.max(f[n - 1][n - 1], 0);
        }
    }

    /**
     * 1463. 摘樱桃 II:
     * 给你一个 rows x cols 的矩阵 grid 来表示一块樱桃地。 grid 中每个格子的数字表示你能获得的樱桃数目。
     * <p>
     * 你有两个机器人帮你收集樱桃，机器人 1 从左上角格子 (0,0) 出发，机器人 2 从右上角格子 (0, cols-1) 出发。
     * <p>
     * 请你按照如下规则，返回两个机器人能收集的最多樱桃数目：
     * <p>
     * 从格子 (i,j) 出发，机器人可以移动到格子 (i+1, j-1)，(i+1, j) 或者 (i+1, j+1) 。
     * 当一个机器人经过某个格子时，它会把该格子内所有的樱桃都摘走，然后这个位置会变成空格子，即没有樱桃的格子。
     * 当两个机器人同时到达同一个格子时，它们中只有一个可以摘到樱桃。
     * 两个机器人在任意时刻都不能移动到 grid 外面。
     * 两个机器人最后都要到达 grid 最底下一行。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/7 9:36
     */
    class Solution_7_1 {
        public int cherryPickupLS(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] pre = new int[n + 2][n + 2];
            int[][] cur = new int[n + 2][n + 2];
            for (int i = m - 1; i >= 0; i--) {
                //j的最大值为n-1，最后趋于0（i=0时）.
                for (int j = 0; j < Math.min(n, i + 1); j++) {
                    //k的最小值为j+1,因为j和k相同时，只能有一个取到樱桃，最后趋于n-1（i=0时）.
                    for (int k = Math.max(j + 1, n - 1 - i); k < n; k++) {
                        cur[j + 1][k + 1] = max(
                                pre[j][k], pre[j][k + 1], pre[j][k + 2],
                                pre[j + 1][k], pre[j + 1][k + 1], pre[j + 1][k + 2],
                                pre[j + 2][k], pre[j + 2][k + 1], pre[j + 2][k + 2]
                        ) + grid[i][j] + grid[i][k];
                    }
                }
                int[][] tmp = pre;
                pre = cur; // 下一个 i 的 pre 是 cur
                cur = tmp;
            }
            return pre[1][n];
        }

        private int max(int x, int... y) {
            for (int v : y) {
                x = Math.max(x, v);
            }
            return x;
        }

    }

    /**
     * 2079. 给植物浇水:
     * 你打算用一个水罐给花园里的 n 株植物浇水。植物排成一行，从左到右进行标记，编号从 0 到 n - 1 。
     * 其中，第 i 株植物的位置是 x = i 。x = -1 处有一条河，你可以在那里重新灌满你的水罐。
     * <p>
     * 每一株植物都需要浇特定量的水。你将会按下面描述的方式完成浇水：
     * <p>
     * 按从左到右的顺序给植物浇水。
     * 在给当前植物浇完水之后，如果你没有足够的水 完全 浇灌下一株植物，那么你就需要返回河边重新装满水罐。
     * 你 不能 提前重新灌满水罐。
     * 最初，你在河边（也就是，x = -1），在 x 轴上每移动 一个单位 都需要 一步 。
     * <p>
     * 给你一个下标从 0 开始的整数数组 plants ，数组由 n 个整数组成。其中，plants[i] 为第 i 株植物需要的水量。
     * 另有一个整数 capacity 表示水罐的容量，返回浇灌所有植物需要的 步数 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/8 11:47
     */
    class Solution_8_1 {
        public int wateringPlants(int[] plants, int capacity) {
            int ans = 0;
            int cur = capacity;
            for (int i = 0; i < plants.length; i++) {
                int water = plants[i];
                if (cur < water) {
                    cur = capacity - water;
                    ans = ans + i * 2 + 1;
                } else {
                    cur = cur - water;
                    ans = ans + 1;
                }
            }
            return ans;
        }
    }

    /**
     * 2105. 给植物浇水 II:
     * Alice 和 Bob 打算给花园里的 n 株植物浇水。植物排成一行，从左到右进行标记，编号从 0 到 n - 1 。其中，第 i 株植物的位置是 x = i 。
     * <p>
     * 每一株植物都需要浇特定量的水。Alice 和 Bob 每人有一个水罐，最初是满的 。他们按下面描述的方式完成浇水：
     * <p>
     * Alice 按 从左到右 的顺序给植物浇水，从植物 0 开始。Bob 按 从右到左 的顺序给植物浇水，从植物 n - 1 开始。他们 同时 给植物浇水。
     * 无论需要多少水，为每株植物浇水所需的时间都是相同的。
     * 如果 Alice/Bob 水罐中的水足以 完全 灌溉植物，他们 必须 给植物浇水。否则，他们 首先（立即）重新装满罐子，然后给植物浇水。
     * 如果 Alice 和 Bob 到达同一株植物，那么当前水罐中水 更多 的人会给这株植物浇水。如果他俩水量相同，那么 Alice 会给这株植物浇水。
     * 给你一个下标从 0 开始的整数数组 plants ，数组由 n 个整数组成。其中，plants[i] 为第 i 株植物需要的水量。
     * 另有两个整数 capacityA 和 capacityB 分别表示 Alice 和 Bob 水罐的容量。返回两人浇灌所有植物过程中重新灌满水罐的 次数 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/9 15:50
     */
    class Solution_9_1 {
        public int minimumRefill(int[] plants, int capacityA, int capacityB) {
            int ans = 0;
            int i = 0, j = plants.length - 1;
            int ca = capacityA, cb = capacityB;
            while (i <= j) {
                if (i == j) {
                    if (ca < plants[i] && cb < plants[i]) {
                        ans++;
                    }
                } else {
                    if (ca < plants[i]) {
                        ans++;
                        ca = capacityA;
                    }
                    if (cb < plants[j]) {
                        ans++;
                        cb = capacityB;
                    }
                }
                ca -= plants[i];
                cb -= plants[j];
                i++;
                j--;
            }
            return ans;
        }
    }

    /**
     * 2960. 统计已测试设备:
     * 给你一个长度为 n 、下标从 0 开始的整数数组 batteryPercentages ，表示 n 个设备的电池百分比。
     * <p>
     * 你的任务是按照顺序测试每个设备 i，执行以下测试操作：
     * <p>
     * 如果 batteryPercentages[i] 大于 0：
     * 增加 已测试设备的计数。
     * 将下标在 [i + 1, n - 1] 的所有设备的电池百分比减少 1，确保它们的电池百分比 不会低于 0 ，即 batteryPercentages[j] = max(0, batteryPercentages[j] - 1)。
     * 移动到下一个设备。
     * 否则，移动到下一个设备而不执行任何测试。
     * 返回一个整数，表示按顺序执行测试操作后 已测试设备 的数量。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/10 10:35
     */
    class Solution_10_1 {
        public int countTestedDevices(int[] batteryPercentages) {
            int ans = 0;
            for (int a : batteryPercentages) {
                if (a - ans > 0) {
                    ans++;
                }
            }
            return ans;
        }
    }

    /**
     * 2391. 收集垃圾的最少总时间:
     * 给你一个下标从 0 开始的字符串数组 garbage ，其中 garbage[i] 表示第 i 个房子的垃圾集合。garbage[i] 只包含字符 'M' ，'P' 和 'G' ，但可能包含多个相同字符，每个字符分别表示一单位的金属、纸和玻璃。垃圾车收拾 一 单位的任何一种垃圾都需要花费 1 分钟。
     * <p>
     * 同时给你一个下标从 0 开始的整数数组 travel ，其中 travel[i] 是垃圾车从房子 i 行驶到房子 i + 1 需要的分钟数。
     * <p>
     * 城市里总共有三辆垃圾车，分别收拾三种垃圾。每辆垃圾车都从房子 0 出发，按顺序 到达每一栋房子。但它们 不是必须 到达所有的房子。
     * <p>
     * 任何时刻只有 一辆 垃圾车处在使用状态。当一辆垃圾车在行驶或者收拾垃圾的时候，另外两辆车 不能 做任何事情。
     * <p>
     * 请你返回收拾完所有垃圾需要花费的 最少 总分钟数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/11 14:34
     */
    class Solution_11_1 {
        public int garbageCollection(String[] garbage, int[] travel) {
            //总时间= 垃圾车运行时间 + 收垃圾时间
            int garbageCarTime = 0;
            int garbageTime = 0;
            // M 0 P 1 G 2
            int[] carTime = new int[]{-1, -1, -1};
            for (int i = garbage.length - 1; i >= 0; i--) {
                String temp = garbage[i];
                garbageTime += temp.length();
                if (i > 0) {
                    if (carTime[0] == -1 && temp.indexOf('M') != -1) {
                        carTime[0] = garbageCarTime;
                    }
                    if (carTime[1] == -1 && temp.indexOf('P') != -1) {
                        carTime[1] = garbageCarTime;
                    }
                    if (carTime[2] == -1 && temp.indexOf('G') != -1) {
                        carTime[2] = garbageCarTime;
                    }
                    garbageCarTime += travel[i - 1];
                }
            }
            int ans = 0;
            for (int i : carTime) {
                if (i != -1) {
                    ans += (garbageCarTime - i);
                }
            }
            return ans + garbageTime;
        }
    }

    /**
     * 1553. 吃掉 N 个橘子的最少天数:
     * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
     * <p>
     * 吃掉一个橘子。
     * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
     * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
     * 每天你只能从以上 3 种方案中选择一种方案。
     * <p>
     * 请你返回吃掉所有 n 个橘子的最少天数。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/12 11:23
     */
    static class Solution_12_1 {
        public int minDays(int n) {
            int ans = 0;
            if ((n & n - 1) == 0) {
                while (n > 0) {
                    if (n > 1) {
                        n >>= 1;
                    } else {
                        n--;
                    }
                    ans++;
                }
                return ans;
            }
            int temp = n;
            while (n > 0) {
                System.out.println(n);
                if ((n > 9) && (n - 1) % 9 == 0) {
                    n--;
                } else {
                    if (n % 3 == 0) {
                        n /= 3;
                    } else if (n % 2 == 0) {
                        n /= 2;
                    } else {
                        n--;
                    }
                }
                ans++;
            }
            int res = 0;
            while (temp > 0) {
                System.out.println(temp);
                if ((temp - 2) % 3 == 0) {
                    temp -= 2;
                    res++;
                } else {
                    if (temp % 3 == 0) {
                        temp /= 3;
                    } else if (temp % 2 == 0) {
                        temp /= 2;
                    } else {
                        temp--;
                    }
                }
                res++;
            }

            return Math.min(res, ans);
        }

        private static final Map<Integer, Integer> map = new HashMap<>();

        public int minDaysLC(int n) {
            switch (n) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 2;
            }
            Integer v = map.get(n);
            if (v != null) {
                return v;
            }
            v = Math.min((n % 3 + 1) + minDays(n / 3), (n % 2 + 1) + minDays(n / 2));
            map.put(n, v);
            return v;
        }

    }

    /**
     * 994. 腐烂的橘子:
     * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
     * <p>
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
     * <p>
     * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/13 22:35
     */
    class Solution_13_1 {
        public int orangesRotting(int[][] grid) {
            int ans = 0;
            int[][] temp = new int[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                temp[i] = new int[grid[0].length];
                System.arraycopy(grid[i], 0, temp[i], 0, grid[0].length);
            }
            while (true) {
                boolean flag = false; //未感染
                boolean haveFresh = false;
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if (grid[i][j] == 1) {
                            haveFresh = true;
                            if (i > 0 && grid[i - 1][j] == 2) {
                                temp[i][j] = 2;
                                flag = true;
                            }
                            if (i < grid.length - 1 && grid[i + 1][j] == 2) {
                                temp[i][j] = 2;
                                flag = true;
                            }
                            if (j > 0 && grid[i][j - 1] == 2) {
                                temp[i][j] = 2;
                                flag = true;
                            }
                            if (j < grid[0].length - 1 && grid[i][j + 1] == 2) {
                                temp[i][j] = 2;
                                flag = true;
                            }
                        }
                    }
                }
                if (flag) {
                    ans++;
                    for (int i = 0; i < grid.length; i++) {
                        System.arraycopy(temp[i], 0, grid[i], 0, grid[0].length);
                    }
                } else if (haveFresh) {
                    return -1;
                } else {
                    return ans;
                }
            }
        }

    }

    /**
     * 官方解法:
     * 没我的快
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/13 23:11
     */
    class Solution_13_2 {
        int[] dr = new int[]{-1, 0, 1, 0};
        int[] dc = new int[]{0, -1, 0, 1};

        public int orangesRotting(int[][] grid) {
            int R = grid.length, C = grid[0].length;
            Queue<Integer> queue = new ArrayDeque<Integer>();
            Map<Integer, Integer> depth = new HashMap<Integer, Integer>();
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    if (grid[r][c] == 2) {
                        int code = r * C + c;
                        queue.add(code);
                        depth.put(code, 0);
                    }
                }
            }
            int ans = 0;
            //求 腐烂橘子的上下左右是否有新鲜的，有的话腐烂，并且将这个加入到队列中进行遍历，这样最差要进行4mn次遍历
            while (!queue.isEmpty()) {
                int code = queue.remove();
                int r = code / C, c = code % C;
                for (int k = 0; k < 4; ++k) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        int ncode = nr * C + nc;
                        queue.add(ncode);
                        depth.put(ncode, depth.get(code) + 1);
                        ans = depth.get(ncode);
                    }
                }
            }
            for (int[] row : grid) {
                for (int v : row) {
                    if (v == 1) {
                        return -1;
                    }
                }
            }
            return ans;
        }
    }

    /**
     * 2244. 完成所有任务需要的最少轮数:
     * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
     * <p>
     * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/14 22:45
     */
    class Solution_14_1 {
        public int minimumRounds(int[] tasks) {
            Arrays.sort(tasks);
            List<Integer> list = new ArrayList<Integer>();
            int old = tasks[0];
            int c = 0;
            for (int task : tasks) {
                if (task != old) {
                    list.add(c);
                    old = task;
                    c = 1;
                } else {
                    c++;
                }
            }
            list.add(c);
            int ans = 0;
            for (int i = 0; i < list.size(); i++) {
                c = list.get(i);
                if (c == 1) {
                    return -1;
                }
                //from 灵神.
                ans += (c + 2) / 3;
            }
            return ans;
        }
    }

    /**
     * 2589. 完成所有任务的最少时间:
     * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，
     * 其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
     * <p>
     * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
     * <p>
     * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
     * 2 3 1
     * 4 5 1
     * 1 5 2
     * <p>
     * 1 3 2
     * 2 5 3
     * 5 6 2
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/15 11:17
     */
    class Solution_15_1 {
        public int findMinimumTime(int[][] tasks) {
            //
            return 0;
        }

        public int findMinimumTimeGFGreed(int[][] tasks) {
            int n = tasks.length;
            Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
            int[] run = new int[tasks[n - 1][1] + 1];
            int res = 0;
            for (int i = 0; i < n; i++) {
                int start = tasks[i][0], end = tasks[i][1], duration = tasks[i][2];
                for (int j = start; j <= end; j++) {
                    duration -= run[j];
                }
                res += Math.max(duration, 0);
                for (int j = end; j >= 0 && duration > 0; j--) {
                    if (run[j] == 0) {
                        duration--;
                        run[j] = 1;
                    }
                }
            }
            return res;
        }

    }

    /**
     * 1953. 你可以工作的最大周数:
     * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
     * <p>
     * 你可以按下面两个规则参与项目中的工作：
     * <p>
     * 每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
     * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
     * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。
     * 注意，由于这些条件的限制，你可能无法完成所有阶段任务。
     * <p>
     * 返回在不违反上面规则的情况下你 最多 能工作多少周。
     *
     * 项目数的最大值 <= 剩余项目的最大值 + 1就可以完成所有工作
     * longest≤rest+1.
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/16 20:34
     */
    class Solution_16_1 {
        public long numberOfWeeks(int[] milestones) {

            long sum = 0;
            int max = 0;
            for (int i = 0; i < milestones.length; i++) {

                max = Math.max(max, milestones[i]);
                sum += milestones[i];
            }
            long res = sum - max;
            if (max <= res + 1) {
                return sum;
            } else {
                return res * 2 + 1;
            }

        }
    }

    /**
     * 826. 安排工作以达到最大收益:
     * 你有 n 个工作和 m 个工人。给定三个数组： difficulty, profit 和 worker ，其中:
     *
     * difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
     * worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
     * 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
     *
     * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。如果一个工人不能完成任何工作，他的收益为 $0 。
     * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/17 23:04
     */
    class Solution_17_1 {
        int a = 1000_000;
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
            int ans = 0;
            for (int i = 0; i < worker.length; i++) {
                int money = 0;
                for (int j = 0; j < difficulty.length; j++) {
                    if (worker[i] >= difficulty[j] && money <= profit[j]) {
                        money = profit[j];
                    }
                }

                ans+=money;
            }
            return ans;
        }

        public int maxProfitAssignmentLS(int[] difficulty, int[] profit, int[] worker) {
            int n = difficulty.length;
            int[][] jobs = new int[n][2];
            for (int i = 0; i < n; i++) {
                jobs[i][0] = difficulty[i];
                jobs[i][1] = profit[i];
            }
            Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
            Arrays.sort(worker);
            int ans = 0, j = 0, maxProfit = 0;
            for (int w : worker) {
                while (j < n && jobs[j][0] <= w) {
                    maxProfit = Math.max(maxProfit, jobs[j++][1]);
                }
                ans += maxProfit;
            }
            return ans;
        }



    }

    /**
     * 2644. 找出可整除性得分最大的整数:
     * 给你两个下标从 0 开始的整数数组 nums 和 divisors 。
     *
     * divisors[i] 的 可整除性得分 等于满足 nums[j] 能被 divisors[i] 整除的下标 j 的数量。
     *
     * 返回 可整除性得分 最大的整数 divisors[i] 。如果有多个整数具有最大得分，则返回数值最小的一个。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/18 11:38
     */
    class Solution_18_1 {
        public int maxDivScore(int[] nums, int[] divisors) {
            int ans = 0;
            int max = 0;
            int min = Integer.MAX_VALUE;
            for (int divisor : divisors) {
                if (divisor < min){
                    min = divisor;
                }
                int temp = 0;
                for (int num : nums) {
                    if (num % divisor == 0) {
                        temp++;
                    }
                }
                if (temp > max) {
                    max = temp;
                    ans = divisor;
                }
                if (temp == max) {
                    ans = Math.min(ans, divisor);
                }
            }
            return ans==0? min : ans;
        }
    }

    /**
     * 1535. 找出数组游戏的赢家:
     * 给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
     *
     * 每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，
     * 较大的整数将会取得这一回合的胜利并保留在位置 0 ，较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
     *
     * 返回赢得比赛的整数。
     *
     * 题目数据 保证 游戏存在赢家。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/19 9:43
     */
    class Solution_19_1 {
        public int getWinner(int[] arr, int k) {
            int ans = 0;
            int len = arr.length;
            int i = 1;
            while(true){
                if(arr[0]>arr[i]){
                    ans++;
                    i++;
//                    int temp = arr[i];
//                    System.arraycopy(arr, 2, arr, 1, len-2);
//                    arr[len-1] = temp;
                }else{
                    ans=1;
                    int temp = arr[0];
                    arr[0] = arr[1];
                    arr[i] = temp;
                    i++;
                }
                if (i == len){
                    i=1;
                }
                if (ans==k || ans>=len-1){
                    return arr[0];
                }
            }
        }
    }

    /**
     * 1542. 找出最长的超赞子字符串:
     * 给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
     *
     * 「超赞子字符串」需满足满足下述两个条件：
     *
     * 该字符串是 s 的一个非空子字符串
     * 进行任意次数的字符交换后，该字符串可以变成一个回文字符串
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/20 20:26
     */
    class Solution_20_1 {
        public int longestAwesomeGF(String s) {
            int n = s.length();
            Map<Integer, Integer> prefix = new HashMap<Integer, Integer>();
            prefix.put(0, -1);
            int ans = 0;
            int sequence = 0;
            for (int j = 0; j < n; ++j) {
                int digit = s.charAt(j) - '0';
                sequence ^= (1 << digit);
                if (prefix.containsKey(sequence)) {
                    ans = Math.max(ans, j - prefix.get(sequence));
                } else {
                    prefix.put(sequence, j);
                }
                for (int k = 0; k < 10; ++k) {
                    if (prefix.containsKey(sequence ^ (1 << k))) {
                        ans = Math.max(ans, j - prefix.get(sequence ^ (1 << k)));
                    }
                }
            }
            return ans;
        }
    }

    /**
     * 2769. 找出最大的可达成数字:
     * 给你两个整数 num 和 t 。
     *
     * 如果整数 x 可以在执行下述操作不超过 t 次的情况下变为与 num 相等，则称其为 可达成数字 ：
     *
     * 每次操作将 x 的值增加或减少 1 ，同时可以选择将 num 的值增加或减少 1 。
     * 返回所有可达成数字中的最大值。可以证明至少存在一个可达成数字。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/21 9:22
     */
    class Solution_21_1 {
        public int theMaximumAchievableX(int num, int t) {
            return num+(t<<1);
        }
    }

    /**
     * 2225. 找出输掉零场或一场比赛的玩家:
     * 给你一个整数数组 matches 其中 matches[i] = [winneri, loseri] 表示在一场比赛中 winneri 击败了 loseri 。
     *
     * 返回一个长度为 2 的列表 answer ：
     *
     * answer[0] 是所有 没有 输掉任何比赛的玩家列表。
     * answer[1] 是所有恰好输掉 一场 比赛的玩家列表。
     * 两个列表中的值都应该按 递增 顺序返回。
     *
     * 注意：
     *
     * 只考虑那些参与 至少一场 比赛的玩家。
     * 生成的测试用例保证 不存在 两场比赛结果 相同 。
     * 超时
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/22 16:14
     */
    class Solution_22_1 {
        public List<List<Integer>> findWinners(int[][] matches) {
            List<List<Integer>> ans = new ArrayList<>();
            ArrayList<Integer> win = new ArrayList<>();
            ArrayList<Integer> loseone = new ArrayList<>();
            PriorityQueue<Integer> owin = new PriorityQueue<>();
            PriorityQueue<Integer> oloseone = new PriorityQueue<>();
            for (int[] match : matches) {
                owin.add(match[0]);
                oloseone.add(match[1]);
            }
            while (!owin.isEmpty()){
                int temp = owin.poll();
                if (!oloseone.contains(temp) || !win.contains(temp)){
                    win.add(temp);
                }
            }
            int pre = oloseone.poll();
            int time = 1;
            while (!oloseone.isEmpty()){
                int temp = oloseone.poll();
                if (pre==temp){
                    time++;
                }else {
                    if (time==1){
                        loseone.add(pre);
                    }
                    pre=temp;
                    time=1;
                }
            }
            if (time==1){
                loseone.add(pre);
            }
            ans.add(win);
            ans.add(loseone);
            return ans;
        }

        public List<List<Integer>> findWinnersGF(int[][] matches) {
            Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
            for (int[] match : matches) {
                int winner = match[0], loser = match[1];
                freq.putIfAbsent(winner, 0);
                freq.put(loser, freq.getOrDefault(loser, 0) + 1);
            }

            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            for (int i = 0; i < 2; ++i) {
                ans.add(new ArrayList<Integer>());
            }
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                int key = entry.getKey(), value = entry.getValue();
                if (value < 2) {
                    ans.get(value).add(key);
                }
            }

            Collections.sort(ans.get(0));
            Collections.sort(ans.get(1));
            return ans;
        }

    }



    /**
     * 2831. 找出最长等值子数组:
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
     *
     * 如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。注意，空数组是 等值子数组 。
     *
     * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
     *
     * 子数组 是数组中一个连续且可能为空的元素序列。
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/23 16:08
     */
    class Solution_23_1 {
        public int longestEqualSubarrayLS(List<Integer> nums, int k) {
            int n = nums.size();
            List<Integer>[] posLists = new ArrayList[n + 1];
            Arrays.setAll(posLists, i -> new ArrayList<>());
            for (int i = 0; i < n; i++) {
                int x = nums.get(i);
                posLists[x].add(i - posLists[x].size());
            }

            int ans = 0;
            for (List<Integer> pos : posLists) {
                if (pos.size() <= ans) {
                    continue; // 无法让 ans 变得更大
                }
                int left = 0;
                for (int right = 0; right < pos.size(); right++) {
                    while (pos.get(right) - pos.get(left) > k) { // 要删除的数太多了
                        left++;
                    }
                    ans = Math.max(ans, right - left + 1);
                }
            }
            return ans;
        }
    }

    /**
     * 1673. 找出最具竞争力的子序列:
     * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
     *
     * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
     *
     * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，
     * 那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5] 更具竞争力，
     * 在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/5/24 14:13
     */
    class Solution_24_1 {
        public int[] mostCompetitive(int[] nums, int k) {
            int n = nums.length;
            int[] st = new int[k];
            int m = 0; // 栈的大小
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                while (m > 0 && x < st[m - 1] && m + n - i > k) {
                    m--;
                }
                if (m < k) {
                    st[m++] = x;
                }
            }
            return st;
        }
    }
    
    
    /**
     * 2903. 找出满足差值条件的下标 I:
     * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，以及整数 indexDifference 和整数 valueDifference 。
     *
     * 你的任务是从范围 [0, n - 1] 内找出  2 个满足下述所有条件的下标 i 和 j ：
     *
     * abs(i - j) >= indexDifference 且
     * abs(nums[i] - nums[j]) >= valueDifference
     * 返回整数数组 answer。如果存在满足题目要求的两个下标，则 answer = [i, j] ；否则，answer = [-1, -1] 。
     * 如果存在多组可供选择的下标对，只需要返回其中任意一组即可。
     *
     * 注意：i 和 j 可能 相等 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/25 15:58
     */
    class Solution_25_1 {
        public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
            int right = indexDifference;
            for (int i = 0; i < nums.length-indexDifference; i++) {
                for (int j = right; j < nums.length; j++) {
                    if (Math.abs(nums[i] - nums[j]) >= valueDifference){
                        return new int[]{i,j};
                    }
                }
                right++;
            }
            return new int[]{-1,-1};
        }

        /**
         * findIndicesGF:
         * 官方一次遍历，记录left的最大值和最小值
         * @author hui
         * @version 1.0
         * @param nums
         * @param indexDifference
         * @param valueDifference
         * @return int[]
         * @date 2024/5/25 16:07
         */
        public int[] findIndicesGF(int[] nums, int indexDifference, int valueDifference) {
            int minIndex = 0, maxIndex = 0;
            for (int j = indexDifference; j < nums.length; j++) {
                int i = j - indexDifference;
                if (nums[i] < nums[minIndex]) {
                    minIndex = i;
                }
                if (nums[j] - nums[minIndex] >= valueDifference) {
                    return new int[]{minIndex, j};
                }
                if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
                if (nums[maxIndex] - nums[j] >= valueDifference) {
                    return new int[]{maxIndex, j};
                }
            }
            return new int[]{-1, -1};
        }

    }

    
    /**
     * 1738. 找出第 K 大的异或坐标值:
     * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
     *
     * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
     *
     * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/26 18:02
     */
    class Solution_26_1 {
        public int kthLargestValue(int[][] matrix, int k) {
            PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> {return o2-o1;});
            int pre = 0;
            int[] prearr = new int[matrix[0].length];
            for(int i = 0;i<matrix.length;i++){
                for(int j = 0;j<matrix[0].length;j++){
                    pre = matrix[i][j] ^ pre;
                    prearr[j] = pre ^ prearr[j];
                    queue.add(prearr[j]);
                }
                pre = 0;
            }
            for(int i = 0;i<k-1;i++){
                queue.poll();
            }
            return queue.poll();
        }

        /**
         * kthLargestValueARR:
         * 用数组比用堆块很多。。。
         * @author hui
         * @version 1.0
         * @param matrix
         * @param k
         * @return int
         * @date 2024/5/26 18:10
         */
        public int kthLargestValueARR(int[][] matrix, int k) {
            // PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> {return o2-o1;});
            int[] ans = new int[matrix.length*matrix[0].length];
            int pre = 0;
            int[] prearr = new int[matrix[0].length];
            int ai = 0;
            for(int i = 0;i<matrix.length;i++){
                for(int j = 0;j<matrix[0].length;j++,ai++){
                    pre = matrix[i][j] ^ pre;
                    prearr[j] = pre ^ prearr[j];
                    // queue.add(prearr[j]);
                    ans[ai] = prearr[j];
                }
                pre = 0;
            }
            // for(int i = 0;i<k-1;i++){
            //     queue.poll();
            // }
            Arrays.sort(ans);
            // return queue.poll();
            return ans[ans.length-k];
        }
    }
    
    
}

    



