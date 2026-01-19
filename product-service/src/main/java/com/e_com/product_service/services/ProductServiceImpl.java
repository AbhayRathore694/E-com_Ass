package com.e_com.product_service.services;

import org.springframework.stereotype.Service;

import com.e_com.product_service.Repo.ProductRepo;
import com.e_com.product_service.model.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService  
{
	
	private final ProductRepo productRepo;
	
	public ProductServiceImpl(ProductRepo productRepo)
	{
		this.productRepo = productRepo;
	}
	
	
	@Override
    public Product addProduct(Product product) {
        if (product.getStock() < 0 || product.getPrice() <= 0) {
            throw new RuntimeException("Invalid product data");
        }
        return productRepo.save(product);
    }

	@Override
    public Product getProductById(Long productId) {
        return productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

	@Override
    public boolean isStockAvailable(Long productId, int quantity) {
        Product product = getProductById(productId);
        return product.getStock() >= quantity;
    }

	@Override
	@Transactional
	public boolean reserveStock(Long productId, int quantity) {
        Product product = getProductById(productId);

        if (product.getStock() < quantity) {
            return false;
        }

        product.setStock(product.getStock() - quantity);
        productRepo.save(product);
        return true;
    }
   
}
