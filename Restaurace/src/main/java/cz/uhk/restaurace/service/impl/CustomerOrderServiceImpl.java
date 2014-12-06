package cz.uhk.restaurace.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.CustomerOrderDao;
import cz.uhk.restaurace.model.CustomerOrder;
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDao customerOrderDao;

	@Override
	@Transactional
	public void addOrder(CustomerOrder customerOrder) {
		customerOrderDao.addOrder(customerOrder);
		
	}

	@Override
	@Transactional
	public void updateOrder(CustomerOrder customerOrder) {
		customerOrderDao.updateOrder(customerOrder);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerOrder> listUserOrder(String username) {
		return customerOrderDao.listUserOrder(username);
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerOrder getOrderById(int id) {
		return customerOrderDao.getOrderById(id);
	}

	@Override
	@Transactional
	public void removeOrder(int id) {
		customerOrderDao.removeOrder(id);
		
	}

	@Override
	public CustomerOrder createCart(){
		CustomerOrder cart = new CustomerOrder();
		Map<String, DishGeneral> orderedDishes = new HashMap<String, DishGeneral>();
		cart.setOrderedDishes(orderedDishes);
		return cart;
	}

}
