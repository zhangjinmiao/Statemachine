package com.lyxiang.fluent;

import com.lyxiang.common.OrderContext;
import com.lyxiang.common.OrderEvent;
import com.lyxiang.common.OrderState;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

/**
 * @author: liyuxiang
 * @create: 2019-01-23
 */
public class OrderStateMachine extends AbstractStateMachine<OrderStateMachine, OrderState, OrderEvent, OrderContext> {

    public void entryStateInit(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println(fromState + "->" + toState);
    }

    public void stateInitToWaitPay(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println(fromState + "->" + toState);
    }

    public void stateWaitPayToWaitSend(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println(fromState + "->" + toState);
    }

    public void stateWaitSendToPartSend(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println(fromState + "->" + toState);
    }

    public void statePartSendToWaitReceive(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println(fromState + "->" + toState);
    }

    public void stateWaitReceiveToComplete(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println(fromState + "->" + toState);
    }

}
