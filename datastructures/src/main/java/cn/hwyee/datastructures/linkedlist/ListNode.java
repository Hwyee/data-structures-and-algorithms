package cn.hwyee.datastructures.linkedlist;

import lombok.NoArgsConstructor;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ListNode
 * @description 简单的链表
 * @date 2023/5/15
 * @since JDK 1.8
 */
@NoArgsConstructor
public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
