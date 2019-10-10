package com.skyblue.statemachine.config.persist;
import com.skyblue.statemachine.config.simple.OrderEvents;
import com.skyblue.statemachine.config.simple.OrderStates;
import java.util.HashMap;
import java.util.Map;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

/**
 * 在内存中持久化状态机
 */
@Component
public class InMemoryStateMachinePersist implements StateMachinePersist<OrderStates, OrderEvents, String> {

	private Map<String, StateMachineContext<OrderStates, OrderEvents>> map = new HashMap<String, StateMachineContext<OrderStates,OrderEvents>>();
	
	@Override
	public void write(StateMachineContext<OrderStates, OrderEvents> context, String contextObj) throws Exception {
		map.put(contextObj, context);
	}

	@Override
	public StateMachineContext<OrderStates, OrderEvents> read(String contextObj) throws Exception {
		return map.get(contextObj);
	}

}