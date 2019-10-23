package com.skyblue.statemachine.web;

import com.skyblue.statemachine.statemachine.Order;
import com.skyblue.statemachine.statemachine.statemachinenum.CreditEvent;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shkstart
 * @create 2019-10-08 下午11:44
 */
@RestController
@RequestMapping("/sm")
public class StateMachineControllerTest {

    @Autowired
    private StateMachineService creditStateMachineService;

    @Autowired
    private StateMachinePersister stateMachineRuntimePersister;

    private ArrayList<Order> orderList = new ArrayList<>();

    Order order1 = new Order("1","","","","-1");

    Order order2 = new Order("2","","","","-1");

    @RequestMapping("/test1")
    public void testFormState() throws Exception {

        orderList.add(order1);
        orderList.add(order2);

        //machineId用业务类型加订单id
        StateMachine stateMachine = creditStateMachineService.acquireStateMachine("credit".concat("_").concat(order1.getId()));

//        StateMachine stateMachine1 = creditStateMachineService.acquireStateMachine("credit1");

//        StateMachine stateMachine1 = creditStateMachineService.acquireStateMachine("credit");

        System.out.println(stateMachine);
//        System.out.println(stateMachine1);

        System.out.println("=====");
        System.out.println(stateMachine.getState().getId());
        System.out.println("=====");


        Message<CreditEvent> message = MessageBuilder
                .withPayload(CreditEvent.REBUILD_CREDIT_SUCC)
                //可以多个setHeader连用,传递多个对象
                //.setHeader("foo", order).setHeader("foo", order)
                .setHeader("order", order1)
                .build();

        stateMachine.sendEvent(message);

        System.out.println("=====");

        System.out.println(stateMachine.getState().getId());

        order1.setState("0");
        stateMachineRuntimePersister.persist(stateMachine,order1.getId());


    }


    @RequestMapping("/test2")
    public void testForm() throws Exception {


        StateMachine stateMachine = creditStateMachineService.acquireStateMachine("credit2");
        System.out.println(stateMachine);

        stateMachineRuntimePersister.restore(stateMachine,order1.getId());
        System.out.println(stateMachine);

        Message<CreditEvent> message = MessageBuilder
                .withPayload(CreditEvent.AUDIT_CREDIT_SUCC)
                //可以多个setHeader连用,传递多个对象
                //.setHeader("foo", order).setHeader("foo", order)
                .setHeader("order", order1)
                .build();

        stateMachine.sendEvent(message);

        System.out.println("=====");
        System.out.println(stateMachine.getState().getId());
        System.out.println("=====");

        creditStateMachineService.releaseStateMachine("credit".concat("_").concat(order1.getId()));



        StateMachine stateMachineTest = creditStateMachineService.acquireStateMachine("credit".concat("_").concat(order1.getId()));

        System.out.println(stateMachineTest);









    }



}
