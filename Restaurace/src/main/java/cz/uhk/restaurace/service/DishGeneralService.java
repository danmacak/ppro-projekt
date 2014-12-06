package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.DishGeneral;

import javax.servlet.http.HttpSession;

public interface DishGeneralService {
	public void addDish(DishGeneral dish);
	public void updateDish(DishGeneral dish);
	public List<DishGeneral> listDishes();
	public List<DishGeneral> listDrinks();
	public DishGeneral getDishById(Integer id);
	public void removeDish(Integer id);

}