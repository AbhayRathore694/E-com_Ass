package com.e_com.product_service.services;

import com.e_com.product_service.model.Product;

public interface ProductService 
{
	public Product addProduct(Product product);
	public Product getProductById(Long id);
	public boolean isStockAvailable(Long productId, int quantity);
	public boolean reserveStock(Long productId, int quantity);

}
