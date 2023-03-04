package com.hardyz.leetcodepractice.UtilsTest;

import com.hardyz.leetcodepractice.UnitTemplateTest;
import com.hardyz.leetcodepractice.entity.TreeNode;
import com.hardyz.leetcodepractice.utils.TreeUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;


public class TreeUtilsTest extends UnitTemplateTest {

    @Test
    public void printTreeTest() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        String tree = TreeUtils.printTree(root);
        result.add(tree);
        predicates = Lists.newArrayList(
                "1, 3"
        );
        evaluate();
    }

    @Test
    public void generateTreeTest() {
        String[] tree = new String[]{"-10","9","20","null","null","15","7"};
        TreeNode root = TreeUtils.generateTree(tree);
        String res = TreeUtils.printTree(root);
        result.add(res);
        predicates = Lists.newArrayList(
                "-10, 9, 20, 15, 7"
        );
        evaluate();
    }
}
