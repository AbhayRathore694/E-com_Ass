package com.e_com.order_service.services;

import org.springframework.stereotype.Service;

import com.e_com.order_service.model.Orders;

@Service
public interface OrderService 
{
	public Orders createOrder(Orders order);
	public Orders getOrderById(Long id);

}
