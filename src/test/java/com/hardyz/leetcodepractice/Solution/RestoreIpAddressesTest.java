package com.hardyz.leetcodepractice.Solution;

import com.hardyz.leetcodepractice.UnitTemplateTest;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

public class RestoreIpAddressesTest extends UnitTemplateTest {

    RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();

    @Test
    public void case1() {
        String input = "25525511135";
        List<String> res = restoreIpAddresses.restoreIpAddresses(input);
        StringBuilder builder = new StringBuilder();
        res.stream().forEach(s -> {
            builder.append(s + ' ');
        });
        result.add(builder.toString());
        predicates = Lists.newArrayList(
                "255.255.11.135 255.255.111.35 "
        );
        evaluate();
    }
}
