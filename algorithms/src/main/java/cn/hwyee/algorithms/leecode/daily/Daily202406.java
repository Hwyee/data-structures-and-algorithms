package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

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

    }

    /**
     * 2928. 给小朋友们分糖果 I:
     * 给你两个正整数 n 和 limit 。
     *
     * 请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的 总方案数 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/2 13:20
     */
    class Solution_1_1 {
        public int distributeCandies(int n, int limit) {

            if (n <= limit){
                return 3*limit+1;
            }
            else {
                int mod = n%3;

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
     *
     * 医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。
     *
     * 给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/2 13:22
     */
    class Solution_2_1 {
        public int distributeCandies(int[] candyType) {
            Arrays.sort(candyType);
            int ans = 1;
            for (int i = 0; i < candyType.length-1; i++) {
                if (ans == candyType.length/2){
                    return ans;
                }
                if (candyType[i] != candyType[i+1]) {
                    ans++;
                }

            }
            return ans;
        }
    }

}
