package cz.uhk.restaurace.service.impl;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.dao.DishGeneralDao;
import cz.uhk.restaurace.dao.DishLocDao;
import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.model.DishLoc;
import cz.uhk.restaurace.service.DishGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DishGeneralServiceImpl implements DishGeneralService {

	@Autowired
	private DishGeneralDao dishDishGeneralDao;

	@Autowired
	private DishLocDao dishLocDao;

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
	public List<DishGeneral> listDishes(String language) {
		List<DishGeneral> dishes =  dishDishGeneralDao.listDishesGeneral();
		for(DishGeneral dish : dishes){
			dish.setDishLoc(dishLocDao.getDishLocById(dish.getId(),language));
		}
		return dishes;

	}

	@Override
	@Transactional(readOnly = true)
	public List<DishGeneral> listDrinks(String language) {
		List<DishGeneral> drinks =  dishDishGeneralDao.listDrinksGeneral();
		for(DishGeneral drink : drinks){
			drink.setDishLoc(dishLocDao.getDishLocById(drink.getId(),language));
		}
		return drinks;
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

	@Override
	@Transactional
	public Map<Integer, DishGeneral> getLocalizedDishesInCart(Map<Integer, DishGeneral> dishes, String language) {
		for(Map.Entry<Integer, DishGeneral> dish : dishes.entrySet()){
			dish.getValue().setDishLoc(dishLocDao.getDishLocById(dish.getValue().getId(), language));
		}
		return dishes;
	}
}