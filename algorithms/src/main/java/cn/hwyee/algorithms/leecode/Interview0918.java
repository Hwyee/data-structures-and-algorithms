package cn.cid.ciss.model;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author hui
 * @version 1.0
 * @className Interview0918
 * @description
 * @date 2023/9/18
 * @since JDK 1.8
 */
public class Interview0918 {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            LinkedList<TreeNode> treeNodes = new LinkedList<>();
            treeNodes.add(root);
            int result = 0;
            while (!treeNodes.isEmpty()) {
                result += 1;
                int size = treeNodes.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = treeNodes.poll();
                    if (poll.left != null) {
                        treeNodes.add(poll.left);
                    }
                    if (poll.right != null) {
                        treeNodes.add(poll.right);
                    }
                }
            }
            return result;
        }
    }

    /**
     * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
     * <p>
     * 请你计算该表达式。返回一个表示表达式值的整数。
     * <p>
     * 注意：
     * <p>
     * 有效的算符为 '+'、'-'、'*' 和 '/' 。
     * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
     * 两个整数之间的除法总是 向零截断 。
     * 表达式中不含除零运算。
     * 输入是一个根据逆波兰表示法表示的算术表达式。
     * 答案及所有中间计算结果可以用 32 位 整数表示。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * 示例 2：
     * <p>
     * 输入：tokens = ["4","13","5","/","+"]
     * 输出：6
     * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     * 示例 3：
     * <p>
     * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * 输出：22
     * 解释：该算式转化为常见的中缀算术表达式为：
     * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= tokens.length <= 104
     * tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数
     * <p>
     * <p>
     * 逆波兰表达式：
     * <p>
     * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
     * <p>
     * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
     * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
     * 逆波兰表达式主要有以下两个优点：
     * <p>
     * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
     * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中:
     *
     * @return
     * @author hui
     * @version 1.0
     * @date 2023/9/18 15:35
     */
    public static void main(String[] args) {
        String[] arr = new String[]{"4","13","5","/","+"};
        evalRPN(arr);
    }
    public  static int evalRPN(String[] tokens) {
        Stack<String> strings = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                Integer num1 = Integer.valueOf(strings.pop());
                Integer num2 = Integer.valueOf(strings.pop());
                strings.add(String.valueOf(num1 + num2));
            } else if ("-".equals(tokens[i])) {
                Integer num2 = Integer.valueOf(strings.pop());
                Integer num1 = Integer.valueOf(strings.pop());
                strings.add(String.valueOf(num1 - num2));
            } else if ("/".equals(tokens[i])) {
                Integer num2 = Integer.valueOf(strings.pop());
                Integer num1 = Integer.valueOf(strings.pop());
                strings.add(String.valueOf(num1 / num2));
            } else if ("*".equals(tokens[i])) {
                Integer num1 = Integer.valueOf(strings.pop());
                Integer num2 = Integer.valueOf(strings.pop());
                strings.add(String.valueOf(num1 * num2));
            } else {
                strings.add(tokens[i]);
            }
        }
        return Integer.parseInt(strings.pop());
    }
}
