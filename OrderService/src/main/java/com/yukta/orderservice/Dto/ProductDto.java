package com.yukta.orderservice.Dto;


import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
}
