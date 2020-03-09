package com.ryq.inspiration.controller;

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
@RequestMapping(value = "/rabbitMQ")
public class RabbitMQ {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/SendMess")
    @ResponseBody
    public String SendMess(@RequestParam(value = "mess") String messageData){
        //生成msg
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        //第一个参数:交换机 exchange 第二个参数：路由键 routingkey 第三个参数：msg
        rabbitTemplate.convertAndSend("MyDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

}
