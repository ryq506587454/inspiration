package com.ryq.inspiration.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //=========名称配置============
    //指定交换机名称
    public final static String DirectExchangeName = "Ryq.DirectExchange";
    public final static String TopicExchangeName = "Ryq.TopicExchange";
    public final static String FanoutExchangeName = "Ryq.FanoutExchange";
    //指定队列名称
    public final static String FirstQueueName = "FirstQueue";
    public final static String SecondQueueName = "SecondQueue";
    public final static String ThirdQueueName = "ThirdQueue";
    //指定路由键
    public final static String FirstDirectRoutingKey = "FirstDirect";
    public final static String SecondDirectRoutingKey = "SecondDirect";
    public final static String FirstTopicRoutingKey = "topic.a";
    public final static String SecondTopicRoutingKey = "topic.b";
    public final static String ThirdTopicRoutingKey = "topic.#";

    //=========队列 Queue============
    //=========第一个参数为队列名称 第二个参数为是否持久化(可选)============
    //新建第一个队列
    @Bean
    public Queue FirstQueue(){
        return new Queue(RabbitConfig.FirstQueueName,true);
    }
    //新建第二个队列
    @Bean
    public Queue SecondQueue(){
        return new Queue(RabbitConfig.SecondQueueName,true);
    }
    //新建第三个队列
    @Bean
    public Queue ThirdQueue(){
        return new Queue(RabbitConfig.ThirdQueueName,true);
    }

    //=========交换机 Exchange============
    //=========参数为交换机名称============
    //新建一个叫做Ryq.DirectExchange的Direct交换机
    @Bean
    DirectExchange DirectExchange() {
        return new DirectExchange(RabbitConfig.DirectExchangeName);
    }
    //新建一个叫做Ryq.TopicExchange的Topic交换机
    @Bean
    TopicExchange TopicExchange() {
        return new TopicExchange(RabbitConfig.TopicExchangeName);
    }
    //新建一个叫做Ryq.FanoutExchange的Fanout交换机
    @Bean
    FanoutExchange FanoutExchange() {
        return new FanoutExchange(RabbitConfig.FanoutExchangeName);
    }


    //=========绑定 Bind
    //=========参数依次为：队列、交换机、路由键(匹配键)============
    //将FirstQueue队列和Ryq.DirectExchange交换机绑定
    //设置匹配键：FirstDirect
    @Bean
    Binding bindingFirstQueueToDirectExchange() {
        return BindingBuilder.bind(FirstQueue()).to(DirectExchange()).with(RabbitConfig.FirstDirectRoutingKey);
    }
    //将SecondQueue队列和Ryq.DirectExchange交换机绑定
    //设置匹配键：SecondDirect
    @Bean
    Binding bindingSecondQueueToDirectExchange(){
        return BindingBuilder.bind(SecondQueue()).to(DirectExchange()).with(RabbitConfig.SecondDirectRoutingKey);
    }

    //将FirstQueue队列和Ryq.TopicExchange交换机绑定
    //设置匹配键：topic.a
    @Bean
    Binding bindingFirstQueueToTopicExchange(){
        return BindingBuilder.bind(FirstQueue()).to(TopicExchange()).with(RabbitConfig.FirstTopicRoutingKey);
    }
    //将SecondQueue队列和Ryq.TopicExchange交换机绑定
    //设置匹配键：topic.a
    @Bean
    Binding bindingSecondQueueToTopicExchange(){
        return BindingBuilder.bind(SecondQueue()).to(TopicExchange()).with(RabbitConfig.SecondTopicRoutingKey);
    }
    //将ThirdQueue队列和Ryq.TopicExchange交换机绑定
    //使用通配符#设置匹配键：topic.#(表示topic.xxx都可以进入)
    @Bean
    Binding bindingThirdQueueToTopicExchange(){
        return BindingBuilder.bind(ThirdQueue()).to(TopicExchange()).with(RabbitConfig.ThirdTopicRoutingKey);
    }

    //将SecondQueue队列和Ryq.FanoutExchange交换机绑定
    //设置匹配键：Fanout型交换机不需要路由键
    @Bean
    Binding bindingSecondQueueToFanoutExchange(){
        return BindingBuilder.bind(SecondQueue()).to(FanoutExchange());
    }
    //将ThirdQueue队列和Ryq.FanoutExchange交换机绑定
    //设置匹配键：Fanout型交换机不需要路由键
    @Bean
    Binding bindingThirdQueueToFanoutExchange(){
        return BindingBuilder.bind(ThirdQueue()).to(FanoutExchange());
    }

}
