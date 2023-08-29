package com.sgg.springboot3.boot.config;

import com.sgg.springboot3.boot.service.ConstellationService;
import com.sgg.springboot3.boot.service.WeightService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * ClassName: APIConfiguration
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 14:36
 * @Version 1.0
 */
@Configuration
public class JuHeAPIConfiguration {

    @Bean
    public HttpServiceProxyFactory factory(){
        //1、创建客户端
        WebClient webClient = WebClient.builder()
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer.
                            defaultCodecs()
                            .maxInMemorySize(256*1024*1024);
                }).build();

        //2、创建工厂
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();

        return factory;
    }

    @Bean
    public ConstellationService ConstellationInterface(HttpServiceProxyFactory factory){
        ConstellationService client = factory.createClient(ConstellationService.class);

        return client;
    }

    @Bean
    public WeightService WeightInterface(HttpServiceProxyFactory factory){
        WeightService client = factory.createClient(WeightService.class);

        return client;
    }
}
