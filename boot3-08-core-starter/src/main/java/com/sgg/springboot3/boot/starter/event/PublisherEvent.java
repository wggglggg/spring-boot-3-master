package com.sgg.springboot3.boot.starter.event;

import com.sgg.springboot3.boot.starter.properties.UserProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * ClassName: PublisherEvent
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-25 11:09
 * @Version 1.0
 */
@Service
public class PublisherEvent implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // 发送事件
    public void sendEvent(ApplicationEvent event){
        // 使用boot提供的publishEvent发送事件
        applicationEventPublisher.publishEvent(event);
    }
}
