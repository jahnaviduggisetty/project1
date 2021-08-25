package com.customer.login.service;

import com.shopping.exception.BusinessException;

public interface CustomerLoginService {

	public boolean checkEmail(String email) throws BusinessException;
	
	public boolean checkPassword(String password) throws BusinessException;
	
}
