package cn.hwyee.algorithms.leecode.leecode75;

import cn.hwyee.algorithms.interview.ODInterview;

import java.awt.geom.Ellipse2D;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author hui
 * @version 1.0
 * @className Stack
 * @description 栈
 * @date 2023/10/17
 * @since JDK 1.8
 */
public class LeetCode75_Stack {

    /**
     * removeStars: 2390. 从字符串中移除星号
     * 给你一个包含若干星号 * 的字符串 s 。
     * <p>
     * 在一步操作中，你可以：
     * <p>
     * 选中 s 中的一个星号。
     * 移除星号 左侧 最近的那个 非星号 字符，并移除该星号自身。
     * 返回移除 所有 星号之后的字符串。
     * <p>
     * 注意：
     * <p>
     * 生成的输入保证总是可以执行题面中描述的操作。
     * 可以证明结果字符串是唯一的。
     * 时间
     * 226ms
     * 击败 22.10%使用 Java 的用户
     * 内存
     * 42.62MB
     * 击败 55.57%使用 Java 的用户
     *
     * @param s
     * @return java.lang.String
     * @author hui
     * @version 1.0
     * @date 2023/10/17 9:18
     */
    public static String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == '*') {
                if (stack.size() != 0) {
                    stack.pop();
                }
            } else {
                stack.add(c);
            }
        }
        int size = stack.size();
        for (int i = size - 1; i >= 0; i--) {
            charArray[i] = stack.pop();
        }

        return new String(charArray, 0, size);
    }

    /**
     * asteroidCollision: 735. 小行星碰撞
     * 给定一个整数数组 asteroids，表示在同一行的小行星。
     * <p>
     * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。
     * 每一颗小行星以相同的速度移动。
     * <p>
     * 找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。
     * 两颗移动方向相同的小行星，永远不会发生碰撞。
     * <p>
     * 时间
     * 2ms
     * 击败 94.23%使用 Java 的用户
     * 内存
     * 42.29MB
     * 击败 24.31%使用 Java 的用户
     *
     * @param asteroids
     * @return int[]
     * @author hui
     * @version 1.0
     * @date 2023/10/17 16:25
     */
    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> integers = new LinkedList<>();
        Deque<Integer> negetive = new LinkedList<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                integers.add(asteroid);
            } else {
                if (!integers.isEmpty()) {
                    int right = integers.peekLast();
                    int left = Math.abs(asteroid);
                    if (right == left) {
                        integers.pollLast();
                    } else if (right < left) {
                        negetive.add(asteroid);
                        while (!integers.isEmpty()) {
                            if (integers.peekLast() < left) {
                                integers.pollLast();
                            } else if (integers.peek() == left) {
                                integers.pollLast();
                                negetive.pollLast();
                                break;
                            } else {
                                negetive.pollLast();
                                break;
                            }
                        }
                    }
                } else {
                    negetive.add(asteroid);
                }
            }
        }
        int[] ints = new int[negetive.size() + integers.size()];
        int i = 0;
        while (i < ints.length) {
            if (negetive.size() != 0) {
                ints[i] = negetive.poll();
            } else {
                ints[i] = integers.poll();
            }
            i++;
        }
        return ints;
    }

    /**
     * asteroidCollisionGF:
     * 时间
     * 2ms
     * 击败 94.23%使用 Java 的用户
     * 内存
     * 41.43MB
     * 击败 99.24%使用 Java 的用户
     *
     * @param asteroids
     * @return int[]
     * @author hui
     * @version 1.0
     * @date 2023/10/18 0:06
     */
    public int[] asteroidCollisionGF(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int aster : asteroids) {
            boolean alive = true;
            while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
                alive = stack.peek() < -aster; // aster 是否存在
                if (stack.peek() <= -aster) {  // 栈顶行星爆炸
                    stack.pop();
                }
            }
            if (alive) {
                stack.push(aster);
            }
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;

    }

    /**
     * decodeString:
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * <p>
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * <p>
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * <p>
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * @param s
     * @return java.lang.String
     * @author hui
     * @version 1.0
     * @date 2023/10/18 0:08
     */
    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        StringBuilder temp = new StringBuilder();

        for (char c : charArray) {
            if (c == ']'){
                while (1){
                    Character pop = stack.pop();
                    if (pop == '['){
                        break;
                    }else {
                        temp.append(pop);
                    }
                }
            }else {
                stack.add(c);
            }
        }
        return "";
    }

    public static String decodeV1(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        char[] charArray = s.toCharArray();
        //是否在解码中（中括号）
        String num = "";
        boolean isDeCoding = false;
        for (char c : charArray) {
            if (isNum(c)) {
                num += c;
            } else if (isDeCoding) {
                if (c == ']') {
                    isDeCoding = false;
                    Integer integer = Integer.valueOf(num);
                    for (int i = 0; i < integer; i++) {
                        sb.append(temp);
                    }
                    temp.setLength(0);
                    num = "";
                } else {
                    temp.append(c);
                }
            } else {
                if (c == '[') {
                    isDeCoding = true;
                } else {
                    sb.append(c);
                }
            }

        }
        return sb.toString();
    }

    public static boolean isNum(char c) {
        return (c >= '0' && c <= '9');
    }

    public static void main(String[] args) {
        String s = "leet**cod*e";
        System.out.println(removeStars(s));
        int[] i = {10, 2, -5};
        for (int i1 : asteroidCollision(i)) {
            System.out.println(i1);
        }
    }
}
