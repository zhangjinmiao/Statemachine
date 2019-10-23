package com.skyblue.statemachine.statemachine.statemachinenum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wangzhao
 * @create 2019-09-24 下午1:07
 * 标的流转驱动事件
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum CreditEvent {

    REBUILD_CREDIT_SUCC("校验知标的信息完整"),
    AUDIT_CREDIT_SUCC("风控通知标的审核通过");

    private String desc;









}
