package com.hardyz.leetcodepractice.UtilsTest;

import com.hardyz.leetcodepractice.UnitTemplateTest;
import com.hardyz.leetcodepractice.utils.MathUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

public class MathUtilsTest extends UnitTemplateTest {

    @Test
    public void isTimesTwoTest() {
        int timesTwo = MathUtils.isTimesTwo(12);
        result.add(String.valueOf(timesTwo));
        predicates = Lists.newArrayList(
                "-1"
        );
        evaluate();
    }
    @Test
    public void isTimesTwoTest2() {
        int timesTwo = MathUtils.isTimesTwo(4);
        result.add(String.valueOf(timesTwo));
        predicates = Lists.newArrayList(
                "2"
        );
        evaluate();
    }
}
