package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.Dish;

import javax.servlet.http.HttpSession;

public interface DishService {
	public void addDish(Dish dish);
	public void updateDish(Dish dish);
	public List<Dish> listDishes();
	public List<Dish> listDrinks();
	public Dish getDishById(String id);
	public void removeDish(String id);

}
