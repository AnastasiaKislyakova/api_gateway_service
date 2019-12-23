package com.asasan.apigateway.dto;


import com.asasan.ordermanagement.api.dto.OrderStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendedOrderDto {

    private int id;

    private OrderStatus status;

    private long totalCost;

    private int totalAmount;

    private String userName;

    private List<ExtendedItemDto> items;
}
