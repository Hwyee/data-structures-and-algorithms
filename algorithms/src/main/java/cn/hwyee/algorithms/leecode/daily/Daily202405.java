package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;

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
     *
     * 现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：
     *
     * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
     * 工资组中的每名工人至少应当得到他们的最低期望工资。
     * 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额 。在实际答案的 10-5 以内的答案将被接受。。
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
     *
     * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
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
            for(int a : salary){
                min = Math.min(min,a);
                max = Math.max(max,a);
                sum+=a;
            }
            return (sum-min-max+0.0)/(salary.length-2);
        }
    }


    /**
     * 1235. 规划兼职工作:
     * 你打算利用空闲时间来做兼职工作赚些零花钱。
     *
     * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
     *
     * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
     *
     * 注意，时间上出现重叠的 2 份工作不能同时进行。
     *
     * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
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
     *
     * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。
     *
     * 如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
     * 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
     * 如果 k == 0 ，将第 i 个数字用 0 替换。
     * 由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
     *
     * 给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/5/4 23:59
     */
    class Solution_5_1 {
        public int[] decrypt(int[] code, int k) {
            int[] ans = new int[code.length];
            if(k>0){
                int sum = 0;
                for (int i = 1; i <= k; i++) {
                    sum += code[i];
                }
                for (int i = 0; i < code.length; i++) {
                    ans[i] = sum;
                    sum -= code[(i+1)%code.length];
                    sum += code[(i+k+1)%code.length];
                }
            }else if (k <0){
                int sum = 0;
                for (int i = code.length-1; i >= code.length+k; i--) {
                    sum += code[i];
                }
                for (int i = 0; i < code.length; i++) {
                    ans[i] = sum;
                    sum += code[i];
                    sum -= code[(i+k+code.length)%code.length];
                }
            }
            return ans;
        }

        /**
         * decryptGF:
         * 滑动窗口
         * 搞了个原数组的两倍容量，使用滑动窗口
         * @author hui
         * @version 1.0
         * @param code
         * @param k
         * @return int[]
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

}

    



