package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Arrays;
import java.util.PriorityQueue;
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
            int[] carTime = new int[]{-1,-1,-1};
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
                if (i!=-1){
                    ans += (garbageCarTime-i);
                }
            }
            return ans+garbageTime;
        }
    }

}

    



