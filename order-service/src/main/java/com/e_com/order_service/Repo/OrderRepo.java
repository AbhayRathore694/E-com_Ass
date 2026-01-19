package com.e_com.order_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.order_service.model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> 
{

}
