package com.hsshy.beam.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "common_queue")
public class CustomerService {
 
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
 
}