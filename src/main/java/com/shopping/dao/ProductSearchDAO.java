package com.shopping.dao;

import java.util.List;

import com.shopping.exception.BusinessException;
import com.shopping.model.Product;

public interface ProductSearchDAO {
	
	public List<Product> getProductsByName(String name) throws BusinessException;
	
	public Product getProductById(int id) throws BusinessException;
	
	public List<Product> getAllProducts() throws BusinessException;

	
}
