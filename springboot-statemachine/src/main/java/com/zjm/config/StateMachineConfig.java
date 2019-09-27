package com.zjm.config;

import com.zjm.enums.Events;
import com.zjm.enums.States;
import java.util.EnumSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * 〈一句话功能简述〉<br> 〈状态机配置类〉
 *
 * @author zhangjinmiao
 * @create 2019/5/27 13:20
 */
@Configuration
@EnableStateMachine(name = "orderSingleMachine") // 启用Spring StateMachine状态机功能
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

  /**
   * 初始化当前状态机拥有哪些状态
   * @param states
   * @throws Exception
   */
  @Override
  public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
    // 定义状态机中的状态
    states.withStates()
        .initial(States.UNPAID) // 初始状态
        .states(EnumSet.allOf(States.class));
  }

  /**
   * 初始化当前状态机有哪些状态迁移动作
   * @param transitions
   * @throws Exception
   */
  @Override
  public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
      throws Exception {
    transitions
        .withExternal()
          .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE) // 指定状态来源和目标
          .event(Events.PAY) // 指定触发事件
          .and()
        .withExternal()
          .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
          .event(Events.RECEIVE);
  }

  /**
   * 为当前的状态机指定了状态监听器
   * @param config
   * @throws Exception
   */
//  @Override
//  public void configure(StateMachineConfigurationConfigurer<States, Events> config)
//      throws Exception {
//    config.withConfiguration()
//        .listener(listener());
//  }
//
//  @Bean
//  public StateMachineListener<States, Events> listener() {
//    return new StateMachineListenerAdapter<States, Events>(){
//      @Override
//      public void transition(Transition<States, Events> transition) {
//        if(transition.getTarget().getId() == States.UNPAID) {
//          log.info("订单创建，待支付");
//          return;
//        }
//
//        if(transition.getSource().getId() == States.UNPAID
//            && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
//          log.info("用户完成支付，待收货");
//          return;
//        }
//
//        if(transition.getSource().getId() == States.WAITING_FOR_RECEIVE
//            && transition.getTarget().getId() == States.DONE) {
//          log.info("用户已收货，订单完成");
//          return;
//        }
//      }
//    };
//  }


}
