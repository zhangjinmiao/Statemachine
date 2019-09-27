package com.lyxiang.common;

import com.lyxiang.pojo.OrderDTO;

/**
 * @author: liyuxiang
 * @create: 2019-01-23
 */
public class OrderContext {

    public OrderContext(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public OrderContext() {
    }

    public OrderDTO orderDTO;

}
