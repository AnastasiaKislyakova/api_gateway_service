package com.asasan.apigateway;

import com.asasan.ordermanagement.api.feign.OrderManagementServiceClient;
import com.asasan.warehousemanagement.api.feign.WarehouseManagementServiceClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public WarehouseManagementServiceClient warehouseClient() {
        WarehouseManagementServiceClient client = Feign.builder()
                .encoder(new GsonEncoder())
                .decode404()
                .decoder(new GsonDecoder())
                .target(WarehouseManagementServiceClient.class, "http://localhost:8080/api/warehouse");
        return client;
    }

    @Bean
    public OrderManagementServiceClient orderClient() {
        OrderManagementServiceClient client = Feign.builder()
                .encoder(new GsonEncoder())
                .decode404()
                .decoder(new GsonDecoder())
                .target(OrderManagementServiceClient.class, "http://localhost:8080/api/orders");
        return client;
    }
}
