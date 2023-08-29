package com.sgg.springboot3.boot.service;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: WeightService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 14:59
 * @Version 1.0
 */
public interface WeightService {


    @GetExchange(value = "http://apis.juhe.cn/fapig/calculator/weight", accept = "application/json")
    Mono<String> getStanderWeight(@RequestParam("sex") Integer sex,
                                  @RequestParam("role") Integer role,
                                  @RequestParam("height") Double height,
                                  @RequestParam("weight") Double weight,
                                  @RequestParam("key") String key);


}
