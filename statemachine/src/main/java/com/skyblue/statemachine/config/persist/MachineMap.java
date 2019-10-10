package com.skyblue.statemachine.config.persist;

import com.skyblue.statemachine.config.form.FormEvents;
import com.skyblue.statemachine.config.form.FormStates;
import com.skyblue.statemachine.config.simple.OrderEvents;
import com.skyblue.statemachine.config.simple.OrderStates;
import java.util.HashMap;
import java.util.Map;

import org.springframework.statemachine.StateMachine;

/**存放
 * @author wphmo
 *
 */
public class MachineMap {
	public static Map<String,StateMachine<OrderStates, OrderEvents>> orderMap = new HashMap<String,StateMachine<OrderStates, OrderEvents>>();
	public static Map<String,StateMachine<FormStates, FormEvents>> formMap = new HashMap<String,StateMachine<FormStates, FormEvents>>();

}
