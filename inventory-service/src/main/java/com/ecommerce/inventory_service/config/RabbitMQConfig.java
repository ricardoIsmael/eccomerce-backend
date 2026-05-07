package com.ecommerce.inventory_service.config;


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


    @Bean  //
    public MessageConverter messageConverter(){
        return  new JacksonJsonMessageConverter(); // combier el objeto java een json
    }
    @Bean
    public Queue inventoryQueue(){ //creamos la cola lugar donde se almacen las colas
        return new Queue("inventory-queue",true); //si rabbit se reinicia que la cola sobrevia
    }
    @Bean
    public TopicExchange orderEventsExchange() {
        return new TopicExchange("order-events");
    } //distrubuidor recibe el mesanje y decide donde tiene que almacenarlo

    @Bean
    public Binding binding(Queue inventoryQueue, TopicExchange orderEventeExchange){ //une contrado conecta el exchange con la cola
        return BindingBuilder.bind(inventoryQueue).to(orderEventeExchange).with("order-placed");
    }
}
