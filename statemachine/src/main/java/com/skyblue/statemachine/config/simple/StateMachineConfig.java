package com.skyblue.statemachine.config.simple;

import java.util.EnumSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;


@Configuration
@EnableStateMachine(name="orderSingleMachine")
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
		states.withStates().initial(OrderStates.UNPAID).states(EnumSet.allOf(OrderStates.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
		transitions.withExternal().source(OrderStates.UNPAID).target(OrderStates.WAITING_FOR_RECEIVE).event(OrderEvents.PAY).and()
				.withExternal().source(OrderStates.WAITING_FOR_RECEIVE).target(OrderStates.DONE).event(OrderEvents.RECEIVE);
	}
}