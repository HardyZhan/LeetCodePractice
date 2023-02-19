package com.hardyz.leetcodepractice.utils;

import com.hardyz.leetcodepractice.entity.ListNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ListNodeUtils {

    public static ListNode makeList(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ListNode();
        }
        ListNode head = new ListNode();
        ListNode res = head;
        for (int num : nums) {
            head.next = new ListNode(num);
            head = head.next;
        }
        return res.next;
    }

    public static String showList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        return sb.toString();
    }
}
