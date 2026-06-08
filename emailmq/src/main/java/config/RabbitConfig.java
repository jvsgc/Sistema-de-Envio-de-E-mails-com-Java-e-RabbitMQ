package com.puc.emailmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_EMAILS = "fila.envio.emails";
    public static final String EXCHANGE_EMAILS = "exchange.direta.emails";
    public static final String ROUTING_KEY_EMAILS = "routing.chave.emails";

    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_EMAILS, true);
    }

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(EXCHANGE_EMAILS);
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange emailExchange) {
        return BindingBuilder.bind(emailQueue).to(emailExchange).with(ROUTING_KEY_EMAILS);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
