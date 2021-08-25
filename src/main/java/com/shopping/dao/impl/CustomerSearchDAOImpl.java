package com.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.CustomerSearchDAO;
import com.shopping.dao.dbutil.MysqlDbConnection;
import com.shopping.exception.BusinessException;
import com.shopping.model.Customer;

public class CustomerSearchDAOImpl implements CustomerSearchDAO {

	@Override
	public List<Customer> getAllCustomers() throws BusinessException {
		List<Customer> customerList = new ArrayList<>();

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT email, fname, lname FROM customer";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				Customer customer = new Customer();

				customer.setEmail(resultSet.getString("email"));
				customer.setFname(resultSet.getString("fname"));
				customer.setLname(resultSet.getString("lname"));

				customerList.add(customer);

			}

			if (customerList == null || customerList.size() == 0) {
				throw new BusinessException("No Customers available in the database");
			}

		} catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException("Internal Server error, contact support");
		}

		return customerList;
	}

	@Override
	public List<Customer> getCustomersByFname(String fname) throws BusinessException {
		List<Customer> customerList = new ArrayList<>();

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT email, fname, lname FROM customer WHERE fname =?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, fname);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Customer customer = new Customer();

				customer.setEmail(resultSet.getString("email"));
				customer.setFname(resultSet.getString("fname"));
				customer.setLname(resultSet.getString("lname"));

				customerList.add(customer);

			}
			if (customerList == null || customerList.size() == 0) {
				throw new BusinessException("No Customers with First Name: " + fname + " available in the database");
			}

		} catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException("Internal Server error, contact support");
		}

		return customerList;
	}

	@Override
	public List<Customer> getCustomersByLname(String lname) throws BusinessException {
		List<Customer> customerList = new ArrayList<>();

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT email, fname, lname FROM customer WHERE lname =?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, lname);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Customer customer = new Customer();

				customer.setEmail(resultSet.getString("email"));
				customer.setFname(resultSet.getString("fname"));
				customer.setLname(resultSet.getString("lname"));

				customerList.add(customer);

			}
			if (customerList == null || customerList.size() == 0) {
				throw new BusinessException("No Customers with Last Name: " + lname + " available in the database");
			}

		} catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException("Internal Server error, contact support");
		}
		
		return customerList;
	}

	@Override
	public Customer getCustomerByEmail(String email) throws BusinessException {
		Customer customer = null;

		try (Connection connection = MysqlDbConnection.getConnection()) {

			String sql = "SELECT email, fname, lname FROM customer WHERE email =?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				customer = new Customer();

				customer.setEmail(email);
				customer.setFname(resultSet.getString("fname"));
				customer.setLname(resultSet.getString("lname"));

			}
			if (customer == null) {
				throw new BusinessException("Customer with email: " + email + " doesn't exists");
			}

		} catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException("Internal Server error, contact support");
		}

		return customer;
	}

}
