package com.lyxiang.declarative;

import com.lyxiang.common.OrderEvent;
import com.lyxiang.common.OrderState;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * @author: liyuxiang
 * @create: 2019-01-24
 */
public class Main {

    public static void main(String[] args) {

        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(DeclarativeOrderMachine.class);

        UntypedStateMachine fsm = builder.newAnyStateMachine(OrderState.INIT);

        fsm.start();

        fsm.fire(OrderEvent.SUBMIT_ORDER);
        fsm.fire(OrderEvent.PAY);
        fsm.fire(OrderEvent.PART_SEND);
        fsm.fire(OrderEvent.SEND);
        fsm.fire(OrderEvent.COMPLETE);

        fsm.terminate();

    }

}
