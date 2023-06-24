package cn.hwyee.algorithms.util;

import cn.hwyee.datastructures.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName TreeUtil
 * @description
 * @date 2023/6/9
 * @since JDK 1.8
 */
public class TreeUtil {

    /**
     * 二叉树的前序遍历
     * 前序遍历首先访问根结点然后遍历左子树，最后遍历右子树。
     * 在遍历左、右子树时，仍然先访问根结点，然后遍历左子树，最后遍历右子树。
     *
     * @param root TreeNode类
     * @return int整型一维数组
     */
    public int[] preorderTraversal(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        traverse(list, root);
        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i).val;
        }
        return ints;
    }

    public void traverse(List<TreeNode> list, TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node);
        if (node.left != null) {
            traverse(list, node.left);
        }
        if (node.right != null) {
            traverse(list, node.right);
        }
    }


    public void inorder(List<Integer> list, TreeNode root) {
        //遇到空节点则返回
        if (root == null) {
            return;
        }
        //先去左子树
        inorder(list, root.left);
        //再访问根节点
        list.add(root.val);
        //最后去右子树
        inorder(list, root.right);
    }

    /**
     * inorderTraversal:
     * 二叉树的中序遍历
     * 中序遍历就是从最左边开始，把每个节点垂直投影到同一直线上，然后从左往右读值
     *
     * @param root
     * @return int[]
     * @author hui
     * @version 1.0
     * @date 2023/6/11 0:21
     */
    public int[] inorderTraversal(TreeNode root) {
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        //递归中序遍历
        inorder(list, root);
        //返回的结果
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    /**
     * 二叉树的后序遍历
     * 什么是二叉树的后续遍历，简单来说就是“左右根”，
     * 展开来说就是优先访问根节点的左子树的全部节点，然后再访问根节点的右子树的全部节点，最后再访问根节点。
     * 对于每棵子树的访问也按照这个逻辑，因此叫做“左右根”的顺序。
     *
     * @param root TreeNode类
     * @return int整型一维数组
     */
    public int[] postorderTraversal(TreeNode root) {
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        //递归中序遍历
        inorder(list, root);
        //返回的结果
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void postorder(List<Integer> list, TreeNode root) {
        //遇到空节点则返回
        if (root == null) {
            return;
        }
        //先去左子树
        inorder(list, root.left);
        //最后去右子树
        inorder(list, root.right);
        //再访问根节点
        list.add(root.val);
    }

    /**
     * 二叉树的层序遍历
     * 从左到右，一层一层地遍历
     *
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (root == null) {
            return arrayLists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            arrayLists.add(list);
        }
        return arrayLists;
    }

    /**
     * print:
     * 按之字形顺序打印二叉树
     * 第一层从左向右，下一层从右向左，一直这样交替
     * 运行时间
     * 28ms
     * 占用内存
     * 10272KB
     *
     * @param pRoot
     * @return java.util.ArrayList<java.util.ArrayList < java.lang.Integer>>
     * @author hui
     * @version 1.0
     * @date 2023/6/13 23:11
     */
    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null) {
            return arrayLists;
        }
        LinkedList<TreeNode> odd = new LinkedList<>();
        LinkedList<TreeNode> even = new LinkedList<>();
        odd.add(pRoot);
        while (!odd.isEmpty() || !even.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            if (!odd.isEmpty()) {
                int size = odd.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = odd.poll();
                    list.add(poll.val);
                    if (poll.left != null) {
                        even.add(poll.left);
                    }
                    if (poll.right != null) {
                        even.add(poll.right);
                    }
                }
            } else {
                int size = even.size();
                for (TreeNode peek : even) {
                    if (peek.left != null) {
                        odd.add(peek.left);
                    }
                    if (peek.right != null) {
                        odd.add(peek.right);
                    }
                }
                for (int i = 0; i < size; i++) {
                    TreeNode poll = even.pollLast();
                    list.add(poll.val);
                }
            }
            arrayLists.add(list);
        }
        return arrayLists;
    }

    /**
     * printOffice:
     * 官方非递归层次遍历
     * 27ms
     * 占用内存
     * 10116KB
     *
     * @param pRoot
     * @return java.util.ArrayList<java.util.ArrayList < java.lang.Integer>>
     * @author hui
     * @version 1.0
     * @date 2023/6/13 23:53
     */
    public ArrayList<ArrayList<Integer>> printOffice(TreeNode pRoot) {
        TreeNode head = pRoot;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (head == null)
        //如果是空，则直接返回空list
        {
            return res;
        }
        //队列存储，进行层次遍历
        Queue<TreeNode> temp = new LinkedList<TreeNode>();
        temp.offer(head);
        TreeNode p;
        boolean flag = true;
        while (!temp.isEmpty()) {
            //记录二叉树的某一行
            ArrayList<Integer> row = new ArrayList<Integer>();
            int n = temp.size();
            //奇数行反转，偶数行不反转
            flag = !flag;
            //因先进入的是根节点，故每层节点多少，队列大小就是多少
            for (int i = 0; i < n; i++) {
                p = temp.poll();
                row.add(p.val);
                //若是左右孩子存在，则存入左右孩子作为下一个层次
                if (p.left != null) {
                    temp.offer(p.left);
                }
                if (p.right != null) {
                    temp.offer(p.right);
                }
            }
            //奇数行反转，偶数行不反转
            if (flag) {
                Collections.reverse(row);
            }
            res.add(row);
        }
        return res;
    }


    /**
     * printOfficeDoubleStack:
     * 官方双栈法
     * 26ms
     * 占用内存
     * 9944KB
     *
     * @param pRoot
     * @return java.util.ArrayList<java.util.ArrayList < java.lang.Integer>>
     * @author hui
     * @version 1.0
     * @date 2023/6/13 23:56
     */
    public ArrayList<ArrayList<Integer>> printOfficeDoubleStack(TreeNode pRoot) {
        TreeNode head = pRoot;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (head == null)
        //如果是空，则直接返回空list
        {
            return res;
        }
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        //放入第一次
        s1.push(head);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            //遍历奇数层
            while (!s1.isEmpty()) {
                TreeNode node = s1.pop();
                //记录奇数层
                temp.add(node.val);
                //奇数层的子节点加入偶数层
                if (node.left != null) {
                    s2.push(node.left);
                }
                if (node.right != null) {
                    s2.push(node.right);
                }
            }
            //数组不为空才添加
            if (temp.size() != 0) {
                res.add(new ArrayList<Integer>(temp));
            }
            //清空本层数据
            temp.clear();
            //遍历偶数层
            while (!s2.isEmpty()) {
                TreeNode node = s2.pop();
                //记录偶数层
                temp.add(node.val);
                //偶数层的子节点加入奇数层
                if (node.right != null) {
                    s1.push(node.right);
                }
                if (node.left != null) {
                    s1.push(node.left);
                }
            }
            //数组不为空才添加
            if (temp.size() != 0) {
                res.add(new ArrayList<Integer>(temp));
            }
            //清空本层数据
            temp.clear();
        }
        return res;
    }

    /**
     * 二叉树的最大深度
     * 运行时间
     * 57ms
     * 占用内存
     * 12568KB
     *
     * @param root TreeNode类
     * @return int整型
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxDepth = 0;
        LinkedList<TreeNode> lists = new LinkedList<>();
        lists.add(root);
        while (!lists.isEmpty()) {
            int size = lists.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = lists.poll();
                if (poll.left != null) {
                    lists.add(poll.left);
                }
                if (poll.right != null) {
                    lists.add(poll.right);
                }
            }
            maxDepth++;
        }
        return maxDepth;
    }

    /**
     * maxDepth:
     * 官方递归
     * 递归是一个过程或函数在其定义或说明中有直接或间接调用自身的一种方法，
     * 它通常把一个大型复杂的问题层层转化为一个与原问题相似的规模较小的问题来求解。
     * 因此递归过程，最重要的就是查看能不能讲原本的问题分解为更小的子问题，这是使用递归的关键。
     * <p>
     * 而二叉树的递归，则是将某个节点的左子树、右子树看成一颗完整的树，
     * 那么对于子树的访问或者操作就是对于原树的访问或者操作的子问题，因此可以自我调用函数不断进入子树。
     * <p>
     * 思路：
     * 二叉树的深度就等于根节点这个1层加上左子树和右子树深度的最大值，即
     * rootDepth = max(leftDepth + rightDepth) + 1
     * 而每个子树我们都可以看成一个根节点，继续用上述方法求的深度，于是我们可以对这个问题划为子问题，利用递归来解决:
     * 终止条件： 当进入叶子节点后，再进入子节点，即为空，没有深度可言，返回0.
     * 返回值： 每一级按照上述公式，返回两边子树深度的最大值加上本级的深度，即加1.
     * 本级任务： 每一级的任务就是进入左右子树，求左右子树的深度。
     * 60ms
     * 占用内存
     * 12236KB
     * 时间内存都是o（n）但是递归的代码很简单。这个思想需要继续磨练。
     *
     * @param root
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/6/14 0:08
     */
    public int maxDepthOfficeRecursion(TreeNode root) {
        //空节点没有深度
        if (root == null) {
            return 0;
        }
        //返回子树深度+1
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 二叉树中和为某一值的路径(一)
     * 给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
     * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
     * 2.叶子节点是指没有子节点的节点
     * 3.路径只能从父节点到子节点，不能从子节点到父节点
     * 4.总节点数目为n
     *
     * @param root TreeNode类
     * @param sum  int整型
     * @return bool布尔型
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        // write code here
        if (root == null) {
            return false;
        }
        //如果是叶子节点，且路径和为sum
        if (root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


    /**
     * hasPathSumStack:
     * 二叉树中和为某一值的路径
     *
     * @param root
     * @param sum
     * @return boolean
     * @author hui
     * @version 1.0
     * @date 2023/6/14 23:47
     */
    public boolean hasPathSumStack(TreeNode root, int sum) {
        //空节点找不到路径
        if (root == null) {
            return false;
        }
        //栈辅助深度优先遍历
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        //跟随s1记录到相应节点为止的路径和
        Stack<Integer> s2 = new Stack<Integer>();
        s1.push(root);
        s2.push(root.val);
        while (!s1.isEmpty()) {
            //弹出相应节点
            TreeNode temp = s1.pop();
            //弹出到该点为止的当前路径和
            int cur_sum = s2.pop();
            //叶子节点且当前路径和等于sum
            if (temp.left == null && temp.right == null && cur_sum == sum) {
                return true;
            }
            //左节点及路径和入栈
            if (temp.left != null) {
                s1.push(temp.left);
                s2.push(cur_sum + temp.left.val);
            }
            //右节点及路径和入栈
            if (temp.right != null) {
                s1.push(temp.right);
                s2.push(cur_sum + temp.right.val);
            }
        }
        return false;
    }

    TreeNode head = null;
    TreeNode pre = null;

    /**
     * convert:
     * 二叉搜索树与双向链表
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表
     * 1.要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
     * 2.返回链表中的第一个节点的指针
     * 3.函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
     * 4.你不用输出双向链表，程序会根据你的返回值自动打印输出
     * <p>
     * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
     * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
     * 它的左、右子树也分别为二叉排序树。二叉搜索树作为一种经典的数据结构，
     * 它既有链表的快速插入与删除操作的特点，又有数组快速查找的优势；所以应用十分广泛，
     * 例如在文件系统和数据库系统一般会采用这种数据结构进行高效率的排序与检索操作。
     *
     * @param pRootOfTree
     * @return cn.hwyee.datastructures.tree.TreeNode
     * @author hui
     * @version 1.0
     * @date 2023/6/15 0:35
     */
    public TreeNode convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        convert(pRootOfTree.left);
        if (pre == null) {
            //最小的节点
            head = pRootOfTree;

        } else {
            //pre的上个节点一定比他大，所以pre.right需要指向比他大的节点也就是root
            pre.right = pRootOfTree;
            //相应的pre比它上个节点小,它上个节点的left需要指向pre
            pRootOfTree.left = pre;
            //换完后,pre需向上移动一位
        }
        pre = pRootOfTree;
        convert(pRootOfTree.right);
        return head;
    }


    /**
     * 对称的二叉树
     * 给定一棵二叉树，判断其是否是自身的镜像（即：是否对称）
     *
     * @param pRoot TreeNode类
     * @return bool布尔型
     */
    boolean isSymmetrical(TreeNode pRoot) {
        return recursion(pRoot, pRoot);
    }

    boolean recursion(TreeNode root1, TreeNode root2) {
        //可以两个都为空
        if (root1 == null && root2 == null) {
            return true;
        }
        //只有一个为空或者节点值不同，必定不对称
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        //每层对应的节点进入递归比较
        return recursion(root1.left, root2.right) && recursion(root1.right, root2.left);
    }

    /**
     * 合并二叉树
     * 已知两颗二叉树，将它们合并成一颗二叉树。合并规则是：
     * 都存在的结点，就将结点值加起来，否则空的位置就由另一个树的结点来代替。
     *
     * @param t1 TreeNode类
     * @param t2 TreeNode类
     * @return TreeNode类
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //若只有一个节点返回另一个，两个都为null自然返回null
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        //根左右的方式递归
        TreeNode head = new TreeNode(t1.val + t2.val);
        head.left = mergeTrees(t1.left, t2.left);
        head.right = mergeTrees(t1.right, t2.right);
        return head;
    }


    /**
     * 二叉树的镜像
     * 操作给定的二叉树，将其变换为源来的二叉树的镜像。
     *
     * @param pRoot TreeNode类
     * @return TreeNode类
     */
    public TreeNode mirror(TreeNode pRoot) {
        // write code here
        if (pRoot == null) {
            return null;
        }

        TreeNode left = mirror(pRoot.left);
        TreeNode right = mirror(pRoot.right);
        pRoot.left = right;
        pRoot.right = left;
        return pRoot;
    }

    int preVal = Integer.MIN_VALUE;

    /**
     * 判断是不是二叉搜索树
     * 二叉搜索树是一种特殊的二叉树，它的每个节点值大于它的左子节点，且大于全部左子树的节点值，小于它右子节点，
     * 且小于全部右子树的节点值。因此二叉搜索树一定程度上算是一种排序结构。
     *
     * @param root TreeNode类
     * @return bool布尔型
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        //先进入左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val < preVal) {
            return false;
        }
        //更新最值
        preVal = root.val;
        //再进入右子树
        return isValidBST(root.right);
    }

    /**
     * 判断是不是完全二叉树
     * 完全二叉树的定义：若二叉树的深度为 h，除第 h 层外，其它各层的结点数都达到最大个数，
     * 第 h 层所有的叶子结点都连续集中在最左边（即节点必须是从左到右连续的），这就是完全二叉树.
     *
     * @param root TreeNode类
     * @return bool布尔型
     */
    public boolean isCompleteTree(TreeNode root) {
        //空树一定是完全二叉树
        if (root == null) {
            return true;
        }
        //辅助队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur;
        //定义一个首次出现的标记位
        boolean notComplete = false;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            //标记第一次遇到空节点
            if (cur == null) {
                notComplete = true;
                continue;
            }
            //后续访问已经遇到空节点了，说明经过了叶子
            if (notComplete) {
                return false;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }


    /**
     * 判断是不是平衡二叉树
     * 平衡二叉树（Balanced Binary Tree），具有以下性质：
     * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
     * 约定： 空树是平衡二叉树。
     *
     * @param pRoot TreeNode类
     * @return bool布尔型
     */
    public boolean isBalancedSolution(TreeNode pRoot) {
        // write code here
        //fuck you baby
        if (pRoot == null) {
            return true;
        }
        int left = deep(pRoot.left);
        int right = deep(pRoot.right);
        if (left - right > 1 || left - right < -1) {
            return false;
        }
        return isBalancedSolution(pRoot.left) && isBalancedSolution(pRoot.right);

    }

    /**
     * deep:
     * 计算树的深度
     *
     * @param root
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/6/17 9:34
     */
    public int deep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = deep(root.left);
        int right = deep(root.right);
        return (left > right) ? left + 1 : right + 1;
    }

    /**
     * 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 1.对于该题的最近的公共祖先定义:对于有根树T的两个节点p、q，最近公共祖先LCA(T,p,q)表示一个节点x，满足x是p和q的祖先且x的深度尽可能大。在这里，一个节点也可以是它自己的祖先.
     * 2.二叉搜索树是若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值
     * 3.所有节点的值都是唯一的。
     * 4.p、q 为不同节点且均存在于给定的二叉搜索树中。
     * 数据范围:
     * 3<=节点总数<=10000
     * 0<=节点值<=10000
     *
     * @param root TreeNode类
     * @param p    int整型
     * @param q    int整型
     * @return int整型
     */
    public int lowestCommonAncestor(TreeNode root, int p, int q) {
        //求根节点到两个节点的路径
        ArrayList<Integer> path_p = getPath(root, p);
        ArrayList<Integer> path_q = getPath(root, q);
        int res = 0;
        //比较两个路径，找到第一个不同的点
        for (int i = 0; i < path_p.size() && i < path_q.size(); i++) {
            int x = path_p.get(i);
            int y = path_q.get(i);
            //最后一个相同的节点就是最近公共祖先
            if (x == y) {
                res = path_p.get(i);
            } else {
                break;
            }
        }
        return res;
    }

    /**
     * getPath:
     * 求得根节点到目标节点的路径
     *
     * @param root
     * @param target
     * @return java.util.ArrayList<java.lang.Integer>
     * @author hui
     * @version 1.0
     * @date 2023/6/19 1:22
     */
    public ArrayList<Integer> getPath(TreeNode root, int target) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        TreeNode node = root;
        //题目中所有节点值都是唯一的，节点值都不同，可以直接用值比较
        while (node.val != target) {
            path.add(node.val);
            //小的在左子树
            if (target < node.val) {
                node = node.left;
            }
            //大的在右子树
            else {
                node = node.right;
            }
        }
        path.add(node.val);
        return path;
    }


    /**
     * 在二叉树（普通二叉树）中找到两个节点的最近公共祖先
     * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
     * 本题保证二叉树中每个节点的val值均不相同。
     *
     * @param root TreeNode类
     * @param o1   int整型
     * @param o2   int整型
     * @return int整型
     */
    public int lowestCommonAncestorNormal(TreeNode root, int o1, int o2) {
        ArrayList<Integer> path1 = new ArrayList<Integer>();
        ArrayList<Integer> path2 = new ArrayList<Integer>();
        //求根节点到两个节点的路径
        dfs(root, path1, o1);
        //重置flag，查找下一个
        flag = false;
        dfs(root, path2, o2);
        int res = 0;
        //比较两个路径，找到第一个不同的点
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            int x = path1.get(i);
            int y = path2.get(i);
            if (x == y)
            //最后一个相同的节点就是最近公共祖先
            {
                res = x;
            } else {
                break;
            }
        }
        return res;
    }

    //记录是否找到到o的路径
    public boolean flag = false;


    /**
     * 求得根节点到目标节点的路径
     * dfs: deep first search
     * 深度优先搜索一般用于树或者图的遍历，其他有分支的（如二维矩阵）也适用。它的原理是从初始点开始，
     * 一直沿着同一个分支遍历，直到该分支结束，然后回溯到上一级继续沿着一个分支走到底，如此往复，直到所有的节点都有被访问到。
     *
     * @param root
     * @param path
     * @param o
     * @return void
     * @author hui
     * @version 1.0
     * @date 2023/6/19 23:04
     */
    public void dfs(TreeNode root, ArrayList<Integer> path, int o) {
        if (flag || root == null) {
            return;
        }
        path.add(root.val);
        //节点值都不同，可以直接用值比较
        if (root.val == o) {
            flag = true;
            return;
        }
        //dfs遍历查找
        dfs(root.left, path, o);
        dfs(root.right, path, o);
        //找到
        if (flag) {
            return;
        }
        //回溯
        path.remove(path.size() - 1);
    }


    //序列的下标
    public int index = 0;

    
    /**
     * SerializeFunction: 
     * 处理序列化的功能函数（递归）
     * @author hui
     * @version 1.0
     * @param root 
     * @param str  
     * @return void
     * @date 2023/6/21 23:59
     */
    private void serializeFunction(TreeNode root, StringBuilder str) {
        //如果节点为空，表示左子节点或右子节点为空，用#表示
        if (root == null) {
            str.append('#');
            return;
        }
        //根节点
        str.append(root.val).append('!');
        //左子树
        serializeFunction(root.left, str);
        //右子树
        serializeFunction(root.right, str);
    }

    /**
     * serialize:
     * 请实现两个函数，分别用来序列化和反序列化二叉树，不对序列化之后的字符串进行约束，
     * 但要求能够根据序列化之后的字符串重新构造出一棵与原二叉树相同的树。
     *
     * 二叉树的序列化(Serialize)是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
     * 序列化可以基于先序、中序、后序、层序的二叉树等遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#）
     *
     * 二叉树的反序列化(Deserialize)是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
     * 序列化即将二叉树的节点值取出，放入一个字符串中，我们可以按照前序遍历的思路，遍历二叉树每个节点，并将节点值存储在字符串中，
     * 我们用‘#’表示空节点，用‘!'表示节点与节点之间的分割。
     * @author hui
     * @version 1.0
     * @param root  
     * @return java.lang.String
     * @date 2023/6/21 23:59
     */
    public String serialize(TreeNode root) {
        //处理空树
        if (root == null) {
            return "#";
        }
        StringBuilder res = new StringBuilder();
        serializeFunction(root, res);
        //把str转换成char
        return res.toString();
    }

    /**
     * deserializeFunction:
     * 处理反序列化的功能函数（递归）
     * @author hui
     * @version 1.0
     * @param str
     * @return cn.hwyee.datastructures.tree.TreeNode
     * @date 2023/6/22 0:01
     */
    private TreeNode deserializeFunction(String str) {
        //到达叶节点时，构建完毕，返回继续构建父节点
        //空节点
        if (str.charAt(index) == '#') {
            index++;
            return null;
        }
        //数字转换
        int val = 0;
        //遇到分隔符或者结尾
        while (str.charAt(index) != '!' && index != str.length()) {
            val = val * 10 + ((str.charAt(index)) - '0');
            index++;
        }
        TreeNode root = new TreeNode(val);
        //序列到底了，构建完成
        if (index == str.length()) {
            return root;
        } else {
            index++;
        }
        //反序列化与序列化一致，都是前序
        root.left = deserializeFunction(str);
        root.right = deserializeFunction(str);
        return root;
    }

    /**
     * deserialize:
     * 反序列化即根据给定的字符串，将二叉树重建，因为字符串中的顺序是前序遍历，因此我们重建的时候也是前序遍历，即可还原。
     * @author hui
     * @version 1.0
     * @param str
     * @return cn.hwyee.datastructures.tree.TreeNode
     * @date 2023/6/22 0:00
     */
    public TreeNode deserialize(String str) {
        //空序列对应空树
        if (str == "#") {
            return null;
        }
        TreeNode res = deserializeFunction(str);
        return res;
    }


    /**
     * 重建二叉树
     * 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
     * 1.vin.length == pre.length
     * 2.pre 和 vin 均无重复元素
     * 3.vin出现的元素均出现在 pre里
     * 4.只需要返回根结点，系统会自动输出整颗树做答案对比
     * 对于二叉树的前序遍历，我们知道序列的第一个元素必定是根节点的值，因为序列没有重复的元素，因此中序遍历中可以找到相同的这个元素，
     * 而我们又知道中序遍历中根节点将二叉树分成了左右子树两个部分，见图reBuildTree.png
     * 我们可以发现，数字1是根节点，并将二叉树分成了(247)和(3568)两棵子树，而子树的的根也是相应前序序列的首位，
     * 比如左子树的根是数字2，右子树的根是数字3，这样我们就可以利用前序遍历序列找子树的根节点，利用中序遍历序列区分每个子树的节点数。
     *
     * @param preOrder int整型一维数组
     * @param vinOrder int整型一维数组
     * @return TreeNode类
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] vin) {
        int n = pre.length;
        int m = vin.length;
        //每个遍历都不能为0
        if(n == 0 || m == 0) {
            return null;
        }
        //构建根节点
        TreeNode root = new TreeNode(pre[0]);
        for(int i = 0; i < vin.length; i++){
            //找到中序遍历中的前序第一个元素
            if(pre[0] == vin[i]){
                //构建左子树
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(vin, 0, i));
                //构建右子树
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(vin, i + 1, vin.length));
                break;
            }
        }
        return root;
    }


    /**
     * buildTree:
     * 建树函数
     * 四个int参数分别是前序最左节点下标，前序最右节点下标
     * 中序最左节点下标，中序最右节点坐标
     * @author hui
     * @version 1.0
     * @param xianxu
     * @param l1
     * @param r1
     * @param zhongxu
     * @param l2
     * @param r2
     * @return cn.hwyee.datastructures.tree.TreeNode
     * @date 2023/6/24 23:42
     */
    public TreeNode buildTree(int[] xianxu, int l1, int r1, int[] zhongxu, int l2, int r2){
        if(l1 > r1 || l2 > r2) {
            return null;
        }
        //构建节点
        TreeNode root = new TreeNode(xianxu[l1]);
        //用来保存根节点在中序遍历列表的下标
        int rootIndex = 0;
        //寻找根节点
        for(int i = l2; i <= r2; i++){
            if(zhongxu[i] == xianxu[l1]){
                rootIndex = i;
                break;
            }
        }
        //左子树大小
        int leftsize = rootIndex - l2;
        //右子树大小
        int rightsize = r2 - rootIndex;
        //递归构建左子树和右子树
        root.left = buildTree(xianxu, l1 + 1, l1 + leftsize, zhongxu, l2 , l2 + leftsize - 1);
        root.right = buildTree(xianxu, r1 - rightsize + 1, r1, zhongxu, rootIndex + 1, r2);
        return root;
    }


    /**
     * rightSideView:
     * 深度优先搜索函数
     * @author hui
     * @version 1.0
     * @param root
     * @return java.util.ArrayList<java.lang.Integer>
     * @date 2023/6/24 23:41
     */
    public ArrayList<Integer> rightSideView(TreeNode root) {
        //右边最深处的值
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        //记录最大深度
        int max_depth = -1;
        //维护深度访问节点
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        //维护dfs时的深度
        Stack<Integer> depths = new Stack<Integer>();
        nodes.push(root);
        depths.push(0);
        while(!nodes.isEmpty()){
            TreeNode node = nodes.pop();
            int depth = depths.pop();
            if(node != null){
                //维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);
                //如果不存在对应深度的节点我们才插入
                if(mp.get(depth) == null) {
                    mp.put(depth, node.val);
                }
                nodes.push(node.left);
                nodes.push(node.right);
                depths.push(depth + 1);
                depths.push(depth + 1);
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        //结果加入链表
        for(int i = 0; i <= max_depth; i++) {
            res.add(mp.get(i));
        }
        return res;
    }

    /**
     * solve:
     * 输出二叉树的右视图
     * 请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
     * 可以发现解这道题，我们有两个步骤：
     *
     * 建树
     * 打印右视图
     * 首先建树方面，前序遍历是根左右的顺序，中序遍历是左根右的顺序，因为节点值互不相同，
     * 我们可以根据在前序遍历中找到根节点（每个子树部分第一个就是），再在中序遍历中找到对应的值，从其左右分割开，左边就是该树的左子树，
     * 右边就是该树的右子树，于是将问题划分为了子问题。
     *
     * 而打印右视图即找到二叉树每层最右边的节点元素，我们可以采取dfs（深度优先搜索）遍历树，根据记录的深度找到最右值。
     *
     * 具体做法：
     *
     * step 1：首先检查两个遍历序列的大小，若是为0，则空树不用打印。
     * step 2：建树函数根据上述说，每次利用前序遍历第一个元素就是根节点，在中序遍历中找到它将二叉树划分为左右子树，
     * 利用l1 r1 l2 r2分别记录子树部分在数组中分别对应的下标，并将子树的数组部分送入函数进行递归。
     * step 3：dfs打印右视图时，使用哈希表存储每个深度对应的最右边节点，初始化两个栈辅助遍历，
     * 第一个栈记录dfs时的节点，第二个栈记录遍历到的深度，根节点先入栈。
     * step 4：对于每个访问的节点，每次左子节点先进栈，右子节点再进栈，这样访问完一层后，因为栈的先进后出原理，
     * 每次都是右边被优先访问，因此我们在哈希表该层没有元素时，添加第一个该层遇到的元素就是最右边的节点。
     * step 5：使用一个变量逐层维护深度最大值，最后遍历每个深度，从哈希表中读出每个深度的最右边节点加入数组中。
     * @author hui
     * @version 1.0
     * @param xianxu
     * @param zhongxu
     * @return int[]
     * @date 2023/6/24 23:42
     */
    public int[] solve (int[] xianxu, int[] zhongxu) {
        //空节点
        if(xianxu.length == 0) {
            return new int[0];
        }
        //建树
        TreeNode root = buildTree(xianxu, 0, xianxu.length - 1, zhongxu, 0, zhongxu.length - 1);
        //获取右视图输出
        ArrayList<Integer> temp = rightSideView(root);
        //转化为数组
        int[] res = new int[temp.size()];
        for(int i = 0; i < temp.size(); i++) {
            res[i] = temp.get(i);
        }
        return res;
    }

}
