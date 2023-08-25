package com.sgg.springboot3.boot307core.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * ClassName: AdviceEvent
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-24 15:01
 * @Version 1.0
 */
@Service
public class EventPublisher implements ApplicationEventPublisherAware {

    /**
     * 底层发送事件用的组件，SpringBoot会通过ApplicationEventPublisherAware接口自动注入给我们
     * 事件是广播出去的。所有监听这个事件的监听器都可以收到
     */
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * 会被自动调用，把真正发事件的底层组组件给我们注入进来
     * @param applicationEventPublisher event publisher to be used by this object
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 所有事件都可以发
     * @param event
     */
    public void sendEvent(ApplicationEvent event){
        applicationEventPublisher.publishEvent(event);
    }

}
