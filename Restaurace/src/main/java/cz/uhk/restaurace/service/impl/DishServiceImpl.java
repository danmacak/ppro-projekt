package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.DishDao;
import cz.uhk.restaurace.model.Dish;

@Service
public class DishServiceImpl implements DishService {

	@Autowired
	private DishDao dishDao;

	@Override
	@Transactional
	public void addDish(Dish dish) {
		dishDao.addDish(dish);
		
	}

	@Override
	@Transactional
	public void updateDish(Dish dish) {
		dishDao.updateDish(dish);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dish> listDishes() {
		return dishDao.listDishes();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dish> listDrinks() {
		return dishDao.listDrinks();
	}

	@Override
	@Transactional(readOnly = true)
	public Dish getDishById(String id) {
		return dishDao.getDishById(id);
	}

	@Override
	@Transactional
	public void removeDish(String id) {
		dishDao.removeDish(id);
	}
}
