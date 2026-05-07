package com.ecommerce.notification_service.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean  //por defecto spring envia en byte de java
    public MessageConverter messageConverter(){
        return  new JacksonJsonMessageConverter(); // cmabia el idioma de los mensajes a json
    }
    @Bean
    public Queue notificationQueue(){ //creamos la cola lugar donde se almacen las colas
        return new Queue("notification-queue",true); //si rabbit se reinicia que la cola sobrevia
    }
    @Bean
    public TopicExchange orderEventsExchange() {
        return new TopicExchange("order-events");
    } //distrubuidor recibe el mesanje y decide donde tiene que almacenarlo

    @Bean
    public Binding binding(Queue notificationQueue, TopicExchange orderEventeExchange){ //une contrado conecta el exchange con la cola
        return BindingBuilder.bind(notificationQueue).to(orderEventeExchange).with("order-placed");
    }
}
