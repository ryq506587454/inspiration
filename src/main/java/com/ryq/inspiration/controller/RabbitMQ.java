package com.ryq.inspiration.controller;

import com.ryq.inspiration.config.RabbitConfig;
import com.ryq.inspiration.util.MessUtil;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@Api
@RequestMapping(value = "/rabbitMQ")
public class RabbitMQ {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/SendMess")
    @ResponseBody
    public String SendMess(@RequestParam(value = "directMess") String direct,
                           @RequestParam(value = "topicMess") String topic,
                           @RequestParam(value = "fanoutMess") String fanout){

        //将消息携带绑定键值发送到交换机DirectExchange
        //第一个参数:交换机 exchange 第二个参数：路由键 routingkey 第三个参数：msg
        rabbitTemplate.convertAndSend(RabbitConfig.DirectExchangeName, RabbitConfig.FirstDirectRoutingKey, MessUtil.creatMessage(direct));
        rabbitTemplate.convertAndSend(RabbitConfig.DirectExchangeName, RabbitConfig.SecondDirectRoutingKey, MessUtil.creatMessage(direct));
        //将消息携带绑定键值发送到交换机TopicExchange
        //第一个参数:交换机 exchange 第二个参数：路由键 routingkey 第三个参数：msg
        //因为第三个队列绑定的路由键使用了通配符,所以路由键符合配对要求就行
        rabbitTemplate.convertAndSend(RabbitConfig.TopicExchangeName, RabbitConfig.FirstTopicRoutingKey, MessUtil.creatMessage(topic));
        rabbitTemplate.convertAndSend(RabbitConfig.TopicExchangeName, RabbitConfig.SecondTopicRoutingKey, MessUtil.creatMessage(topic));
        rabbitTemplate.convertAndSend(RabbitConfig.TopicExchangeName, "topic.c", MessUtil.creatMessage(topic));
        //将消息携带绑定键值发送到交换机FanoutExchange
        //第一个参数:交换机 exchange 第二个参数：路由键 routingkey 第三个参数：msg
        //FanoutExchange不需要配置路由键位，所以第二个参数为null
        rabbitTemplate.convertAndSend(RabbitConfig.FanoutExchangeName,null, MessUtil.creatMessage(fanout));
        return "ok";
    }

}
