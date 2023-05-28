package cn.hwyee.algorithms.util;

import cn.hwyee.datastructures.linkedlist.ListNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

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
            if(tail == null)
                return head;
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
     * mergeKLists: 
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
}
