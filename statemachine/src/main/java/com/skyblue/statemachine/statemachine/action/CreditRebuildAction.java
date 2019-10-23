package com.skyblue.statemachine.statemachine.action;

import com.skyblue.statemachine.statemachine.Order;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author wangzhao
 * @create 2019-09-24 下午3:20
 * 重建标的完成后状态机的行为
 * -->处理签章
 * -->发送消息去风控审核
 */

@Component("creditRebuildAction")
public class CreditRebuildAction<CreditState, CreditEvent> implements BaseAction<CreditState, CreditEvent>{


    @Override
    public void execute(StateContext stateContext) {

        //模拟业务处理************带订单号
        Order order = (Order) stateContext.getMessageHeader("order");

        System.out.println("模拟业务处理--RebuildAction--订单id="+order.getId());

        System.out.println("credit--RebuildAction---（-1->0）--执行成功");
        //模拟业务处理************

//        int i = 1/0;

    }
}
