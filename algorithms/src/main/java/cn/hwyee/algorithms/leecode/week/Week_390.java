package cn.hwyee.algorithms.leecode.week;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Week_390
 * @description 周赛390
 * @date 2024/3/24
 * @since JDK 1.8
 */
@Slf4j
public class Week_390 {
    public static void main(String[] args) {
        Solution_1 solution1 = new Solution_1();
        solution1.maximumLengthSubstring("bcbbbcba");
    }

    /**
     * 100245. 每个字符最多出现两次的最长子字符串:
     * 给你一个字符串 s ，请找出满足每个字符最多出现两次的最长子字符串，并返回该子字符串的 最大 长度
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/24 11:08
     */
   static class Solution_1 {
        public int maximumLengthSubstring(String s) {
            HashMap<Character,Integer> map = new HashMap<>();
            int[] chars ;
            int res = 0;
            char[] charArr = s.toCharArray();
            for(int i = 0;i< charArr.length;i++){
                chars =  new int[26];
                for (int j = i; j < charArr.length; j++) {
                    char c = charArr[j];
                    if (chars[c-'a'] == 2){
                        res = Math.max(res, s.substring(i, j).length());
                        break;
                    }
                    if (j == charArr.length-1){
                        res = Math.max(res, s.substring(i).length());
                    }
                    chars[c-'a']++;
                }
            }
            return res;
        }
    }
}
