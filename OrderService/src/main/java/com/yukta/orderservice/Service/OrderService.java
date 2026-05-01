package com.yukta.orderservice.Service;


import com.yukta.orderservice.Dto.ProductDto;
import com.yukta.orderservice.Entity.Order;
import com.yukta.orderservice.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private  final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public Order placeOrder(Order order){
        String url = "http://PRODUCTSERVICE/products/" + order.getProductId();
        ProductDto product = restTemplate.getForObject(url, ProductDto.class);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        return orderRepository.save(order);
    }
}
