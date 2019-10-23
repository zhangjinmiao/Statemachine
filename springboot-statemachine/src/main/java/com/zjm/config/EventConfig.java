package com.zjm.config;

import com.zjm.annotation.StatesOnTransition;
import com.zjm.enums.Events;
import com.zjm.enums.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 〈一句话功能简述〉<br> 〈指定了状态监听器〉
 *
 * @author zhangjinmiao
 * @create 2019/5/27 15:14
 */
@WithStateMachine(id="orderSingleMachine")
public class EventConfig {
  private Logger logger = LoggerFactory.getLogger(getClass());


  @OnTransition(target = "UNPAID")
  public void create() {
    logger.info("订单创建，待支付");
  }

  @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
  public void pay() {
    logger.info("用户完成支付，待收货");
  }

  @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
  public void receive(Message<Events> message) {
    logger.info("传递的参数：" + message.getHeaders().get("order"));
    logger.info("用户已收货，订单完成");
  }

  // 对于上面例子，source 和 target 不能使用创建的事件状态枚举，只能使用字符串（很容易写错）
  // 所以使用自定义类型安全的注解


  /*@StatesOnTransition(target = States.UNPAID)
  public void create() {
    logger.info("订单创建，待支付");
  }

  @StatesOnTransition(source = States.UNPAID, target = States.WAITING_FOR_RECEIVE)
  public void pay() {
    logger.info("用户完成支付，待收货");
  }

  @StatesOnTransition(source = States.WAITING_FOR_RECEIVE, target = States.DONE)
  public void receive(Message<Events> message) {
    logger.info("传递的参数：" + message.getHeaders().get("order"));
    logger.info("用户已收货，订单完成");
  }
*/
}
