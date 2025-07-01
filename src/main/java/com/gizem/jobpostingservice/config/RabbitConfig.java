package com.gizem.jobpostingservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Value("${app.rabbitmq.queue}")
    private String queue;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Bean
    public Queue jobQueue() {
        return new Queue(queue, true);
    }

    @Bean
    public TopicExchange jobExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(Queue jobQueue, TopicExchange jobExchange) {
        return BindingBuilder.bind(jobQueue).to(jobExchange).with(routingKey);
    }
}
