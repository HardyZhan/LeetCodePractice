package com.hardyz.leetcodepractice.Solution;

import com.hardyz.leetcodepractice.UnitTemplateTest;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

public class MyAtoiTest extends UnitTemplateTest {

    MyAtoi myAtoi = new MyAtoi();

    @Test
    public void case1() {
        String str = "words and 987";
        result.add(String.valueOf(myAtoi.myAtoi(str)));
        predicates = Lists.newArrayList(
                "987"
        );
        evaluate();
    }
}
