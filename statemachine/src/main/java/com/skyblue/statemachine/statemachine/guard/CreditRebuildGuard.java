package com.skyblue.statemachine.statemachine.guard;

import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author shkstart
 * @create 2019-09-25 上午10:05
 * guard保护状态转换--相当于屏蔽了event，action、transition不会继续执行了
 * 根据返回值boolean类型确定是否
 */
@Component("creditRebuildGuard")
public class CreditRebuildGuard<CreditState, CreditEvent> implements BaseGuard<CreditState, CreditEvent> {
    @Override
    public boolean evaluate(StateContext<CreditState, CreditEvent> stateContext) {
        //守卫逻辑
        return false;
    }
}
