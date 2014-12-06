package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.dao.DishGeneralDao;
import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.service.DishGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DishGeneralGeneralServiceImpl implements DishGeneralService {

	@Autowired
	private DishGeneralDao dishDishGeneralDao;

	@Override
	@Transactional
	public void addDish(DishGeneral dish) {
		dishDishGeneralDao.addDishGeneral(dish);
		
	}

	@Override
	@Transactional
	public void updateDish(DishGeneral dish) {
		dishDishGeneralDao.updateDishGeneral(dish);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<DishGeneral> listDishes() {
		return dishDishGeneralDao.listDishesGeneral();
	}

	@Override
	@Transactional(readOnly = true)
	public List<DishGeneral> listDrinks() {
		return dishDishGeneralDao.listDrinksGeneral();
	}

	@Override
	@Transactional(readOnly = true)
	public DishGeneral getDishById(Integer id) {
		return dishDishGeneralDao.getDishGeneralById(id);
	}

	@Override
	@Transactional
	public void removeDish(Integer id) {
		dishDishGeneralDao.removeDishGeneral(id);
	}
}
