package com.hsshy.beam.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列
@Configuration
public class TopicRabbitConfig {

    final static String message = "topic.message";
    final static String messages = "topic.messages";

    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.messages);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        // #：相当于一个或者多个单词，例如一个匹配模式是topic.#，那么，以topic开头的路由键都是可以的
        // *：相当于一个单词，例如一个匹配模式是topic.*，那么，以topic开头的路由键,后面接一个单词的都可以
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}