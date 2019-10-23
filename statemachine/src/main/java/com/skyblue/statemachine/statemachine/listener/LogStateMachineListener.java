package com.skyblue.statemachine.statemachine.listener;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author wangzhao
 * @Description:
 * @create 2019/10/08 16:49
 *  通用处理，比如日志记录之类的，不做业务！
 *  打印日志日志，监听状态机订单流转全过程！
 *
 */

// @WithStateMachine(id = "creditStatesListener")
// 这种方式@OnTransition(target = "CREDIT_CHECK_REBUILD",source = "CREDIT_PARTNER_INPUT")
//里面只能用"CREDIT_CHECK_REBUILD"不能用State.XXX枚举形式，使用意义不大
//且@WithStateMachine(id = "creditStatesListener")必须与machined对应（enableFactory与beanFactory情况下）

@WithStateMachine
public class LogStateMachineListener<CreditState, CreditEvent> {

    @OnTransition
    public void anyTransition() {
        System.out.println("--- OnTransition --- init");
    }

    @OnTransition(target = "CREDIT_CHECK_REBUILD",source = "CREDIT_PARTNER_INPUT")
    public void fromlow1to0() {
        System.out.println("log--- OnTransition --- INPUT--REBUILD");
    }


    @OnTransition(target = "CREDIT_AUDIT",source = "CREDIT_CHECK_REBUILD")
    public void from1to2() {
        System.out.println("log--- OnTransition --- REBUILD--AUDIT");
    }
}
