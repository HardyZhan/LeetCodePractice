package com.hardyz.leetcodepractice.utils;

import com.hardyz.leetcodepractice.entity.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class TreeUtils {

    public static TreeNode generateTree(String[] nodes) {
        int len = nodes.length;
        if (nodes == null || len == 0 || "null".equals(nodes[0])) {
            return null;
        }
        int i = 1;
        int level = 1;
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        List<TreeNode> rootList = new LinkedList<TreeNode>(){{
            add(root);
        }};

        while (i < len) {
            level <<= 1;
            int count = 0;
            TreeNode curRootTreeNode = new TreeNode();
            for (int j = i; j < i + level && j < len; j++) {
                if ((j - i) % 2 == 0) {
                    curRootTreeNode = rootList.remove(0);
                }
                if (!"null".equals(nodes[j])) {
                    TreeNode cur = new TreeNode(Integer.parseInt(nodes[j]));
                    rootList.add(cur);
                    if (count == 0) {
                        curRootTreeNode.left = cur;
                    } else {
                        curRootTreeNode.right = cur;
                    }
                }
                count = (count + 1) % 2;
            }
            i = i + level;
        }
        return root;
    }

    public static String printTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> list = new ArrayList<TreeNode>(){
            {
                add(root);
            }
        };
        StringBuilder sb = new StringBuilder();
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = list.remove(0);
                sb.append(cur.val + ", ");
                if (cur.left != null) {
                    list.add(cur.left);
                }
                if (cur.right != null) {
                    list.add(cur.right);
                }
            }
        }
        String res = sb.toString().trim();
        res = res.substring(0, res.length() - 1);
        return res;
    }

}
