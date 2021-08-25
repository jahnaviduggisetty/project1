package com.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.shopping.dao.CustomerCreateDAO;
import com.shopping.dao.dbutil.MysqlDbConnection;
import com.shopping.exception.BusinessException;
import com.shopping.model.Customer;

public class CustomerCreateDAOImpl implements CustomerCreateDAO {
	
	private static Logger log = Logger.getLogger(CustomerCreateDAOImpl.class);

	@Override
	public int createCustomer(Customer customer) throws BusinessException {
		int c = 0;
		
		try(Connection connection = MysqlDbConnection.getConnection()){
			
			String sql = "INSERT INTO customer(email, fname, lname, password) VALUES(?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getEmail());
			preparedStatement.setString(2, customer.getFname());
			preparedStatement.setString(3, customer.getLname());
			preparedStatement.setString(4, customer.getPassword());
			
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal server error occured");
		}
		
		return c;
	}

}
