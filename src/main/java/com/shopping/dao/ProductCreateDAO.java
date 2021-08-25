package com.shopping.dao;

import com.shopping.exception.BusinessException;
import com.shopping.model.Product;

public interface ProductCreateDAO {
	public int createProduct(Product product) throws BusinessException;
}
