package cn.hwyee.algorithms.leecode.leecode75;

import java.awt.geom.Ellipse2D;
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
     *
     * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。
     * 每一颗小行星以相同的速度移动。
     *
     * 找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。
     * 两颗移动方向相同的小行星，永远不会发生碰撞。
     *
     * @author hui
     * @version 1.0
     * @param asteroids
     * @return int[]
     * @date 2023/10/17 16:25
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> integers = new LinkedList<>();
        Deque<Integer> negetive = new LinkedList<>();
        for (int asteroid : asteroids) {
            if (asteroid>0){
                integers.add(asteroid);
            }else{
                if (integers.size()!=0){
                    int right = integers.poll();
                    int left = Math.abs(asteroid);
                    if (right>left){
                        integers.add(right);
                    }else if (right<left){
                        negetive.add(asteroid);
                        while(!integers.isEmpty()){
                            if (integers.peek()<left){
                                integers.poll();
                            }else if(integers.peek()==left){
                                integers.poll();
                                negetive.poll();
                                break;
                            }else{
                                negetive.poll();
                                break;
                            }
                        }
                    }
                }
            }
        }
        int[] ints = new int[negetive.size() + integers.size()];
        int i = 0;
        while (i<ints.length){
            if (negetive.size()!=0){
                negetive.pollLast();
            }
        }
        return ints;
    }

    public static void main(String[] args) {
        String s = "leet**cod*e";
        System.out.println(removeStars(s));
    }
}
