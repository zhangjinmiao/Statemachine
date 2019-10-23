package com.skyblue.statemachine.statemachine.statemachinenum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wangzhao
 * @create 2019-09-24 下午1:06
 * 标的流转全状态
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum CreditState {

    CREDIT_PARTNER_INPUT(-1,"合作方推标入表待校验"),
    CREDIT_CHECK_REBUILD(0,"标的重建完成待审核"),
    CREDIT_AUDIT(1,"标的审核完成待定价");


    private int status;
    private String desc;
}
