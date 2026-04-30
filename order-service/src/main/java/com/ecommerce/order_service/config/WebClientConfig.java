package com.ecommerce.order_service.config;

import com.ecommerce.order_service.service.client.InventoryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig
{
    @Bean   //es el codigo que sabe abirr un codig https
    @LoadBalanced //tiene 2 funciones de gps le ense;a de apring Inventory-Service no es una direcion de ip sino un nombre logico intercepta la llamada y le pregunta a eureka
    //que ip y puete tiene ese servicio y lo remplaazo lueg reparta la carga y esta decida al cual llama.
    public WebClient.Builder webClientBuilder(){ //solo una instancia en la memoria
        return WebClient.builder();
    }// es el motor que sabe como conectarse a internet y como recibir respuesta webClient
    //necesita espeficirase xq si va solo no sabe que pedir

    @Bean //unimos la interfaz y el webclient xq java no sabe como hacerlo
    public InventoryClient inventoryClient(WebClient.Builder builder){
        WebClient webClient = builder
                .baseUrl("http://INVENTORY-SERVICE").build();
        HttpServiceProxyFactory factory = //generar la implementacionm, como genera la peticion
                HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();

        return factory.createClient(InventoryClient.class);
    }
}
