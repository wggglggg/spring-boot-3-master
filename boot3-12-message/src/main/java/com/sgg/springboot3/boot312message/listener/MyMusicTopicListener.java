package com.sgg.springboot3.boot312message.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * ClassName: MyMusicTopicListener
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 21:31
 * @Version 1.0
 */
@Component
public class MyMusicTopicListener {


    //默认的监听是从消息队列最后一个消息开始拿。新消息才能拿到
    @KafkaListener(topics = "music", groupId = "Music-A")    //监听music Topic
    public void MusicTopicListener(ConsumerRecord record){
        // ConsumerRecord 是kafka为接受端consumer提供的一个方法，可以获取key value
        Object key = record.key();
        Object value = record.value();
        System.out.println("收到消息：key【"+key+"】 value【"+value+"】");
    }

    //拿到以前的完整消息 initialOffset = "0",从索引0开始获取
    @KafkaListener(groupId = "Sport-A" ,topicPartitions = {
            @TopicPartition(topic = "sport", partitionOffsets = {
                    @PartitionOffset(partition = "0", initialOffset = "0")
            })
    })
    public void SportTopicListener(ConsumerRecord record){
        Object key = record.key();
        Object value = record.value();
        System.out.println("收到消息：key【"+key+"】 value【"+value+"】");
    }


}
