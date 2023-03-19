package com.hardyz.acm;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main2 {
    public static List<int[]> list;
    public static Map<Integer, List<Integer>> tree;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[] dfs = nextArray();
        int[] childSize = nextArray();
        list = new ArrayList<>();
        tree = new HashMap<>();
        buildTree(dfs, childSize, 0);
        for (int i = 0; i < n; i++) {
            List<Integer> list = tree.get(i + 1);
            if (list != null && !list.isEmpty()) {
                list.stream().sorted().collect(Collectors.toList());
                for (int j = 0; j < list.size(); j++) {
                    pw.println(i + " " + list.get(j));
                }
            }
        }
        pw.flush();

    }
    public static int buildTree(int[] dfs, int[] childSize, int curIndex) {
        if (curIndex >= dfs.length) {
            return curIndex;
        }
        int node = dfs[curIndex];
        int children = childSize[node - 1];
        if (children == 1) {
            return curIndex;
        }
        children --;
        while (curIndex + 1 < dfs.length && children > 0) {
            int childNode = dfs[++curIndex];
            children -= childSize[childNode - 1];
            if (node > childNode) {
                if (tree.containsKey(childNode)) {
                    List<Integer> c = tree.get(childNode);
                    c.add(node);
                } else {
                    List<Integer> c = new ArrayList<>();
                    c.add(node);
                    tree.put(childNode, c);
                }
            } else {
                if (tree.containsKey(node)) {
                    List<Integer> c = tree.get(node);
                    c.add(childNode);
                } else {
                    List<Integer> c = new ArrayList<>();
                    c.add(childNode);
                    tree.put(node, c);
                }
            }
            curIndex = buildTree(dfs, childSize, curIndex);
        }
        return curIndex;
    }
    public static int nextInt() throws Exception {
        return Integer.parseInt(br.readLine());
    }
    public static int[] nextArray() throws Exception {
        String[] strs = br.readLine().trim().split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        return nums;
    }
}