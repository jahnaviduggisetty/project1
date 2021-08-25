package com.customer.create.service;

import com.shopping.exception.BusinessException;

public interface CustomerCreateService {
	
	public boolean checkEmail(String email) throws BusinessException;
	
	public boolean checkFname(String fname) throws BusinessException;
	
	public boolean checkLname(String lname) throws BusinessException;
	
	public boolean checkPassword(String password) throws BusinessException;
	
}
