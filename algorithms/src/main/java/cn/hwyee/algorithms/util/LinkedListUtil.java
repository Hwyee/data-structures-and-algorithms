package cn.hwyee.algorithms.util;

import cn.hwyee.datastructures.linkedlist.ListNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

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
     * @author hui
     * @version 1.0
     * @param head
     * @return cn.hwyee.datastructures.linkedlist.ListNode
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
     * @param head ListNode类
     * @param m int整型
     * @param n int整型
     * @return ListNode类
     */
    public ListNode reverseBetween (ListNode head, int m, int n) {
        //设置虚拟头节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next =head;
        ListNode pre = dummyNode;
        for(int i=0;i<m-1;i++){
            pre = pre.next;
        }
        //m节点的位置
        ListNode cur = pre.next;
        ListNode Cur_next ;
        //让m每次与后面的节点互换位置
        for(int i=0;i<n-m;i++){
            //m-1       m           m+1             m+2
            //pre      cur          Cur_next
            Cur_next = cur.next;
            //          cur                         cur.next
            cur.next = Cur_next.next;
            //       Cur_next.next
            Cur_next .next = pre.next;
            //                      pre.next
            pre.next = Cur_next ;
        }
        return dummyNode.next;
    }

    /**
     * 链表中的节点每k个一组翻转
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        //找到每次翻转的尾部
        ListNode tail = head;
        //遍历k次到尾部
        for(int i = 0; i < k; i++){
            //如果不足k到了链表尾，直接返回，不翻转
            if(tail == null) {
                return head;
            }
            tail = tail.next;
        }
        //翻转时需要的前序和当前节点
        ListNode pre = null;
        ListNode cur = head;
        //在到达当前段尾节点前
        while(cur != tail){
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
     * @author hui
     * @version 1.0
     * @param list1 
     * @param list2  
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @date 2023/5/28 1:03
     */
    public ListNode merge(ListNode list1,ListNode list2) {
        if(list1==null) return list2;
        if(list2==null) return list1;
        ListNode l = new ListNode(-1);
        ListNode t = l;
        while(list1 != null && list2 !=null){
            if(list1.val < list2.val){
                t.next=list1;
                list1=list1.next;
            }else{
                t.next=list2;
                list2=list2.next;
            }
            t=t.next;
        }
        t.next=list1==null?list2:list1;
        return l.next;
    }

    
    /**
     * divideMerge: 
     * 划分合并区间函数
     * @author hui
     * @version 1.0
     * @param lists 
     * @param left 
     * @param right  
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @date 2023/5/29 0:23
     */
    ListNode divideMerge(ArrayList<ListNode> lists, int left, int right){
        if(left > right)
            return null;
            //中间一个的情况
        else if(left == right)
            return lists.get(left);
        //从中间分成两段，再将合并好的两段合并
        int mid = (left + right) / 2;
        return merge(divideMerge(lists, left, mid), divideMerge(lists, mid + 1, right));
    }

    /**
     * mergeKLists: 归并排序
     * 合并k个已排序的链表
     * @author hui
     * @version 1.0
     * @param lists  
     * @return cn.hwyee.datastructures.linkedlist.ListNode
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
     * @author hui
     * @version 1.0
     * @param lists
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @date 2023/5/29 0:36
     */
    public ListNode mergeKLists2(ArrayList<ListNode> lists) {
        //小顶堆
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        //遍历所有链表第一个元素
        for(int i = 0; i < lists.size(); i++){
            //不为空则加入小顶堆
            if(lists.get(i) != null)
                pq.add(lists.get(i));
        }
        //加一个表头
        ListNode res = new ListNode(-1);
        ListNode head = res;
        //直到小顶堆为空
        while(!pq.isEmpty()){
            //取出最小的元素
            ListNode temp = pq.poll();
            //连接
            head.next = temp;
            head = head.next;
            //每次取出链表的后一个元素加入小顶堆
            if(temp.next != null)
                pq.add(temp.next);
        }
        //去掉表头
        return res.next;
    }


    /**
     * hasCycle:
     * 判断链表中是否有环
     * 时间复杂度：O(n)，最坏情况下遍历链表n个节点
     * 空间复杂度：O(1)，仅使用了两个指针，没有额外辅助空间
     * @author hui
     * @version 1.0
     * @param head  
     * @return boolean
     * @date 2023/5/30 0:01
     */
    public boolean hasCycle(ListNode head) {
        //先判断链表为空的情况
        if(head == null) {
            return false;
        }
        //快慢双指针
        ListNode fast = head;
        ListNode slow = head;
        //如果没环快指针会先到链表尾
        while(fast != null && fast.next != null){
            //快指针移动两步
            fast = fast.next.next;
            //慢指针移动一步
            slow = slow.next;
            //相遇则有环
            if(fast == slow)
                return true;
        }
        //到末尾则没有环
        return false;
    }

    public ListNode hasCycle1(ListNode head) {
        //先判断链表为空的情况
        if (head == null)
            return null;
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
            if (fast == slow)
                return slow;
        }
        //到末尾说明没有环，返回null
        return null;
    }

    /**
     * entryNodeOfLoop: 
     * 链表中环的入口结点 数学问题
     * @author hui
     * @version 1.0
     * @param pHead  
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @date 2023/5/30 21:47
     */
    public ListNode entryNodeOfLoop(ListNode pHead) {
        ListNode slow = hasCycle1(pHead);
        //没有环
        if(slow == null)
            return null;
        //快指针回到表头
        ListNode fast = pHead;
        //再次相遇即是环入口
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 链表中倒数最后k个结点
     *
     *
     * @param pHead ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode findKthToTail (ListNode pHead, int k) {
        int n = 0;
        ListNode fast = pHead;
        ListNode slow = pHead;
        //快指针先行k步
        for (int i = 0; i < k; i++) {
            if (fast != null)
                fast = fast.next;
                //达不到k步说明链表过短，没有倒数k
            else
                return slow = null;
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
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        //添加表头
        ListNode res = new ListNode(-1);
        res.next = head;
        //当前节点
        ListNode cur = head;
        //前序节点
        ListNode pre = res;
        ListNode fast = head;
        //快指针先行n步
        while(n != 0){
            fast = fast.next;
            n--;
        }
        //快慢指针同步，快指针到达末尾，慢指针就到了倒数第n个位置
        while(fast != null){
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
     * @author hui
     * @version 1.0
     * @param pHead1
     * @param pHead2
     * @return cn.hwyee.datastructures.linkedlist.ListNode
     * @date 2023/6/3 0:04
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }

}
