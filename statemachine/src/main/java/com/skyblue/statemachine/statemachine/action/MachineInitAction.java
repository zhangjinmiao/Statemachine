package com.skyblue.statemachine.statemachine.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author wangzhao
 * @create 2019-09-25 上午11:54
 * 状态机初始化动作
 * 记录起点，初始化状态机context
 */

@Slf4j
@Component("machineInitAction")
public class MachineInitAction<CreditState, CreditEvent> implements BaseAction<CreditState, CreditEvent>{


    @Override
    public void execute(StateContext stateContext) {
        log.info("状态机初始化context",stateContext);
    }
}
