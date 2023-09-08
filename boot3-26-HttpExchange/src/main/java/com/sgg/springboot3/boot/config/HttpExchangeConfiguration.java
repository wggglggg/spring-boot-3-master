package com.sgg.springboot3.boot.config;

import com.sgg.springboot3.boot.modle.Todo;
import com.sgg.springboot3.boot.service.AlbumsService;
import com.sgg.springboot3.boot.service.TodoService;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.net.http.HttpRequest;

/**
 * ClassName: HttpExchangeConfiguration
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 11:10
 * @Version 1.0
 */
@Configuration
public class HttpExchangeConfiguration {
    @Bean
    public TodoService todoService() {
        WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();

        TodoService todoService = proxyFactory.createClient(TodoService.class);

        return todoService;
    }

    //创建AlbumsService代理,使用HttpExchange GetExchange PostExchange等等组合实现， 不一定要在WebClient里面添加url地址
    //@HttpExchange(url = "https://jsonplaceholder.typicode.com")
    //@HttpExchange(method = "GET", url = "/albums/{id}")
    //@PostExchange(url = "/albums")

//    @Bean
//    public AlbumsService albumsService() {
//        WebClient webClient = WebClient.create();
//
//        WebClientAdapter webClientAdapter = WebClientAdapter.forClient(webClient);
//
//        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder(webClientAdapter).build();
//
//        return proxyFactory.createClient(AlbumsService.class);
//    }

    //定制Http服务
    @Bean
    public AlbumsService albumsServiceCustom() {
        //超时
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(1));
                    connection.addHandlerLast(new WriteTimeoutHandler(1));
                });

        //设置异常
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultStatusHandler(HttpStatusCode::isError, clientResponse -> {
                    System.out.println("*********WebClient请求异常***********");
                    return Mono.error(new RuntimeException("请求异常" + clientResponse.statusCode().value()));
                }).build();

        WebClientAdapter clientAdapter = WebClientAdapter.forClient(webClient);

        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder(clientAdapter).build();

        return proxyFactory.createClient(AlbumsService.class);
    }
}
