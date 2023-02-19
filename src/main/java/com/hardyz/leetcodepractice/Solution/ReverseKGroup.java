package com.hardyz.leetcodepractice.Solution;

import com.hardyz.leetcodepractice.entity.ListNode;

public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cpHead = new ListNode();
        cpHead.next = head;
        ListNode res = cpHead;
        int size = 1;
        while (head != null) {
            if (size < k) {
                head = head.next;
                size ++;
            } else {
                size = 1;
                ListNode tail = reverseKGroupHelper(cpHead, head);
                cpHead = tail;
                head = tail.next;
            }
        }
        return res.next;

    }
    public ListNode reverseKGroupHelper(ListNode head, ListNode tail) {
        ListNode cpHead = new ListNode();
        cpHead.next = tail.next;
        ListNode st = head.next;
        ListNode ed = tail.next;
        ListNode finalTail = st;
        while (st != ed) {
            ListNode cpNext = cpHead.next;
            ListNode stNext = st.next;
            cpHead.next = st;
            st.next = cpNext;
            st = stNext;
        }
        head.next = cpHead.next;
        return finalTail;
    }
}
