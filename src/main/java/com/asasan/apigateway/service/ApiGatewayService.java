package com.asasan.apigateway.service;

import com.asasan.apigateway.dto.ExtendedOrderDto;

public interface ApiGatewayService {
    ExtendedOrderDto getExtendedOrderById(int orderId);
}
