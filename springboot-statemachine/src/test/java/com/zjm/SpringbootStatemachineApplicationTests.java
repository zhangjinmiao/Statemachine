package com.zjm;

import com.zjm.enums.Events;
import com.zjm.enums.States;
import com.zjm.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootStatemachineApplicationTests {

	@Autowired
	private StateMachine<States, Events> stateMachine;

	@Test
	public void contextLoads() {
		//创建流程
		stateMachine.start();

		//触发PAY事件
		stateMachine.sendEvent(Events.PAY);

		//触发RECEIVE事件
		//stateMachine.sendEvent(Events.RECEIVE);

		Order order = new Order("35349645567", "547568678", "广东省深圳市", "13435465465", "RECEIVE");
		Message<Events> message = MessageBuilder.withPayload(Events.RECEIVE).setHeader("order", order).build();
		stateMachine.sendEvent(message);

		//获取最终状态
		System.out.println("最终状态：" + stateMachine.getState().getId());
	}

}
