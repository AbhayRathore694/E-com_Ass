package com.e_com.order_service.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.e_com.order_service.Repo.OrderRepo;
import com.e_com.order_service.config.RestConfig;
import com.e_com.order_service.model.Orders;


@Service
public class OrderServiceImp implements OrderService
{
	private final OrderRepo orderRepo;
	private final RestTemplate restTemplate;
	
	public OrderServiceImp(OrderRepo orderRepo, RestTemplate restTemplate)
	{
		this.orderRepo = orderRepo;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Orders createOrder(Orders order) {

        Boolean reserved = restTemplate.getForObject(
            "http://localhost:8081/products/reserveStock/"
            + order.getProductId()
            + "?quantity=" + order.getQuantity(),
            Boolean.class
        );

        if (!Boolean.TRUE.equals(reserved)) {
            throw new RuntimeException("Out of stock");
        }

        return orderRepo.save(order);
    }
	
	@Override
	public Orders getOrderById(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

}
