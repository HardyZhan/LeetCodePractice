package com.hardyz.leetcodepractice.Solution;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {
    List<String> res;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(s, 0, 4, sb);
        return res;
    }
    public void helper(String s, int st, int rest, StringBuilder pre) {
        StringBuilder cur = new StringBuilder(pre);
        if (rest == 0) {
            if (st < s.length()) {
                return;
            }
            cur.deleteCharAt(cur.length() - 1);
            String result = cur.toString();
            String[] strs = result.split("\\.");
            for (String str : strs) {
                if (Integer.valueOf(str) > 255) {
                    return;
                }
            }
            res.add(result);
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (st + i >= s.length()) {
                return;
            }
            char c = s.charAt(st + i);
            if (i == 0 && c == '0') {
                cur.append(c);
                cur.append('.');
                helper(s, st + i + 1, rest - 1, cur);
                return;
            }
            cur.append(c);
            cur.append('.');
            helper(s, st + i + 1, rest - 1, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
