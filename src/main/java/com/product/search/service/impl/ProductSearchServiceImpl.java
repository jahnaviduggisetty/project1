package com.product.search.service.impl;

import com.shopping.exception.BusinessException;
import com.product.search.service.ProductSearchService;

public class ProductSearchServiceImpl implements ProductSearchService{

	@Override
	public boolean checkName(String name) throws BusinessException {
		boolean c = false;
		
		if(name.matches("[a-zA-Z]{2,20}")) {
			c = true;
		}
		else {
			throw new BusinessException("Invalid name : "+name+" entered" );
		}
		
		return c;
	}

	@Override
	public boolean checkId(int id) throws BusinessException {
		boolean c = false;
		
		if(id <1 || id>100 ) {
			throw new BusinessException("Invalid ID: "+id+" entered");
		}
		else {
			c = true;
		}
		return c;
	}

}
