package com.skyblue.statemachine.statemachine.config;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.AbstractPersistingStateMachineInterceptor;
import org.springframework.stereotype.Component;
import com.skyblue.statemachine.statemachine.statemachinenum.CreditEvent;
import com.skyblue.statemachine.statemachine.statemachinenum.CreditState;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhao
 * @Description:
 * @create 2019/10/08 16:13
 * 状态机运行时持久化
 *
 *
 * 仅用作配置
 * 使用方法
 * 存储状态机---stateMachineRuntimePersister.persist(stateMachine,order1.getId());
 * 恢复状态机---stateMachineRuntimePersister.restore(stateMachine,order1.getId());
 *
 * 恢复状态机即获取一个新的状态机实例，获取之前保存的状态机的状态，把新状态机的状态置为保存的状态
 */


@Component
public class StateMachineRuntimePersist extends AbstractPersistingStateMachineInterceptor<CreditState, CreditEvent,String> {

//    @Override
//    public void write(StateMachineContext<S, E> stateMachineContext, T t) throws Exception {
//
//    }
//
//    @Override
//    public StateMachineContext<S, E> read(T t) throws Exception {
//        return null;
//    }

    private Map<String, StateMachineContext<CreditState, CreditEvent>> map = new HashMap<String, StateMachineContext<CreditState, CreditEvent>>();


    @Override
    public void write(StateMachineContext<CreditState, CreditEvent> context, String str) throws Exception {
        map.put(str, context);
    }


    @Override
    public StateMachineContext<CreditState, CreditEvent> read(String str) throws Exception {
        return map.get(str);
    }
}
