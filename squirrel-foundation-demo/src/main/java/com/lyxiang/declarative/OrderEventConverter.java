package com.lyxiang.declarative;

import com.lyxiang.common.OrderEvent;
import org.squirrelframework.foundation.fsm.Converter;

/**
 * @author: liyuxiang
 * @create: 2019-01-24
 */
public class OrderEventConverter implements Converter<OrderEvent> {


    @Override
    public String convertToString(OrderEvent orderEvent) {
        return orderEvent.name();
    }

    @Override
    public OrderEvent convertFromString(String name) {
        return OrderEvent.valueOf(name);
    }
}
