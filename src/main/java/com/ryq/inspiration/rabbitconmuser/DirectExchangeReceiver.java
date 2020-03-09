package com.ryq.inspiration.rabbitconmuser;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

//消费者 监听类 负责监听并消费数据
@Component
@RabbitListener(queues = "DirectQueue")//监听的队列名称 DirectQueue
public class DirectExchangeReceiver {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("消费者收到消息  : " + testMessage.toString());
    }
}