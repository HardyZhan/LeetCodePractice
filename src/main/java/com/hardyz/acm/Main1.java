package com.hardyz.acm;
/**
 * 链接：https://www.nowcoder.com/questionTerminal/f4161b1bac304f5b803fed3e3758d4bb
 * 来源：牛客网
 *
 * 小美在观察一棵美丽的无根树。
 *
 * 小团问小美：“小美，我考考你，如果我选一个点为根，你能不能找出子树大小不超过K的前提下，子树内最大值和最小值差最大的子树的根是哪个点？多个点的话你给我编号最小的那个点就行了。”
 *
 * 小美思索一番，说这个问题难不倒他。
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1 {
    static int max = 0;
    static int[] cMax;
    static int[] cMin;
    static int[] subSize;
    static int resR;
    static boolean[] visited;
    static Map<Integer, List<Integer>> tree;
    static int[] weights;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1 = br.readLine().trim();
        String[] NK = line1.split(" ");
        int N = Integer.parseInt(NK[0]);
        K = Integer.parseInt(NK[1]);
        String line2 = br.readLine();
        String[] w = line2.split(" ");
        weights = new int[N + 1];
        cMax = new int[N + 1];
        cMin = new int[N + 1];
        visited = new boolean[N + 1];
        subSize = new int[N + 1];
        for (int i = 0; i < w.length; i++) {
            weights[i + 1] = Integer.parseInt(w[i]);
        }
        tree = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            String cur = br.readLine().trim();
            String[] nums = cur.split(" ");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);
            if (tree.containsKey(a)) {
                List<Integer> children = tree.get(a);
                children.add(b);
            } else {
                List<Integer> children = new ArrayList<>();
                children.add(b);
                tree.put(a, children);
            }
            if (tree.containsKey(b)) {
                List<Integer> children = tree.get(b);
                children.add(a);
            } else {
                List<Integer> children = new ArrayList<>();
                children.add(a);
                tree.put(b, children);
            }
        }
        int rootIndex = Integer.parseInt(br.readLine().trim());
        resR = -1;
        dfs(rootIndex);
        System.out.println(resR);
    }
    public static void dfs(int rootIndex) {
        visited[rootIndex] = true;
        cMax[rootIndex] = weights[rootIndex];
        cMin[rootIndex] = weights[rootIndex];
        subSize[rootIndex] = 1;
        List<Integer> children = tree.get(rootIndex);
        for (int i = 0; i < children.size(); i++) {
            int childIndex = children.get(i);
            if (!visited[childIndex]) {
                dfs(childIndex);
                cMax[rootIndex] = Math.max(cMax[rootIndex], cMax[childIndex]);
                cMin[rootIndex] = Math.min(cMin[rootIndex], cMin[childIndex]);
                subSize[rootIndex] += subSize[childIndex];
            }
        }
        if (subSize[rootIndex] <= K && cMax[rootIndex] - cMin[rootIndex] >= max) {
            if (cMax[rootIndex] - cMin[rootIndex] > max) {
                max = cMax[rootIndex] - cMin[rootIndex];
                resR = rootIndex;
            } else {
                resR = resR == -1 ? rootIndex : Math.min(resR, rootIndex);
            }
        }
    }

}
