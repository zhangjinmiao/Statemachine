package com.lyxiang.common;


import org.squirrelframework.foundation.fsm.Condition;

/**
 * @author: liyuxiang
 * @create: 2019-01-23
 */
public class OrderCondition implements Condition {

    @Override
    public boolean isSatisfied(Object context) {
        return true;
    }

    @Override
    public String name() {
        return "hahah";
    }
}
