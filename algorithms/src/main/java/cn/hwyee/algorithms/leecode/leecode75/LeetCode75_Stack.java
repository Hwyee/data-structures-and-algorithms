package cn.hwyee.algorithms.leecode.leecode75;

import cn.hwyee.algorithms.interview.ODInterview;

import java.awt.geom.Ellipse2D;
import java.util.ArrayDeque;
import java.util.Collections;
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
                while (true){
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

    int ptr;

    /**
     * decodeStringGF:
     * 官方-栈。
     * 时间
     * 1ms
     * 击败 75.84%使用 Java 的用户
     * 内存
     * 38.45MB
     * 击败 71.65%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param s
     * @return java.lang.String
     * @date 2023/10/18 16:46
     */
    public String decodeStringGFStack(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    String src;
    
    /**
     * decodeStringGFRecursion:
     *
     * 我们也可以用递归来解决这个问题，从左向右解析字符串：
     *
     * 如果当前位置为数字位，那么后面一定包含一个用方括号表示的字符串，即属于这种情况：k[...]：
     * 我们可以先解析出一个数字，然后解析到了左括号，递归向下解析后面的内容，遇到对应的右括号就返回，此时我们可以根据解析出的数字 xxx 解析出的括号里的字符串 s′s's
     * ′
     *   构造出一个新的字符串 x×s′x \times s'x×s
     * ′
     *  ；
     * 我们把 k[...] 解析结束后，再次调用递归函数，解析右括号右边的内容。
     * 如果当前位置是字母位，那么我们直接解析当前这个字母，然后递归向下解析这个字母后面的内容。
     * 如果觉得这里讲的比较抽象，可以结合代码理解一下这个过程。
     *
     * 下面我们可以来讲讲这样做的依据，涉及到《编译原理》相关内容，感兴趣的同学可以参考阅读。 根据题目的定义，我们可以推导出这样的巴科斯范式（BNF）：
     *
     * String→Digits [String] String ∣ Alpha String ∣ ϵDigits→Digit Digits ∣ DigitAlpha→a∣⋯∣z∣A∣⋯∣ZDigit→0∣⋯∣9\begin{aligned} {\rm String} &\rightarrow { \rm Digits \, [String] \, String \, | \, Alpha \, String \, | \, \epsilon } \\ {\rm Digits} &\rightarrow { \rm Digit \, Digits \, | \, Digit } \\ {\rm Alpha} &\rightarrow { a | \cdots | z | A | \cdots | Z } \\ {\rm Digit} &\rightarrow { 0 | \cdots | 9 } \\ \end{aligned}
     * String
     * Digits
     * Alpha
     * Digit
     * ​
     *
     * →Digits[String]String∣AlphaString∣ϵ
     * →DigitDigits∣Digit
     * →a∣⋯∣z∣A∣⋯∣Z
     * →0∣⋯∣9
     * ​
     *
     * Digit\rm DigitDigit 表示十进制数位，可能的取值是 000 到 999 之间的整数
     * Alpha\rm AlphaAlpha 表示字母，可能的取值是大小写字母的集合，共 525252 个
     * Digit\rm DigitDigit 表示一个整数，它的组成是 Digit\rm DigitDigit 出现一次或多次
     * String\rm StringString 代表一个代解析的字符串，它可能有三种构成，如 BNF 所示
     * ϵ\rm \epsilonϵ 表示空串，即没有任何子字符
     *
     * 由于 Digits\rm DigitsDigits 和 Alpha\rm AlphaAlpha 构成简单，很容易进行词法分析，我们把它他们看作独立的 TOKEN。那么此时的非终结符有 String\rm StringString，终结符有 Digits\rm DigitsDigits、Alpha\rm AlphaAlpha 和 ϵ\rm \epsilonϵ，我们可以根据非终结符和 FOLLOW 集构造出这样的预测分析表：
     *
     * Alpha\rm AlphaAlpha	Digits\rm DigitsDigits	ϵ\rm \epsilonϵ
     * String\rm StringString	String→Alpha String\rm String \rightarrow Alpha \, StringString→AlphaString	String→Digits [String] String\rm String \rightarrow Digits \, [String] \, StringString→Digits[String]String	String→ϵ\rm String \rightarrow \epsilonString→ϵ
     * 可见不含多重定义的项，为 LL(1) 文法，即：
     *
     * 从左向右分析（Left-to-right-parse）
     * 最左推导（Leftmost-derivation）
     * 超前查看一个符号（1-symbol lookahead）
     * 它决定了我们从左向右遍历这个字符串，每次只判断当前最左边的一个字符的分析方法是正确的。
     *
     * 代码如下。
     * 时间
     * 1ms
     * 击败 75.84%使用 Java 的用户
     * 内存
     * 38.64MB
     * 击败 37.80%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param s  
     * @return java.lang.String
     * @date 2023/10/18 17:19
     */
    public String decodeStringGFRecursion(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString();
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
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
//        String s = "leet**cod*e";
//        System.out.println(removeStars(s));
//        int[] i = {10, 2, -5};
//        for (int i1 : asteroidCollision(i)) {
//            System.out.println(i1);
//        }
        int i = 0;
        System.out.println(~0);
    }
}
