package com.customer.search.service.impl;

import com.shopping.exception.BusinessException;
import com.customer.search.service.CustomerSearchService;

public class CustomerSearchServiceImpl implements CustomerSearchService {

	@Override
	public boolean checkFname(String fname) throws BusinessException {
		boolean c = false;

		if (fname.matches("[a-zA-Z]{2,15}")) {
			c = true;
		} else {
			throw new BusinessException("Invalid first name: " + fname);
		}
		return c;

	}

	@Override
	public boolean checkLname(String lname) throws BusinessException {
		boolean c = false;

		if (lname.matches("[a-zA-Z]{2,15}")) {
			c = true;
		} else {
			throw new BusinessException("Invalid last name: " + lname);
		}

		return c;
	}

	@Override
	public boolean checkEmail(String email) throws BusinessException {
		boolean c = false;

		if (email.matches("[a-zA-Z0-9]{3,15}@[a-z]{3,15}.com")) {
			c = true;
		} else {
			throw new BusinessException("Invalid email : " + email);
		}
		return c;
	}

}
