package com.hardyz.leetcodepractice.Solution;

public class MyAtoi {
    int MAX = Integer.MAX_VALUE;
    int MIN = Integer.MIN_VALUE;
    /**
     * 正 true, 负 false
     */
    boolean flag = true;
    public int myAtoi(String s) {
        s = s.trim();
        char[] ch = s.toCharArray();
        int i = 0;
        while (i < ch.length) {
            if (ch[i] == '-') {
                flag = false;
                i ++;
                break;
            } else if (ch[i] == '+') {
                flag = true;
                i ++;
                break;
            } else if (ch[i] >= '0' && ch[i] <= '9') {
                break;
            }
            return 0;
        }
        // 消除前导0
        while (i < ch.length) {
            if (ch[i] == '0') {
                i ++;
            } else {
                break;
            }
        }
        int res = getNumber(ch, i, 0L);
        return flag ? res : -res;
    }
    public int getNumber(char[] ch, int i, long sum) {
        if (i >= ch.length || ch[i] < '0' || ch[i] > '9') {
            return (int)sum;
        }
        int cur = ch[i] - '0';
        if (flag) {
            if (MAX / 10 < sum) {
                return MAX;
            }
        } else {
            if (MIN / 10 > (- sum)) {
                return - MIN;
            }
        }
        sum = sum * 10;
        if (flag) {
            if (MAX - sum < cur) {
                return MAX;
            }
        } else {
            if ((MIN + sum) > - cur) {
                return - MIN;
            }
        }
        sum = sum + cur;
        return getNumber(ch, ++i, sum);
    }
}
