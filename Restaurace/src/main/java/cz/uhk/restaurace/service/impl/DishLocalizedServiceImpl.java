package cz.uhk.restaurace.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.DishGeneralDao;
import cz.uhk.restaurace.dao.DishLocDao;
import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.model.DishLoc;
import cz.uhk.restaurace.model.DishLocalized;
import cz.uhk.restaurace.service.DishLocalizedService;


@Service
public class DishLocalizedServiceImpl implements DishLocalizedService {
	public static final String CZECH = "cs";
	public static final String ENGLISH = "en";
	
	private String language = CZECH;
	@Autowired
	private DishGeneralDao dishGeneralDao;
	@Autowired
	private DishLocDao dishLocDao;
	

	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Override
	@Transactional
	public void addDishLocalized(DishLocalized dish) {

	}

	@Override
	@Transactional
	public void updateDishLocalized(DishLocalized dish) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<DishLocalized> listDishesLocalized() {
		List<DishLocalized> res = new ArrayList<DishLocalized>();
		List<DishGeneral> dl = dishGeneralDao.listDishesGeneral();
		for (int i = 0; i < dl.size(); i++) {
			DishLoc dishLoc = dishLocDao.getDishLocById(dl.get(i).getId(), language);
			if(dishLoc != null){
				res.add(new DishLocalized(dl.get(i), dishLoc));
			}
		}
		return res;
	}

	@Override
	@Transactional
	public List<DishLocalized> listDrinksLocalized() {
		List<DishLocalized> res = new ArrayList<DishLocalized>();
		List<DishGeneral> dl = dishGeneralDao.listDrinksGeneral();
		for (int i = 0; i < dl.size(); i++) {
			DishLoc dishLoc = dishLocDao.getDishLocById(dl.get(i).getId(), language);
			if(dishLoc != null){
				res.add(new DishLocalized(dl.get(i), dishLoc));
			}
		}
		return res;
	}

	@Override
	@Transactional
	public Map<String, DishLocalized> getDishesLocalizedInCart(List<Integer> ids) {
		Map<String, DishLocalized> dishesLoc = new HashMap<String, DishLocalized>();
		List<DishGeneral> dishes = dishGeneralDao.getDishesInCart(ids);
		for(int i = 0; i < dishes.size(); i++){
			DishGeneral dish = dishes.get(i);
			DishLoc dishLoc = dishLocDao.getDishLocById(dish.getId(), language);
			if(dishLoc != null){
				dishesLoc.put(dish.getName(), new DishLocalized(dish, dishLoc));
			}
		}
		return dishesLoc;
	}

	@Override
	@Transactional
	public DishLocalized getDishLocalizedById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void removeDishLocalized(int id) {
		// TODO Auto-generated method stub

	}

}
