package com.cart.add.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shopping.dao.dbutil.MysqlDbConnection;
import com.shopping.exception.BusinessException;

import com.cart.add.dao.CartAddDAO;

public class CartAddDAOImpl implements CartAddDAO {

	@Override
	public int addToCart(String email, int productID) throws BusinessException {
		
		int orderId = 0;
		
		int c = 0;

		try (Connection connection = MysqlDbConnection.getConnection()) {
			
			String sql = "INSERT INTO cart(customerEmail, productid) VALUES(?,?) ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, productID);
			
			c = preparedStatement.executeUpdate();
			
			if(c == 0) {
				throw new BusinessException("Product ID : "+productID+" not found");
			}
			else {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					orderId = resultSet.getInt(1);
				}
			}
			

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Product ID "+productID+" doesn't exist");
		}
		return orderId;
	}

}
