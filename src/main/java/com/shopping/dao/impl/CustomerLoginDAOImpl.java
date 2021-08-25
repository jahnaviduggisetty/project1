package com.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shopping.dao.CustomerLoginDAO;
import com.shopping.dao.dbutil.MysqlDbConnection;
import com.shopping.exception.BusinessException;
import com.shopping.model.Customer;


public class CustomerLoginDAOImpl implements CustomerLoginDAO{

	@Override
	public Customer loginCustomer(String email, String password) throws BusinessException {
		
		Customer customer = null;
		
		boolean c = false;
		
		try(Connection connection = MysqlDbConnection.getConnection()) {
			
			String sql = "SELECT email, fname, lname, password FROM customer";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				if(email.equals(resultSet.getString("email")) && password.equals(resultSet.getString("password"))) {
					c = true;
					customer = new Customer();
					
					customer.setEmail(email);
					customer.setFname(resultSet.getString("fname"));
					customer.setLname(resultSet.getString("lname"));
					customer.setPassword(password);
					
					break;
				}
			}
			
			if(!c) {
				throw new BusinessException("User not found... Please register yourself!");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Server error occured");
		}
		
		return customer;
	}

}
