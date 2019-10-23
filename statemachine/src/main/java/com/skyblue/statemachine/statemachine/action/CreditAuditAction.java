package com.skyblue.statemachine.statemachine.action;

import com.skyblue.statemachine.statemachine.Order;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author wangzhao
 * @create 2019-09-24 下午4:57
 * 审核完成后状态机的行为
 * -->通知合作方
 * -->发送定价消息体
 *
 */

@Component("creditAuditAction")
public class CreditAuditAction <CreditState, CreditEvent> implements BaseAction<CreditState, CreditEvent>{


    @Override
    public void execute(StateContext stateContext) {

        //模拟业务处理************带订单号
        Order order = (Order) stateContext.getMessageHeader("order");

        System.out.println("模拟业务处理--RebuildAction--订单id="+order.getId());

        System.out.println("creditAuditAction--（0->1）---执行成功");
        //模拟业务处理************
    }
}

