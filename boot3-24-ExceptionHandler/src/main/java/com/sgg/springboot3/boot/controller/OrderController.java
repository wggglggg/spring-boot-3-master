package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.vo.OrderVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: OrderController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 19:46
 * @Version 1.0
 */
@RestController
public class OrderController {

    @PostMapping("/order/new")
    public String createOrder(@Validated @RequestBody OrderVo orderVo){

        return "订单信息：" + orderVo;
    }
}
