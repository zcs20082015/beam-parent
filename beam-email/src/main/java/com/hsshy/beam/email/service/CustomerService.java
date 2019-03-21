package com.hsshy.beam.email.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "common_queue")
public class CustomerService {

    @Autowired
    private EmailService emailService;

    @RabbitHandler
    public void process(String targetAddress) {
        emailService.sendEmail(targetAddress);
    }
 
}