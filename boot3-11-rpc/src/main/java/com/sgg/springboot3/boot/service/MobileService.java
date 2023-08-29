package com.sgg.springboot3.boot.service;

import com.sgg.springboot3.boot.properties.JuheCnProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * ClassName: MobileService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 13:49
 * @Version 1.0
 */
@Service
public class MobileService {

    @Autowired
    private JuheCnProperties juheProperties;

    public Mono<String> queryMobileLocation(String mobile){

        //1、创建WebClient
        WebClient webClient = WebClient.create();

        Mono<String> mono = webClient.get()
                .uri("http://apis.juhe.cn/mobile/get?key=" + juheProperties.getMobileKey() + "&phone=" + mobile)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        return mono;
    }
}
