package com.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.ProductSearchDAO;
import com.shopping.dao.dbutil.MysqlDbConnection;
import com.shopping.exception.BusinessException;
import com.shopping.model.Product;

public class ProductSearchDAOImpl implements ProductSearchDAO {

	@Override
	public List<Product> getProductsByName(String name) throws BusinessException {
		List<Product> productList = new ArrayList<>();

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT id, name, price FROM product WHERE name =?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Product product = new Product();

				product.setId(resultSet.getInt("id"));
				product.setName(name);
				product.setPrice(resultSet.getInt("price"));

				productList.add(product);

			}
			
			if(productList.size() == 0) {
				throw new BusinessException("No products of name : "+name+" found");		
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal server error occured");
		}

		return productList;
	}

	@Override
	public Product getProductById(int id) throws BusinessException {
		Product product = null;
		
		try (Connection connection = MysqlDbConnection.getConnection()) {
			
			String sql ="SELECT id, name, price FROM product where id =?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				product = new Product();
				
				product.setId(id);
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				
			}
			if(product == null) {
				throw new BusinessException("No product of ID : "+id+" found");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal server error occured");
		}
		
		return product;
	}

	@Override
	public List<Product> getAllProducts() throws BusinessException {
		List<Product> productList = new ArrayList<>();

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT id, name, price FROM product";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Product product = new Product();

				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));

				productList.add(product);

			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal server error occured");
		}

		return productList;
	}

}
