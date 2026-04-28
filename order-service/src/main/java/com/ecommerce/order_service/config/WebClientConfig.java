package com.ecommerce.order_service.config;

import com.ecommerce.order_service.service.client.InventoryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig
{
    @Bean   //es el codigo que sabe abirr un codig https
    public WebClient webClientBuilder(){ //solo una instancia en la memoria
        return WebClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }// es el motor que sabe como conectarse a internet y como recibir respuesta webClient
    //necesita espeficirase xq si va solo no sabe que pedir

    @Bean //unimos la interfaz y el webclient xq java no sabe como hacerlo
    public InventoryClient inventoryClient(WebClient webClient){
        HttpServiceProxyFactory factory = //generar la implementacionm, como genera la peticion
                HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();

        return factory.createClient(InventoryClient.class);
    }
}
