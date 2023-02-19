package com.hardyz.leetcodepractice.Solution;

import java.util.Random;

public class FindKthLargest {
    private final static Random rdm = new Random(System.currentTimeMillis());

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int target = len - k;
        int l = 0;
        int r = len - 1;
        while (true) {
            int index = partition(nums, l, r);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                l = index + 1;
            } else {
                r = index - 1;
            }
        }


    }

    public int partition(int[] nums, int l, int r) {
        int randomIndex = l + rdm.nextInt(r - l + 1);
        swap(nums, randomIndex, l);
        int cap = nums[l];
        int left = l + 1;
        int right = r;
        while (true) {
            while (left <= right && nums[left] < cap) {
                left ++;
            }
            while (left <= right && nums[right] > cap) {
                right --;
            }
            if (left >= right) {
                break;
            }
            swap(nums, left, right);
            left ++;
            right --;
        }
        swap(nums, l, right);
        return right;


    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
