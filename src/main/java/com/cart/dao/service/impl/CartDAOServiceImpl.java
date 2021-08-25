package com.cart.dao.service.impl;

import com.shopping.exception.BusinessException;
import com.cart.dao.service.CartDAOService;

public class CartDAOServiceImpl implements CartDAOService{

	@Override
	public boolean checkProductID(int orderId) throws BusinessException {
	boolean c = false;
		
		if(orderId <1 || orderId>100 ) {
			throw new BusinessException("Invalid ID: "+orderId+" entered");
		}
		else {
			c = true;
		}
		return c;
	}

}
