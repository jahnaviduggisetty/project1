package com.shopping;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.shopping.dao.CustomerCreateDAO;
import com.shopping.dao.CustomerLoginDAO;
import com.shopping.dao.CustomerSearchDAO;
import com.shopping.dao.ProductCreateDAO;
import com.shopping.dao.ProductSearchDAO;
import com.shopping.dao.impl.CustomerCreateDAOImpl;
import com.shopping.dao.impl.CustomerLoginDAOImpl;
import com.shopping.dao.impl.CustomerSearchDAOImpl;
import com.shopping.dao.impl.ProductCreateDAOImpl;
import com.shopping.dao.impl.ProductSearchDAOImpl;
import com.shopping.exception.BusinessException;
import com.shopping.model.Cart;
import com.shopping.model.Customer;
import com.shopping.model.Product;
import com.associate.login.service.AssociateLoginService;
import com.associate.login.service.impl.AssociateLoginServiceImpl;
import com.cart.add.dao.CartAddDAO;
import com.cart.add.dao.impl.CartAddDAOImpl;
import com.cart.dao.CartDAO;
import com.cart.dao.impl.CartDAOImpl;
import com.cart.dao.service.CartDAOService;
import com.cart.dao.service.impl.CartDAOServiceImpl;
import com.customer.create.service.CustomerCreateService;
import com.customer.create.service.impl.CustomerCreateServiceImpl;
import com.customer.login.service.CustomerLoginService;
import com.customer.login.service.impl.CustomerLoginServiceImpl;
import com.customer.search.service.CustomerSearchService;
import com.customer.search.service.impl.CustomerSearchServiceImpl;
import com.product.create.service.ProductCreateService;
import com.product.create.service.impl.ProductCreateServiceImpl;
import com.product.search.service.ProductSearchService;
import com.product.search.service.impl.ProductSearchServiceImpl;

public class Main {

	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		log.info("Welcome to shopping app");
		log.info("-------------------------------");

		int choice = 0;

		do {

			log.info("Please login: ");
			log.info("1.) Admin Login");
			log.info("2.) Customer Registration");
			log.info("3.) Customer Login");
			log.info("4.) Close App");

			log.info("\n Please enter your choice: ");

			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.warn("Please enter a digit between 1 and 4 only");
				continue;
			}

