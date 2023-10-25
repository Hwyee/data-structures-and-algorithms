package cn.hwyee.algorithms.leecode.leecode75;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
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
            }else {
                return arrayDeque.size();
            }
            while (!arrayDeque.isEmpty()){
                int i = t - 3000;
                if (arrayDeque.peek()<i){
                    arrayDeque.poll();
                }else {
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

}
