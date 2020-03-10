package com.ryq.inspiration.rabbitconmuser;

import com.ryq.inspiration.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

//消费者 监听类
//负责监听并消费队列中的数据
@Component
public class DirectExchangeReceiver {
    //参数为：监听的队列名称 FirstQueue
    @RabbitListener(queues = RabbitConfig.FirstQueueName)
    public void process(Map testMessage) {
        System.out.println("消费者收到FirstQueue的消息  : " + testMessage.toString());
    }
    //参数为：监听的队列名称 SecondQueue
    @RabbitListener(queues = RabbitConfig.SecondQueueName)
    public void process2(Map testMessage) {
        System.out.println("消费者收到SecondQueue的消息  : " + testMessage.toString());
    }
    //参数为：监听的队列名称 ThirdQueue
    @RabbitListener(queues = RabbitConfig.ThirdQueueName)
    public void process5(Map testMessage) {
        System.out.println("消费者收到ThirdQueue的消息  : " + testMessage.toString());
    }
}