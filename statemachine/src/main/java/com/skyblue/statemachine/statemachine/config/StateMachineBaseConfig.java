package com.skyblue.statemachine.statemachine.config;

import com.skyblue.statemachine.config.InMemoryStateMachinePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;
import com.skyblue.statemachine.statemachine.statemachinenum.CreditEvent;
import com.skyblue.statemachine.statemachine.statemachinenum.CreditState;

/**
 * @author shkstart
 * @create 2019-10-08 下午9:22
 * 主要包括提供状态机类型、
 * 订单状态更新方法、
 * 运行时持久化配置、persister
 * 日志监听器、listener
 * 订单状态机管理服务、service
 */

@Configuration
public class StateMachineBaseConfig {

    //运行时加入springbean
    @Autowired
    @Qualifier(value = "creditStateMachineFactory")
    private StateMachineFactory creditStateMachineFactory;


    @Autowired
    private StateMachineRuntimePersist stateMachineRuntimePersist;



    /**
     * StateMachinePersister持久化对象
     * 状态机持久化、恢复
     * @return
     */
    @Bean(name="stateMachineRuntimePersister")
    public StateMachinePersister<CreditState, CreditEvent, String> getPersister() {
        return new DefaultStateMachinePersister<>(stateMachineRuntimePersist);
    }


    /**
     * StateMachineService状态机服务对象
     * 状态机获取、释放
     * @return
     */
    @Bean("creditStateMachineService")
    public StateMachineService<CreditState, CreditEvent> creditStateMachineService(){
        return new DefaultStateMachineService<CreditState, CreditEvent>(creditStateMachineFactory,stateMachineRuntimePersist);
    }
}
