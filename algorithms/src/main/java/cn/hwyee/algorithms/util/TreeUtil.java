package cn.hwyee.algorithms.util;

import cn.hwyee.datastructures.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
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
     * @author hui
     * @version 1.0
     * @param root
     * @param path
     * @param o
     * @return void
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

}
