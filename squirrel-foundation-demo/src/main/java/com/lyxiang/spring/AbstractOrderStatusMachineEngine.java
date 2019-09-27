package com.lyxiang.spring;

import com.lyxiang.common.OrderContext;
import com.lyxiang.common.OrderEvent;
import com.lyxiang.common.OrderState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.GenericTypeResolver;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.StateMachineConfiguration;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * @author: liyuxiang
 * @create: 2019-01-21
 */
public abstract class AbstractOrderStatusMachineEngine<T extends UntypedStateMachine> implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(AbstractOrderStatusMachineEngine.class);

    private ApplicationContext applicationContext;

    protected UntypedStateMachineBuilder builder;

    public AbstractOrderStatusMachineEngine() {
        //识别泛型参数
        Class<T> genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractOrderStatusMachineEngine.class);
        builder = StateMachineBuilderFactory.create(genericType, ApplicationContext.class);
    }

    //注入applicationContext，并在创建StateMachine实例时注入
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //delegate fire
    public void fire(OrderEvent orderEvent, OrderContext orderContext) {
        T stateMachine = builder.newUntypedStateMachine(OrderState.getState(orderContext.orderDTO.getState()),
                StateMachineConfiguration.create().enableDebugMode(true).enableAutoStart(true),
                applicationContext);
        try {
            stateMachine.fire(orderEvent, orderContext);
        } catch (Exception e) {
            logger.error("stateMachine.fire error");
        }
    }


    protected abstract boolean accept(OrderState orderState);

}
