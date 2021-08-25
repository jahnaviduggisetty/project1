package com.cart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.dbutil.MysqlDbConnection;
import com.shopping.exception.BusinessException;
import com.shopping.model.Cart;
import com.shopping.model.Customer;
import com.shopping.model.Product;
import com.cart.dao.CartDAO;

public class CartDAOImpl implements CartDAO {

	@Override
	public List<Cart> getAllOrders() throws BusinessException {
		List<Cart> orderList = new ArrayList<>();

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT orderid, customerEmail, fname, lname, productid, p.name, p.price, tracker FROM cart c INNER JOIN product p ON c.productid = p.id INNER JOIN customer cu ON c.customerEmail = cu.email";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Cart cart = new Cart();

				cart.setOrderid(resultSet.getInt("orderid"));
				cart.setTracker(resultSet.getString("tracker"));

				Product product = new Product();
				product.setId(resultSet.getInt("productid"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));

				cart.setProduct(product);

				Customer customer = new Customer();
				customer.setEmail(resultSet.getString("customerEmail"));
				customer.setFname(resultSet.getString("fname"));
				customer.setLname(resultSet.getString("lname"));

				cart.setCustomer(customer);

				orderList.add(cart);

			}

			if (orderList.size() == 0) {
				throw new BusinessException("No orders found");
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Server error, contact support");
		}

		return orderList;
	}

	@Override
	public List<Cart> getCustomerOrders(String email) throws BusinessException {
		List<Cart> orderList = new ArrayList<>();

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT orderid, customerEmail, fname, lname, productid, p.name, p.price, tracker FROM cart c INNER JOIN product p ON c.productid = p.id INNER JOIN customer cu ON customerEmail = email WHERE email =?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Cart cart = new Cart();

				cart.setOrderid(resultSet.getInt("orderid"));
				cart.setTracker(resultSet.getString("tracker"));

				Product product = new Product();
				product.setId(resultSet.getInt("productid"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));

				cart.setProduct(product);

				Customer customer = new Customer();
				customer.setEmail(resultSet.getString("customerEmail"));
				customer.setFname(resultSet.getString("fname"));
				customer.setLname(resultSet.getString("lname"));

				cart.setCustomer(customer);

				orderList.add(cart);

			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Server error, contact support");
		}

		if (orderList.size() == 0) {
			throw new BusinessException("Your cart is empty. Start shopping!");
		}

		return orderList;
	}

	@Override
	public int placeOrder(int orderId) throws BusinessException {

		int c = 0;

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "UPDATE cart SET tracker = 'Ordered' WHERE orderid =? and tracker = 'In stock'";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, orderId);

			c = preparedStatement.executeUpdate();

			if (c == 0) {
				throw new BusinessException(
						"Order ID : " + orderId + " not found in cart or the order might have already been placed.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal Server error, contact support");
		}
		return c;
	}

	@Override
	public int deleteOrder(int orderId) throws BusinessException {
		int c = 0;

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "DELETE FROM cart WHERE orderid =? and tracker = 'In stock'";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, orderId);

			c = preparedStatement.executeUpdate();

			if (c == 0) {
				throw new BusinessException(
						"Order ID : " + orderId + " not found in cart or might have already been dispatched.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal Server error, contact support");
		}

		return c;
	}

	@Override
	public int updateOrderAssociate(int orderId) throws BusinessException {
		int c = 0;

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "UPDATE cart SET tracker = 'Dispatched' WHERE orderid =? and tracker = 'Ordered'";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, orderId);

			c = preparedStatement.executeUpdate();

			if (c == 0) {
				throw new BusinessException(
						"Order ID : " + orderId + " not found in cart or might have already been dispatched.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Server error, contact support");
		}

		return c;
	}

	@Override
	public int updateOrderCustomer(int orderId) throws BusinessException {
		int c = 0;

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "UPDATE cart SET tracker = 'Received' WHERE orderid =? and tracker = 'Dispatched'";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, orderId);

			c = preparedStatement.executeUpdate();

			if (c == 0) {
				throw new BusinessException(
						"Order ID : " + orderId + " not found in cart or order might not have been dispatched yet or might have already been delivered.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Server error, contact support");
		}

		return c;
	}

	@Override
	public int cartTotal(String email) throws BusinessException {
		int cost = 0;

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT p.price FROM cart c INNER JOIN product p ON c.productid = p.id WHERE customerEmail =? and tracker = 'In stock'";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, email);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				cost = cost+resultSet.getInt("price");
			}
			if(cost == 0) {
				throw new BusinessException("No Products to buy in your cart. Add products to cart and start Shopping!");
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Server error, contact support");
		}

		return cost;
	}
}
