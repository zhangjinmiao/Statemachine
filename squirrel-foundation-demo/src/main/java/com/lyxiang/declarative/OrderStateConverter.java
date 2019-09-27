package com.lyxiang.declarative;

import com.lyxiang.common.OrderState;
import org.squirrelframework.foundation.fsm.Converter;

/**
 * @author: liyuxiang
 * @create: 2019-01-24
 */
public class OrderStateConverter implements Converter<OrderState> {

    @Override
    public String convertToString(OrderState orderState) {
        return orderState.name();
    }

    @Override
    public OrderState convertFromString(String name) {
        return OrderState.valueOf(name);
    }
}
