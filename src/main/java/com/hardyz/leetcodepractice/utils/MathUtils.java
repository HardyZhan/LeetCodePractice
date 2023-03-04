package com.hardyz.leetcodepractice.utils;

import lombok.extern.slf4j.Slf4j;

public class MathUtils {
    // 判断是否为2的整数幂次
    public static int isTimesTwo(int val) {
        if (val > 0 && (val & (val - 1)) != 0) {
            return -1;
        }
        return Integer.toBinaryString(val).length() - 1;
    }
}
