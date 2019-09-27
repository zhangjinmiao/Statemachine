package com.lyxiang.fluent;

import com.lyxiang.common.OrderContext;
import com.lyxiang.common.OrderEvent;
import com.lyxiang.common.OrderState;
import com.lyxiang.common.OrderCondition;
import org.squirrelframework.foundation.component.SquirrelProvider;
import org.squirrelframework.foundation.fsm.DotVisitor;
import org.squirrelframework.foundation.fsm.StateMachineBuilder;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;

/**
 * @author: liyuxiang
 * @create: 2019-01-23
 */
public class Main {

    public static void main(String[] args) {

        OrderContext orderContext = new OrderContext();

        StateMachineBuilder<OrderStateMachine, OrderState, OrderEvent, OrderContext> builder = StateMachineBuilderFactory.create(OrderStateMachine.class, OrderState.class, OrderEvent.class, OrderContext.class);

        builder.onEntry(OrderState.INIT).callMethod("entryStateInit");
        builder.onExit(OrderState.INIT).callMethod("exitStateInit");

        builder.onEntry(OrderState.WAIT_PAY).callMethod("entryStateWaitPay");
        builder.onExit(OrderState.WAIT_PAY).callMethod("exitStateWaitPay");

        builder.onEntry(OrderState.WAIT_SEND).callMethod("entryStateWaitSend");
        builder.onExit(OrderState.WAIT_SEND).callMethod("exitStateWaitSend");

        builder.onEntry(OrderState.PART_SEND).callMethod("entryStatePartSend");
        builder.onExit(OrderState.PART_SEND).callMethod("exitStatePartSend");

        builder.onEntry(OrderState.WAIT_RECEIVE).callMethod("entryStateWaitReceive");
        builder.onExit(OrderState.WAIT_RECEIVE).callMethod("exitStateWaitReceive");

        builder.onEntry(OrderState.COMPLETE).callMethod("entryStateCompleteByAdmin");
        builder.onExit(OrderState.COMPLETE).callMethod("entryStateCompleteByAdmin");

        builder.externalTransition().from(OrderState.INIT).to(OrderState.WAIT_PAY).on(OrderEvent.SUBMIT_ORDER).callMethod("stateInitToWaitPay");
        builder.externalTransition().from(OrderState.WAIT_PAY).to(OrderState.WAIT_SEND).on(OrderEvent.PAY).callMethod("stateWaitPayToWaitSend");
        builder.externalTransition().from(OrderState.WAIT_SEND).to(OrderState.PART_SEND).on(OrderEvent.PART_SEND).when(new OrderCondition()).callMethod("stateWaitSendToPartSend");
        builder.externalTransition().from(OrderState.PART_SEND).to(OrderState.WAIT_RECEIVE).on(OrderEvent.SEND).callMethod("statePartSendToWaitReceive");
        builder.externalTransition().from(OrderState.WAIT_RECEIVE).to(OrderState.COMPLETE).on(OrderEvent.COMPLETE).callMethod("stateWaitReceiveToComplete");

        OrderStateMachine fsm = builder.newStateMachine(OrderState.INIT);
        fsm.start();
        fsm.fire(OrderEvent.SUBMIT_ORDER, orderContext);
        fsm.fire(OrderEvent.PAY, orderContext);
        fsm.fire(OrderEvent.PART_SEND, orderContext);
        fsm.fire(OrderEvent.SEND, orderContext);
        fsm.fire(OrderEvent.COMPLETE, orderContext);

        //生成状态图
        DotVisitor visitor = SquirrelProvider.getInstance().newInstance(DotVisitor.class);
        fsm.accept(visitor);
        visitor.convertDotFile("MyStateMachine");

        fsm.terminate();
    }

}
