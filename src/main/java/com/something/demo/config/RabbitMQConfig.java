package com.something.demo.config;

import com.something.demo.entity.Notification;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Declarables directBindings() {
        Queue directQueue1 = new Queue("notification_queue", false);
        DirectExchange directExchange = new DirectExchange("directExchange");
        return new Declarables(
                directQueue1,
                directExchange,
                bind(directQueue1).to(directExchange).with("noti")
        );
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverterConverter() {
        return new Jackson2JsonMessageConverter();
    }




}
