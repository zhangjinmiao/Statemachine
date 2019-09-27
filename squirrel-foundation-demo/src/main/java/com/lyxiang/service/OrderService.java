package com.lyxiang.service;

import com.lyxiang.common.OrderContext;
import com.lyxiang.common.OrderEvent;
import com.lyxiang.common.OrderState;
import com.lyxiang.pojo.OrderDTO;
import com.lyxiang.spring.OrderStateMachineEngineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: liyuxiang
 * @create: 2019-01-29
 */
@Service
public class OrderService {

    @Autowired
    OrderStateMachineEngineFactory orderStateMachineEngineFactory;

    public int submitOrder() {
        OrderDTO orderDTO = new OrderDTO(OrderState.INIT.name());
        OrderContext orderContext = new OrderContext(orderDTO);
        orderStateMachineEngineFactory.fire(orderContext, OrderEvent.SUBMIT_ORDER);
        return orderContext.orderDTO.getId();
    }

    public int pay() {
        OrderDTO orderDTO = new OrderDTO(OrderState.WAIT_PAY.name());
        OrderContext orderContext = new OrderContext(orderDTO);
        orderStateMachineEngineFactory.fire(orderContext, OrderEvent.PAY);
        return orderContext.orderDTO.getId();
    }
}
