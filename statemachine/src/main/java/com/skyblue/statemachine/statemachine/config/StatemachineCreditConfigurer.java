package com.skyblue.statemachine.statemachine.config;

import com.skyblue.statemachine.statemachine.listener.CreditStateMachineListener1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import com.skyblue.statemachine.statemachine.listener.LogStateMachineListener;
import com.skyblue.statemachine.statemachine.action.BaseAction;
import com.skyblue.statemachine.statemachine.statemachinenum.CreditEvent;
import com.skyblue.statemachine.statemachine.statemachinenum.CreditState;

import java.util.EnumSet;

/**
 * @Author: wangzhao
 * @Date: 2018/9/28 21:03
 * 业务状态机配置
 * 配置业务迁移表
 */
@Configuration
@EnableStateMachineFactory(name="creditStateMachineFactory",contextEvents = false)
public class StatemachineCreditConfigurer extends EnumStateMachineConfigurerAdapter<CreditState, CreditEvent> {



    //订单状态机监听器（也可以做相关的业务）
    @Autowired
    private CreditStateMachineListener1<CreditState, CreditEvent> listener;

    //日志监听器(打印日志，显示状态)
    @Autowired
    private LogStateMachineListener<CreditState, CreditEvent> logStateMachineListener;



    //待执行动作
    @Autowired
    @Qualifier("creditAuditAction")
    private BaseAction<CreditState, CreditEvent> creditAuditAction;

    @Autowired
    @Qualifier("creditRebuildAction")
    private BaseAction<CreditState, CreditEvent> creditRebuildAction;

    @Autowired
    @Qualifier("errorHandleAction")
    private BaseAction<CreditState, CreditEvent> errorHandleAction;






    //配置初态--lock状态
    //加载所有状态到状态机
    //可以同时配置状态进入、退出时的动作--api 11.7
    //state()如果状态机在初始状态和非初始状态之间来回转换，则执行用定义的动作。
    @Override
    public void configure(StateMachineStateConfigurer<CreditState, CreditEvent> states)
            throws Exception {
            states
                .withStates()
                // 初始状态：合作方推标
                .initial(CreditState.CREDIT_PARTNER_INPUT)
                //结束状态。。。。。。。。。。。。。。。。。。。。。。。。。。。。
//                .end()
                .states(EnumSet.allOf(CreditState.class));
    }

    //配置转换--状态迁移表：流式调用中包含action与guard
    //unlock--push-->locked
    //lock--coin-->unlocked
    @Override
    public void configure(StateMachineTransitionConfigurer<CreditState, CreditEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                    .source(CreditState.CREDIT_PARTNER_INPUT).target(CreditState.CREDIT_CHECK_REBUILD)
                    .event(CreditEvent.REBUILD_CREDIT_SUCC)
                    .action(creditRebuildAction,errorHandleAction)//顺序执行

                .and()
                .withExternal()
                    .source(CreditState.CREDIT_CHECK_REBUILD).target(CreditState.CREDIT_AUDIT)
                    .event(CreditEvent.AUDIT_CREDIT_SUCC)
                    .action(creditAuditAction,errorHandleAction)
        ;
    }

    //配置状态机监听类
    @Override
    public void configure(StateMachineConfigurationConfigurer<CreditState, CreditEvent> config)
            throws Exception {

        config
                .withConfiguration()
                //注册监听器
                .listener(listener)
//                .listener(logStateMachineListener)
        ;
    }








}
