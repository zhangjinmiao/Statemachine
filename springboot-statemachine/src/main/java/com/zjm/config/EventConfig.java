package com.zjm.config;

import com.zjm.enums.Events;
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
@WithStateMachine(id="orderMachine")
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
}
