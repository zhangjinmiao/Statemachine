package com.zjm.enums;

/**
 * 〈一句话功能简述〉<br> 〈事件驱动〉
 *
 * @author zhangjinmiao
 * @create 2019/5/27 13:18
 */
public enum Events {
  PAY,        // 支付 触发状态从待支付UNPAID状态到待收货WAITING_FOR_RECEIVE状态的迁移
  RECEIVE     // 收货 触发状态从待收货WAITING_FOR_RECEIVE状态到结束DONE状态的迁移
}
