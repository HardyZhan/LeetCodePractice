package com.hardyz.leetcodepractice;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UnitTemplateTest {
    public List<String> predicates = new ArrayList<>();
    public List<String> result = new ArrayList<>();
    public void evaluate(){
        evaluatePredicates();
    }

    public void evaluatePredicates() {
        if (predicates.size() != result.size()) {
            log.error("实际结果与预期结果数目不一致！实际:{}, 预期:{}", result.size(), predicates.size());
            return;
        }
        int size = predicates.size();
        for (int i = 0; i < size; i++) {
            assert result.get(i).equals(predicates.get(i)) : "result结果不正确:result:" + result.get(i) + ",predicates:" + predicates.get(i);
            log.info("result:{},predicates:{}", result.get(i), predicates.get(i));
        }
    }
}
