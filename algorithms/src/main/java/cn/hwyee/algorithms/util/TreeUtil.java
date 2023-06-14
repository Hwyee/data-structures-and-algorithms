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
    public int[] preorderTraversal (TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        traverse(list,root);
        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i).val;
        }
        return ints;
    }

    public void traverse(List<TreeNode> list,TreeNode node){
        if (node == null) {
            return ;
        }
        list.add(node);
        if (node.left != null) {
            traverse(list,node.left);
        }
        if (node.right != null) {
            traverse(list,node.right);
        }
    }


    public void inorder(List<Integer> list, TreeNode root){
        //遇到空节点则返回
        if(root == null) {
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
     *  二叉树的中序遍历
     *  中序遍历就是从最左边开始，把每个节点垂直投影到同一直线上，然后从左往右读值
     * @author hui
     * @version 1.0
     * @param root  
     * @return int[]
     * @date 2023/6/11 0:21
     */
    public int[] inorderTraversal (TreeNode root) {
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        //递归中序遍历
        inorder(list, root);
        //返回的结果
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
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
    public int[] postorderTraversal (TreeNode root) {
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
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (root==null) {
            return arrayLists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left!=null){
                    queue.add(poll.left);
                }
                if (poll.right!=null){
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
     * @author hui
     * @version 1.0
     * @param pRoot  
     * @return java.util.ArrayList<java.util.ArrayList<java.lang.Integer>>
     * @date 2023/6/13 23:11
     */
    public ArrayList<ArrayList<Integer> > print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot==null) {
            return arrayLists;
        }
        LinkedList<TreeNode> odd = new LinkedList<>();
        LinkedList<TreeNode> even = new LinkedList<>();
        odd.add(pRoot);
        while (!odd.isEmpty() || !even.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            if (!odd.isEmpty()){
                int size = odd.size();
                for(int i = 0; i < size; i++) {
                    TreeNode poll = odd.poll();
                    list.add(poll.val);
                    if (poll.left!=null){
                        even.add(poll.left);
                    }
                    if (poll.right!=null){
                        even.add(poll.right);
                    }
                }
            }else {
                int size = even.size();
                for (TreeNode peek : even) {
                    if (peek.left != null) {
                        odd.add(peek.left);
                    }
                    if (peek.right != null) {
                        odd.add(peek.right);
                    }
                }
                for(int i = 0; i < size; i++) {
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
     * @author hui
     * @version 1.0
     * @param pRoot  
     * @return java.util.ArrayList<java.util.ArrayList<java.lang.Integer>>
     * @date 2023/6/13 23:53
     */
    public ArrayList<ArrayList<Integer> > printOffice(TreeNode pRoot) {
        TreeNode head = pRoot;
        ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer>>();
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
     * @author hui
     * @version 1.0
     * @param pRoot
     * @return java.util.ArrayList<java.util.ArrayList<java.lang.Integer>>
     * @date 2023/6/13 23:56
     */
    public ArrayList<ArrayList<Integer> > printOfficeDoubleStack(TreeNode pRoot) {
        TreeNode head = pRoot;
        ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer>>();
        if(head == null)
            //如果是空，则直接返回空list
        {
            return res;
        }
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        //放入第一次
        s1.push(head);
        while(!s1.isEmpty() || !s2.isEmpty()){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            //遍历奇数层
            while(!s1.isEmpty()){
                TreeNode node = s1.pop();
                //记录奇数层
                temp.add(node.val);
                //奇数层的子节点加入偶数层
                if(node.left != null) {
                    s2.push(node.left);
                }
                if(node.right != null) {
                    s2.push(node.right);
                }
            }
            //数组不为空才添加
            if(temp.size() != 0) {
                res.add(new ArrayList<Integer>(temp));
            }
            //清空本层数据
            temp.clear();
            //遍历偶数层
            while(!s2.isEmpty()){
                TreeNode node = s2.pop();
                //记录偶数层
                temp.add(node.val);
                //偶数层的子节点加入奇数层
                if(node.right != null) {
                    s1.push(node.right);
                }
                if(node.left != null) {
                    s1.push(node.left);
                }
            }
            //数组不为空才添加
            if(temp.size() != 0) {
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
     * @param root TreeNode类
     * @return int整型
     */
    public int maxDepth (TreeNode root) {
        if (root==null) {
            return 0;
        }
        int maxDepth = 0;
        LinkedList<TreeNode> lists = new LinkedList<>();
        lists.add(root);
        while(!lists.isEmpty()){
            int size = lists.size();
            for(int i = 0; i < size; i++){
                TreeNode poll = lists.poll();
                if (poll.left != null){
                    lists.add(poll.left);
                }
                if (poll.right != null){
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
     *
     * 而二叉树的递归，则是将某个节点的左子树、右子树看成一颗完整的树，
     * 那么对于子树的访问或者操作就是对于原树的访问或者操作的子问题，因此可以自我调用函数不断进入子树。
     *
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
     * @author hui
     * @version 1.0
     * @param root
     * @return int
     * @date 2023/6/14 0:08
     */
    public int maxDepthOfficeRecursion (TreeNode root) {
        //空节点没有深度
        if(root == null) {
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
     * @param root TreeNode类
     * @param sum int整型
     * @return bool布尔型
     */
    public boolean hasPathSum (TreeNode root, int sum) {
        // write code here
        if(root == null){
            return false;
        }
        //如果是叶子节点，且路径和为sum
        if(root.left==null&&root.right==null&& sum-root.val==0){
            return true;
        }
        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);
    }


    /**
     * hasPathSumStack:
     * 二叉树中和为某一值的路径
     *
     * @author hui
     * @version 1.0
     * @param root
     * @param sum
     * @return boolean
     * @date 2023/6/14 23:47
     */
    public boolean hasPathSumStack (TreeNode root, int sum) {
        //空节点找不到路径
        if(root == null) {
            return false;
        }
        //栈辅助深度优先遍历
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        //跟随s1记录到相应节点为止的路径和
        Stack<Integer> s2 = new Stack<Integer>();
        s1.push(root);
        s2.push(root.val);
        while(!s1.isEmpty()){
            //弹出相应节点
            TreeNode temp = s1.pop();
            //弹出到该点为止的当前路径和
            int cur_sum = s2.pop();
            //叶子节点且当前路径和等于sum
            if(temp.left == null && temp.right == null && cur_sum == sum) {
                return true;
            }
            //左节点及路径和入栈
            if(temp.left != null){
                s1.push(temp.left);
                s2.push(cur_sum + temp.left.val);
            }
            //右节点及路径和入栈
            if(temp.right != null){
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
     *
     * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
     * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
     * 它的左、右子树也分别为二叉排序树。二叉搜索树作为一种经典的数据结构，
     * 它既有链表的快速插入与删除操作的特点，又有数组快速查找的优势；所以应用十分广泛，
     * 例如在文件系统和数据库系统一般会采用这种数据结构进行高效率的排序与检索操作。
     * @author hui
     * @version 1.0
     * @param pRootOfTree  
     * @return cn.hwyee.datastructures.tree.TreeNode
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


}
