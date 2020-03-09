package com.ryq.inspiration.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {
    //新建
    //新建一个叫做DirectQueue的队列
    //第一个参数为队列名称 第二个参数为是否持久化(可选)
    @Bean
    public Queue DirectQueue(){
        return new Queue("DirectQueue",true);
    }
    //新建
    //新建一个叫做MyDirectExchange的Direct交换机
    //参数为交换机名称
    @Bean
    DirectExchange MyDirectExchange() {
        return new DirectExchange("MyDirectExchange");
    }
    //绑定
    //将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    //最后的那个参数为路由键(匹配键)
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(DirectQueue()).to(MyDirectExchange()).with("TestDirectRouting");
    }
}
