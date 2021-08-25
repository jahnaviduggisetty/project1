package com.product.create.service;

import com.shopping.exception.BusinessException;

public interface ProductCreateService {

	public boolean checkName(String name) throws BusinessException;

	public boolean checkId(int id) throws BusinessException;
	
	public boolean checkPrice(int price) throws BusinessException;

}
