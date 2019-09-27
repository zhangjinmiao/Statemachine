package com.lyxiang.spring;

import com.lyxiang.common.OrderState;
import org.springframework.stereotype.Service;

/**
 * @author: liyuxiang
 * @create: 2019-01-22
 */
@Service
public class SubmitOrderStateMachineEngine extends AbstractOrderStatusMachineEngine<SubmitOrderStateMachine> {

    @Override
    protected boolean accept(OrderState orderState) {
        return OrderState.INIT == orderState;
    }

}
