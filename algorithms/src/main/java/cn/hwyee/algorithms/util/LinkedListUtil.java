package cn.hwyee.algorithms.util;

import cn.hwyee.datastructures.linkedlist.ListNode;
import lombok.extern.slf4j.Slf4j;

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

        ListNode cur = pre.next;
        ListNode Cur_next ;
        for(int i=0;i<n-m;i++){
            Cur_next = cur.next;
            cur.next = Cur_next.next;
            Cur_next .next = pre.next;
            pre.next = Cur_next ;
        }
        return dummyNode.next;
    }

}
