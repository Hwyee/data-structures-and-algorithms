package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202404
 * @description leetcode 每日
 * @date 2024/4/1
 * @since JDK 1.8
 */
@Slf4j
public class Daily202404 {
    public static void main(String[] args) {
        Solution_1_1 solution11 = new Solution_1_1();
        solution11.finalString("poiinter");
    }

    /**
     * 2810. 故障键盘:
     * 你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。
     * 给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。
     * 返回最终笔记本屏幕上输出的字符串。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/1 23:39
     */
    static class Solution_1_1 {
        public String finalString(String s) {
            char[] charArray = s.toCharArray();
            int j = -1;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] =='i'){
                    s = reverse(s,i+j);
                    j--;
                }
            }
            return s;
        }

        public String reverse(String s, int end) {
            char[] charArray = s.toCharArray();
            String substring = s.substring(end + 2);
            for (int i = end; i > end/2; i--) {
                char temp = charArray[i];
                charArray[i] = charArray[end-i];
                charArray[end-i] = temp;
            }
            String s1 = new String(charArray, 0, end + 1);
            return s1+substring;
        }
    }

    public String finalStringGF(String s) {
        Deque<Character> q = new ArrayDeque<Character>();
        boolean head = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != 'i') {
                if (head) {
                    q.offerFirst(ch);
                } else {
                    q.offerLast(ch);
                }
            } else {
                head = !head;
            }
        }
        StringBuilder ans = new StringBuilder();
        if (head) {
            while (!q.isEmpty()) {
                ans.append(q.pollLast());
            }
        } else {
            while (!q.isEmpty()) {
                ans.append(q.pollFirst());
            }
        }
        return ans.toString();
    }



}
