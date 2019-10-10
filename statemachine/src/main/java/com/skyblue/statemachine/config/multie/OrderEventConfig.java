package com.skyblue.statemachine.config.multie;

import com.skyblue.statemachine.config.simple.OrderEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine(id="orderMachine")
public class OrderEventConfig {
private Logger logger = LoggerFactory.getLogger(getClass());
	
    /**
     * 当前状态UNPAID
     */
    @OnTransition(target = "UNPAID")
    public void create() {
        logger.info("---订单创建，待支付---");
    }
    
    /**
     * UNPAID->WAITING_FOR_RECEIVE 执行的动作
     */
    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay(Message<OrderEvents> message) {
    	System.out.println("传递的参数：" + message.getHeaders().get("order"));
        logger.info("---用户完成支付，待收货---");
    }
    
    /**
     * WAITING_FOR_RECEIVE->DONE 执行的动作 接收多个参数
     */
    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive(Message<OrderEvents> message) {
    	System.out.println("传递的参数：" + message.getHeaders().get("order"));
    	System.out.println("传递的参数：" + message.getHeaders().get("otherObj"));
        logger.info("---用户已收货，订单完成---");
    }

}
