package com.hsshy.beam.rabbitmq.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
 
    @Bean
    public Queue Queue() {
        return new Queue("common_queue");
    }
 
}
