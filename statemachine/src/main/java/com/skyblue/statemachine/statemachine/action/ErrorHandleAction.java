package com.skyblue.statemachine.statemachine.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author wangzhao
 * @create 2019-09-24 下午10:32
 * 获取stateContext中的异常，将其输出到错误日志中！！
 *
 * （这里并没有什么业务逻辑，只是封装了异常发生时的信息）
 * （将发生的异常信息记录在StateMachineContext中，在外部可以根据这个这个值是否存在来判断是否有异常发生。）
 */

@Slf4j
@Component("errorHandleAction")
public class ErrorHandleAction<CreditState, CreditEvent> implements BaseAction<CreditState, CreditEvent>{


    @Override
    public void execute(StateContext stateContext) {

        // RuntimeException("MyError") added to stateContext
        Exception exception = stateContext.getException();
        exception.getMessage();

//        RuntimeException exception = (RuntimeException) stateContext.getException();
//        log.error("stateMachine execute error = ", exception);
//        stateContext.getStateMachine()
//                    .getExtendedState().getVariables()
//                    .put(RuntimeException.class, exception);
    }
}
