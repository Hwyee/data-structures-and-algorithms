package cn.hwyee.algorithms.util;

import cn.hwyee.datastructures.linkedlist.ListNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName LinkedListUtil
 * @description 链表工具类
 * @date 2023/5/16
 * @since JDK 1.8
 */
@Slf4j
public class LinkedListUtil {
    private LinkedListUtil() {
    }

    /**
     * reverseLinkedList:
     * 翻转链表
     *
     * @param head
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @author hui
     * @version 1.0
     * @date 2023/5/25 23:45
     */
    public static ListNode reverseLinkedList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            //暂存链表的下一个节点
            ListNode temp = head.next;
            //当前节点即是新节点的顶级节点，当前节点的下一级是新节点
            head.next = newHead;
            //将当前节点赋值给新节点
            newHead = head;
            head = temp;

        }
        return newHead;
    }

    /**
     * 链表内指定区间反转
     *
     * @param head ListNode类
     * @param m    int整型
     * @param n    int整型
     * @return ListNode类
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //设置虚拟头节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        //m节点的位置
        ListNode cur = pre.next;
        ListNode Cur_next;
        //让m每次与后面的节点互换位置
        for (int i = 0; i < n - m; i++) {
            //m-1       m           m+1             m+2
            //pre      cur          Cur_next
            Cur_next = cur.next;
            //          cur                         cur.next
            cur.next = Cur_next.next;
            //       Cur_next.next
            Cur_next.next = pre.next;
            //                      pre.next
            pre.next = Cur_next;
        }
        return dummyNode.next;
    }

    /**
     * 链表中的节点每k个一组翻转
     *
     * @param head ListNode类
     * @param k    int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        //找到每次翻转的尾部
        ListNode tail = head;
        //遍历k次到尾部
        for (int i = 0; i < k; i++) {
            //如果不足k到了链表尾，直接返回，不翻转
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        //翻转时需要的前序和当前节点
        ListNode pre = null;
        ListNode cur = head;
        //在到达当前段尾节点前
        while (cur != tail) {
            //翻转
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        //当前尾指向下一段要翻转的链表
        head.next = reverseKGroup(tail, k);
        return pre;
    }

    /**
     * merge:
     * 合并两个排序的链表 合并后也是有序的
     *
     * @param list1
     * @param list2
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @author hui
     * @version 1.0
     * @date 2023/5/28 1:03
     */
    public ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode l = new ListNode(-1);
        ListNode t = l;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                t.next = list1;
                list1 = list1.next;
            } else {
                t.next = list2;
                list2 = list2.next;
            }
            t = t.next;
        }
        t.next = list1 == null ? list2 : list1;
        return l.next;
    }


    /**
     * divideMerge:
     * 划分合并区间函数
     *
     * @param lists
     * @param left
     * @param right
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @author hui
     * @version 1.0
     * @date 2023/5/29 0:23
     */
    ListNode divideMerge(ArrayList<ListNode> lists, int left, int right) {
        if (left > right) {
            return null;
        }
        //中间一个的情况
        else if (left == right) {
            return lists.get(left);
        }
        //从中间分成两段，再将合并好的两段合并
        int mid = (left + right) / 2;
        return merge(divideMerge(lists, left, mid), divideMerge(lists, mid + 1, right));
    }

    /**
     * mergeKLists: 归并排序
     * 合并k个已排序的链表
     *
     * @param lists
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @author hui
     * @version 1.0
     * @date 2023/5/29 0:23
     */
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        //k个链表归并排序
        return divideMerge(lists, 0, lists.size() - 1);
    }

    /**
     * mergeKLists2:
     * 合并k个已排序的链表
     * 优先队列 "先进先出" 堆顶即第一个元素
     * 分为大顶堆与小顶堆，大顶堆的堆顶为最大元素，其余更小的元素在堆下方，小顶堆与其刚好相反。
     *
     * @param lists
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @author hui
     * @version 1.0
     * @date 2023/5/29 0:36
     */
    public ListNode mergeKLists2(ArrayList<ListNode> lists) {
        //小顶堆
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        //遍历所有链表第一个元素
        for (int i = 0; i < lists.size(); i++) {
            //不为空则加入小顶堆
            if (lists.get(i) != null) {
                pq.add(lists.get(i));
            }
        }
        //加一个表头
        ListNode res = new ListNode(-1);
        ListNode head = res;
        //直到小顶堆为空
        while (!pq.isEmpty()) {
            //取出最小的元素
            ListNode temp = pq.poll();
            //连接
            head.next = temp;
            head = head.next;
            //每次取出链表的后一个元素加入小顶堆
            if (temp.next != null) {
                pq.add(temp.next);
            }
        }
        //去掉表头
        return res.next;
    }


    /**
     * hasCycle:
     * 判断链表中是否有环
     * 时间复杂度：O(n)，最坏情况下遍历链表n个节点
     * 空间复杂度：O(1)，仅使用了两个指针，没有额外辅助空间
     *
     * @param head
     * @return boolean
     * @author hui
     * @version 1.0
     * @date 2023/5/30 0:01
     */
    public boolean hasCycle(ListNode head) {
        //先判断链表为空的情况
        if (head == null) {
            return false;
        }
        //快慢双指针
        ListNode fast = head;
        ListNode slow = head;
        //如果没环快指针会先到链表尾
        while (fast != null && fast.next != null) {
            //快指针移动两步
            fast = fast.next.next;
            //慢指针移动一步
            slow = slow.next;
            //相遇则有环
            if (fast == slow) {
                return true;
            }
        }
        //到末尾则没有环
        return false;
    }

    public ListNode hasCycle1(ListNode head) {
        //先判断链表为空的情况
        if (head == null) {
            return null;
        }
        //快慢双指针
        ListNode fast = head;
        ListNode slow = head;
        //如果没环快指针会先到链表尾
        while (fast != null && fast.next != null) {
            //快指针移动两步
            fast = fast.next.next;
            //慢指针移动一步
            slow = slow.next;
            //相遇则有环，返回相遇的位置
            if (fast == slow) {
                return slow;
            }
        }
        //到末尾说明没有环，返回null
        return null;
    }

    /**
     * entryNodeOfLoop:
     * 链表中环的入口结点 数学问题
     *
     * @param pHead
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @author hui
     * @version 1.0
     * @date 2023/5/30 21:47
     */
    public ListNode entryNodeOfLoop(ListNode pHead) {
        ListNode slow = hasCycle1(pHead);
        //没有环
        if (slow == null) {
            return null;
        }
        //快指针回到表头
        ListNode fast = pHead;
        //再次相遇即是环入口
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 链表中倒数最后k个结点
     *
     * @param pHead ListNode类
     * @param k     int整型
     * @return ListNode类
     */
    public ListNode findKthToTail(ListNode pHead, int k) {
        int n = 0;
        ListNode fast = pHead;
        ListNode slow = pHead;
        //快指针先行k步
        for (int i = 0; i < k; i++) {
            if (fast != null) {
                fast = fast.next;
            }
            //达不到k步说明链表过短，没有倒数k
            else {
                return slow = null;
            }
        }
        //快慢指针同步，快指针先到底，慢指针指向倒数第k个
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 删除链表的倒数第n个节点
     *
     * @param head ListNode类
     * @param n    int整型
     * @return ListNode类
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //添加表头
        ListNode res = new ListNode(-1);
        res.next = head;
        //当前节点
        ListNode cur = head;
        //前序节点
        ListNode pre = res;
        ListNode fast = head;
        //快指针先行n步
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        //快慢指针同步，快指针到达末尾，慢指针就到了倒数第n个位置
        while (fast != null) {
            fast = fast.next;
            pre = cur;
            cur = cur.next;
        }
        //删除该位置的节点
        pre.next = cur.next;
        //返回去掉头
        return res.next;
    }

    /**
     * FindFirstCommonNode:
     * 两个链表的第一个公共结点
     *
     * @param pHead1
     * @param pHead2
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @author hui
     * @version 1.0
     * @date 2023/6/3 0:04
     */
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }


    /**
     * 链表相加(二)
     *
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public ListNode addInList(ListNode head1, ListNode head2) {
        // 进行判空处理
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        // 反转h1链表
        head1 = reverseLinkedList(head1);
        // 反转h2链表
        head2 = reverseLinkedList(head2);
        // 创建新的链表头节点
        ListNode head = new ListNode(-1);
        ListNode nHead = head;
        // 记录进位的数值
        int tmp = 0;
        while (head1 != null || head2 != null) {
            // val用来累加此时的数值（加数+加数+上一位的进位=当前总的数值）
            int val = tmp;
            // 当节点不为空的时候，则需要加上当前节点的值
            if (head1 != null) {
                val += head1.val;
                head1 = head1.next;
            }
            // 当节点不为空的时候，则需要加上当前节点的值
            if (head2 != null) {
                val += head2.val;
                head2 = head2.next;
            }
            // 求出进位
            tmp = val / 10;
            // 进位后剩下的数值即为当前节点的数值
            nHead.next = new ListNode(val % 10);
            // 下一个节点
            nHead = nHead.next;

        }
        // 最后当两条链表都加完的时候，进位不为0的时候，则需要再加上这个进位
        if (tmp > 0) {
            nHead.next = new ListNode(tmp);
        }
        // 重新反转回来返回
        return reverseLinkedList(head.next);
    }

    /**
     * 判断一个链表是否为回文结构
     * 回文是指该字符串正序逆序完全一致。
     * 304ms
     * 占用内存
     * 27384KB
     *
     * @param head ListNode类 the head
     * @return bool布尔型
     */
    public boolean isPailMy(ListNode head) {
        // write code here
        if (head.next == null) {
            return true;
        }
        int i = 0;
        ListNode tmp = head;
        Stack<ListNode> stack = new Stack<>();
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
            i++;
        }
        for (int j = 0; j < i / 2; j++) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 判断一个链表是否为回文结构 双指针
     *
     * @param head ListNode类 the head
     * @return bool布尔型
     */
    public boolean isPail(ListNode head) {
        ListNode q = head, p = head;
        //通过快慢指针找到中点
        while (q != null && q.next != null) {
            q = q.next.next;
            p = p.next;
        }
        //如果q不为空，说明链表的长度是奇数个
        if (q != null) {
            p = p.next;
        }
        //反转后半部分链表
        p = reverseLinkedList(p);

        q = head;
        while (p != null) {
            //然后比较，判断节点值是否相等
            if (q.val != p.val) {
                return false;
            }
            q = q.next;
            p = p.next;
        }
        return true;
    }

    /**
     * 链表的奇偶重排
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 194ms
     * 占用内存
     * 25568KB
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode oddEvenListMy(ListNode head) {
        // write code here
        int i = 1;
        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode oddt = odd;
        ListNode event = even;
        while (head != null) {
            if (i % 2 == 0) {
                event.next = head;
                event = event.next;
            } else {
                oddt.next = head;
                oddt = oddt.next;
            }
            head = head.next;
            i++;
        }
        event.next = null;
        oddt.next = even.next;
        return odd.next;
    }

    /**
     * oddEvenList:
     * 运行时间
     * 226ms
     * 占用内存
     * 26104KB
     *
     * @param head
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @author hui
     * @version 1.0
     * @date 2023/6/4 14:13
     */
    public ListNode oddEvenList(ListNode head) {
        //如果链表为空，不用重排
        if (head == null) {
            return head;
        }
        //even开头指向第二个节点，可能为空
        ListNode even = head.next;
        //odd开头指向第一个节点
        ListNode odd = head;
        //指向even开头
        ListNode evenhead = even;
        while (even != null && even.next != null) {
            //odd连接even的后一个，即奇数位
            odd.next = even.next;
            //odd进入后一个奇数位
            odd = odd.next;
            //even连接后一个奇数的后一位，即偶数位
            even.next = odd.next;
            //even进入后一个偶数位
            even = even.next;
        }
        //even整体接在odd后面
        odd.next = evenhead;
        return head;
    }

    /**
     * 删除有序链表中重复的元素-I
     * 删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicates(ListNode head) {
        //空链表
        if (head == null) {
            return null;
        }
        //遍历指针
        ListNode cur = head;
        //指针当前和下一位不为空
        while (cur != null && cur.next != null) {
            //如果当前与下一位相等则忽略下一位
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }
            //否则指针正常遍历
            else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
     * 可以跳过多个相同的值。
     * 运行时间
     * 74ms
     * 占用内存
     * 12896KB
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicates2(ListNode head) {
        //空链表
        if (head == null) {
            return null;
        }
        ListNode res = new ListNode(0);
        //在链表前加一个表头
        res.next = head;
        ListNode cur = res;
        while (cur.next != null && cur.next.next != null) {
            //遇到相邻两个节点值相同
            if (cur.next.val == cur.next.next.val) {
                int temp = cur.next.val;
                //将所有相同的都跳过
                while (cur.next != null && cur.next.val == temp) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        //返回时去掉表头
        return res.next;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 二分查找-I
     *
     * @param nums   int整型一维数组
     * @param target int整型
     * @return int整型
     */
    public int search(int[] nums, int target) {
        // write code here
        if (nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = (start + end) / 2;
        while (start <= end) {

            if (target == nums[mid]) {
                return mid;
            }
            if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            mid = (start + end) / 2;
        }

        return -1;
    }

    /**
     * find:二维数组中的查找 线性搜索 利用二维数组行列递增特性
     * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param target
     * @param array
     * @return boolean
     * @author hui
     * @version 1.0
     * @date 2023/6/5 23:39
     */
    public boolean find(int target, int[][] array) {
        //优先判断特殊
        if (array.length == 0) {
            return false;
        }
        int n = array.length;
        if (array[0].length == 0) {
            return false;
        }
        int m = array[0].length;
        //从最左下角的元素开始往左或往上
        for (int i = n - 1, j = 0; i >= 0 && j < m; ) {
            //元素较大，往上走
            if (array[i][j] > target) {
                i--;
            }
            //元素较小，往右走
            else if (array[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 双二分查找
     * 解题思路： 利用数组行列递增特性。
     * 主要思路：一维的二分查找可以舍弃一半的查找范围，二维的二分可以舍弃左上部分或者右下部分1/4的查找范围。
     * public:
     * bool double_binary(vector<vector<int>> arr,int x1,int x2,int y1, int y2,int target){
     * if(x1 == x2 || y1 == y2) return false;
     * int xmid = (x1+x2)/2, ymid = (y1+y2)/2;
     * int num = arr[xmid][ymid];
     * if(num == target) return true;
     * if(num > target)
     * {
     * if(double_binary(arr, x1, xmid, y1, y2, target)) return true;
     * if(double_binary(arr,xmid,x2,y1,ymid,target)) return true;
     * }
     * else
     * {
     * if(double_binary(arr, xmid+1, x2, y1, y2, target)) return true;
     * if(double_binary(arr, x1, xmid+1, ymid+1, y2, target)) return true;
     * }
     * return false;
     * }
     * bool Find(int target, vector<vector<int> > array) {
     * if(array.size() == 0) return false;
     * return double_binary(array, 0, array.size(), 0, array[0].size(), target);
     * }
     */
    public boolean find1(int target, int[][] array) {
        return false;
    }

    /**
     * 寻找峰值
     * 给定一个长度为n的数组，返回其中任何一个峰值的索引
     * 峰值元素是指其值严格大于左右相邻值的元素
     * 数组两个边界可以看成是最小，nums[−1]=nums[n]=−∞
     * 峰值不存在平的情况，即相邻元素不会相等
     * 因为题目将数组边界看成最小值，而我们只需要找到其中一个波峰，因此只要不断地往高处走，一定会有波峰。
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int findPeakElement(int[] nums) {
        // write code here
        int left = 0;
        int right = nums.length - 1;
        //二分法
        while (left < right) {
            int mid = (left + right) / 2;
            //右边是往下，不一定有坡峰
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            }
            //右边是往上，一定能找到波峰
            else {
                left = mid + 1;
            }
        }
        //其中一个波峰
        return right;
    }

    public int mergeSort(int left, int right, int[] data, int[] temp) {
        int mod = 1000000007;
        //停止划分
        if (left >= right) {
            return 0;
        }
        //取中间
        int mid = (left + right) / 2;
        //左右划分合并
        int res = mergeSort(left, mid, data, temp) + mergeSort(mid + 1, right, data, temp);
        //防止溢出
        res %= mod;
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            temp[k] = data[k];
        }
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                data[k] = temp[j++];
            } else if (j == right + 1 || temp[i] <= temp[j]) {
                data[k] = temp[i++];
            }
            //左边比右边大，答案增加
            else {
                data[k] = temp[j++];
                // 统计逆序对
                res += mid - i + 1;
            }
        }
        return res % mod;
    }

    /**
     * inversePairs:
     * 数组中的逆序对
     * 如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组，求一个数组的全部逆序对，答案对1000000007取模
     * 题目保证输入的数组中没有的相同的数字
     *
     * @param array
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/6/6 23:53
     */
    public int inversePairs(int[] array) {
        int n = array.length;
        int[] res = new int[n];
        return mergeSort(0, n - 1, array, res);
    }

    /**
     * minNumberInRotateArray:
     * 旋转数组的最小数字
     * 有一个长度为 n 的非降序数组，比如[1,2,3,4,5]，将它进行旋转，即把一个数组最开始的若干个元素搬到数组的末尾，
     * 变成一个旋转数组，比如变成了[3,4,5,1,2]，或者[4,5,1,2,3]这样的。请问，给定这样一个旋转数组，求数组中的最小值。
     * 运行时间
     * 75ms
     * 占用内存
     * 12936KB
     *
     * @param array
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/6/7 23:56
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null) {
            return -1;
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            }
        }
        return array[0];
    }


    /**
     * 版本号是由修订号组成，修订号与修订号之间由一个"."连接。
     * 1个修订号可能有多位数字组成，修订号可能包含前导0，且是合法的。例如，1.02.11，0.1，0.2都是合法的版本号
     * 每个版本号至少包含1个修订号。
     * 修订号从左到右编号，下标从0开始，最左边的修订号下标为0，下一个修订号下标为1，以此类推。
     * <p>
     * 比较规则：
     * 一. 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较忽略任何前导零后的整数值。
     * 比如"0.1"和"0.01"的版本号是相等的
     * 二. 如果版本号没有指定某个下标处的修订号，则该修订号视为0。例如，"1.1"的版本号小于"1.1.1"。
     * 因为"1.1"的版本号相当于"1.1.0"，第3位修订号的下标为0，小于1
     * 三.  version1 > version2 返回1，如果 version1 < version2 返回-1，不然返回0.
     * 运行时间
     * 26ms
     * 占用内存
     * 10096KB
     * 比较版本号
     *
     * @param version1 string字符串
     * @param version2 string字符串
     * @return int整型
     */
    public int compare(String version1, String version2) {
        // write code here
        String[] a1 = version1.split("\\.");
        String[] a2 = version2.split("\\.");
        int f1 = findFirstNotZero(a1);
        int f2 = findFirstNotZero(a2);
        int max = Math.max(a1.length, a2.length);
        int v1 = 0;
        int v2 = 0;
        boolean isFirst = false;

        for (int i = 0; i < max; i++) {
            if (i == 0) {
                v1 = Integer.parseInt(a1[i]);
            } else if ((i + f1) > (a1.length - 1)) {
                v1 = 0;
            } else {
                v1 = Integer.parseInt(a1[i + f1]);
            }
            if (i == 0) {
                v2 = Integer.parseInt(a2[i]);
            } else if ((i + f2) > (a2.length - 1)) {
                v2 = 0;
            } else {
                v2 = Integer.parseInt(a2[i + f2]);
            }
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }
        return 0;
    }

    public int findFirstNotZero(String[] s) {
        for (int i = 1; i < s.length; i++) {
            if (Integer.parseInt(s[i]) != 0) {
                return i - 1;
            }
        }
        return 0;
    }

    /**
     * compareGF:
     * 官方 双指针遍历截取
     * 运行时间
     * 20ms
     * 占用内存
     * 9800KB
     * @author hui
     * @version 1.0
     * @param version1
     * @param version2
     * @return int
     * @date 2023/6/9 0:11
     */
    public int compareGF(String version1, String version2) {
        int n1 = version1.length();
        int n2 = version2.length();
        int i = 0, j = 0;
        //直到某个字符串结束
        while (i < n1 || j < n2) {
            long num1 = 0;
            //从下一个点前截取数字
            while (i < n1 && version1.charAt(i) != '.') {
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            //跳过点
            i++;
            long num2 = 0;
            //从下一个点前截取数字
            while (j < n2 && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            //跳过点
            j++;
            //比较数字大小
            if (num1 > num2)
                return 1;
            if (num1 < num2)
                return -1;
        }
        //版本号相同
        return 0;
    }
}
