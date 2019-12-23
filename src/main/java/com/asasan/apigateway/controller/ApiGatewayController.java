package com.asasan.apigateway.controller;

import com.asasan.apigateway.dto.ExtendedOrderDto;
import com.asasan.apigateway.service.ApiGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/extended_order")
public class ApiGatewayController {
    private ApiGatewayService apiGatewayService;

    @Autowired
    public ApiGatewayController(ApiGatewayService apiGatewayService) {
        this.apiGatewayService = apiGatewayService;
    }

    @GetMapping(value = "{orderId}")
    ResponseEntity<ExtendedOrderDto> getOrderById(@PathVariable int orderId) {
        ExtendedOrderDto orderById = apiGatewayService.getExtendedOrderById(orderId);
        if (orderById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderById);
    }

}
