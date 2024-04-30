package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.PriorityQueue;


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
}
