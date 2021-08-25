package com.cart.dao.service;

import com.shopping.exception.BusinessException;

public interface CartDAOService {
	public boolean checkProductID(int orderId) throws BusinessException;
}
