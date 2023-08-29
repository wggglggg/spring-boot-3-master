package com.sgg.springboot3.boot.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: ConstellationService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 14:32
 * @Version 1.0
 */
public interface ConstellationService {

    @GetExchange(value = "http://web.juhe.cn/constellation/getAll", accept = "application/json")
    Mono<String> getConstellation(@RequestParam("consName") String consName,
                                  @RequestParam("type") String type,
                                  @RequestParam("key") String key);
}
