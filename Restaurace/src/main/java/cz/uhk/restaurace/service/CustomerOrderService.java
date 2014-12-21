package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.CustomerOrder;

public interface CustomerOrderService {
	public void addOrder(CustomerOrder customerOrder);
	public void updateOrder(CustomerOrder customerOrder);

	/**
	 * Get all customers orders
	 * @param username
	 * @return
	 */
	public List<CustomerOrder> getUserOrdersByUsername(String username);
	public CustomerOrder getOrderById(int id);
	public void removeOrder(int id);

	/**
	 * Just create empty cart
	 * @return
	 */
	public CustomerOrder createCart();

	/**
	 * Get new customer orders
	 * @return
	 */
	public List<CustomerOrder> getUnprocessedCustomerOrders();
}
