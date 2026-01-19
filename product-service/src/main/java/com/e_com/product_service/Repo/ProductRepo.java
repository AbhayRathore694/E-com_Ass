package com.e_com.product_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_com.product_service.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
