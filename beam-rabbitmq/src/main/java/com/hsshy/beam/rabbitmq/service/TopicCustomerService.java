package com.hsshy.beam.rabbitmq.service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicCustomerService {

    @RabbitListener(queues = "topic.message")
    public void message(String msg) {
        System.out.println("message  : " + msg);
    }
    @RabbitListener(queues = "topic.messages")
    public void messages(String msg) {
        System.out.println("messages  : " + msg);
    }

 
}