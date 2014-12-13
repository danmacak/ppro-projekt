package cz.uhk.restaurace.service;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.DishGeneral;

import javax.servlet.http.HttpSession;

public interface DishGeneralService {
	public void addDish(DishGeneral dish);
	public void updateDish(DishGeneral dish);
	public List<DishGeneral> listDishes(String language);
	public List<DishGeneral> listDrinks(String language);
	public DishGeneral getDishById(Integer id);
	public void removeDish(Integer id);
	public Map<Integer, DishGeneral> getLocalizedDishesInCart(Map<Integer, DishGeneral> dishes, String language);
	public Map<String, DishGeneral> getLocalizedTeppanyakiDishes(Map<String, DishGeneral> dishes, String language);
}
