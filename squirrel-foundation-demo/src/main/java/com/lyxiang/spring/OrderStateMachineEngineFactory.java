package com.lyxiang.spring;

import com.lyxiang.common.OrderContext;
import com.lyxiang.common.OrderEvent;
import com.lyxiang.common.OrderState;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: liyuxiang
 * @create: 2019-01-21
 */
@Service
public class OrderStateMachineEngineFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void fire(OrderContext orderContext, OrderEvent orderEvent) {
        Map<String, AbstractOrderStatusMachineEngine> orderStatusMachineEngineMap = applicationContext.getBeansOfType(AbstractOrderStatusMachineEngine.class);
        boolean accept = false;
        for (AbstractOrderStatusMachineEngine orderStatusMachineEngine : orderStatusMachineEngineMap.values()) {
//            if (orderStatusMachineEngine.accept(OrderState.getState(orderContext.orderDTO.getState()))) {
//                accept = true;
//                orderStatusMachineEngine.fire(orderEvent, orderContext);
//            }
            accept = true;
            orderStatusMachineEngine.fire(orderEvent, orderContext);
        }
        if (!accept) {
            throw new RuntimeException("执行失败");
        }
    }
}
