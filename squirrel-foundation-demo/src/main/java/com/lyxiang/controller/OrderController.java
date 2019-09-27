package com.lyxiang.controller;

import com.google.common.collect.ImmutableMap;
import com.lyxiang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: liyuxiang
 * @create: 2019-01-29
 */
@RestController
@RequestMapping(value = "/order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "submitOrder")
    public Map<Object, Object> submitOrder() {
        int orderId = orderService.submitOrder();
        return ImmutableMap.of("orderId", orderId);
    }

    @RequestMapping(value = "pay")
    public Map<Object, Object> pay(){
        int orderId = orderService.pay();
        return ImmutableMap.of("orderId", orderId);
    }
}
