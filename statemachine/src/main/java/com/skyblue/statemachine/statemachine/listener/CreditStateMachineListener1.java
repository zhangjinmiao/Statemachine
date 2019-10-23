package com.skyblue.statemachine.statemachine.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author shkstart
 * @create 2019-10-09 上午12:59
 */

@Slf4j
@Component
public class CreditStateMachineListener1<CreditState, CreditEvent> extends StateMachineListenerAdapter<CreditState, CreditEvent> {

    // 5
    @Override
    public void stateChanged(State<CreditState, CreditEvent> from, State<CreditState, CreditEvent> to) {
        log.info("OrderStateMachineListener stateChanged,source:{},target:{}",from,to);
    }
    // 4
    @Override
    public void stateEntered(State<CreditState, CreditEvent> state) {
        log.info("OrderStateMachineListener stateEntered，state:{}",state.getId());
    }

    @Override
    public void stateExited(State<CreditState, CreditEvent> state) {
        log.info("OrderStateMachineListener stateExited,state:{}",state.getId());
    }

    @Override
    public void eventNotAccepted(Message<CreditEvent> event) {
        log.info("OrderStateMachineListener eventNotAccepted,,event:{}",event.getPayload());
    }
    //3
    @Override
    public void transition(Transition<CreditState, CreditEvent> transition) {
        log.info("OrderStateMachineListener transition,source:{},target:{}",transition,transition.getTarget().getId());
    }
    // 1
    @Override
    public void transitionStarted(Transition<CreditState, CreditEvent> transition) {
        log.info("OrderStateMachineListener transitionStarted,source:{},target:{}",transition,transition.getTarget().getId());
    }
    // 7
    @Override
    public void transitionEnded(Transition<CreditState, CreditEvent> transition) {
        log.info("OrderStateMachineListener transitionEnded,source:{},target:{}",
                transition.getSource(), Objects.nonNull(transition.getTarget()) ? transition.getTarget().getId() : "");
    }
    // 6
    @Override
    public void stateMachineStarted(StateMachine<CreditState, CreditEvent> stateMachine) {
        log.info("OrderStateMachineListener stateMachineStarted");
    }

    @Override
    public void stateMachineStopped(StateMachine<CreditState, CreditEvent> stateMachine) {
        log.info("OrderStateMachineListener stateMachine");
    }

    @Override
    public void stateMachineError(StateMachine<CreditState, CreditEvent> stateMachine, Exception exception) {
        log.info("OrderStateMachineListener stateMachineError",exception);
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        log.info("OrderStateMachineListener extendedStateChanged");
    }
    // 2
    @Override
    public void stateContext(StateContext<CreditState, CreditEvent> stateContext) {
        log.info("OrderStateMachineListener stateContext");
    }
}
