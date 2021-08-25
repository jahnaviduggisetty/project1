package com.customer.login.service.impl;

import com.shopping.exception.BusinessException;
import com.customer.login.service.CustomerLoginService;

public class CustomerLoginServiceImpl implements CustomerLoginService{

	@Override
	public boolean checkEmail(String email) throws BusinessException {
		boolean c = false;
		
		if(email.matches("[a-zA-Z0-9]{3,15}@[a-z]{3,15}.com")) {
			c = true;
		}
		else {
			throw new BusinessException("Invalid email : "+email);
		}
		
		return c;
	}

	@Override
	public boolean checkPassword(String password) throws BusinessException {
		boolean c = false;
		
		if(password.matches("[a-zA-Z]{5,10}")) {
			c = true;
		}
		else {
			throw new BusinessException("Invalid password");
		}
		
		return c;
	}
	
	
	
}