			switch (choice) {
			case 1:
				log.info("Enter associate login details: ");
				log.info("1.) Enter username");

				AssociateLoginService associateLoginService = new AssociateLoginServiceImpl();

				String username = scanner.nextLine();

				try {
					if (associateLoginService.checkUserName(username)) {

					}
				} catch (BusinessException e3) {
					log.warn(e3.getMessage());
					break;
				}

				log.info("2.) Enter password");

				String password2 = scanner.nextLine();

				try {
					if (associateLoginService.checkPassword(password2)) {
						log.info("\nAssociate login successful!");
						log.info("Welcome");
					}
				} catch (BusinessException e3) {
					log.warn(e3.getMessage());
					break;
				}

				int choiceass = 0;

				do {

					log.info("What would you want to do today admin?");

					log.info("1.) Search Products");
					log.info("2.) Add new Product");
					log.info("3.) Search Customers");
					log.info("4.) View all orders in the app");
					log.info("5.) Logout");

					try {
						choiceass = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.warn("\nPlease enter a digit between 1 and 5 only");
						continue;
					}

					switch (choiceass) {
					case 1:

						int choiceass1 = 0;

						log.info("\nWelcome to Product Search");

						do {

							log.info("How would you want to search the products?");

							log.info("1.) By name");
							log.info("2.) By Id");
							log.info("3.) Show all products");
							log.info("4.) Return back to previous menu");

							log.info("Please enter your choice");

							try {
								choiceass1 = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								log.warn("\nPlease enter digits between 1 and 4 only");
								continue;
							}

							ProductSearchService productSearchService = new ProductSearchServiceImpl();
							ProductSearchDAO productSearchDAO = new ProductSearchDAOImpl();

							switch (choiceass1) {

							case 1:

								log.info("\nEnter Product Name");
								String productName = "";
								productName = scanner.nextLine();

								try {
									if (productSearchService.checkName(productName)) {
									}
								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
									continue;
								}

								try {
									List<Product> productList = productSearchDAO.getProductsByName(productName);

									log.info("\nThere are " + productList.size() + " products with the name: "
											+ productName);
									log.info("Printing product details");

									for (Product product : productList) {
										log.info(product);
									}

								} catch (BusinessException e2) {
									log.warn(e2.getMessage());
								}

								break;

							case 2:
								log.info("\nEnter product id");
								int id = 0;

								try {
									id = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.warn("Please enter digits only");
									continue;
								}

								try {
									if (productSearchService.checkId(id)) {
									}
								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
									continue;
								}

								try {

									Product product = productSearchDAO.getProductById(id);

									log.info("\nPrinting product details with ID : " + id);
									log.info(product);

								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
									continue;
								}

								break;

							case 3:
								log.info("\nAll the available products are listed below");

								try {
									List<Product> productList = productSearchDAO.getAllProducts();
									for (Product product : productList) {
										log.info(product);
									}

								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								break;

							case 4:
								log.info("\nReturning back to the previous menu...");
								break;

							default:
								log.info("\nPlease enter digits between 1 and 4 only");
								break;
							}

						} while (choiceass1 != 4);

						break;

					case 2:

						ProductCreateService productCreateService = new ProductCreateServiceImpl();
						ProductCreateDAO productCreateDAO = new ProductCreateDAOImpl();

						log.info("\nEnter the Product details to be added to the app: ");
						log.info("1.) Enter Product ID:");

						int id = 0;
						try {
							id = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {
							log.warn("Please enter a number only");
							break;
						}

						try {
							if (productCreateService.checkId(id)) {

							}
						} catch (BusinessException e3) {
							log.warn(e3.getMessage());
							break;

						}

						log.info("2.) Enter Product Name");

						String name = scanner.nextLine();

						try {
							if (productCreateService.checkName(name)) {

							}
						} catch (BusinessException e3) {
							log.warn(e3.getMessage());
							break;
						}

						int price = 0;

						log.info("3.) Enter Product Price");

						try {
							price = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {
							log.warn("Please enter a valid number");
							break;
						}

						try {
							if (productCreateService.checkPrice(price)) {

							}
						} catch (BusinessException e3) {
							log.warn(e3.getMessage());
							break;
						}

						Product product = new Product(id, name, price);

						try {
							int c = productCreateDAO.createProduct(product);

							if (c == 1) {
								log.info("\nProduct Created Successfully with the details: ");
								log.info(product);

							} else {
								log.info("\nCould not create product");
							}

						} catch (BusinessException e3) {
							log.warn(e3.getMessage());
						}

						break;

					case 3:
						log.info("\nWelcome to Customer Search");

						int cust = 0;

						do {
							CustomerSearchService customerSearchService = new CustomerSearchServiceImpl();
							CustomerSearchDAO customerSearchDAO = new CustomerSearchDAOImpl();

							log.info("\n1.) Search Customer by email");
							log.info("2.) Search Customer(s) by First Name");
							log.info("3.) Search Customer(s) by Last Name");
							log.info("4.) Show All Customers");
							log.info("5.) Return back to the Previous Menu");

							log.info("Please Enter your choice: ");

							try {
								cust = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								log.warn("Please enter a valid number only");
							}

							switch (cust) {

							case 1:
								log.info("\n1.) Enter Customer's email: ");

								String email = scanner.nextLine();

								try {
									if (customerSearchService.checkEmail(email)) {

									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								try {
									Customer customer1 = customerSearchDAO.getCustomerByEmail(email);

									if (customer1 != null) {
										log.info("\nPrinting Customer details: ");
										log.info("\nCustomer's email :" + customer1.getEmail()
												+ ", Customer's First Name: " + customer1.getFname()
												+ ", Customer's Last Name: " + customer1.getLname());
									}

								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
								}

								break;

							case 2:
								log.info("\n2.) Enter Customer's First Name");

								String fname = scanner.nextLine();

								try {
									if (customerSearchService.checkFname(fname)) {

									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								try {
									List<Customer> customerListf = customerSearchDAO.getCustomersByFname(fname);
									if (customerListf.size() > 0) {
										log.info("\nThere are : " + customerListf.size()
												+ " customers with the First Name: " + fname);
										log.info("Printing their details:");

										for (Customer customer : customerListf) {
											log.info("\nCustomer's email :" + customer.getEmail()
													+ ", Customer's First Name: " + customer.getFname()
													+ ", Customer's Last Name: " + customer.getLname());
										}
									}

								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
								}

								break;

							case 3:
								log.info("\n3.) Enter Customer's Last Name");

								String lname = scanner.nextLine();

								try {
									if (customerSearchService.checkLname(lname)) {

									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								try {
									List<Customer> customerListl = customerSearchDAO.getCustomersByLname(lname);
									if (customerListl.size() > 0) {
										log.info("\nThere are : " + customerListl.size()
												+ " customers with the Last Name: " + lname);
										log.info("Printing their details:");

										for (Customer customer : customerListl) {
											log.info("\nCustomer's email :" + customer.getEmail()
													+ ", Customer's First Name: " + customer.getFname()
													+ ", Customer's Last Name: " + customer.getLname());
										}
									}

								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
								}

								break;

							case 4:
								log.info("\n4.) Showing All Customers: ");

								try {
									List<Customer> customerList = customerSearchDAO.getAllCustomers();

									if (customerList.size() > 0) {
										log.info("\nThere are : " + customerList.size() + " customers in the database");
										log.info("Printing all the Customers:");
										for (Customer customer : customerList) {
											log.info("\nCustomer's email :" + customer.getEmail()
													+ ", Customer's First Name: " + customer.getFname()
													+ ", Customer's Last Name: " + customer.getLname());
										}
									}

								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}

								break;

							case 5:
								log.info("\nReturning back to the Previous Menu...");

								break;

							default:
								log.info("\nPlease enter a digit between 1 and 5 only.");
								//continue;
							break;
							}

						} while (cust != 5);

						break;

					case 4:
						log.info("\nShowing all orders");

						CartDAO cartDAO = new CartDAOImpl();

						try {
							List<Cart> orderList = cartDAO.getAllOrders();

							if (orderList.size() > 0) {
								log.info("There are: " + orderList.size() + " orders :");
								for (Cart cart : orderList) {

									log.info("\n Order ID : " + cart.getOrderid() + ", Customer email: "
											+ cart.getCustomer().getEmail() + ", Customer's First Name: "
											+ cart.getCustomer().getFname() + ", Customer's Last Name: "
											+ cart.getCustomer().getLname() + ", Product ID: "
											+ cart.getProduct().getId() + ", Product Name: "
											+ cart.getProduct().getName() + ", Product Price : "
											+ cart.getProduct().getPrice() + ", Order Status: " + cart.getTracker());
									// log.info(cart);
								}

							}

						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						int adchoice = 0;

						do {

							log.info("\n1.) Update order as Dispatched");
							log.info("2.) Return to previous menu");

							adchoice = Integer.parseInt(scanner.nextLine());

							switch (adchoice) {
							case 1:

								int orderId = 0;

								CartDAOService cartDAOService = new CartDAOServiceImpl();

								log.info("Enter Order ID to update status");

								try {
									orderId = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.warn("Please enter a valid number");
									continue;
								}

								try {
									cartDAOService.checkProductID(orderId);
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								try {
									if (cartDAO.updateOrderAssociate(orderId) == 1) {
										log.info("\nOrder status updated successfully");
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								break;

							case 2:
								log.info("\nReturning to previous menu...");
								break;

							default:
								log.info("\nPlease choose 1 or 2 only.");
								continue;
							// break;
							}

						} while (adchoice != 2);

						break;

					case 5:
						log.info("\nOkay admin, Logging you out...");

						break;

					default:
						log.warn("\nPlease enter a number between 1 and 5 only.");
						continue;
					// break;
					}

				} while (choiceass != 5);

				break;

			case 2:
				log.info("Enter customer details for creation :\n");

				CustomerCreateService customerCreateService = new CustomerCreateServiceImpl();
				CustomerCreateDAO customerCreateDAO = new CustomerCreateDAOImpl();

				Customer customer = new Customer();

				log.info("1.) Enter email");

				String email = scanner.nextLine();

				try {
					if (customerCreateService.checkEmail(email)) {
						customer.setEmail(email);
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					continue;
				}

				log.info("2.) Enter first name");

				String fname = scanner.nextLine();

				try {
					if (customerCreateService.checkFname(fname)) {
						customer.setFname(fname);
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					continue;
				}

				log.info("3.) Enter last name");

				String lname = scanner.nextLine();

				try {
					if (customerCreateService.checkLname(lname)) {
						customer.setLname(lname);
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					continue;
				}

				log.info("4.) Enter password");

				String password = scanner.nextLine();

				try {
					if (customerCreateService.checkPassword(password)) {
						customer.setPassword(password);
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					continue;
				}

				try {
					int c = customerCreateDAO.createCustomer(customer);

					if (c == 1) {
						log.info("\nCustomer created Successfully");
					}

				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}

				break;

			case 3:
				log.info("\nEnter customer login details: ");

				log.info("1.) Enter email");

				CustomerLoginService customerLoginService = new CustomerLoginServiceImpl();
				CustomerLoginDAO customerLoginDAO = new CustomerLoginDAOImpl();

				// Customer customer1 = new Customer();

				String email1 = scanner.nextLine();

				try {
					if (customerLoginService.checkEmail(email1)) {
						// customer1.setEmail(email1);
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					continue;
				}

				log.info("2.) Enter password");

				String password1 = scanner.nextLine();

				try {
					if (customerLoginService.checkPassword(password1)) {
						// customer1.setPassword(password1);
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					continue;
				}

				Customer customerlogin = null;

				try {
					customerlogin = customerLoginDAO.loginCustomer(email1, password1);

					if (customerlogin != null) {
						log.info("\nCustomer logged in successfully");
						log.info("Hello " + customerlogin.getFname() + " " + customerlogin.getLname()
								+ "\nWhat would you like to do today?");
					}

				} catch (BusinessException e) {
					log.warn(e.getMessage());
					continue;
				}

				int choice1 = 0;

				do {

					log.info("\n1.) Search products");
					log.info("2.) View your orders");
					log.info("3.) View Cart total");
					log.info("4.) Logout");

					log.info("\nPlease enter your choice");

					try {
						choice1 = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.warn("Please enter a digit between 1 and 3 only");
						continue;
					}

					switch (choice1) {
					case 1:
						int choice2 = 0;
						do {

							log.info("\nWelcome to Product Search");
							log.info("How would you want to search the products?");

							log.info("1.) By name");
							log.info("2.) By Id");
							log.info("3.) Show all products");
							log.info("4.) Add product to cart");
							log.info("5.) Return back to the previous menu");

							log.info("Please enter your choice");

							try {
								choice2 = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								log.warn("Please enter digits between 1 and 4 only");
								continue;
							}

							ProductSearchDAO productSearchDAO = new ProductSearchDAOImpl();

							ProductSearchService productSearchService = new ProductSearchServiceImpl();

							switch (choice2) {
							case 1:
								log.info("\nEnter product name: ");

								String name = scanner.nextLine();

								try {
									if (productSearchService.checkName(name)) {

									}
								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
								}

								try {
									List<Product> productList = productSearchDAO.getProductsByName(name);

									log.info("\nThere are " + productList.size() + " products with the name: " + name);
									log.info("Printing product details");

									for (Product product : productList) {
										log.info(product);
									}

								} catch (BusinessException e2) {
									log.warn(e2.getMessage());
								}

								break;

							case 2:
								log.info("\nEnter product id");
								int id = 0;

								try {
									id = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.warn("Please enter digits only");
									continue;
								}

								try {
									if (productSearchService.checkId(id)) {

									}
								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
								}

								try {

									Product product = productSearchDAO.getProductById(id);

									log.info("\nPrinting product details with ID : " + id);
									log.info(product);

								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
								}

								break;

							case 3:
								log.info("\nAll the available products are listed below");

								try {
									List<Product> productList = productSearchDAO.getAllProducts();
									for (Product product : productList) {
										log.info("\n"+product);
									}

								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								break;

							case 4:
								log.info("\nEnter product ID to add to cart:");
								int productId = 0;

								CartAddDAO cartAddDAO = new CartAddDAOImpl();

								try {
									productId = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.warn("Please enter a valid ID");
									continue;
								}

								CartDAOService cartDAOService = new CartDAOServiceImpl();

								try {
									cartDAOService.checkProductID(productId);
								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
									continue;
								}

								try {
									int orderId = cartAddDAO.addToCart(customerlogin.getEmail(), productId);

									if (orderId != 0) {
										log.info("Product added to cart successfully with Order ID : " + orderId);
									}

								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								break;

							case 5:
								log.info("\nTaking you to the previous menu...");
								break;

							default:
								log.warn("\nPlease enter a number between 1 and 4 only");
								continue;
							// break;
							}

						} while (choice2 != 4);

						break;

					case 2:
						log.info("\nShowing your orders: ");

						CartDAO cartDAO = new CartDAOImpl();

						try {
							List<Cart> orderList = cartDAO.getCustomerOrders(customerlogin.getEmail());

							if (orderList.size() > 0) {
								log.info("You have :" + orderList.size() + " orders in your cart. Printing them: ");
								for (Cart cart : orderList) {
									log.info("\n Order ID : " + cart.getOrderid() + ", Customer email: "
											+ cart.getCustomer().getEmail() + ", Customer's First Name: "
											+ cart.getCustomer().getFname() + ", Customer's Last Name: "
											+ cart.getCustomer().getLname() + ", Product ID: "
											+ cart.getProduct().getId() + ", Product Name: "
											+ cart.getProduct().getName() + ", Product Price : "
											+ cart.getProduct().getPrice() + ", Order Status: " + cart.getTracker());
									// log.info(cart);
								}
							}

						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						int ch = 0;
						do {

							log.info("\nWhat do you want to do in your cart?");

							log.info("1.) Place order");
							log.info("2.) Update order as recieved");
							log.info("3.) Delete order");
							log.info("4.) Return back to previous menu");

							ch = Integer.parseInt(scanner.nextLine());

							int orderId = 0;
							CartDAOService cartDAOService = new CartDAOServiceImpl();

							switch (ch) {
							case 1:

								log.info("\nEnter the order ID for which you want to place the order: ");

								try {
									orderId = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.warn("\nPlease enter a valid Order ID");
								}

								try {
									cartDAOService.checkProductID(orderId);
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								try {
									log.info(customerlogin);
									int c = cartDAO.placeOrder(orderId);
									if (c == 1) {
										log.info("\nUpdated the order status to: Ordered of Order ID: " + orderId);
									}

								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}

								break;

							case 2:
								log.info("\nEnter the order ID for which you want to update the order status: ");

								try {
									orderId = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.warn("\nPlease enter a valid Order ID");
									continue;
								}

								try {
									cartDAOService.checkProductID(orderId);
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								try {
									if (cartDAO.updateOrderCustomer(orderId) == 1) {
										log.info("\nOrder status updated successfully for the order ID: " + orderId);
									}
								} catch (BusinessException e1) {
									log.warn(e1.getMessage());
								}

								break;

							case 3:
								log.info("\nEnter the order ID which you want to delete from your cart");

								try {
									orderId = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.warn("\nPlease enter a valid Order ID");
									continue;
								}

								try {
									cartDAOService.checkProductID(orderId);
								} catch (BusinessException e) {
									log.warn(e.getMessage());
									continue;
								}

								try {
									int c = cartDAO.deleteOrder(orderId);
									if (c == 1) {
										log.info("\nOrder deleted from your cart of Order ID: " + orderId);
									}

								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}

								break;

							case 4:
								log.info("\nTaking you back to the previous menu...");

							default:
								log.warn("\nPlease enter a digit between 1 and 4 only.");
								continue;
							// break;
							}

						} while (ch != 4);

						break;

					case 3:
						log.info("\nPrinting Cart total:");
						CartDAO cartDAO2 = new CartDAOImpl();
						int cost = 0;
						
						try {
							cost = cartDAO2.cartTotal(customerlogin.getEmail());
							
							if(cost>0) {
								log.info("\nThe total bill of your cart is: "+cost);
							}
							
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						break;

					case 4:
						log.info("\nLogging you out...");

						break;
					default:
						log.warn("\nPlease enter a number between 1 and 3 only.");
						continue;
					// break;
					}

				} while (choice1 != 4);

				break;

			case 4:
				log.info("\nThanks for shopping with us! Hope to see you soon :)");

				break;

			default:
				log.warn("\nInvalid choice! The choice should only be a number between 1-4. Please try again.");
				continue;
			// break;

			}

		} while (choice != 4);
		scanner.close();
	}
}
