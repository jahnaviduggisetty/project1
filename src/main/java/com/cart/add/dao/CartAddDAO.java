package com.cart.add.dao;

import com.shopping.exception.BusinessException;
import com.shopping.model.Cart;

public interface CartAddDAO {
	public int addToCart(String email , int productId) throws BusinessException;
}
