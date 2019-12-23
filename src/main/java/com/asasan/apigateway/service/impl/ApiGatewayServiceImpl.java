package com.asasan.apigateway.service.impl;

import com.asasan.apigateway.dto.ExtendedItemDto;
import com.asasan.apigateway.dto.ExtendedOrderDto;
import com.asasan.apigateway.service.ApiGatewayService;
import com.asasan.apigateway.util.Streams;
import com.asasan.ordermanagement.api.dto.ItemDto;
import com.asasan.ordermanagement.api.dto.OrderDto;
import com.asasan.ordermanagement.api.feign.OrderManagementServiceClient;
import com.asasan.warehousemanagement.api.feign.WarehouseManagementServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiGatewayServiceImpl implements ApiGatewayService {

    private WarehouseManagementServiceClient warehouseClient;
    private OrderManagementServiceClient orderClient;

    @Autowired
    public ApiGatewayServiceImpl(WarehouseManagementServiceClient warehouseClient,
                                 OrderManagementServiceClient orderClient) {
        this.warehouseClient = warehouseClient;
        this.orderClient = orderClient;
    }

    @Override
    public ExtendedOrderDto getExtendedOrderById(int orderId) {
        OrderDto order = orderClient.getOrderById(orderId);
        if (order == null) {
            return null;
        }

        List<ExtendedItemDto> items = Streams.of(order.getItems())
                .map((item) -> {
                    int availableAmount = warehouseClient.getItemById(item.getId()).getAmount();
                    return getExtendedItemDto(item, availableAmount);
                })
                .collect(Collectors.toList());

        ExtendedOrderDto extOrder = new ExtendedOrderDto(order.getId(),
                order.getStatus(),
                order.getTotalCost(),
                order.getTotalAmount(),
                order.getUserName(),
                items);
        return extOrder;
    }

    private ExtendedItemDto getExtendedItemDto(ItemDto orderItem, int availableAmount) {
        return new ExtendedItemDto(orderItem.getId(),
                orderItem.getName(),
                orderItem.getAmount(),
                availableAmount,
                orderItem.getPrice());
    }
}
