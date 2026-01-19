package com.e_com.order_service.services;

import org.springframework.stereotype.Service;

import com.e_com.order_service.Repo.OrderRepo;
import com.e_com.order_service.model.Orders;


@Service
public class OrderServiceImp implements OrderService
{
	private final OrderRepo orderRepo;
	
	public OrderServiceImp(OrderRepo orderRepo)
	{
		this.orderRepo = orderRepo;
	}
	
	@Override
	public Orders createOrder(Orders order)
	{
		if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        
        double pricePerUnit = 100.0;
        double total = pricePerUnit * order.getQuantity();

   
        order.setTotal_amt(total);

        
        return orderRepo.save(order);
	}
	
	@Override
	public Orders getOrderById(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

}
