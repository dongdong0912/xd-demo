package com.example.xddemo.demo;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

/**
 * @author xuedong
 * Date: 2025/3/8
 */
public class RuleDemo {

    public static void main(String[] args) {
        // 创建执行器参数
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        // 创建执行期引擎
        DefaultRulesEngine discountEngine = new DefaultRulesEngine(parameters);

        // 注解实现方式
        CutTenRule cutTenRule = new CutTenRule();
        // 添加规则
        Rules rules = new Rules();
        rules.register(cutTenRule);

        // 定义事实
        Facts facts = new Facts();
        // 添加事实
        facts.put("price", 300);
        // 触发引擎
        discountEngine.fire(rules, facts);

        Object score = facts.get("score");

        System.out.println(score);


    }
}
