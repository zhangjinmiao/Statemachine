package com.lyxiang.declarative;

import com.lyxiang.common.OrderContext;
import com.lyxiang.common.OrderEvent;
import com.lyxiang.common.OrderState;
import org.squirrelframework.foundation.fsm.annotation.State;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.annotation.States;
import org.squirrelframework.foundation.fsm.annotation.Transit;
import org.squirrelframework.foundation.fsm.annotation.Transitions;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

/**
 * @author: liyuxiang
 * @create: 2019-01-23
 */
@States({
        @State(name = "INIT", entryCallMethod = "entryStateInit", exitCallMethod = "exitStateInit", initialState = true),
        @State(name = "WAIT_PAY", entryCallMethod = "entryStateWaitPay", exitCallMethod = "exitStateWaitPay"),
        @State(name = "WAIT_SEND", entryCallMethod = "entryStateWaitSend", exitCallMethod = "exitStateWaitSend"),
        @State(name = "PART_SEND", entryCallMethod = "entryStatePartSend", exitCallMethod = "exitStatePartSend"),
        @State(name = "WAIT_RECEIVE", entryCallMethod = "entryStateWaitReceive", exitCallMethod = "exitStateWaitReceive"),
        @State(name = "COMPLETE", entryCallMethod = "entryStateComplete", exitCallMethod = "exitStateComplete")
})
@Transitions({
        @Transit(from = "INIT", to = "WAIT_PAY", on = "SUBMIT_ORDER", callMethod = "submitOrder"),
        @Transit(from = "WAIT_PAY", to = "WAIT_SEND", on = "PAY", callMethod = "pay"),
        @Transit(from = "WAIT_SEND", to = "PART_SEND", on = "PART_SEND", callMethod = "partSend"),
        @Transit(from = "PART_SEND", to = "WAIT_RECEIVE", on = "SEND", callMethod = "send"),
        @Transit(from = "WAIT_RECEIVE", to = "COMPLETE", on = "COMPLETE", callMethod = "complete")
})
@StateMachineParameters(stateType = OrderState.class, eventType = OrderEvent.class, contextType = OrderContext.class)
public class DeclarativeOrderMachine extends AbstractUntypedStateMachine {

    public void submitOrder(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("submitOrder");
    }

    public void pay(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("pay");
    }

    public void partSend(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("partSend");
    }

    public void send(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("send");
    }

    public void complete(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("complete");
    }

    public void entryStateInit(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("entryStateInit");
    }

    public void exitStateInit(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("exitStateInit");
    }

    public void entryStateWaitPay(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("entryStateWaitPay");
    }

    public void exitStateWaitPay(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("exitStateWaitPay");
    }

    public void entryStateWaitSend(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("entryStateWaitSend");
    }

    public void exitStateWaitSend(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("exitStateWaitSend");
    }

    public void entryStatePartSend(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("entryStatePartSend");
    }

    public void exitStatePartSend(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("exitStatePartSend");
    }

    public void entryStateWaitReceive(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("entryStateWaitReceive");
    }

    public void exitStateWaitReceive(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("exitStateWaitReceive");
    }

    public void entryStateComplete(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("entryStateComplete");
    }

    public void exitStateComplete(OrderState fromState, OrderState toState, OrderEvent orderEvent, OrderContext orderContext) {
        System.out.println("exitStateComplete");
    }

}
