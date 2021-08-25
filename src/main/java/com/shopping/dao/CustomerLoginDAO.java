package com.shopping.dao;

import com.shopping.exception.BusinessException;
import com.shopping.model.Customer;

public interface CustomerLoginDAO {
	
	public Customer loginCustomer(String email, String password) throws BusinessException;
	
}
