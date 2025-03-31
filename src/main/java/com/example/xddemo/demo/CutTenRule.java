package com.example.xddemo.demo;

import lombok.Data;
import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

/**
 * @author xuedong
 * Date: 2025/3/8
 */
@Data
@Rule
public class CutTenRule {



    // 该注解标记为条件

    @Condition
    public boolean isLowerThan200(@Fact("price") Integer price) {
        return price < 700;
    }

    // 该注解表示条件判断之后的操作
    @Action
    public void printPriceAfterDiscount(Facts facts) {


        facts.put("score",1000);


    }

    //该注解表示优先级
    @Priority
    public int getPriority() {
        return 0;
    }


}
