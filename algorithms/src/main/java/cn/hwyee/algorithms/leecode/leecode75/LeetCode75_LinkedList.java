package cn.hwyee.algorithms.leecode.leecode75;

import cn.hwyee.datastructures.linkedlist.ListNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName LeetCode75_LinkedList
 * @description 链表
 * @date 2024/3/15
 * @since JDK 1.8
 */
@Slf4j
public class LeetCode75_LinkedList {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        Solution_1 solution1 = new Solution_1();
        solution1.deleteMiddle(listNode1);
    }
    
    /**
     * 2095. 删除链表的中间节点: 
     * 给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
     * 长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始），其中 ⌊x⌋ 表示小于或等于 x 的最大整数。
     * 对于 n = 1、2、3、4 和 5 的情况，中间节点的下标分别是 0、1、1、2 和 2 。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/15 0:52
     */
    static class Solution_1 {
        public ListNode deleteMiddle(ListNode head) {
            ArrayList<ListNode> list = new ArrayList<>();
            int len = 0;
            ListNode temp = head;
            while (temp != null) {
                len++;
                list.add(temp);
                temp = temp.next;
            }
            int mid = len/2;
            if (mid == 0){
                return null;
            }else if (len == 2){
                head.next=null;
                return head;
            }else {
                list.get(mid-1).next=list.get(mid+1);
                return head;
            }
        }

        /**
         * deleteMiddleYH:
         * 优化算法 -> 快慢指针
         * @author hui
         * @version 1.0
         * @param head
         * @return cn.hwyee.datastructures.linkedlist.ListNode
         * @date 2024/3/15 1:24
         */
        public ListNode deleteMiddleYH(ListNode head) {
            if (head.next == null){
                return null;
            }
            ListNode fast = head;
            ListNode low = head;
            ListNode pre = low;
            while (fast != null && fast.next!=null) {
                fast = fast.next.next;
                pre = low;
                low = low.next;
            }
            pre.next=low.next;
            return head;
        }
    }
}
