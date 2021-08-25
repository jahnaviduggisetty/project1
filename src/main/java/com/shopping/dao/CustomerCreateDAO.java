package com.shopping.dao;

import com.shopping.exception.BusinessException;
import com.shopping.model.Customer;

public interface CustomerCreateDAO {
	
	public int createCustomer(Customer customer) throws BusinessException;
	
}
