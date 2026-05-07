package com.ecommerce.order_service.config;


import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME=  "order-events"; //creamos para que exista fisicamnete en el servidor
    //el order service en via el mensaje al exchange sin conercer los consumidores, la flexibilidad y enrutamiento por patrones
    @Bean  //
    public MessageConverter messageConverter(){
        return  new JacksonJsonMessageConverter(); // cmabia el idioma de los mensajes a json
    }
    @Bean
    public TopicExchange orderEventsExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
}
