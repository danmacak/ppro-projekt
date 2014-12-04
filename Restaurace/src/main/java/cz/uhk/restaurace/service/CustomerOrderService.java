package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.CustomerOrder;

public interface CustomerOrderService {
	public void addOrder(CustomerOrder customerOrder);
	public void updateOrder(CustomerOrder customerOrder);
	public List<CustomerOrder> listUserOrder(String username);
	public CustomerOrder getOrderById(int id);
	public void removeOrder(int id);
	public CustomerOrder createCart();

}
