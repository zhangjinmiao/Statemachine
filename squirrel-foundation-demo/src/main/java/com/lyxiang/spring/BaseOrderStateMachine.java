package com.lyxiang.spring;

import com.lyxiang.dao.OrderDTOMapper;
import org.springframework.context.ApplicationContext;

/**
 * @author: liyuxiang
 * @create: 2019-01-30
 */
public abstract class BaseOrderStateMachine extends AbstractUntypedStateMachine  {

    protected OrderDTOMapper orderDTOMapper;

    public BaseOrderStateMachine(ApplicationContext applicationContext) {
        this.orderDTOMapper = applicationContext.getBean(OrderDTOMapper.class);
    }
}
