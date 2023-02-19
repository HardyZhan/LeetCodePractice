package com.hardyz.leetcodepractice.Solution;

import com.hardyz.leetcodepractice.entity.ListNode;
import com.hardyz.leetcodepractice.utils.ListNodeUtils;
import org.junit.jupiter.api.Test;

public class ReverseKGroupTest {
    ReverseKGroup reverseKGroup = new ReverseKGroup();

    @Test
    public void test() {
        int[] nums = new int[]{1,2,3,4,5};
        ListNode head = ListNodeUtils.makeList(nums);
        int k = 2;
        ListNode res = reverseKGroup.reverseKGroup(head, k);
        System.out.println(ListNodeUtils.showList(res));
    }
}
