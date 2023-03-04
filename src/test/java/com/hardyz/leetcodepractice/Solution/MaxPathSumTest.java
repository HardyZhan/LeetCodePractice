package com.hardyz.leetcodepractice.Solution;

import com.hardyz.leetcodepractice.UnitTemplateTest;
import com.hardyz.leetcodepractice.entity.TreeNode;
import com.hardyz.leetcodepractice.utils.TreeUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

public class MaxPathSumTest extends UnitTemplateTest {

    @Test
    public void maxPathSumTest() {
        String[] strs = new String[]{"-1","-2","10","-6","null","-3","-6"};
        TreeNode root = TreeUtils.generateTree(strs);
        int res = MaxPathSum.maxPathSum(root);
        result.add(String.valueOf(res));
        predicates = Lists.newArrayList(
                "10"
        );
        evaluate();
    }
}
