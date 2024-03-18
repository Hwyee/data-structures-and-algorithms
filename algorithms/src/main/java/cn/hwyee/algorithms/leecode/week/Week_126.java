package cn.hwyee.algorithms.leecode.week;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Week_126
 * @description 126双周考
 * @date 2024/3/16
 * @since JDK 1.8
 */
@Slf4j
public class Week_126 {

    /**
     * 100262. 求出加密整数的和:
     * 给你一个整数数组 nums ，数组中的元素都是 正 整数。定义一个加密函数 encrypt ，encrypt(x)
     * 将一个整数 x 中 每一个 数位都用 x 中的 最大 数位替换。比方说 encrypt(523) = 555 且 encrypt(213) = 333 。
     *
     * 请你返回数组中所有元素加密后的 和 。
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/16 23:30
     */
    class Solution_1 {
        public int sumOfEncryptedInt(int[] nums) {
            int[] arr = new int[]{1,11,111,1111};
            int max = 0;
            for(int j = 0;j<nums.length;j++){
                int n = nums[j];
                String s = String.valueOf(n);
                int len = s.length();
                char big = '0';
                for(int i = 0;i<len;i++){
                    if(s.charAt(i)>=big){
                        big=s.charAt(i);
                    }
                }
                max += (Integer.valueOf(String.valueOf(big))*arr[len-1]);
            }
            return max;
        }
    }

    
    /**
     * 100209. 执行操作标记数组中的元素:
     * 给你一个长度为 n 下标从 0 开始的正整数数组 nums 。
     *
     * 同时给你一个长度为 m 的二维操作数组 queries ，其中 queries[i] = [indexi, ki] 。
     *
     * 一开始，数组中的所有元素都 未标记 。
     *
     * 你需要依次对数组执行 m 次操作，第 i 次操作中，你需要执行：
     *
     * 如果下标 indexi 对应的元素还没标记，那么标记这个元素。
     * 然后标记 ki 个数组中还没有标记的 最小 元素。如果有元素的值相等，那么优先标记它们中下标较小的。如果少于 ki 个未标记元素存在，那么将它们全部标记。
     * 请你返回一个长度为 m 的数组 answer ，其中 answer[i]是第 i 次操作后数组中还没标记元素的 和 。
     *
     * 超出时间限制
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/16 23:16
     */
    class Solution_2 {
        public long[] unmarkedSumArray(int[] nums, int[][] queries) {
            long total = 0L;
            PriorityQueue<KeyVal> queue = new PriorityQueue<>((a,b) ->{
                if(a.val==b.val){
                    return a.key - b.key;
                }else{
                    return a.val - b.val;
                }
            });
            for(int p = 0;p<nums.length ;p++){
                int n = nums[p];
                queue.offer(new KeyVal(p,n));
                total += n;
            }
            int len = queries.length;
            //存储下标
            int[] indexarr = new int[nums.length];
            long[] answer = new long[len];
            for(int i =0;i<len;i++){
                int[] cur = queries[i];
                int index = cur[0];
                int k = cur[1];
                if(indexarr[index] == 0){
                    indexarr[index] = 1;
                    total -= nums[index];
                }

                while(k>0 && !queue.isEmpty()){
                    KeyVal poll = queue.poll();
                    if (indexarr[poll.key]==0){
                        indexarr[poll.key]=1;
                        total-=poll.val;
                        k--;
                    }
                }
                answer[i] = total;

            }
            return answer;
        }
        class KeyVal{
            int key;
            int val;

            public KeyVal(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }
}
