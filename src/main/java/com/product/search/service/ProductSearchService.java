package com.product.search.service;

import com.shopping.exception.BusinessException;

public interface ProductSearchService {
	
	public boolean checkName(String name) throws BusinessException;
	
	public boolean checkId(int id) throws BusinessException;
	
	
}
