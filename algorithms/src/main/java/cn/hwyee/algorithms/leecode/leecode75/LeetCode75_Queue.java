package cn.hwyee.algorithms.leecode.leecode75;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author hui
 * @version 1.0
 * @className LeetCode75_Queue
 * @description 队列
 * @date 2023/10/23
 * @since JDK 1.8
 */
@Slf4j
public class LeetCode75_Queue {

    /**
     * :
     * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
     * <p>
     * 请你实现 RecentCounter 类：
     * <p>
     * RecentCounter() 初始化计数器，请求数为 0 。
     * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。
     * 确切地说，返回在 [t-3000, t] 内发生的请求数。
     * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
     * 时间
     * 26ms
     * 击败 20.53%使用 Java 的用户
     * 内存
     * 52.99MB
     * 击败 10.07%使用 Java 的用户
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/10/23 10:42
     */
    class RecentCounter {
        private ArrayDeque<Integer> arrayDeque;

        public RecentCounter() {
            arrayDeque = new ArrayDeque<Integer>();
        }

        public int ping(int t) {
            if (arrayDeque.isEmpty() || t > arrayDeque.peekLast()) {
                arrayDeque.add(t);
            } else {
                return arrayDeque.size();
            }
            while (!arrayDeque.isEmpty()) {
                int i = t - 3000;
                if (arrayDeque.peek() < i) {
                    arrayDeque.poll();
                } else {
                    return arrayDeque.size();
                }
            }
            return arrayDeque.size();
        }
    }

    /**
     * :
     * 时间
     * 详情
     * 19ms
     * 击败 85.06%使用 Java 的用户
     * 内存
     * 详情
     * 52.78MB
     * 击败 26.40%使用 Java 的用户
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/10/25 17:42
     */
    class RecentCounterGF {
        Queue<Integer> queue;

        public RecentCounterGF() {
            queue = new ArrayDeque<Integer>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }


    /**
     * predictPartyVictory:
     * Dota2 的世界里有两个阵营：Radiant（天辉）和 Dire（夜魇）
     * <p>
     * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的 一 项：
     * <p>
     * 禁止一名参议员的权利：参议员可以让另一位参议员在这一轮和随后的几轮中丧失 所有的权利 。
     * 宣布胜利：如果参议员发现有权利投票的参议员都是 同一个阵营的 ，他可以宣布胜利并决定在游戏中的有关变化。
     * 给你一个字符串 senate 代表每个参议员的阵营。字母 'R' 和 'D'分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
     * <p>
     * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
     * <p>
     * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 "Radiant" 或 "Dire" 。
     * 未解答出来。
     * 未考虑到一次循环可能不足以将一方的参议员全部禁用，可能需要多个循环。
     * @param senate
     * @return java.lang.String
     * @author hui
     * @version 1.0
     * @date 2023/11/7 23:32
     */
    public static String predictPartyVictory(String senate) {
        char[] charArray = senate.toCharArray();
        int r = 0;
        int d = 0;
        int sum = 0;
//        StringBuilder sb = new StringBuilder();

        for (char c : charArray) {
            if (c == 'R') {
                if (r == 0) {
                    d++;
                    sum++;
//                    sb.append("R");
                } else {
                    r--;

                }
            } else {
                if (d == 0) {
                    r++;
                    sum--;
//                    sb.append("D");
                } else {
                    d--;
                }
            }
        }

        if (sum == 0) {
            if (charArray[senate.length() - 1] == 'R') {
                return "Radiant";
            } else {
                return "Dire";
            }
        }
        return sum > 0 ? "Radiant" : "Dire";
    }



    /**
     * predictPartyVictoryGF:
     * 官方题解
     *
     * @author hui
     * @version 1.0
     * @param senate
     * @return java.lang.String
     * @date 2023/11/10 0:26
     */
    public String predictPartyVictoryGF(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<Integer>();
        Queue<Integer> dire = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }




    public static void main(String[] args) {
        String s = predictPartyVictory("DDDDRRDDDRDRDRRDDRDDDRDRRRRDRRRRRDRDDRDDRRDDRRRDDRRRDDDDRRRRRRRDDRRRDDRDDDRRRDRDDRDDDRRDRRDRRRDRDRDR"

        );
        System.out.println(s);
    }

}
