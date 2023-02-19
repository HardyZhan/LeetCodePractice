package com.hardyz.leetcodepractice.Solution;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FindKthLargestTest {
    FindKthLargest findKthLargest = new FindKthLargest();

    @Test
    public void test() {
        int[] nums = new int[]{3,2,1,5,6,4};
        int k = 2;
        System.out.println(findKthLargest.findKthLargest(nums, k));;
    }
}
