package com.sgg.springboot3.boot.service;

import com.sgg.springboot3.boot.properties.JuheCnProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: WeatherService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 10:59
 * @Version 1.0
 */
@Service
public class WeatherService {

    @Autowired
    private JuheCnProperties juheProperties;
    public Mono<String> weatherInterface(String area){

        //1、创建WebClient
        WebClient webClient = WebClient.create();

        //2、准备数据
        Map<String, String> params = new HashMap<>();
        params.put("city", area);
        params.put("key", juheProperties.getWeatherKey());

        //3、定义发请求行为
        Mono<String> mono = webClient.get()
                .uri("http://apis.juhe.cn/simpleWeather/query?city={city}&key={key}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        return mono;
    }
}
